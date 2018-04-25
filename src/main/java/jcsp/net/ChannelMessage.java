
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

/**
 * Messages used by channels.
 *
 * <p>This is a package-private implementation class.
 *
 * @author Quickstone Technologies Limited
 */
// package-private
abstract class ChannelMessage extends Message
{
   /**
    * Data from channel output to channel input.
    *
    * <p>This is a package-private implementation class.
    *
    */
   // package-private
   static final class Data extends ChannelMessage
   {
      /**
       * The actual data being transmitted.
       * This needs to be Serializable.
       *
       * @see java.io.Serializable
       *
       * @serial
       */
      Object data;
      
      boolean acknowledged = true;
      
      //debug code
      public String toString()
      {
         return "MessageData: " + data;
      }
   }
   
   /**
    * An acknowledgement.
    *
    * <p>This is a package-private implementation class.
    *
    */
   // package-private
   static final class Ack extends ChannelMessage
   {
   }
   
   static final class WriteRejected extends ChannelMessage
   {
   }
}