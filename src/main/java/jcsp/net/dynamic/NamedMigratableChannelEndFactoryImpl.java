
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
 * Implementation of the factory for creating named migratable networked channel ends.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class NamedMigratableChannelEndFactoryImpl implements NamedMigratableChannelEndFactory
{
   /**
    * CNS service name to use.
    */
   private String cnsServiceName;
   
   /**
    * CNS user reference.
    */
   private CNSUser cnsUser;
   
   /**
    * Factory to create the underlying networked channel ends.
    */
   private NetChannelEndFactory factoryToUse;
   
   /**
    * Constructs a new <code>NamedMigratableChannelEndFactoryImpl</code> for use with the given
    * CNS service name.
    *
    * @param cnsServiceName the name of the CNS service to use.
    */
   public NamedMigratableChannelEndFactoryImpl(String cnsServiceName)
   {
      super();
      factoryToUse = StandardNetChannelEndFactory.getDefaultInstance();
      this.cnsServiceName = cnsServiceName;
      cnsUser = (CNSUser)Node.getInstance().getServiceUserObject(cnsServiceName);
   }
   
   /**
    * Constructs a new <code>NamedMigratableChannelEndFactoryImpl</code> using the default CNS
    * service name.
    */
   public NamedMigratableChannelEndFactoryImpl()
   {
      this(CNSService.CNS_DEFAULT_SERVICE_NAME);
   }
   
   /**
    * @see NamedMigratableChannelEndFactory#createNet2One(String)
    */
   public MigratableAltingChannelInput createNet2One(String name)
   {
      return createNet2One(name, null);
   }
   
   /**
    * @see NamedMigratableChannelEndFactory#createNet2One(String, NameAccessLevel)
    */
   public MigratableAltingChannelInput createNet2One(String name, NameAccessLevel nameAccessLevel)
   {
      NetAltingChannelInput chanIn = factoryToUse.createNet2One();
      ChannelNameKey key = (nameAccessLevel != null)
                         ? cnsUser.register(chanIn, name, nameAccessLevel)
                         : cnsUser.register(chanIn, name);
      
      InputReconnectionManager mgr = new InputReconnectionManagerCNSImpl(chanIn, name, nameAccessLevel, key, cnsServiceName);
      return new MigratableAltingChannelInputImpl(mgr);
   }
   
   /**
    * @see NamedMigratableChannelEndFactory#createOne2Net(String)
    */
   public MigratableChannelOutput createOne2Net(String name)
   {
      return createOne2Net(name);
   }
   
   /**
    * @see NamedMigratableChannelEndFactory#createOne2Net(String, NameAccessLevel)
    */
   public MigratableChannelOutput createOne2Net(String name, NameAccessLevel accessLevel)
   {
      NetChannelLocation loc = (accessLevel != null)
                             ? cnsUser.resolve(name)
                             : cnsUser.resolve(name, accessLevel);
      return new MigratableChannelOutputImpl(factoryToUse.createOne2Net(loc));
   }
}