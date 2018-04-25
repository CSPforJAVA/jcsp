
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

import java.io.ByteArrayInputStream;

/**
 * This class is used by the ObjectNetworkMessageFilter. It acts as a ByteArrayInputStream, but allows the internal byte
 * array to be replaced by another. This stops unnecessary object creation
 * 
 * @author Kevin Chalmers
 */
final class ResettableByteArrayInputStream
    extends ByteArrayInputStream
{
    /**
     * Creates a new ResettableByteArrayInputStream
     * 
     * @param bytes
     *            The byte array to read data from
     */
    ResettableByteArrayInputStream(byte[] bytes)
    {
        super(bytes);
    }

    /**
     * Replaces the internal byte array
     * 
     * @param bytes
     *            The byte array to replace the existing internal one
     */
    void reset(byte[] bytes)
    {
        this.buf = bytes;
        this.count = bytes.length;
        this.pos = 0;
    }
}
