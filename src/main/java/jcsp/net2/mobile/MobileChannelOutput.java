
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import jcsp.net2.JCSPNetworkException;
import jcsp.net2.NetChannel;
import jcsp.net2.NetChannelLocation;
import jcsp.net2.NetChannelOutput;
import jcsp.net2.NetLocation;
import jcsp.net2.NetworkPoisonException;
import jcsp.net2.NetworkMessageFilter.FilterTx;

/**
 * @author Kevin
 */
public final class MobileChannelOutput
    implements NetChannelOutput, Serializable
{
    private NetChannelLocation msgBoxLocation;

    private transient NetChannelOutput actualOut;

    public MobileChannelOutput(NetChannelLocation loc)
    {
        this.msgBoxLocation = loc;
        this.actualOut = NetChannel.one2net(loc);
    }

    public MobileChannelOutput(NetChannelLocation loc, FilterTx encoder)
    {
        this.msgBoxLocation = loc;
        this.actualOut = NetChannel.one2net(loc, encoder);
    }

    public void write(Object object)
    {
        this.actualOut.write(object);
    }

    public void destroy()
    {
        this.actualOut.destroy();
    }

    public NetLocation getLocation()
    {
        return this.actualOut.getLocation();
    }

    public void poison(int strength)
    {
        this.actualOut.poison(strength);
    }

    public void asyncWrite(Object obj)
        throws JCSPNetworkException, NetworkPoisonException
    {
        this.actualOut.asyncWrite(obj);
    }

    public void setEncoder(FilterTx encoder)
    {
        this.actualOut.setEncoder(encoder);
    }

    private void writeObject(ObjectOutputStream output)
        throws IOException
    {
        output.writeObject(this.msgBoxLocation);
        this.actualOut.destroy();
    }

    private void readObject(ObjectInputStream input)
        throws IOException, ClassNotFoundException
    {
        this.msgBoxLocation = (NetChannelLocation)input.readObject();
        this.actualOut = NetChannel.one2net(this.msgBoxLocation);
    }

}
