
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

import java.io.Serializable;
import jcsp.lang.ChannelOutput;

/**
 * <p>
 * Message to be transmitted.  This is an abstract class containing
 * only header information - you must subclass it to use it.
 * </p>
 * <p>
 * This is a package-private implementation class.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
// package-private
abstract class Message implements Serializable
{
   public Message()
   {
   }
   
   static final PingMessage PING_MESSAGE = new PingMessage();
   
   static final PingReplyMessage PING_REPLY_MESSAGE = new PingReplyMessage();
   
   public final void bounce(ChannelOutput txChannel)
   {
      BounceMessage msg = new BounceMessage();
      msg.destIndex = sourceIndex;
      msg.sourceIndex = -1;
      txChannel.write(msg);
   }
   
   /**
    * The destination channel index.
    *
    * @serial
    */
   // package-private
   long destIndex;
   
   String destVCNLabel;
   
   /**
    * The source channel index.
    *
    * @serial
    */
   // package-private
   long sourceIndex;
   
   /**
    * The source computer address.  This is not transmitted, instead, it
    * is filled in automatically by the demux on arrival.  (Indeed, it
    * is not usually even filled in at the sending end).
    */
   // package-private
   transient NodeID sourceID;
   
   /**
    * The channel for transmitting replies.  It doesn't make sense to
    * transmit this value (or even to bother to fill it in at the
    * transmitting end), so it is filled in automatically by the demux
    * on arrival.
    */
   // package-private
   transient ChannelOutput txReplyChannel;
   
   public static class BounceMessage extends Message
   {
      private BounceMessage()
      {
      }
   }
   
   public static class PingMessage extends Message
   {
   }
   
   public static class PingReplyMessage extends Message
   {
   }
}