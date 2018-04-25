
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

package jcsp.net.tcpip;

import java.net.*;
import java.io.*;
import jcsp.lang.*;
import jcsp.net.*;

/**
 * A process to accept links to a ServerSocket, create appropriate
 * TCPIPLink objects, and register them with the LinkManager.
 * <p>
 *
 * This is a package-private implementation class.
 *
 *
 * @author Quickstone Technologies Limited
 */
// package-private.
class TCPIPLinkServer extends LinkServer implements CSProcess
{
   /*----------------------Constructors------------------------------------------*/
   
   /**
    * Private constructor.  To make life easier for you, you don't have to
    * call this.  Just use the static start() method in this class and a
    * process will be created and executed for you.
    *
    * @see #start(ServerSocket)
    *
    * @param socket The ServerSocket to accept from
    */
   private TCPIPLinkServer(ServerSocket serverSocket, boolean uniqueAddress)
   {
      super(new TCPIPProtocolID(), new TCPIPAddressID(serverSocket.getInetAddress(), serverSocket.getLocalPort(),uniqueAddress));
      this.serverSocket = serverSocket;
   }
   
   /*----------------------Public Methods----------------------------------------*/
   
   /**
    * Start accepting links and dealing with them.
    * This method runs forever.
    */
   public void run()
   {
      try
      {
         Node.info.log(this, "TCP/IP V4 LinkServer listening on " + getLinkServerAddressID() + " Started");
         while (true)
         {
            // Accept an incoming link
            Socket incoming = serverSocket.accept();
            // Create a Link object to represent it.
            TCPIPLink link = new TCPIPLink(incoming, false);
            // spawn off the Link object to deal with it, but
            // let us continue in parallel.
            new ProcessManager(link).start();
         }
      }
      catch (Exception ex)
      {
         // Will be IOException
         // warn but otherwise ignore
         Node.err.log(this, ex);
      }
      try
      {
         serverSocket.close();
      }
      catch (Exception ignored)
      {
      }
      Node.info.log(this, "TCP/IP V4 LinkServer listening on " + getLinkServerAddressID() + " Ended");
   }
   
   /*----------------------Non-public Methods------------------------------------*/
   
   /**
    * Create a server on a specified NodeAddressID, and start it.  The server
    * is spawned off in parallel, so this call returns immediately.
    *
    * This NEEDS to be overridden.
    *
    * @param addressID The NodeAddressID to accept from
    */
   // package-private
   protected static LinkServer create(NodeAddressID addressID)
   {
      if(!(addressID instanceof TCPIPAddressID))
         throw new IllegalArgumentException("Unable to start TCPIPLinkServer, wrong type of address.");
      TCPIPAddressID add = (TCPIPAddressID) addressID;
      InetAddress ipAdd = add.getHost();
      int port = add.getPort();
      TCPIPLinkServer ls;
      try
      {
         ServerSocket serverSocket = new ServerSocket(port, QUEUE_LENGTH, ipAdd);
         ls = new TCPIPLinkServer(serverSocket, addressID.isGloballyUnique());
         new ProcessManager(ls).start(ProcessManager.PRIORITY_MAX);
      }
      catch(IOException e)
      {
         Node.info.log(TCPIPLinkServer.class, e.getMessage());
         ls = null;
      }
      return ls;
   }
   
   /**
    * Stops the LinkServer.
    *
    * This NEEDS to be overridden.
    */
   protected boolean stop()
   {
      try
      {
         Node.info.log(this, "Trying to stop TCP/IP V4 LinkServer listening on " + getLinkServerAddressID());
         serverSocket.close();
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
      return true;
   }
   
   /*----------------------Attributes--------------------------------------------*/
   
   /**
    * The socket to accept from.
    */
   private final ServerSocket serverSocket;
   private static int QUEUE_LENGTH = 10;
}