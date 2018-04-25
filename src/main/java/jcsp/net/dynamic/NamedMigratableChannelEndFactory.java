
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

import jcsp.net.cns.NameAccessLevel;

/**
 * Factory interface for creating migratable networked channel input ends using a CNS service.
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface NamedMigratableChannelEndFactory
{
   /**
    * Creates a named migratable networked channel input end that can be used as a guard in an <code>Alternative</code>.
    *
    * @param name the name to use.
    * @return the created channel end.
    */
   public MigratableAltingChannelInput createNet2One(String name);
   
   /**
    * Creates a named migratable networked channel input end that can be used as a guard in an <code>Alternative</code>.
    *
    * @param name the name to use.
    * @param nameAccessLevel the namespace to declare the name within.
    * @return the created channel end.
    */
   public MigratableAltingChannelInput createNet2One(String name, NameAccessLevel nameAccessLevel);
   
   /**
    * Creates a networked migratable channel output end connected to the input end created with the given name.
    *
    * @param name the name the input end was created with.
    * @return the created channel end.
    */
   public MigratableChannelOutput createOne2Net(String name);
   
   /**
    * Creates a networked migratable channel output end connected to the input end created with the given name.
    *
    * @param name the name the input end was created with.
    * @return the created channel end.
    */
   public MigratableChannelOutput createOne2Net(String name, NameAccessLevel accessLevel);
}