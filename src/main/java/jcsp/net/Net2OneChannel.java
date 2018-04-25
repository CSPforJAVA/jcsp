
//////////////////////////////////////////////////////////////////////
//                                                                  //
//  JCSP ("CSP for Java") Libraries                                 //
//  Copyright (C) 1996-2018 Peter Welch, Paul Austin and Neil Brown //
//                2001-2004 Quickstone Technologies Limited         //
//                2005-2018 Kevin Chalmers                          //
//                                                                  //
//  You may use this work under the terms of either                 //
//  1. The Apache License, Version 2.0                              //
//  2. or (at your option), the GNU Lesser General Public License,  //
//       version 2.1 or greater.                                    //
//                                                                  //
//  Full licence texts are included in the LICENCE file with        //
//  this library.                                                   //
//                                                                  //
//  Author contacts: P.H.Welch@kent.ac.uk K.Chalmers@napier.ac.uk   //
//                                                                  //
//////////////////////////////////////////////////////////////////////

package jcsp.net;

import jcsp.lang.*;
import jcsp.util.ChannelDataStore;

/**
 * A channel for network input (RX).  This is a "Net2One" channel,
 * which can only be used by one reader at a time.
 *
 *
 * @author Quickstone Technologies Limited
 */
class Net2OneChannel extends NetAltingChannelInput
{
   static Net2OneChannel create(String label)
   {
      RejectableOne2OneChannel chan = new RejectableOne2OneChannel();
      return new Net2OneChannel(label, chan.inAlt(), chan);
   }
   
   static Net2OneChannel create()
   {
      RejectableOne2OneChannel chan = new RejectableOne2OneChannel();
      return new Net2OneChannel(chan.inAlt(), chan);
   }
   
   static Net2OneChannel create(ChannelDataStore buffer)
   {
      RejectableBufferedOne2OneChannel chan = new RejectableBufferedOne2OneChannel(buffer);
      return new Net2OneChannel(chan.inAlt(), chan);
   }
   
   static Net2OneChannel create(String label, ChannelDataStore buffer)
   {
      RejectableBufferedOne2OneChannel chan = new RejectableBufferedOne2OneChannel(buffer);
      return new Net2OneChannel(label, chan.inAlt(), chan);
   }
   
   /**
    * Creates a channel which receives data on a labelled VCN.
    *
    * @param   label   The label to apply to this channel's VCN.
    * @throws  IllegalArgumentException if the label supplied
    *                  is a <code>null</code> reference.
    */
   private Net2OneChannel(String label, AltingChannelInput actualChan, RejectableChannel ch) throws IllegalArgumentException
   {
      super(actualChan);
      if (label == null)
         throw new IllegalArgumentException("Label supplied is null");
      if (label != "")
         Node.info.log(this, "Creating a channel with VCN label: " + label);
      this.label = label;
      netChannelInputProcess = new NetChannelInputProcess(label,ch);
      new ProcessManager(netChannelInputProcess).start();
   }
   
   /**
    * Creates a zero-buffered channel reader.
    * The <code>getChannelLocation()</code> method can
    * be called to obtain the location information of the constructed
    * channel.
    */
   private Net2OneChannel(AltingChannelInput actualChan, RejectableChannel ch)
   {
      super(actualChan);
      this.label = null;
      netChannelInputProcess = new NetChannelInputProcess(this.label,ch);
      new ProcessManager(netChannelInputProcess).start();
   }
   
   /**
    * Returns a new <code>NetChannelLocation</code> object which holds the
    * information necessary for a networked <code>ChannelOutput</code> to
    * establish a connection to this channel reader.
    *
    * @return the location information for this channel reader.
    */
   public NetChannelLocation getChannelLocation()
   {
      return new NetChannelLocation(Node.getInstance().getNodeID(), netChannelInputProcess.getChannelIndex());
   }
   
   /**
    * Destroys this end of the channel.
    */
   public void destroyReader()
   {
      netChannelInputProcess.breakChannel();
   }
   
   /**
    * This method should not be called.  The implementation channel is not
    * available to subclasses of Net2OneChannel.
    *
    * @return Always null.
    */
   protected AltingChannelInput getChannel()
   {
      return null;
   }
   
   long getChannelIndex()
   {
      return netChannelInputProcess.getChannelIndex();
   }
   
   public Class getFactoryClass()
   {
      return StandardNetChannelEndFactory.class;
   }
   
   /**
    * Currently, network channels are unpoisonable so this method has no effect.
    */
   public void poison(PoisonException poison) {   
   }
   /**
    * Currently, network channels are unpoisonable so this method will never throw a PoisonException
    */
   public void checkPoison() throws PoisonException {   
   }
   
   
   /*-----------------Attributes-------------------------------------------------*/
   
   private final String label;
   
   private NetChannelInputProcess netChannelInputProcess;
}