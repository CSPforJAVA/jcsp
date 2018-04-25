
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

import jcsp.lang.ChannelInput;

/**
 * This interface defines a ChannelInput that is also networked. For information on ChannelInput see the relevant class.
 * For information on how to create a NetChannelInput, see the relevant factory class
 * 
 * @see ChannelInput
 * @see Networked
 * @see NetChannel
 * @author Kevin Chalmers (updated from Quickstone Technologies)
 */
public interface NetChannelInput
    extends ChannelInput, Networked
{
    /**
     * Sets the underlying decoder for the channel
     * 
     * @param decoder
     *            The new decoder to use.
     */
    public void setDecoder(NetworkMessageFilter.FilterRx decoder);
}
