
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

package jcsp.net;

/**
 * An exception caused by a link underlying a channel breaking.
 *
 *
 * @author Quickstone Technologies Limited.
 */
public class LinkLostException extends RuntimeException
{
    /**
     * The object which threw this exception.
     */
    Object source;
    
    /**
     * Get the object which threw this exception.  This could be a channel
     * or any other distributed construct.
     *
     * @return The object which broke.
     */
    Object getSource()
    {
        return source;
    }
    
    /**
     * Constructor.
     */
    public LinkLostException(Object source)
    {
        this.source = source;
    }
    
    /**
     * Constructor.
     *
     * @param description Description of the error.
     */
    public LinkLostException(Object source, String description)
    {
        super(description);
        this.source = source;
    }
}