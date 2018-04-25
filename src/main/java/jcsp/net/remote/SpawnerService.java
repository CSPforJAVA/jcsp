
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
import jcsp.net.dynamic.*;
import jcsp.util.filter.*;

/**
 * <p>Services requests from <code>RemoteProcess</code> proxies to start up child JVMs running the
 * actual processes.</p>
 *
 * <p>If started from the command line, it will use the XML config file specified by the first
 * command parameter. If no file is given it will try and use <code>JCSPNetSpawnerService.xml</code> to
 * initialize the local node. Alternatively it can be started programmatically and the caller must
 * take responsibility for initializing the node.</p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public class SpawnerService implements CSProcess
{
   /** The node key. */
   private final NodeKey nodeKey;
   
   /** Termination channel. */
   private final One2OneChannel terminate = Channel.one2one();
   
   /**
    * Constructs a new service.
    *
    * @param nodeKey the local node key.
    */
   public SpawnerService(NodeKey nodeKey)
   {
      this.nodeKey = nodeKey;
   }
   
   /** Runs the service. */
   public void run()
   {
      // Create an input channel to accept requests on
      AltingChannelInput in = NetChannelEnd.createNet2One("controlSignals");
      
      // Put a dynamic class loader in place
      ServiceManager mgr = Node.getInstance().getServiceManager(nodeKey);
      if (mgr != null)
      {
         Service svc = mgr.getService("dynamic_loading");
         if (svc != null)
         {
            DynamicClassLoader dcl = (DynamicClassLoader)svc;
            in = FilteredChannelEnd.createFiltered(in);
            ((FilteredChannelInput)in).addReadFilter(dcl.getChannelRxFilter());
         }
      }
     
      int unique = 0;
      
      // Service requests
      Alternative alt = new Alternative(new Guard[] { terminate.in(), in });
      while (true)
      {
         try
         {
            if (alt.priSelect() == 0)
            {
               terminate.in().read();
               return;
            }
            else
            {
               SpawnerMessage msg = (SpawnerMessage)in.read();
               if (msg != null)
               {
                  NetChannelOutput out = NetChannelEnd.createOne2Net(msg.caller);
                  new ProcessManager(new ProcessSpawner(this, msg.process, out, msg.factory, msg.applicationID, unique++, msg.classPath)).start();
               }
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }
   
   void stop()
   {
      terminate.out().write(null);
   }
   
   public static SpawnerService construct(String[] args)
   {
      NodeKey nodeKey = null;
      // Initialize the node
      try
      {
         String configFile = "JCSPNetSpawnerService.xml";
         if (args.length > 0) 
            configFile = args[0];
         nodeKey = Node.getInstance().init(new XMLNodeFactory(configFile));
      }
      catch (IOException e)
      {
         System.err.println("Error reading from config file");
         System.exit(1);
      }
      catch (NodeInitFailedException e)
      {
         System.err.println("Unable to initialize node - aborting");
         System.exit(1);
      }
      return new SpawnerService(nodeKey);
   }
   
   /**
    * Program entry point.
    *
    * @param args the command line arguments. The first one may be the name of an XML file for
    *             initializing the local node.
    */
   public static void main(String[] args)
   {
      construct(args).run();
   }
}