
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

import jcsp.lang.PoisonException;

/**
 * An exception thrown when a networked channel is poisoned. See the poison exception in the core package for more
 * information
 * 
 * @see PoisonException
 * @author Kevin Chalmers
 */
public final class NetworkPoisonException
    extends PoisonException
{
    /**
     * The SUID of the class
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new NetworkPoisonException
     * 
     * @param strength
     *            The strength of the poison
     */
    protected NetworkPoisonException(int strength)
    {
        super(strength);
    }
}
