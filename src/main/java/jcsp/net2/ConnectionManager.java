
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

import java.util.Hashtable;

final class ConnectionManager
{
    private static int index = 50;

    private final Hashtable connections = new Hashtable();

    private static ConnectionManager instance = new ConnectionManager();

    private ConnectionManager()
    {

    }

    static ConnectionManager getInstance()
    {
        return instance;
    }

    synchronized void create(ConnectionData data)
    {
        Integer objIndex = new Integer(index);
        while (this.connections.get(objIndex) != null)
        {
            objIndex = new Integer(++index);
        }

        data.vconnn = index;

        this.connections.put(objIndex, data);

        index++;
    }

    synchronized void create(int idx, ConnectionData data)
        throws IllegalArgumentException
    {
        Integer objIndex = new Integer(idx);
        if (this.connections.get(objIndex) != null)
        {
            throw new IllegalArgumentException("Connection of given number already exists");
        }

        data.vconnn = idx;

        this.connections.put(objIndex, data);

        if (idx == ConnectionManager.index)
        {
            index++;
        }
    }

    ConnectionData getConnection(int idx)
    {
        Integer objIndex = new Integer(idx);
        return (ConnectionData)this.connections.get(objIndex);
    }

    void removeConnection(ConnectionData data)
    {
        Integer objIndex = new Integer(data.vconnn);
        this.connections.remove(objIndex);
    }
}
