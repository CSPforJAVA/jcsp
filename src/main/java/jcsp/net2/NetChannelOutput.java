
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

package jcsp.net2;

import jcsp.lang.ChannelOutput;

/**
 * An interface defining a ChannelOutput that is networked. For information on how to an object of this type, see
 * ChannelOutput. For information on how to create a NetChannelOutput, see the the relevant factory.
 * <p>
 * The only method that this interface defines is asyncSend. This is considered a dangerous method to use, and careful
 * consideration must be taken. The inclusion of asyncSend is to provide the impression of a simple infinitely buffered
 * networked channel, without having to create extra buffers beyond what the channel uses.
 * </p>
 * 
 * @see ChannelOutput
 * @see Networked
 * @see NetChannel
 * @author Kevin Chalmers (updated from Quickstone Technologies)
 */
public interface NetChannelOutput
    extends ChannelOutput, Networked
{
    /**
     * Sends a message to the input end of the channel asynchronously (no blocking)
     * 
     * @param obj
     *            The object to send to the input end
     * @throws JCSPNetworkException
     *             Thrown if something goes wrong in the underlying architecture
     * @throws NetworkPoisonException
     *             Thrown if the channel is poisoned
     */
    public void asyncWrite(Object obj)
        throws JCSPNetworkException, NetworkPoisonException;

    /**
     * Sets the underlying encoder for the channel
     * 
     * @param encoder
     *            The encoder to use for the channel.
     */
    public void setEncoder(NetworkMessageFilter.FilterTx encoder);
}
