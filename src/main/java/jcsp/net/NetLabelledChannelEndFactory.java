
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
 * This factory constructs <code>NetChannelInput</code> objects
 * which have labelled VCN's.
 * </p>
 * <p>
 * JCSP.NET network channel addresses (signified by
 * <code>{@link NetChannelLocation}</code> objects) have a
 * Virtual Channel Number (VCN). This number is not exposed
 * to JCSP users but is an integral part of channel addressing.
 * </p>
 * <p>
 * If two Nodes have no means of communication, there is no
 * way that one can pass a <code>{@link NetChannelLocation}</code>
 * object to the other. This means that a channel cannot be
 * established using convential means. JCSP.NET solves this
 * problem by allowing VCN's to be labelled by the user.
 * If the address of a Node hosting a <code>ChannelInput</code>
 * with a known labelled VCN is known by a process in another Node,
 * then a <code>ChannelOutput</code> can be established by
 * constructing with a <code>NetChannelLocation</code> object constrcuted
 * using the <code>{@link
 * NetChannelLocation#NetChannelLocation(NodeAddressID, String)
 * }</code> constructor.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface NetLabelledChannelEndFactory
{
   /**
    * Constructs a <code>NetAltingChannelInput</code> which
    * has a VCN assigned with the specified label.
    *
    * @param label the label to apply to the channel's VCN.
    *
    * @return the constructed <code>NetAltingChannelInput</code>
    *          object.
    */
   public NetAltingChannelInput createNet2One(String label);
   
   /**
    * Constructs a <code>NetSharedChannelInput</code> which
    * has a VCN assigned with the specified label.
    *
    * @param label the label to apply to the channel's VCN.
    *
    * @return the constructed <code>NetSharedChannelInput</code>
    *          object.
    */
   public NetSharedChannelInput createNet2Any(String label);
}