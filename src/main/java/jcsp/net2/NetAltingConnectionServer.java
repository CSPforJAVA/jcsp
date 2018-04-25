
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

import jcsp.lang.AltingChannelInput;
import jcsp.lang.AltingConnectionServer;
import jcsp.lang.Any2OneChannel;
import jcsp.lang.Channel;
import jcsp.util.InfiniteBuffer;

public final class NetAltingConnectionServer
    extends AltingConnectionServer
    implements NetConnectionServer
{
    private final AltingChannelInput requestIn;

    private final AltingChannelInput openIn;

    private final NetConnectionLocation location;

    private final NetworkMessageFilter.FilterRx inputFilter;

    private final NetworkMessageFilter.FilterTx outputFilter;

    private final ConnectionData data;

    private final NetworkMessage lastRead = null;

    private final Link linkConnectedTo = null;

    static NetAltingConnectionServer create(int index, NetworkMessageFilter.FilterRx filterRX,
            NetworkMessageFilter.FilterTx filterTX)
        throws IllegalArgumentException
    {
        ConnectionData data = new ConnectionData();
        Any2OneChannel requestChan = Channel.any2one(new InfiniteBuffer());
        Any2OneChannel openChan = Channel.any2one(new InfiniteBuffer());
        data.toConnection = requestChan.out();
        data.openServer = openChan.out();
        data.state = ConnectionDataState.SERVER_STATE_CLOSED;
        ConnectionManager.getInstance().create(index, data);
        return new NetAltingConnectionServer(openChan.in(), requestChan.in(), data, filterRX, filterTX);
    }

    static NetAltingConnectionServer create(NetworkMessageFilter.FilterRx filterRX,
                                            NetworkMessageFilter.FilterTx filterTX)
    {
        ConnectionData data = new ConnectionData();
        Any2OneChannel requestChan = Channel.any2one(new InfiniteBuffer());
        Any2OneChannel openChan = Channel.any2one(new InfiniteBuffer());
        data.toConnection = requestChan.out();
        data.openServer = openChan.out();
        data.state = ConnectionDataState.SERVER_STATE_CLOSED;
        ConnectionManager.getInstance().create(data);
        return new NetAltingConnectionServer(openChan.in(), requestChan.in(), data, filterRX, filterTX);
    }

    private NetAltingConnectionServer(AltingChannelInput openChan, AltingChannelInput requestChan,
                                      ConnectionData connData, NetworkMessageFilter.FilterRx filterRX, NetworkMessageFilter.FilterTx filterTX)
        throws JCSPNetworkException
    {
        super(openChan);
        this.openIn = openChan;
        this.requestIn = requestChan;
        this.data = connData;
        this.inputFilter = filterRX;
        this.outputFilter = filterTX;
        this.location = new NetConnectionLocation(Node.getInstance().getNodeID(), this.data.vconnn);
    }

    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    public NetLocation getLocation()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void reply(Object data, boolean close)
        throws IllegalStateException
    {
        // TODO Auto-generated method stub

    }

    public void reply(Object data)
        throws IllegalStateException
    {
        // TODO Auto-generated method stub

    }

    public void replyAndClose(Object data)
        throws IllegalStateException
    {
        // TODO Auto-generated method stub

    }

    public Object request()
        throws IllegalStateException
    {
        // TODO Auto-generated method stub
        return null;
    }

}
