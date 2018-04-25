
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

import java.util.Hashtable;

import jcsp.net2.JCSPNetworkException;
import jcsp.net2.NetChannel;
import jcsp.net2.NetChannelInput;
import jcsp.net2.NetChannelLocation;
import jcsp.net2.NetChannelOutput;
import jcsp.net2.NodeID;

/**
 * @author Kevin
 */
final class DynamicClassLoader
    extends ClassLoader
{
    final NodeID originatingNode;

    NetChannelOutput requestClassData;

    NetChannelInput classDataResponse = NetChannel.net2one();

    final Hashtable classes = new Hashtable();

    DynamicClassLoader(NodeID originator, NetChannelLocation requestLocation)
    {
        super(ClassLoader.getSystemClassLoader());
        this.originatingNode = originator;
        this.requestClassData = NetChannel.one2net(requestLocation);
    }

    protected Class findClass(String className)
        throws ClassNotFoundException, JCSPNetworkException
    {
        try
        {
            Class clazz = Class.forName(className, false, ClassLoader.getSystemClassLoader());
            return clazz;
        }
        catch (ClassNotFoundException cnfe)
        {
            try
            {
                byte[] bytes = this.requestClass(className);
                Class toReturn = this.defineClass(className, bytes, 0, bytes.length);
                this.resolveClass(toReturn);
                return toReturn;
            }
            catch (ClassNotFoundException cnf)
            {
                throw cnf;
            }
        }
    }

    synchronized byte[] requestClass(String className)
        throws ClassNotFoundException
    {
        try
        {
            byte[] bytes = (byte[])classes.get(className);
            if (bytes != null)
            {
                return bytes;
            }
            if (this.requestClassData == null)
            {
                throw new ClassNotFoundException(className);
            }

            ClassRequest req = new ClassRequest(this.originatingNode, className,
                    (NetChannelLocation)this.classDataResponse.getLocation());
            this.requestClassData.write(req);
            ClassData data = (ClassData)classDataResponse.read();
            if (data.bytes == null)
            {
                throw new ClassNotFoundException(className);
            }
            this.classes.put(className, data.bytes);
            return data.bytes;
        }
        catch (JCSPNetworkException jne)
        {
            this.classDataResponse.destroy();
            this.classDataResponse = null;
            this.requestClassData.destroy();
            this.requestClassData = null;
            throw new ClassNotFoundException(className);
        }
    }
}
