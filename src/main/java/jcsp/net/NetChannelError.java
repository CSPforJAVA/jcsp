
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
 * <p>
 * This is an error that is thrown by a Networked
 * channel if something happens that should never
 * happen. This can occur if users disobey rules
 * to do with the number of concurrent processes
 * that are allowed to access a channel object at
 * any instant.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public class NetChannelError extends Error
{
   /**
    * <p>
    * Constructor for NetChannelError.
    * </p>
    */
   public NetChannelError()
   {
      super();
   }
   
   /**
    * <p>
    * Constructor for NetChannelError.
    * </p>
    * @param message
    */
   public NetChannelError(String message)
   {
      super(message);
   }
}