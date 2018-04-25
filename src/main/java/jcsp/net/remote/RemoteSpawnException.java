
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

package jcsp.net.remote;

/**
 * Wraps up a non-RuntimeException into a runtime exception that can be ignored or caught and
 * rethrown if required.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class RemoteSpawnException extends RuntimeException
{
   /** The actual exception. */
   public final Throwable cause;
   
   /**
    * Constructs a new exception.
    *
    * @param cause the actual exception.
    */
   public RemoteSpawnException(Throwable cause)
   {
      this.cause = cause;
   }
   
   /**
    * Rethrows the actual exception.
    */
   public void rethrow() throws Throwable
   {
      throw cause;
   }
   
   /**
    * Prints the stack trace of the actual exception.
    */
   public void printStackTrace()
   {
      cause.printStackTrace();
   }
   
   /**
    * Returns a string representation of the actual exception.
    */
   public String toString()
   {
      return cause.toString();
   }
}