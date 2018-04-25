
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
 * <p>
 * This class provides static factory methods for constructing
 * Networked channel ends.
 * </p>
 * <p>
 * The methods are equivalent to the methods defined in
 * <code>{@link NetChannelEndFactory}</code>.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public class NetChannelEnd
{
   
   private static StandardNetChannelEndFactory factory = StandardNetChannelEndFactory.getDefaultInstance();
   
   private NetChannelEnd()
   {
      //private constructor to prevent instantiation
   }
   
   /**
    * @see NetChannelEndFactory#createNet2One()
    */
   public static NetAltingChannelInput createNet2One()
   {
      return factory.createNet2One();
   }
   
   /**
    * @see jcsp.net.NetLabelledChannelEndFactory#createNet2One(String)
    */
   public static NetAltingChannelInput createNet2One(String label)
   {
      return factory.createNet2One(label);
   }
   
   /**
    * @see jcsp.net.NetBufferedChannelEndFactory#createNet2One(ChannelDataStore)
    */
   public static NetAltingChannelInput createNet2One(ChannelDataStore buffer)
   {
      return factory.createNet2One(buffer);
   }
   
   /**
    * @see jcsp.net.NetLabelledBufferedChannelEndFactory#createNet2One(String, ChannelDataStore)
    */
   public static NetAltingChannelInput createNet2One(String label, ChannelDataStore buffer)
   {
      return factory.createNet2One(label, buffer);
   }
   
   /**
    * @see NetChannelEndFactory#createNet2Any()
    */
   public static NetSharedChannelInput createNet2Any()
   {
      return factory.createNet2Any();
   }
   
   /**
    * @see jcsp.net.NetLabelledChannelEndFactory#createNet2Any(String)
    */
   public static NetSharedChannelInput createNet2Any(String label)
   {
      return factory.createNet2Any(label);
   }
   
   /**
    * @see jcsp.net.NetBufferedChannelEndFactory#createNet2Any(ChannelDataStore)
    */
   public static NetSharedChannelInput createNet2Any(ChannelDataStore buffer)
   {
      return factory.createNet2Any(buffer);
   }
   
   /**
    * @see jcsp.net.NetLabelledBufferedChannelEndFactory#createNet2Any(String, ChannelDataStore)
    */
   public static NetSharedChannelInput createNet2Any(String label, ChannelDataStore buffer)
   {
      return factory.createNet2Any(label, buffer);
   }
   
   /**
    * @see NetChannelEndFactory#createOne2Net(NetChannelLocation)
    */
   public static NetChannelOutput createOne2Net(NetChannelLocation loc)
   {
      return factory.createOne2Net(loc);
   }
   
   /**
    * @see NetChannelEndFactory#createAny2Net(NetChannelLocation)
    */
   public static NetSharedChannelOutput createAny2Net(NetChannelLocation loc)
   {
      return factory.createAny2Net(loc);
   }
}