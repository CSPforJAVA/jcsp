
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

import jcsp.lang.*;

/**
 * <p>
 * An abstract class that is sub-classed by classes whose instances
 * should be networked channel ends that can be used as an
 * <code>{@link AltingChannelInput}</code> objects.
 * </p>
 * <p>
 * This class does not need to be sub-classed by JCSP users.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public abstract class NetAltingChannelInput extends AltingChannelInputWrapper implements NetChannelInput
{
   /**
    * <p>
    * Constructs a channel end and takes the actual channel to use
    * to deliver the data.
    * </p>
    *
    * @param channel the actual channel used to deliver data to the user.
    */
   protected NetAltingChannelInput(AltingChannelInput channel)
   {
      super(channel);
   }
   
   /**
    * <p>
    * Constructs a channel end without supplying the actual channel to
    * use. <code>setChannel(AltingChannelInput)</code> should be called
    * before the channel end is actually used.
    * </p>
    */
   protected NetAltingChannelInput()
   {
   }
}