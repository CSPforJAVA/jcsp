
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

package jcsp.net2.bns;

import jcsp.net2.NetBarrierLocation;
import jcsp.net2.NetChannelLocation;

/**
 * A message sent between a BNS and a BNSService. This is an internal structure to JCSP
 * 
 * @author Kevin Chalmers
 */
final class BNSMessage
{

    /**
     * The message type. See BNSMessageProtocol
     */
    byte type = 0;

    /**
     * Whether the previous message was successful
     */
    boolean success = false;

    /**
     * The location that the BNS must reply to
     */
    NetChannelLocation serviceLocation = null;

    /**
     * The location of a resolves or registered barrier
     */
    NetBarrierLocation location = null;

    /**
     * The name to register or resolve
     */
    String name = "";
}
