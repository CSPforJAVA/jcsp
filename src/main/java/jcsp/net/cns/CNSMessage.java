
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

package jcsp.net.cns;

import java.io.*;
import jcsp.net.*;

/**
 * This class is only visible to this package and does not need to be
 * used by JCSP users.
 *
 * The class is used for sending messages between the CNS server
 * process and CNSService client processes.
 *
 *
 * @author Quickstone Technologies Limited
 */
abstract class CNSMessage implements Serializable
{
   static class LogonMessage extends CNSMessage
   {
      NetChannelLocation replyLocation;
   }
   
   static class LogonReplyMessage extends CNSMessage
   {
      boolean success;
   }
   
   static abstract class CNSRequestMessage extends CNSMessage
   {
      NetChannelLocation replyLocation;
      int RequestIndex;
      String name;
      NameAccessLevel accessLevel;
   }
   
   static abstract class CNSReplyMessage extends CNSMessage
   {
      int RequestIndex;
   }
   
   static class RegisterRequest extends CNSRequestMessage
   {
      NetChannelLocation channelLocation;
      ChannelNameKey key;
   }
   
   static class ResolveRequest extends CNSRequestMessage
   {
   }
   
   static class LeaseRequest extends CNSRequestMessage
   {
      ChannelNameKey key;
   }
   
   static class DeregisterRequest extends CNSRequestMessage
   {
      ChannelNameKey key;
   }
   
   static class RegisterReply extends CNSReplyMessage
   {
      ChannelNameKey key;
   }
   
   static class ResolveReply extends CNSReplyMessage
   {
      NetChannelLocation channelLocation;
      String name;
      NameAccessLevel accessLevel;
   }
   
   static class LeaseReply extends CNSReplyMessage
   {
      ChannelNameKey key;
   }
   
   static class DeregisterReply extends CNSReplyMessage
   {
      boolean success;
   }
}