
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

import jcsp.lang.*;

/**
 * Thrown if a remote process terminates abruptly with a non-zero error code.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class RemoteProcessFailedException extends RuntimeException
{
   /** The error code returned by the process. */
   private final int errorCode;
   
   /** The offending process. */
   private final CSProcess process;
   
   /**
    * Constructs a new exception.
    *
    * @param ec the exit code from the remote JVM.
    * @param proc the process that was running.
    */
   public RemoteProcessFailedException(int ec, CSProcess proc)
   {
      errorCode = ec;
      process = proc;
   }
   
   /**
    * Returns a string description of the exception.
    */
   public String toString()
   {
      return "Remote process '" + process.toString() + "' failed with error code " + errorCode;
   }
   
   /**
    * Returns the error code of the remote JVM.
    */
   public int getErrorCode()
   {
      return errorCode;
   }
   
   /**
    * Returns the process that was running when the error occurred.
    */
   public CSProcess getFailedProcess()
   {
      return process;
   }
}