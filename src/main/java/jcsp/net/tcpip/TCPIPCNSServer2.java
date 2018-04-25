
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

package jcsp.net.tcpip;

import java.io.*;
import jcsp.net.*;
import jcsp.net.cns.*;
import jcsp.lang.*;

/**
 * <p>Program to run a Channel Name Service. The service will listen on all locally available
 * addresses using either a default port of 7890 or a port specified in the XML file.</p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public class TCPIPCNSServer2
{
   /**
    * For use by other sub classes of this class to terminate the server. To terminate the server write a null
    * to this channel. This gets used in the NT service implementation to response to the STOP event.
    */
   static final One2OneChannel terminate = Channel.one2one();
   
   private TCPIPCNSServer2()
   {
   }
   
   /**
    * Main method, running the service. This will never terminate if the service can be started.
    */
   public static void main(String[] args)
   {
      Node.info.log(TCPIPCNSServer2.class, "Starting CNS server");
      try
      {
         NodeKey key = Node.getInstance().init(new XMLNodeFactory("JCSPNetCNSService.xml"));
         ServiceManager sm = Node.getInstance().getServiceManager(key);
         CNS cns = new CNS(key);
         if (sm.installService(cns, "Channel Name Server") && sm.startService("Channel Name Server"))
            Node.info.log(TCPIPCNSServer2.class, "CNS Started");
         else
            Node.info.log(TCPIPCNSServer2.class, "CNS failed to start");
      }
      catch (NodeInitFailedException e)
      {
         e.printStackTrace();
         return;
      }
      catch (IOException e)
      {
         Node.info.log(TCPIPCNSServer2.class,"XML file not found");
         return;
      }
      Node.info.log(TCPIPCNSServer2.class,"CNS server running on " + Node.getInstance().getNodeID());
      // sleep forever (or until terminated)
      terminate.in().read();
   }
}