
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

package jcsp.net.dynamic;

import jcsp.net.*;
import java.io.Serializable;

/**
 * Receiving end of a migratable channel. The underlying channel can be obtained by the
 * <code>getInputChannel</code> method and used like any other channel. Before migrating the channel,
 * the <code>prepareToMove</code> method must be called.
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface InputReconnectionManager extends Serializable
{
   /**
    * Returns the underlying input channel.
    */
   public NetAltingChannelInput getInputChannel();
   
   /**
    * Returns the current location of the channel.
    */
   public NetChannelLocation getCurrentLocation();
   
   /**
    * Prepares the channel for a transfer between nodes.
    */
   public void prepareToMove();
}