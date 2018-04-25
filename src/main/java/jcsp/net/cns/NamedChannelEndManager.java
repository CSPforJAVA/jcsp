
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
 * <code>NetChannelInput</code> and <code>NetChannelOutput</code> objects
 * (see {@link NamedChannelEndFactory}).
 * </p>
 * <p>
 * They also supply methods for destroying the channel ends created, either
 * an individual end or all constructed so far. The later provides a convenient
 * way for a releasing all jcsp.net resources used by a process network. An
 * instance of an implementing class could be passed as parameter around a
 * process network and used to construct all channels. Once the network has
 * terminated the rousources can be released by calling
 * <code>{@link #destroyAllChannelEnds()}</code>.
 * </p>
 * <p>
 * If an attempt is made to destroy a channel end that was not constructed
 * by the instance of this class that was invoked, then a
 * <code>WrongFactoryException</code> should be thrown.
 * </p>
 *
 * @author Quickstone Technologies Limited
 */
public interface NamedChannelEndManager extends NamedChannelEndFactory
{
   /**
    * Destroys an individual <code>NetChannelInput</code> object
    * that was constructed with this instance. This will deregister
    * the channel name and destroy the channel end.
    *
    * @param chanInEnd  the channel end to destroy.
    */
   public void destroyChannelEnd(NetChannelInput chanInEnd);
   
   /**
    * Destroys an individual <code>NetChannelOutput</code> object
    * that was constructed with this instance. This will simply
    * destroy the channel end.
    *
    * @param chanOutEnd the channel end to destroy.
    */
   public void destroyChannelEnd(NetChannelOutput chanOutEnd);
   
   /**
    * Destroys all channel ends constructed with this instance
    * of the factory.
    */
   public void destroyAllChannelEnds();
}
