
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

package jcsp.net.dynamic;

import java.io.Serializable;
import jcsp.net.*;

/**
 * Writing end of a migratable channel. The underlying channel end can be obtained by a call to
 * <code>getOutputChannel</code> and used like any other channel. Before transfering the channel end
 * to another node the <code>prepareToMove()</code> method must be called.
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface OutputReconnectionManager extends Serializable
{
   /**
    * Returns the underlying channel output end.
    */
   public NetChannelOutput getOutputChannel();
   
   /**
    * Prepares the channel end for transfer to another node.
    */
   public void prepareToMove();
}