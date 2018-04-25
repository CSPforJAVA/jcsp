
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

package jcsp.plugNplay;

import java.util.*;
import jcsp.lang.*;

/**
 * This process broadcasts objects arriving on its input channel <I>in parallel</I>
 * to its output channel array -- those output channels can be changed dynamically.
 *
 * <H2>Process Diagram</H2>
 * <p><img src="doc-files/DynamicDelta1.gif"></p>
 * <H2>Description</H2>
 * A <TT>DynamicDelta</TT> process is a broadcasting node whose array of broadcasting
 * channels can be changed dynamically.  Any process can become one of the recipients
 * of the broadcast by sending the <TT>DynamicDelta</TT> a channel on which it will be
 * listening.  A process may leave the broadcast be re-sending that same channel.
 * <P>
 * In each cycle, <TT>DynamicDelta</TT> waits for either its <TT>in</TT> or <TT>configure</TT>
 * channel to become ready, giving priority to <TT>configure</TT>.
 * <P>
 * Anything arriving from <TT>in</TT> is broadcast <I>in parallel</I> down each element
 * of its array of <TT>out</TT> channels.
 * <P>
 * The <TT>configure</TT> channel delivers <TT>ChannelOutput</TT> channels -- anything
 * else is discarded.  If the delivered <TT>ChannelOutput</TT> channel is <I>not</I>
 * one of the channels already in the output array, it is added -- otherwise it is removed.
 * <P>
 * <P>
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in</TH>
 *     <TD>java.lang.Object</TD>
 *     <TD>
 *       The in Channel can accept data of any Class.
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH>in</TH>
 *     <TD>jcsp.lang.ChannelOutput</TD>
 *     <TD>
 *       The configure Channel accepts Objects of type {@link ChannelOutput} only.
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out[]</TH>
 *     <TD>java.lang.Object</TD>
 *     <TD>
 *       The output Channels will carry a broadcast of whatever
 *       is sent down the in Channel.
 *     </TD>
 *   </TR>
 * </TABLE>
 *
 * @author P.H. Welch and P.D. Austin
 */
public final class DynamicDelta implements CSProcess
{
   private AltingChannelInput in;
   private AltingChannelInput config;
   
   private Hashtable hash;
   private Parallel par;
   
   /**
    * Construct a new <TT>DynamicDelta</TT> process with the input channel <TT>in</TT> and
    * the configuration channel <TT>configure</TT>.
    *
    * @param in the input Channel
    * @param config the configuration Channel
    */
   public DynamicDelta(AltingChannelInput in, AltingChannelInput config)
   {
      this(in, config, null);
   }
   
   /**
    * Construct a new <TT>DynamicDelta</TT> process with the input channel <TT>in</TT>,
    * the configuration channel <TT>configure</TT> and the initial output
    * channels <TT>out</TT>. The ordering of the channels in the <TT>out</TT> array make
    * no difference to the functionality of this process.
    *
    * @param in the input channel
    * @param config the configuration channel
    * @param out the output channels
    */
   public DynamicDelta(AltingChannelInput in, AltingChannelInput config, ChannelOutput[] out)
   {
      this.in  = in;
      par = new Parallel();
      if (out != null)
      {
         hash = new Hashtable(out.length);
         for (int i = 0; i < out.length; i++)
            addOutputChannel(out[i]);
      }
      else
         hash = new Hashtable();
      this.config = config;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      AltingChannelInput[] chans = {config, in};
      Alternative alt = new Alternative(chans);
      while (true)
      {
         switch (alt.priSelect())
         {
            case 0:
               Object object = config.read();
               if (object instanceof ChannelOutput)
               {
                  if (hash.containsKey(object))
                     removeOutputChannel((ChannelOutput) object);
                  else
                     addOutputChannel((ChannelOutput) object);
               }
               break;
            case 1:
               Object message = in.read();
               Enumeration hashChans = hash.elements();
               while (hashChans.hasMoreElements())
                  ((ProcessWrite) hashChans.nextElement()).value = message;
               par.run();
               break;
         }
      }
   }
   
   /**
    * Adds a Channel to the list of output Channels. This method is
    * private as the only way clients can add Channels is via the
    * configure Channel.
    */
   private void addOutputChannel(ChannelOutput c)
   {
      ProcessWrite p = new ProcessWrite(c);
      par.addProcess(p);
      hash.put(c, p);
   }
   
   /**
    * Removes a Channel from the list of output Channels. This method is
    * private as the only way clients can remove Channels is via the
    * configure Channel.
    */
   private void removeOutputChannel(ChannelOutput c)
   {
      ProcessWrite p = (ProcessWrite) hash.get(c);
      par.removeProcess(p);
      hash.remove(c);
   }
}
