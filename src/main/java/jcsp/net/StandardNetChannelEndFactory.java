
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

package jcsp.net;

import jcsp.util.ChannelDataStore;

/**
 * A standard implementation of the JCSP.NET
 * Networked channel factory interaces.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class StandardNetChannelEndFactory
implements NetChannelEndFactory, NetLabelledChannelEndFactory, NetBufferedChannelEndFactory, NetLabelledBufferedChannelEndFactory
{
   private static StandardNetChannelEndFactory instance = new StandardNetChannelEndFactory();
   
   private final Profile linkProfile;
   
   public StandardNetChannelEndFactory()
   {
      linkProfile = null;
   }
   
   /**
    * Creates a factory that creates links using a given profile rather than the default one.
    */
   public StandardNetChannelEndFactory(Profile profile)
   {
      linkProfile = profile;
   }
   
   public static StandardNetChannelEndFactory getDefaultInstance()
   {
      return instance;
   }
   
   /**
    * @see NetChannelEndFactory#createNet2One()
    */
   public NetAltingChannelInput createNet2One()
   {
      return Net2OneChannel.create();
   }
   
   /**
    * @see NetLabelledChannelEndFactory#createNet2One(String)
    */
   public NetAltingChannelInput createNet2One(String label)
   {
      return Net2OneChannel.create(label);
   }
   
   /**
    * @see NetBufferedChannelEndFactory#createNet2One(ChannelDataStore)
    */
   public NetAltingChannelInput createNet2One(ChannelDataStore buffer)
   {
      return Net2OneChannel.create(buffer);
   }
   
   /**
    * @see NetLabelledBufferedChannelEndFactory#createNet2One(String, ChannelDataStore)
    */
   public NetAltingChannelInput createNet2One(String label, ChannelDataStore buffer)
   {
      return Net2OneChannel.create(label, buffer);
   }
   
   /**
    * @see NetChannelEndFactory#createNet2Any()
    */
   public NetSharedChannelInput createNet2Any()
   {
      return new Net2AnyChannel();
   }
   
   /**
    * @see NetLabelledChannelEndFactory#createNet2Any(String)
    */
   public NetSharedChannelInput createNet2Any(String label)
   {
      return new Net2AnyChannel(label);
   }
   
   /**
    * @see NetBufferedChannelEndFactory#createNet2Any(ChannelDataStore)
    */
   public NetSharedChannelInput createNet2Any(ChannelDataStore buffer)
   {
      return new Net2AnyChannel(buffer);
   }
   
   /**
    * @see NetLabelledBufferedChannelEndFactory#createNet2Any(String, ChannelDataStore)
    */
   public NetSharedChannelInput createNet2Any(String label, ChannelDataStore buffer)
   {
      return new Net2AnyChannel(label, buffer);
   }
   
   /**
    * @see NetChannelEndFactory#createOne2Net(NetChannelLocation)
    */
   public NetChannelOutput createOne2Net(NetChannelLocation loc)
   {
      return new One2NetChannel(loc, linkProfile);
   }
   
   /**
    * @see NetChannelEndFactory#createAny2Net(NetChannelLocation)
    */
   public NetSharedChannelOutput createAny2Net(NetChannelLocation loc)
   {
      return new Any2NetChannel(loc, linkProfile);
   }
}