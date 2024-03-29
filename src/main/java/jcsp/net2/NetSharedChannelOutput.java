
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

import jcsp.lang.SharedChannelOutput;

/**
 * Defines a networked ChannelOutput that is safe to be used by multiple concurrent processes. For more information see
 * NetChannelOutput and SharedChannelOutput. To create an instance, see the relevant factory method.
 * 
 * @see NetChannelOutput
 * @see SharedChannelOutput
 * @see NetChannel
 * @author Kevin Chalmers (updated from Quickstone Technologies)
 */
public interface NetSharedChannelOutput<T>
    extends NetChannelOutput<T>, SharedChannelOutput<T>
{
    // Nothing to add
}
