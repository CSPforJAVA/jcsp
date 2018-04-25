
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

import java.io.ByteArrayInputStream;
import java.io.IOException;

import jcsp.net2.NetChannelLocation;
import jcsp.net2.NetworkMessageFilter;
import jcsp.net2.Node;
import jcsp.net2.ObjectNetworkMessageFilter;

/**
 * @author Kevin
 */
public final class CodeLoadingChannelFilter
{
    public static final class FilterRX
        implements NetworkMessageFilter.FilterRx
    {
        private final ObjectNetworkMessageFilter.FilterRX objectFilter = new ObjectNetworkMessageFilter.FilterRX();

        public FilterRX()
        {
            // Do nothing
        }

        public Object filterRX(byte[] bytes)
            throws IOException
        {
            try
            {
                Object message = this.objectFilter.filterRX(bytes);
                if (!(message instanceof DynamicClassLoaderMessage))
                {
                    return message;
                }

                DynamicClassLoaderMessage loaderMessage = (DynamicClassLoaderMessage)message;
                byte[] bytesWithHeader = new byte[4 + loaderMessage.bytes.length];
                byte[] header = { -84, -19, 0, 5 };
                System.arraycopy(header, 0, bytesWithHeader, 0, 4);
                System.arraycopy(loaderMessage.bytes, 0, bytesWithHeader, 4, loaderMessage.bytes.length);
                ByteArrayInputStream bais = new ByteArrayInputStream(bytesWithHeader);
                DynamicClassLoader loader = (DynamicClassLoader) ClassManager.classLoaders
                        .get(loaderMessage.originatingNode);

                if (loader == null)
                {
                    loader = new DynamicClassLoader(loaderMessage.originatingNode, loaderMessage.requestLocation);
                    ClassManager.classLoaders.put(loaderMessage.originatingNode, loader);
                }

                DynamicObjectInputStream dois = new DynamicObjectInputStream(bais, loader);

                Object toReturn = dois.readObject();
                return toReturn;
            }
            catch (ClassNotFoundException cnfe)
            {
                throw new IOException("Failed to load class");
            }
        }
    }

    public static final class FilterTX
        implements NetworkMessageFilter.FilterTx
    {
        private final ObjectNetworkMessageFilter.FilterTX internalFilter = new ObjectNetworkMessageFilter.FilterTX();

        public FilterTX()
        {

        }

        public byte[] filterTX(Object obj)
            throws IOException
        {
            ClassLoader loader = obj.getClass().getClassLoader();
            byte[] bytes = this.internalFilter.filterTX(obj);
            if (loader == ClassLoader.getSystemClassLoader() || loader == null)
            {
                DynamicClassLoaderMessage message = new DynamicClassLoaderMessage(Node.getInstance().getNodeID(),
                        (NetChannelLocation) ClassManager.in.getLocation(), bytes);
                byte[] wrappedData = this.internalFilter.filterTX(message);
                return wrappedData;
            }
            DynamicClassLoader dcl = (DynamicClassLoader)loader;
            DynamicClassLoaderMessage message = new DynamicClassLoaderMessage(dcl.originatingNode,
                    (NetChannelLocation) ClassManager.in.getLocation(), bytes);
            byte[] wrappedData = this.internalFilter.filterTX(message);
            return wrappedData;
        }

    }
}
