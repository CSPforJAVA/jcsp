
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
 *
 * @author Quickstone Technologies Limited
 */
class NetSharedConnectionServerImpl extends SharedConnectionServerImpl implements NetSharedConnectionServer
{
   static NetSharedConnectionServerImpl create()
   {
      NetAltingChannelInput in = NetChannelEnd.createNet2One();
      Any2OneChannel synchChan = Channel.any2one(new Buffer(1));
      return new NetSharedConnectionServerImpl(synchChan, in);
   }
   
   private Any2OneChannel synchChan;
   private NetAltingChannelInput in;
   
   /**
    * Constructor for NetSharedConnectionServerImpl.
    */
   private NetSharedConnectionServerImpl(Any2OneChannel synchChan, NetAltingChannelInput in)
   {
      super(in,in,synchChan.in(),synchChan.out(),null);
      this.synchChan = synchChan;
      this.in = in;
   }
   
   /**
    * Returns the server's location.
    *
    * @return the server's <code>NetChannelLocation</code> object.
    *
    * @see Networked#getChannelLocation()
    */
   public NetChannelLocation getChannelLocation()
   {
      return in.getChannelLocation();
   }
   
   /**
    * <p>
    * Produces a duplicate
    * <code>SharedConnectionServer</code> object which
    * may be used by another process.
    * </p>
    * @return a new duplicate <code>SharedConnectionServer</code>
    *          object.
    */
   public SharedConnectionServer duplicate()
   {
      return new NetSharedConnectionServerImpl(synchChan, in);
   }
   
   /**
    * Destroys the server and frees any resources used within
    * the JCSP.NET infrastructure.
    *
    */
   public void destroyServer()
   {
      synchChan.out().write(null);
      in.destroyReader();
      synchChan.in().read();
   }
}