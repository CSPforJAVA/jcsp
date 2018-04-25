
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
 * Extends the standard network channel factory to give unacknowledged channel output ends. Use these with caution
 * as the lack of synchronization between sender and receiver can lead to potential problems.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class UnacknowledgedNetChannelEndFactory extends StandardNetChannelEndFactory
{
   /**
    * Creates a new factory object.
    */
   public UnacknowledgedNetChannelEndFactory()
   {
      super();
   }
   
   /**
    * Creates an unacknowledged output channel end suitable for use by a single writer.
    *
    * @param loc address of the input channel end to connect to.
    */
   public NetChannelOutput createOne2Net(NetChannelLocation loc)
   {
      return new One2NetChannel(loc, false);
   }
   
   /**
    * Creates an unacknowledged output channel end suitable for use by multiple writers.
    *
    * @param loc address of the input channel end to connect to.
    */
   public NetSharedChannelOutput createAny2Net(NetChannelLocation loc)
   {
      return new Any2NetChannel(loc, false);
   }
}