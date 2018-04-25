
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

package jcsp.net2;

import jcsp.lang.ChannelOutput;

/**
 * A message received or to be sent via a Link. This is an internal structure to JCSP, and is an object encapsulation of
 * the messages sent between nodes
 * 
 * @author Kevin Chalmers
 */
final class NetworkMessage
{
    /**
     * The message type, as described in NetworkProtocol.
     */
    byte type = -1;

    /**
     * The first attribute of the message.
     */
    int attr1 = -1;

    /**
     * The second attribute of the message
     */
    int attr2 = -1;

    /**
     * Data sent in the message if relevant.
     */
    byte[] data = null;

    /**
     * ChannelOutput to the Link so that acknowledgements can be sent.
     */
    ChannelOutput toLink = null;

}
