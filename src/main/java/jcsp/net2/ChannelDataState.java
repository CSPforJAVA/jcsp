
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

/**
 * Represents the state of the networked channel. For information on networked channels, see the relevant documentation.
 * 
 * @see NetChannelInput
 * @see jcsp.net2.NetChannelOutput
 * @author Kevin Chalmers
 */
final class ChannelDataState
{
    /**
     * Private default constructor.
     */
    private ChannelDataState()
    {
        // Empty constructor
    }

    /**
     * Signifies that the channel has not been activated yet.
     */
    static final byte INACTIVE = 0;

    /**
     * Signifies that the channel has been started and is a input end.
     */
    static final byte OK_INPUT = 1;

    /**
     * Signified that the channel has been started and is a output end.
     */
    static final byte OK_OUTPUT = 2;

    /**
     * Signifies that the channel has been destroyed.
     */
    static final byte DESTROYED = 3;

    /**
     * Signifies that the channel is broken. This is from the original JCSP model, and may be unnecessary as Destroyed
     * and Poisoned may cover this.
     */
    static final byte BROKEN = 4;

    /**
     * Signifies that the channel has recently moved and has yet to be reestablished at a new location.
     */
    static final byte MOVING = 5;

    /**
     * Signifies that the channel has moved to a new location and that this new location is available.
     */
    static final byte MOVED = 6;

    /**
     * Signifies that the channel has been poisoned.
     */
    static final byte POISONED = 7;
}
