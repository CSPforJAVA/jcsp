
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

import jcsp.net.*;
import jcsp.net.cns.*;

/**
 * Static factory for creating migratable channel ends.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class MigratableChannelEnd
{
   /**
    * Standard factory for anonymous channel ends.
    */
   private static MigratableChannelEndFactory FACTORY = new MigratableChannelEndFactory();
   
   /**
    * Factory for named channel ends.
    */
   private static NamedMigratableChannelEndFactory NAMED_FACTORY = new NamedMigratableChannelEndFactoryImpl();
   
   /**
    * Creates a new <code>MigratableChannelEnd</code> object. This is private to prevent any
    * instances from being created. This class contains only static methods.
    */
   private MigratableChannelEnd()
   {
      super();
   }
   
   /**
    * Creates an anonymous migratable channel input.
    *
    * @return the created channel end.
    */
   public static MigratableAltingChannelInput createNet2One()
   {
      return (MigratableAltingChannelInput) FACTORY.createNet2One();
   }
   
   /**
    * Creates a migratable channel output to a given location.
    *
    * @param loc location of the input end of the channel.
    * @return the created channel end.
    */
   public static MigratableChannelOutput createOne2Net(NetChannelLocation loc)
   {
      return (MigratableChannelOutput) FACTORY.createOne2Net(loc);
   }
   
   /**
    * Creates a named migratable channel input using the default namespace.
    *
    * @param name the name of the channel to register with the CNS.
    * @return the created channel end.
    */
   public static MigratableAltingChannelInput createNet2One(String name)
   {
      return createNet2One(name, null);
   }
   
   /**
    * Creates a named migratable channel input within the given namespace.
    *
    * @param name the name of the channel to register with the CNS.
    * @param nameAccessLevel the namespace to register the name within.
    * @return the created channel end.
    */
   public static MigratableAltingChannelInput createNet2One(String name, NameAccessLevel nameAccessLevel)
   {
      return NAMED_FACTORY.createNet2One(name, nameAccessLevel);
   }
   
   /**
    * Creates a migratable channel output to a named channel within the default namespace.
    *
    * @param name the name of the channel as registered with the CNS.
    * @return the created channel end.
    */
   public static MigratableChannelOutput createOne2Net(String name)
   {
      return createOne2Net(name, null);
   }
   
   /**
    * Creates a migratable channel output to a named channel within a given namespace.
    *
    * @param name the name of the channel as registered with the CNS.
    * @param nameAccessLevel the namespace the name is registered within.
    * @return the created channel end.
    */
   public static MigratableChannelOutput createOne2Net(String name, NameAccessLevel nameAccessLevel)
   {
      return NAMED_FACTORY.createOne2Net(name, nameAccessLevel);
   }
}