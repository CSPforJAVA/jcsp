
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

import java.io.*;

/**
 * Node initialisation factory interface. A specific initialisation can be written by implementing
 * this interface and passing an instance to the <code>factoryInit</code> method of
 * <code>Node</code>.
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface NodeFactory extends Serializable
{
   /*
    * Initialises the node passed and returns the resulting <code>NodeKey</code> for the caller
    * to subsequently use.
    *
    * @return the <code>NodeKey</code> of the Node or <code>null</code>
    *          if initialization failed.
    */
   public NodeKey initNode(Node node, Node.Attributes attribs) throws NodeInitFailedException;
}