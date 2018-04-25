
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

/**
 * <p>
 * This class provides static methods for constructing
 * <code>NetConnectionServer</code> and <code>NetConnectionClient</code>
 * objects.
 * </p>
 * <p>
 * The methods provided are equivalent to the methods defined in
 * <code>{@link NetConnectionFactory}</code>.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public class NetConnection
{
   private static StandardNetConnectionFactory factory = new StandardNetConnectionFactory();
   
   /**
    * @see NetConnectionFactory#createNet2One()
    */
   public static NetAltingConnectionServer createNet2One()
   {
      return factory.createNet2One();
   }
   
   /**
    * @see NetConnectionFactory#createNet2Any()
    */
   public static NetSharedConnectionServer createNet2Any()
   {
      return factory.createNet2Any();
   }
   
   /**
    * @see NetConnectionFactory#createOne2Net(NetChannelLocation)
    */
   public static NetAltingConnectionClient createOne2Net(NetChannelLocation serverLoc)
   {
      return factory.createOne2Net(serverLoc);
   }
   
   /**
    * @see NetConnectionFactory#createAny2Net(NetChannelLocation)
    */
   public static NetSharedAltingConnectionClient createAny2Net(NetChannelLocation serverLoc)
   {
      return factory.createAny2Net(serverLoc);
   }
}