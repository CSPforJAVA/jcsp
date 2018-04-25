
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

import jcsp.lang.ChannelOutput;

/**
 * Message warning that a Link broke.
 *
 * <p>This is a package-private implementation class.
 *
 *
 * @author Quickstone Technologies Limited
 */
// package-private
public class LinkLost
{
    /**
     * The broken Link's transmit channel.
     */
    // package-private
    final ChannelOutput txChannel;
    
    /**
     * The broken Link's computer address.
     */
    // package-private
    final NodeID address;
    
    /**
     * Constructor.
     *
     * @param conn The broken Link.
     */
    // package-private
    LinkLost(Link conn)
    {
        
        txChannel = conn.getTxChannel();
        address = conn.getRemoteNodeID();
    }
}