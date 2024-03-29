
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

package jcsp.lang;

class Any2OneImpl<T> implements ChannelInternals<T>, Any2OneChannel<T> {

	private ChannelInternals<T> channel;
	private final Object writeMonitor = new Object();
	
	Any2OneImpl(ChannelInternals<T> _channel) {
		channel = _channel;
	}

	//Begin never used:
	public void endRead() {
		channel.endRead();
	}

	public T read() {
		return channel.read();
	}

	public boolean readerDisable() {
		return channel.readerDisable();
	}

	public boolean readerEnable(Alternative alt) {
		return channel.readerEnable(alt);
	}

	public boolean readerPending() {
		return channel.readerPending();
	}

	public void readerPoison(int strength) {
		channel.readerPoison(strength);

	}

	public T startRead() {
		return channel.startRead();
	}
	//End never used

	public void write(T obj) {
		synchronized (writeMonitor) {
			channel.write(obj);
		}

	}

	public void writerPoison(int strength) {
		synchronized (writeMonitor) {
			channel.writerPoison(strength);
		}

	}

	public AltingChannelInput<T> in() {
                //TODO BUG Immunity wrong for poisonable channels.  Also check o2o, o2a, etc.
		return new AltingChannelInputImpl<T>(channel,0);
	}

	public SharedChannelOutput<T> out() {
                //TODO BUG Immunity wrong for poisonable channels.  Also check o2o, o2a, etc.
		return new SharedChannelOutputImpl<T>(this,0);
	}

}
