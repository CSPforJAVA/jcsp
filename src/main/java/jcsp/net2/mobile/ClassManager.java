
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

package jcsp.net2.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import jcsp.lang.CSProcess;
import jcsp.net2.JCSPNetworkException;
import jcsp.net2.NetChannel;
import jcsp.net2.NetChannelInput;
import jcsp.net2.NetChannelOutput;
import jcsp.net2.Node;

/**
 * @author Kevin
 */
final class ClassManager
    implements CSProcess
{
    static Hashtable classLoaders = new Hashtable();

    static NetChannelInput in = NetChannel.numberedNet2One(10);

    public void run()
    {
        while (true)
        {
            try
            {
                ClassRequest req = (ClassRequest)in.read();
                if (req.originatingNode.equals(Node.getInstance().getNodeID()))
                {
                    String className = req.className.replace('.', '/') + ".class";
                    InputStream is = ClassLoader.getSystemResourceAsStream(className);
                    try
                    {
                        if (is != null)
                        {
                            int read = 0;
                            byte[] bytes = new byte[is.available()];
                            while (read < bytes.length)
                                read += is.read(bytes, read, bytes.length - read);
                            ClassData resp = new ClassData(req.className, bytes);
                            NetChannelOutput out = NetChannel.one2net(req.returnLocation);
                            out.asyncWrite(resp);
                            out.destroy();
                            out = null;
                        }
                        else
                        {
                            ClassData resp = new ClassData(req.className, null);
                            NetChannelOutput out = NetChannel.one2net(req.returnLocation);
                            out.asyncWrite(resp);
                            out.destroy();
                            out = null;
                        }
                    }
                    catch (IOException ioe)
                    {
                        ClassData resp = new ClassData(req.className, null);
                        NetChannelOutput out = NetChannel.one2net(req.returnLocation);
                        out.asyncWrite(resp);
                        out.destroy();
                    }
                }
                else
                {
                    DynamicClassLoader loader = (DynamicClassLoader)ClassManager.classLoaders.get(req.originatingNode);
                    if (loader == null)
                    {
                        ClassData resp = new ClassData(req.className, null);
                        NetChannelOutput out = NetChannel.one2net(req.returnLocation);
                        out.asyncWrite(resp);
                        out.destroy();
                    }
                    else
                    {
                        try
                        {
                            byte[] bytes = loader.requestClass(req.className);
                            ClassData resp = new ClassData(req.className, bytes);
                            NetChannelOutput out = NetChannel.one2net(req.returnLocation);
                            out.asyncWrite(resp);
                            out.destroy();
                        }
                        catch (ClassNotFoundException cnf)
                        {
                            ClassData resp = new ClassData(req.className, null);
                            NetChannelOutput out = NetChannel.one2net(req.returnLocation);
                            out.asyncWrite(resp);
                            out.destroy();
                        }
                    }
                }
            }
            catch (JCSPNetworkException jne)
            {
                // Do nothing
            }
        }
    }
}
