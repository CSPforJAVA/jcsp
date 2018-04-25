
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
 * Contains the data that relates to a networked Barrier. This is an external data structure within JCSP networking, and
 * is held by both the NetBarrier and the BarrierManager. For information on the operation of the NetBarrier, see the
 * relevant documentation.
 * 
 * @see NetBarrier
 * @author Kevin Chalmers
 */
final class BarrierData
{
    /**
     * The virtual Barrier number that uniquely identifies the Barrier within the Node
     */
    int vbn = -1;

    /**
     * The current state of the Barrier
     */
    byte state = BarrierDataState.INACTIVE;

    /**
     * The connection to the Barrier for connecting to the NetBarrier object from the Link
     */
    ChannelOutput toBarrier = null;

}
