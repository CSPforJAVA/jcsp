
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
 * An interface that should be implemented by any
 * class that has a Networked channel location or wishes
 * to expose the location of an underlying channel.
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface Networked
{
   /**
    * Returns the location of the <code>Networked</code>
    * <code>ChannelInput</code>.
    *
    * @return the <code>NetChannelLocation</code> object.
    */
   public NetChannelLocation getChannelLocation();
}