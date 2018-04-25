
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

package jcsp.net.security;

/**
 * <p>Thrown by the security authority implementations if the credentials supplied are not correct or
 * another error occurs as a result of user parameters.</p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public class AccessDeniedException extends Exception
{
   /**
    * The reason the exception was raised.
    */
   private final String reason;
   
   /**
    * The security authority raising the exception.
    */
   private final SecurityAuthority auth;
   
   /**
    * Creates a new exeception.
    *
    * @param auth the authority raising the exception.
    * @param reason the reason the exception was raised.
    */
   public AccessDeniedException(SecurityAuthority auth, String reason)
   {
      super("Access Denied");
      this.reason = reason;
      this.auth = auth;
   }
   
   /**
    * Returns a printable string describing the exception.
    */
   public String toString()
   {
      return "Access denied by " + auth.toString() + " - " + reason;
   }
}