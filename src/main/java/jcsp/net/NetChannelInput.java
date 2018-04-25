
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

import jcsp.lang.ChannelInput;

/**
 * <p>
 * An interface implemented by classes wishing to be Networked
 * <code>ChannelInput</code> objects.
 * </p>
 * <p>
 * Implementing this interface does not guarantee that the input
 * end can be used by multiple concurrent processes nor can it
 * necessarily be ALTed over.
 * </p>
 * @see NetAltingChannelInput
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface NetChannelInput extends ChannelInput, Networked
{
   /**
    * <p>
    * Returns a Class file of the factory used to construct the
    * channel end.
    * </p>
    *
    * @return the <code>Class</code> of the factory class.
    */
   public Class getFactoryClass();
   
   /**
    * <p>
    * Destroys the channel end and frees any resources within
    * the JCSP.NET infrastructure.
    * </p>
    */
   public void destroyReader();
}