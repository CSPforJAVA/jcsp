
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

package jcsp.net.cns;

import jcsp.net.*;

/**
 * <p>
 * Classes implementing this interface act as factories for constructing
 * <code>NetChannelInput</code> and <code>NetChannelOutput</code> objects.
 * </p>
 * <p>
 * <code>NetChannelInput</code> objects are constructed and have their
 * location registered with a channel naming service.
 * </p>
 * <p>
 * <code>NetChannelOutput</code> objects are constructed and connected
 * to <code>NetChannelInput</code> objects whose location is resolved from
 * a channel naming service.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface NamedChannelEndFactory
{
   /**
    * Constructs a <code>NetAltingChannelInput</code> object and
    * registers its location with the supplied name in the global namespace
    * of a channel naming service.
    *
    * @param name the name against which to register the channel.
    *
    * @return the constructed <code>NetAltingChannelInput</code> object.
    */
   public NetAltingChannelInput createNet2One(String name);
   
   /**
    * Constructs a <code>NetAltingChannelInput</code> object and
    * registers its location with the supplied name in specified
    * namespace of a channel naming service.
    *
    * @param name the name against which to register the channel.
    * @param nameAccessLevel the namespace in which to register the name.
    *
    * @return the constructed <code>NetAltingChannelInput</code> object.
    */
   public NetAltingChannelInput createNet2One(String name, NameAccessLevel nameAccessLevel);
   
   /**
    * Constructs a <code>NetSharedChannelInput</code> object and
    * registers its location with the supplied name in the global namespace
    * of a channel naming service.
    *
    * @param name the name against which to register the channel.
    *
    * @return the constructed <code>NetSharedChannelInput</code> object.
    */
   public NetSharedChannelInput createNet2Any(String name);
   
   /**
    * Constructs a <code>NetSharedChannelInput</code> object and
    * registers its location with the supplied name in specified
    * namespace of a channel naming service.
    *
    * @param name the name against which to register the channel.
    * @param nameAccessLevel the namespace in which to register the name.
    *
    * @return the constructed <code>NetSharedChannelInput</code> object.
    */
   public NetSharedChannelInput createNet2Any(String name, NameAccessLevel nameAccessLevel);
   
   /**
    * Constructs a <code>NetChannelOutput</code> object connected
    * to a <code>NetChannelInput</code> located at a location
    * resolved from the specified channel name.
    *
    * @param name the name of the channel from which to resolve the location.
    *
    * @return the constructed <code>NetChannelOutput</code> object.
    */
   public NetChannelOutput createOne2Net(String name);
   
   /**
    * Constructs a <code>NetChannelOutput</code> object connected
    * to a <code>NetChannelInput</code> located at a location
    * resolved from the specified channel name that exists in the supplied
    * namespace.
    *
    * @param name the name of the channel from which to resolve the location.
    * @param accessLevel the namespace in which the channel name exists.
    *
    * @return the constructed <code>NetChannelOutput</code> object.
    */
   public NetChannelOutput createOne2Net(String name, NameAccessLevel accessLevel);
   
   
   /**
    * Constructs a <code>NetSharedChannelOutput</code> object connected
    * to a <code>NetChannelInput</code> located at a location
    * resolved from the specified channel name.
    *
    * @param name the name of the channel from which to resolve the location.
    *
    * @return the constructed <code>NetChannelOutput</code> object.
    */
   public NetSharedChannelOutput createAny2Net(String name);
   
   /**
    * Constructs a <code>NetSharedChannelOutput</code> object connected
    * to a <code>NetChannelInput</code> located at a location
    * resolved from the specified channel name that exists in the supplied
    * namespace.
    *
    * @param name the name of the channel from which to resolve the location.
    * @param accessLevel the namespace in which the channel name exists.
    *
    * @return the constructed <code>NetChannelOutput</code> object.
    */
   public NetSharedChannelOutput createAny2Net(String name, NameAccessLevel accessLevel);
}