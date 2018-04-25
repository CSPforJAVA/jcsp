
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

/**
 * <p>
 * Instances of this class are <code>AltingConnectionServer</code>
 * objects which allow connections from <code>ConnectionClient</code>
 * objects from over a JCSP.NET network.
 * </p>
 * <p>
 * Instances of this class are not guaranteed to be safe to use
 * by muliple concurrent processes. See
 * <code>{@link NetSharedConnectionServer}</code> for a server
 * class that may be used between multiple processes, however
 * this may not be ALTed over.
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
class NetAltingConnectionServer extends AltingConnectionServerImpl implements NetConnectionServer
{
   static NetAltingConnectionServer create()
   {
      //buffering done at sending end
      NetAltingChannelInput chan = NetChannelEnd.createNet2One();
      return new NetAltingConnectionServer(chan);
   }
   
   private NetAltingChannelInput chan;
   
   private NetAltingConnectionServer(NetAltingChannelInput chan)
   {
      super(chan, chan);
      this.chan = chan;
   }
   
   /**
    * Returns the server's location.
    *
    * @return the server's <code>NetChannelLocation</code>
    *          object.
    */
   public NetChannelLocation getChannelLocation()
   {
      return chan.getChannelLocation();
   }
   
   /**
    * Destroys the server and frees any resources used
    * in the JCSP.NET infrastructure.
    */
   public void destroyServer()
   {
      chan.destroyReader();
   }
}