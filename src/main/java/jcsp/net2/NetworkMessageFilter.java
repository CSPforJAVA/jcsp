
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

import java.io.IOException;

/**
 * This class defines what is required for the internal networked channel filter. These filters are used to encode and
 * decode objects into a byte array equivalent. This enables data independence between separate platforms, as well as
 * data separation from the communication. Two filters are supplied with JCSP; a Object filter and a raw data filter.
 * The former will encode and decode messages using the standard object serialization techniques. The latter will expect
 * byte arrays only, and will do no encoding or decoding. The object filter is the default created by the network
 * factory methods.
 * 
 * @see ObjectNetworkMessageFilter
 * @see RawNetworkMessageFilter
 * @author Kevin Chalmers
 */
public final class NetworkMessageFilter
{
    /**
     * The filter used to decode an incoming message
     * 
     * @author Kevin Chalmers
     */
    public interface FilterRx
    {
        /**
         * Decodes an incoming byte array back into an object
         * 
         * @param bytes
         *            The bytes to be decoded
         * @return The recreated Object
         * @throws IOException
         *             Thrown if something goes wrong during the decoding
         */
        public Object filterRX(byte[] bytes)
            throws IOException;
    }

    /**
     * The filter used to encode an outgoing message
     * 
     * @author Kevin Chalmers
     */
    public interface FilterTx
    {
        /**
         * Encodes an object into an array of bytes for sending
         * 
         * @param obj
         *            The object to convert into bytes
         * @return The byte array representation of the Object
         * @throws IOException
         *             Thrown if something goes wrong during the encoding
         */
        public byte[] filterTX(Object obj)
            throws IOException;
    }
}
