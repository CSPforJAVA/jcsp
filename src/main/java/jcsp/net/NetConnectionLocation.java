
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
 * Instances of this class take two <code>NetConnectionLocation</code>
 * objects. One for a connection's server channel and one for a
 * connection's further request channel.
 *
 * Instances of this class can be used as the open channel's
 * <code>NetChannelLocation</code> object while the further request
 * channel's can be obtained by calling
 * <code>getRequestChannelLocation()</code>.
 *
 *
 * @author Quickstone Technologies Limited
 */
class NetConnectionLocation extends NetChannelLocation
{
   private NetChannelLocation reqLoc;
   
   /**
    * Constructor for NetConnectionLocation.
    * @param other
    * @throws IllegalArgumentException
    */
   public NetConnectionLocation(NetChannelLocation open, NetChannelLocation req) throws IllegalArgumentException
   {
      super(open);
      this.reqLoc = req;
   }
   
   public NetChannelLocation getRequestChannelLocation()
   {
      return this.reqLoc;
   }
}