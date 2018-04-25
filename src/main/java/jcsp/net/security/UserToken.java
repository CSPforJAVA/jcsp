
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
 * <p>Uniquely identifies an authenticated user. An instance of a <code>SecurityAuthority</code> will be
 * able to create a user token from a user ID. To do so may require additional information such as passwords
 * or other authentication details. It is the responsibility of the authority to do the authentication and
 * only supply a token instance if the credentials are okay.</p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface UserToken extends java.io.Serializable
{
   /**
    * Returns the ID of the logged on user.
    */
   public UserID getUserID();
}