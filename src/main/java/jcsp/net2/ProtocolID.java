
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

import jcsp.net.tcpip.TCPIPProtocolID;

/**
 * This abstract class must be defined in concrete protocol implementations. Its main usage is to allow installation and
 * correct parsing of relevant address strings into correct address objects. See TCPIPProtocolID for an example.
 * 
 * @see TCPIPProtocolID
 * @author Kevin Chalmers
 */
public abstract class ProtocolID
{
    /**
     * Parses an address string into an address object
     * 
     * @param addressString
     *            String representation of an address
     * @return A new NodeAddress object
     * @throws IllegalArgumentException
     *             Thrown if the string is in an incorrect form
     */
    protected abstract NodeAddress parse(String addressString)
        throws IllegalArgumentException;
}
