
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

/**
 * Thrown when a reconnection mannager is unable to recreate the underlying channel.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class ChannelEndContructionException extends RuntimeException
{
   /**
    * Constructs a <code>ChannelEndConstructionException</code> without a detail message or cause.
    */
   public ChannelEndContructionException()
   {
      super();
   }
   
   /**
    * Constructs a <code>ChannelEndConstructionException</code> with a detail message.
    *
    * @param message the detail message indicating why the exception was raised.
    */
   public ChannelEndContructionException(String message)
   {
      super(message);
   }
   
   /**
    * Constructs a <code>ChannelEndContructionException</code> with a detail message and underlying
    * cause.
    *
    * @param message the detail message.
    * @param cause the exception that was caught while trying to perform the construction operation.
    */
   public ChannelEndContructionException(String message, Throwable cause)
   {
      super(message, cause);
   }
   
   /**
    * Constructs a <code>ChannelEndConstructionException</code> with a cause indicator.
    *
    * @param cause the exception that was caught while trying to perform the construction operation.
    */
   public ChannelEndContructionException(Throwable cause)
   {
      super(cause);
   }
}