
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
 * This factory constructs buffered <code>NetChannelInput</code>
 * objects which have labelled VCN's.
 * </p>
 * <p>
 * See {@link NetLabelledChannelEndFactory} for an explanation of
 * labelled VCN's.
 * </p>
 *
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface NetLabelledBufferedChannelEndFactory
{
   /**
    * Constructs a buffered <code>NetAltingChannelInput</code> which
    * has a VCN assigned with the specified label.
    *
    * @param label the label to apply to the channel's VCN.
    * @param buffer the <code>ChannelDataStore</code> to use.
    *
    * @return the constructed <code>NetAltingChannelInput</code>
    *          object.
    */
   public NetAltingChannelInput createNet2One(String label, ChannelDataStore buffer);
   
   /**
    * Constructs a buffered <code>NetSharedChannelInput</code> which
    * has a VCN assigned with the specified label.
    *
    * @param label the label to apply to the channel's VCN.
    * @param buffer the <code>ChannelDataStore</code> to use.
    *
    * @return the constructed <code>NetSharedChannelInput</code>
    *          object.
    */
   public NetSharedChannelInput createNet2Any(String label, ChannelDataStore buffer);
}