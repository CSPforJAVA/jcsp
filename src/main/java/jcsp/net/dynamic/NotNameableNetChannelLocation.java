
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

package jcsp.net.dynamic;

import jcsp.net.NetChannelLocation;
import jcsp.net.cns.LocationNotCNSRegisterable;

/**
 * Instances of this class take another <code>NetChannelLocation</code>
 * object and effectivly "clone" its attributes. The CNS will not
 * register channels at these locations due to this class implementing
 * the <code>LocationNotCNSRegisterable</code> interface.
 *
 *
 * @author Quickstone Technologies Limited
 */
class NotNameableNetChannelLocation extends NetChannelLocation implements LocationNotCNSRegisterable
{
   /**
    * Constructor which takes another <code>NetChannelLocation</code>
    * object to "copy".
    *
    * @param other
    * @throws IllegalArgumentException	if super class constructor throws it.
    */
   public NotNameableNetChannelLocation(NetChannelLocation other) throws IllegalArgumentException
   {
      super(other);
   }
}