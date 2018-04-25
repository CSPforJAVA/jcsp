
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
 * An abstract class that must be implemented by communication
 * protocol implementations. <CODE>LinkServer</CODE> objects are
 * processes which listen on a certain address for connection
 * requests. When a request is received, a <CODE>Link</CODE> should be spawned in
 * server mode.
 *
 *
 * @author Quickstone Technologies Limited
 */
public abstract class LinkServer
{
   /**
    * Constructor. A LinkServer must have an associated protocolID.
    * @param protocolID The <CODE>ProtocolID</CODE> for the protocol that the concrete implementation of <CODE>LinkServer</CODE> supports.
    * @param linkServerAddressID the <CODE>NodeAddressID</CODE> for this <CODE>LinkServer</CODE> to listen on.
    */
   protected LinkServer(ProtocolID protocolID, NodeAddressID linkServerAddressID)
   {
      if (protocolID == null || linkServerAddressID == null)
         throw new IllegalArgumentException("ProtocolID cannot be null");
      this.protocolID = protocolID;
      this.linkServerAddressID = linkServerAddressID;
   }
   
   /**
    * Create a server on a specifiedNodeAddressID, and start it.  The server
    * is spawned off in parallel, so this call returns immediately. This needs
    * to be implemented by the concrete implementation of this class. This is
    * not enforced by the compiler due to this being a static method.
    *
    * This NEEDS to be overridden.
    *
    * @param addressID The NodeAddressID to accept from
    * @return the instance of <CODE>LinkServer</CODE>.
    */
   protected static LinkServer create(NodeAddressID addressID)
   {
      throw new UnsupportedOperationException();
   }
   
   /**
    * Stops the LinkServer.
    *
    * This NEEDS to be overridden.
    * @return <CODE>true</CODE> iff the <CODE>LinkServer</CODE> has stopped.
    */
   protected boolean stop()
   {
      throw new UnsupportedOperationException();
   }
   
   /**
    *	Gets the protocol that this LinkServer supports.
    *
    * @return	the ProtocolID representing this LinkServers protocol.
    */
   protected final ProtocolID getProtocolID()
   {
      return protocolID;
   }
   
   /**
    * Protected accessor for obtaining the <CODE>NodeAddressID</CODE> on which
    * this server is listening.
    * @return the <CODE>NodeAddressID</CODE> on which this server is listening.
    */
   protected final NodeAddressID getLinkServerAddressID()
   {
      return linkServerAddressID;
   }
   
   private final ProtocolID protocolID;
   private final NodeAddressID linkServerAddressID;
   
}