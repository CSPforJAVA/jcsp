
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
 * A message between a Connection Client & Server.
 *
 * <p>This is a package-private implementation class.
 *
 *
 * @author Quickstone Technologies Limited
 */
// package-private
abstract class ConnectionMessage extends Message
{
   
   /**
    * A request from client to server to open().
    *
    * <p>This is a package-private implementation class.
    *
    */
   // package-private
   static final class Open extends ConnectionMessage
   {
      /**
       * @serial
       */
      Object data;
   }
   
   /**
    * A ping() from server to client.
    *
    * <p>This is a package-private implementation class.
    *
    */
   // package-private
   static final class Ping extends ConnectionMessage
   {
      /**
       * @serial
       */
      Object data;
   }
   
   /**
    * A pong() from client to server.
    *
    * <p>This is a package-private implementation class.
    *
    */
   // package-private
   static final class Pong extends ConnectionMessage
   {
      /**
       * @serial
       */
      Object data;
   }
   
   /**
    * A close() from client to server to open().
    *
    * <p>This is a package-private implementation class.
    *
    */
   // package-private
   static final class Close extends ConnectionMessage
   {
      /**
       * @serial
       */
      Object data;
   }
}