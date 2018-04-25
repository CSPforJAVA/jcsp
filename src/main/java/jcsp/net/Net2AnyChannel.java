
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

import jcsp.lang.ProcessManager;
import jcsp.lang.RejectableBufferedOne2AnyChannel;
import jcsp.lang.RejectableChannel;
import jcsp.lang.RejectableOne2AnyChannel;
import jcsp.util.ChannelDataStore;

/**
 * A channel for network input (RX).  This is a "Net2Any" channel,
 * which can be safely used by multiple readers on the same Node.
 *
 *
 * @author Quickstone Technologies Limited
 */
class Net2AnyChannel implements NetSharedChannelInput, Networked
{
   /*-----------------Attributes-------------------------------------------------*/
   
   /**
    * The channel name.
    */
   private final String label;

   /**
    * The local channel used for output from the recieving process
    */
   private RejectableChannel ch;
   
   private NetChannelInputProcess netChannelInputProcess;
   
   
   public Net2AnyChannel(String label) throws NullPointerException
   {
      if (label == null)
         throw new NullPointerException("Label supplied is null");
      if (label != "")
         Node.info.log(this, "Creating a channel with VCN label: " + label);
      ch = new RejectableOne2AnyChannel();
      netChannelInputProcess = new NetChannelInputProcess(label,ch);
      this.label = label;
   }
   
   /**
    * <p>
    * Creates an anonymous input channel.
    * </p>
    * <p>
    * To create writers that write to this channel, you need to call
    * getChannelName() to get a valid name for this channel.  You
    * will need to use some other means (e.g. a named channel) to pass
    * the channel name to the writing computer.
    * </p>
    */
   public Net2AnyChannel()
   {
      ch = new RejectableOne2AnyChannel();
      this.label = null;
      netChannelInputProcess = new NetChannelInputProcess(label,ch);
      new ProcessManager(netChannelInputProcess).start();
   }
   
   /**
    * <p>
    * Creates an anonymous, buffered input channel.
    * </p>
    * <p>
    * To create writers that write to this channel, you need to call
    * getChannelName() to get a valid name for this channel.  You
    * will need to use some other means (e.g. a named channel) to pass
    * the channel name to the writing computer.
    * </p>
    *
    * @param buffer The ChannelDataStore to use.
    */
   public Net2AnyChannel(ChannelDataStore buffer)
   {
      ch = new RejectableBufferedOne2AnyChannel(buffer);
      netChannelInputProcess = new NetChannelInputProcess(null,ch);
      this.label = null;
      new ProcessManager(netChannelInputProcess).start();
   }
   
   /**
    *
    *
    *
    *
    */
   public Net2AnyChannel(String label, ChannelDataStore buffer)
   {
      if (label == null)
         throw new NullPointerException("Label supplied is null");
      if (label != "")
         Node.info.log(this, "Creating a channel with VCN label: " + label);
      this.label = label;
      ch = new RejectableBufferedOne2AnyChannel(buffer);
      netChannelInputProcess = new NetChannelInputProcess(label,ch);
      new ProcessManager(netChannelInputProcess).start();
   }
   
   /**
    * Read data from this channel.  This can safely be called by
    * multiple readers.
    *
    * @return The object read from the network.
    */
   public Object read()
   {
      return ch.in().read();
   }
   
   public Object startRead()
   {
     return ch.in().startRead();
   }
   
   public void endRead()
   {
     ch.in().endRead();
   }
   
   
   /**
    * Currently, network channels are unpoisonable so this method has no effect.
    */
   public void poison(int strength) {   
   }   
   
   public NetChannelLocation getChannelLocation()
   {
      return new NetChannelLocation(Node.getInstance().getNodeID(), netChannelInputProcess.getChannelIndex());
   }
   
   public Class getFactoryClass()
   {
      return StandardNetChannelEndFactory.class;
   }
   
   public void destroyReader()
   {
      netChannelInputProcess.breakChannel();
   }
}