
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

package jcsp.lang;

    /**
 * This implements an any-to-any object channel,
 * safe for use by many writers and many readers. Refer to the {@link Any2AnyChannel} interface
 * for more details.
 *
 * @see jcsp.lang.One2OneChannelImpl
 * @see jcsp.lang.Any2OneChannelImpl
 * @see jcsp.lang.One2AnyChannelImpl
 *
 * @author P.D. Austin and P.H. Welch
 */

class Any2AnyChannelImpl<T> extends Any2AnyImpl<T>
{
	Any2AnyChannelImpl() {
		super(new One2OneChannelImpl());
	}
}
