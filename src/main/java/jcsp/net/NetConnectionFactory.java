
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
 * This class should be implemented by classes wishing to act
 * as factories for creating Networked <code>ConnectionServer</code>
 * and <code>ConnectionClient</code> objects.
 * </p>
 * <p>
 * An implementation is provided, see
 * <code>{@link StandardNetConnectionFactory}</code>.
 * </p>
 *
 * @author Quickstone Technologies Limited
 */
public interface NetConnectionFactory
{
   /**
    * <p>
    * Constructs a <code>NetAltingConnectionServer</code> object.
    * </p>
    *
    * @return the constructed <code>NetAltingConnectionServer</code> object.
    */
   public NetAltingConnectionServer createNet2One();
   
   /**
    * <p>
    * Constructs a <code>NetSharedConnectionServer</code> object.
    * </p>
    *
    * @return the constructed <code>NetSharedConnectionServer</code> object.
    */
   public NetSharedConnectionServer createNet2Any();
   
   /**
    * <p>
    * Constructs a <code>NetAltingConnectionClient</code> object.
    * </p>
    *
    * @return the constructed <code>NetAltingConnectionClient</code> object.
    */
   public NetAltingConnectionClient createOne2Net(NetChannelLocation serverLoc);
   
   /**
    * <p>
    * Constructs a <code>NetSharedAltingConnectionClient</code> object.
    * </p>
    *
    * @return the constructed <code>NetSharedAltingConnectionClient</code> object.
    */
   public NetSharedAltingConnectionClient createAny2Net(NetChannelLocation serverLoc);
}