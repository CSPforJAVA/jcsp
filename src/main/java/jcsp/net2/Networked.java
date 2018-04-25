
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
 * Defines a standard JCSP synchronization mechanism which is also networked. For concrete examples of this class, see
 * NetBarrier, and the networked channels
 * 
 * @see NetBarrier
 * @see NetChannelInput
 * @see NetChannelOutput
 * @author Kevin Chalmers (updated from Quickstone Technologies)
 */
public interface Networked
{
    /**
     * Gets the networked location of the Networked construct
     * 
     * @return The location of the construct
     */
    public NetLocation getLocation();

    /**
     * Destroys the Networked construct
     */
    public void destroy();
}
