
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
 * Thrown when a channel end cannot be moved.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class ChannelEndNotMoveableException extends RuntimeException
{
   /**
    * Constructs a new <code>ChannelEndNotMoveableException</code> without a detail message or
    * cause.
    */
   public ChannelEndNotMoveableException()
   {
      super();
   }
   
   /**
    * Constructs a new <Code>ChannelEndNotMoveableException</code> with a detail message.
    *
    * @param message the detail message.
    */
   public ChannelEndNotMoveableException(String message)
   {
      super(message);
   }
   
   /**
    * Constructs a new <Code>ChannelEndNotMoveableException</code> with a detail message and
    * underlying cause exception.
    *
    * @param message the detail message.
    * @param cause the exception that caused this one to be raised.
    */
   public ChannelEndNotMoveableException(String message, Throwable cause)
   {
      super(message, cause);
   }
   
   /**
    * Constructs a new <code>ChannelEndNotMoveableException</code> with a cause exception.
    *
    * @param cause the exception that caused this one to be raised.
    */
   public ChannelEndNotMoveableException(Throwable cause)
   {
      super(cause);
   }
}