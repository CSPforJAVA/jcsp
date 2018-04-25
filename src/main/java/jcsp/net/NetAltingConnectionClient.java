
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

import jcsp.lang.AltingConnectionClientImpl;

/**
 * <p>
 * Instances of this class are <code>AltingConnectionClient</code>
 * objects which connect to <code>ConnectionServer</code> objects
 * over a JCSP.NET network.
 * </p>
 * <p>
 * Instances of this class are not guaranteed to be safe to use
 * by muliple concurrent processes. See
 * <code>{@link NetSharedAltingConnectionClient}</code>.
 * </p>
 * <p>
 * Instances can be constructed by using a
 * <code>{@link NetConnectionFactory}</code> or by
 * using the <code>{@link NetConnection}</code> class.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public class NetAltingConnectionClient extends AltingConnectionClientImpl implements NetConnectionClient
{
   
   static NetAltingConnectionClient create(NetChannelLocation serverLoc)
   {
      NetChannelOutput openToServer = new One2NetChannel(serverLoc, false);
      NetChannelOutput reqToServer = new One2NetChannel(serverLoc, false);
      NetAltingChannelInput fromServer = NetChannelEnd.createNet2One();
      NetChannelOutput replyToClient = new One2NetChannel(fromServer.getChannelLocation(), false);
      return new NetAltingConnectionClient(fromServer, openToServer, reqToServer, replyToClient);
   }
   
   private NetAltingChannelInput fromServer;
   private NetConnectionLocation location;
   
   NetAltingConnectionClient(NetAltingChannelInput fromServer, NetChannelOutput openToServer,
                             NetChannelOutput reqToServer, NetChannelOutput backToClient)
   {
      super(fromServer, openToServer, reqToServer, backToClient);
      this.fromServer = fromServer;
      this.location = new NetConnectionLocation(openToServer.getChannelLocation(), reqToServer.getChannelLocation());
   }
   
   /**
    * Returns the location of the server.
    *
    * @return the server's <code>NetChannelLocation</code>
    *          object.
    */
   public NetChannelLocation getChannelLocation()
   {
      return this.location;
   }
   
   /**
    * Destroys the client and frees any resources used
    * in the JCSP.NET infrastructure.
    *
    */
   public void destroyClient()
   {
      fromServer.destroyReader();
   }
}