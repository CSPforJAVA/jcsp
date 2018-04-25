
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

import jcsp.lang.CSProcess;
import jcsp.lang.ProcessManager;

/**
 * Abstract class defining the LinkServer.
 * 
 * @author Kevin Chalmers
 */
public abstract class LinkServer
    implements CSProcess
{
    /**
     * @param address
     * @throws IllegalArgumentException
     * @throws JCSPNetworkException
     */
    public static final void start(NodeAddress address)
        throws IllegalArgumentException, JCSPNetworkException
    {
        Node.log.log(LinkServer.class, "Attempting to start Link Server on " + address);
        LinkServer linkServer = address.createLinkServer();
        ProcessManager linkServProc = new ProcessManager(linkServer);
        linkServProc.setPriority(Link.LINK_PRIORITY);
        linkServProc.start();
        Node.log.log(LinkServer.class, "Link Server started on " + address);
    }

    /**
     * @param nodeID
     * @return The Link connected to the Node with the corresponding NodeID, or null if no such Node exists
     */
    protected final Link requestLink(NodeID nodeID)
    {
        return LinkManager.getInstance().requestLink(nodeID);
    }

    /**
     * @param link
     * @return True if the Link to the Node was successfully registered, false otherwise
     */
    protected final boolean registerLink(Link link)
    {
        return LinkManager.getInstance().registerLink(link);
    }
}
