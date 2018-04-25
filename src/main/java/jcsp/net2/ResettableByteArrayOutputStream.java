
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

import java.io.ByteArrayOutputStream;

/**
 * This class is used by the ObjectNetworkMessageFilter. It acts as a normal ByteArrayOutputStream, but allows the
 * internal buffer to be reset in size, thereby regaining some resources.
 * 
 * @author Kevin Chalmers
 */
final class ResettableByteArrayOutputStream
    extends ByteArrayOutputStream
{
    /**
     * Creates a new ResettableByteArrayOutputStream
     * 
     * @param size
     *            The size of the internal buffer
     */
    ResettableByteArrayOutputStream(int size)
    {
        super(size);
    }

    /**
     * Resets the internal buffer
     * 
     * @param size
     *            The size to reset the internal buffer to
     */
    void reset(int size)
    {
        this.reset();
        if (this.buf.length != size)
            this.buf = new byte[size];
    }
}
