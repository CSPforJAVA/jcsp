
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

/**
 * Used to initialise a Node by connecting to the CNS. This approach is now deprecated. To initialise a Node now:
 * <p>
 * <code>
 * NodeAddress localAddress = ...;<br>
 * NodeAddress nodeServerAddr = ...;<br>
 * Node.getInstance().init(localAddress);<br>
 * CNS.init(nodeServerAddr);<br>
 * BNS.init(nodeServerAddr);<br>
 * </code>
 * </p>
 * 
 * @see Node
 * @author Kevin Chalmers
 */
public abstract class NodeFactory
{
    /**
     * The NodeAddress where the CNS / BNS is located
     */
    protected NodeAddress cnsAddress;

    /**
     * Initialises a Node
     * 
     * @param node
     *            The Node to initialise
     * @return A new NodeAddress for the Node
     * @throws JCSPNetworkException
     *             Thrown if something goes wrong during the initialisation
     */
    protected abstract NodeAddress initNode(Node node)
        throws JCSPNetworkException;
}
