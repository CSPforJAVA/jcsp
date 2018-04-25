
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
import jcsp.util.Buffer;

/**
 * <p>
 * Defines a class whose instances should be
 * <code>{@link SharedAltingConnectionClient}</code>
 * that connect to a <code>ConnectionServer</code> over a JCSP.NET
 * network.
 * </p>
 * <p>
 * Individual instances may not be used by multiple processes but
 * duplicate clients can be obtained by invoking
 * <code>{@link #duplicate()}</code>. These duplicates work over the
 * same connection and each one may be used by a different process.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public class NetSharedAltingConnectionClient extends SharedAltingConnectionClient implements NetSharedConnectionClient
{
   static NetSharedAltingConnectionClient create(NetChannelLocation serverLoc)
   {
      Any2OneChannel synchChan = Channel.any2one(new Buffer(1));
      NetChannelOutput openToServer = NetChannelEnd.createOne2Net(serverLoc);
      NetChannelOutput reqToServer = NetChannelEnd.createOne2Net(serverLoc);
      NetAltingChannelInput fromServer = NetChannelEnd.createNet2One(new Buffer(1));
      NetChannelOutput replyToClient = NetChannelEnd.createOne2Net(fromServer.getChannelLocation());
      return new NetSharedAltingConnectionClient(synchChan, fromServer, openToServer, reqToServer, replyToClient);
   }
   
   private Any2OneChannel synchChan;
   private NetAltingChannelInput fromServer;
   private NetChannelOutput openToServer;
   private NetChannelOutput reqToServer;
   private NetChannelOutput backToClient;
   private NetConnectionLocation serverLocation;
   
   /**
    * <p>
    * Constructor for NetSharedAltingConnectionClient.
    * </p>
    *
    * @param fromServer
    * @param synchIn
    * @param toServer
    * @param synchOut
    * @param backToClient
    * @param parent
    */
   protected NetSharedAltingConnectionClient(
                        Any2OneChannel synchChan,
                        NetAltingChannelInput fromServer,
                        NetChannelOutput openToServer,
                        NetChannelOutput reqToServer,
                        NetChannelOutput backToClient)
   {
      super(fromServer, synchChan.in(), openToServer, reqToServer, synchChan.out(), backToClient, null);
      
      this.synchChan = synchChan;
      this.fromServer = fromServer;
      this.openToServer = openToServer;
      this.reqToServer = reqToServer;
      this.backToClient = backToClient;
      
      this.serverLocation = new NetConnectionLocation(openToServer.getChannelLocation(), reqToServer.getChannelLocation());
   }
   
   /**
    * Returns the address location of the connection server.
    *
    * @return the <code>NetChannelLocation</code> object.
    *
    * @see Networked#getChannelLocation()
    */
   public NetChannelLocation getChannelLocation()
   {
      return this.serverLocation;
   }
   
   /**
    * <p>
    * Produces a duplicate
    * <code>NetSharedAltingConnectionClient</code> object which
    * may be used by another process.
    * </p>
    * @return a new duplicate <code>SharedConnectionClient</code>
    *          object.
    */
   public SharedConnectionClient duplicate()
   {
      return new NetSharedAltingConnectionClient(synchChan,
              fromServer,
              openToServer,
              reqToServer,
              backToClient);
   }
   
   /**
    * <p>
    * Destroys this networked client object.
    * </p>
    * <p>
    * This frees any resources used within the JCSP.NET
    * infrastructure.
    * </p>
    *
    */
   public void destroyClient()
   {
      synchChan.out().write(null);
      fromServer.destroyReader();
      synchChan.in().read();
   }
}