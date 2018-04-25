
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

/**
 * Factory for creating networked migratable channel ends. An instance of this can be created and
 * the methods used. Alternatively the static methods in <code>MigratableChannelEnd</code> can be
 * used to create the channel ends.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class MigratableChannelEndFactory implements NetChannelEndFactory
{
   /**
    * Default channel factory for creating the underlying channels.
    */
   private static NetChannelEndFactory FACTORY = StandardNetChannelEndFactory.getDefaultInstance();
   
   /**
    * Constructs a new <code>MigratableChannelEndFactory</code>.
    */
   public MigratableChannelEndFactory()
   {
      super();
   }
   
   /**
    * @see NetChannelEndFactory#createNet2One()
    */
   public NetAltingChannelInput createNet2One()
   {
      return new MigratableAltingChannelInputImpl(FACTORY.createNet2One());
   }
   
   /**
    * @see NetChannelEndFactory#createNet2Any()
    */
   public NetSharedChannelInput createNet2Any()
   {
      throw new UnsupportedOperationException("Cannot create a shared migratable channel");
   }
   
   /**
    * @see NetChannelEndFactory#createOne2Net(NetChannelLocation)
    */
   public NetChannelOutput createOne2Net(NetChannelLocation loc)
   {
      return new MigratableChannelOutputImpl(FACTORY.createOne2Net(loc));
   }
   
   /**
    * @see NetChannelEndFactory#createAny2Net(NetChannelLocation)
    */
   public NetSharedChannelOutput createAny2Net(NetChannelLocation loc)
   {
      throw new UnsupportedOperationException("Cannot create a shared migratable channel");
   }
}