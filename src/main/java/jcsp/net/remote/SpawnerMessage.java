
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

package jcsp.net.remote;

import java.io.*;
import jcsp.lang.*;
import jcsp.net.*;

/**
 * Message sent from <code>RemoteProcess</code> to <code>SpawnerService</code> listing the details of
 * the process that should be started and a network channel address for replying on.
 *
 *
 * @author Quickstone Technologies Limited
 */
class SpawnerMessage implements Serializable
{
   public final CSProcess process;
   public final NetChannelLocation caller;
   public final NodeFactory factory;
   public final ApplicationID applicationID;
   public final String classPath;
   
   /**
    * Constructs a new message.
    *
    * @param process the process to be spawned.
    * @param caller the location of the <code>RemoteProcess</code>'s channel for replies.
    * @param factory the optional factory for initializing the remote node.
    * @param applicationID the application ID that the remote node should adopt.
    * @param classPath the class path the remote JVM should use.
    */
   public SpawnerMessage(CSProcess process, NetChannelLocation caller, NodeFactory factory, 
                         ApplicationID applicationID, String classPath)
   {
      this.process = process;
      this.caller = caller;
      this.factory = factory;
      this.applicationID = applicationID;
      this.classPath = classPath;
   }
}