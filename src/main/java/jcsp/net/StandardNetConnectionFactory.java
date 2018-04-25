
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
 * This is an implementation of <code>{@link NetConnectionFactory}</code>.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public class StandardNetConnectionFactory implements NetConnectionFactory
{
   /**
    * Constructor for StandardNetConnectionFactory.
    */
   public StandardNetConnectionFactory()
   {
      super();
   }
   
   /**
    * @see NetConnectionFactory#createNet2One()
    */
   public NetAltingConnectionServer createNet2One()
   {
      return NetAltingConnectionServer.create();
   }
   
   /**
    * @see NetConnectionFactory#createNet2Any()
    */
   public NetSharedConnectionServer createNet2Any()
   {
      return NetSharedConnectionServerImpl.create();
   }
   
   /**
    * @see NetConnectionFactory#createOne2Net(NetChannelLocation)
    */
   public NetAltingConnectionClient createOne2Net(NetChannelLocation serverLoc)
   {
      return NetAltingConnectionClient.create(serverLoc);
   }
   
   /**
    * @see NetConnectionFactory#createAny2Net(NetChannelLocation)
    */
   public NetSharedAltingConnectionClient createAny2Net(NetChannelLocation serverLoc)
   {
      return NetSharedAltingConnectionClient.create(serverLoc);
   }
}