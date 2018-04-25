
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
 * This is the general exception thrown when something bad happens in the underlying architecture. Currently this is
 * generalised for the sake of simplicity. However, a number of different errors may occur internally, and therefore
 * this exception may be specialised into particular exception types in the future.
 * 
 * @author Kevin Chalmers
 */
public final class JCSPNetworkException
    extends RuntimeException
{
    /**
     * Default serial ID. Given for the sake of completeness.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for a new JCSPNetworkException
     * 
     * @param message
     *            The message for the exception
     */
    public JCSPNetworkException(String message)
    {
        super(message);
    }
}
