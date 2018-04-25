
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

package jcsp.net2.tcpip;

import jcsp.net2.NodeAddress;
import jcsp.net2.ProtocolID;

/**
 * Concrete implementation of a ProtocolID used to parse a string representation of a TCPIPNodeAddress into a
 * TCPIPNodeAddress object.
 * 
 * @author Kevin Chalmers
 */
public final class TCPIPProtocolID
    extends ProtocolID
{
    /**
     * Singleton instance of this class
     */
    private static TCPIPProtocolID instance = new TCPIPProtocolID();

    /**
     * Gets the singleton instance of this class
     * 
     * @return A new singleton instance of this class
     */
    public static TCPIPProtocolID getInstance()
    {
        return instance;
    }

    /**
     * Default private constructor
     */
    private TCPIPProtocolID()
    {
        // Empty constructor
    }

    /**
     * Parses a string to recreate a TCPIPNodeAddress object
     * 
     * @param addressString
     *            String representing the address
     * @return A new TCPIPNodeAddress object
     * @throws IllegalArgumentException
     *             Thrown if the address is not in a correct form
     */
    protected NodeAddress parse(String addressString)
        throws IllegalArgumentException
    {
        // Split address into IP and port
        int index = addressString.indexOf("\\\\");
        String temp = addressString.substring(index + 2);
        index = temp.indexOf(":");
        String address = temp.substring(0, index);
        int port = Integer.parseInt(temp.substring(index + 1, temp.length()));
        return new TCPIPNodeAddress(address, port);
    }

}
