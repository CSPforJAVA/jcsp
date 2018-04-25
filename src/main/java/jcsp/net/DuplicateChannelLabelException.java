
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
 * This Exception is thrown if there is an attempt to label
 * a channel's VCN with a label that already exists at the
 * same Node.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class DuplicateChannelLabelException extends RuntimeException
{
   /**
    * Constructor for DuplicateChannelLabelException.
    */
   DuplicateChannelLabelException()
   {
      super();
   }
   
   /**
    * Constructor for DuplicateChannelLabelException.
    * @param message
    */
   DuplicateChannelLabelException(String message)
   {
      super(message);
   }
}