
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

    /**
 * This defines the interface for reading from an Object channel.
 * <p>
 * A <i>reading-end</i>, conforming to this interface,
 * is obtained from a channel by invoking its <tt>in()</tt> method.
 * <H2>Description</H2>
 * <TT>ChannelInput</TT> defines the interface for reading from object channels.
 * The interface contains three methods:
 * {@link #read <code>read</code>}, {@link #startRead <code>startRead</code>} and
 * {@link #endRead <code>endRead</code>}.
 * The {@link #read <code>read</code>} and {@link #startRead <code>startRead</code>}
 * methods block until an <TT>Object</TT> has been written
 * to the channel by a process at the other end.  If an <TT>Object</TT> has
 * already been written when this method is called, the method will return
 * without blocking.  Either way, the methods return the <TT>Object</TT>
 * sent down the channel.
 * <P>
 * When a {@link #read <code>read</code>} completes, the matching
 * {@link ChannelOutput#write <code>write</code>} method (invoked by
 * the writing process) also completes.
 * When a {@link #startRead <code>startRead</code>} completes, the matching
 * {@link ChannelOutput#write <code>write</code>} method does not complete
 * until the reader process invokes an {@link #endRead <code>endRead</code>}.
 * Actions performed by the reader in between a {@link #startRead <code>startRead</code>}
 * and {@link #endRead <code>endRead</code>} make up an <i>extended rendezvous</i>.
 * 
 * <P>
 * <TT>ChannelInput</TT> variables are used to hold channels
 * that are going to be used only for <I>input</I> by the declaring process.
 * This is a security matter -- by declaring a <TT>ChannelInput</TT>
 * interface, any attempt to <I>output</I> to the channel will generate
 * a compile-time error.  For example, the following code fragment will
 * not compile:
 *
 * <PRE>
 * void doWrite (ChannelInput c, Object o) {
 *   c.write (o);   // illegal
 * }
 * </PRE>
 *
 * When configuring a <TT>CSProcess</TT> with input channels, they should
 * be declared as <TT>ChannelInput</TT> (or, if we wish to be able to make
 * choices between events, as <TT>AltingChannelInput</TT>)
 * variables.  The actual channel passed,
 * of course, may belong to <I>any</I> channel class that implements
 * <TT>ChannelInput</TT> (or <TT>AltingChannelInput</TT>).
 * <P>
 * The <TT>Object</TT> returned can be cast into the actual
 * class the reader process expects.  If the reader can handle more than one
 * class of <TT>Object</TT> (similar to tagged protocols
 * in <I><B>occam</B></I>), checks should be made before casting.
 * <H2>Examples</H2>
 * <H3>Discard data</H3>
 * <PRE>
 * void doRead (ChannelInput c) {
 *   c.read ();                       // clear the channel
 * }
 * </PRE>
 * <H3>Cast data to expected type</H3>
 * <PRE>
 * void doRead (ChannelInput c) {
 *   Boolean b = (Boolean) c.read();  // will cause a ClassCastException
 *                                    // if read does not return a Boolean
 *   ...  etc.
 * }
 * </PRE>
 * <H3>Cast data after checking type</H3>
 * <PRE>
 * void doRead (ChannelInput c) {
 *   Object o = c.read ();
 *   if (o instanceof Boolean) {
 *     System.out.println ("Boolean: " + (Boolean) o);
 *   }
 *   else if (o instanceof Integer) {
 *     System.out.println ("Integer: " + (Integer) o);
 *   }
 *   else {
 *     System.out.println ("Unexpected Class: " + o);
 *   }
 * }
 * </PRE>
 *
 * @see jcsp.lang.AltingChannelInput
 * @see jcsp.lang.SharedChannelInput
 * @see ChannelOutput
 * @author P.D. Austin and P.H. Welch and N.C.C.Brown
 */

public interface ChannelInput<T> extends Poisonable
{
    /**
     * Read an Object from the channel.
     *
     * @return the object read from the channel
     */
    public T read();
    
    /**
     * Begin an extended rendezvous read from the channel.
     * An extended rendezvous is not completed until the reader
     * has completed its extended action.  This method starts
     * an extended rendezvous.  When a writer to this channel
     * writes, this method returns what was sent immediately.
     * The extended rendezvous continues with reader actions
     * until the reader invokes {@link #endRead <code>endRead</code>}.
     * Only then will the writer be released (from its
     * {@link ChannelOutput#write <code>write</code>} method).
     * The writer is unaware of the extended nature of the communication.
     * </p>
     * <p>
     * <b>The reader process must call {@link #endRead <code>endRead</code>}
     * at some point after this function</b>, otherwise the writer will not
     * be freed and deadlock will probably follow.
     * </p>
     * <p>
     * The reader process may perform any actions between calling 
     * {@link #startRead <code>startRead</code>} and
     * {@link #endRead <code>endRead</code>}, including communications
     * on other channels.  Further communications on this channel, of course,
     * should not be made.
     * </p>
     * <p>
     * An extended rendezvous may be started after the channel's Guard
     * has been selected by an {@link Alternative} (i.e.
     * {@link #startRead <code>startRead</code>} instead of
     * {@link #read <code>read</code>}).
     * 
     * @return The object read from the channel 
     */
    public T startRead();
    
    /**
     * End an extended rendezvous.
     * It must be invoked once (and only once) following
     * a {@link #startRead <code>startRead</code>}.
     */
    public void endRead();
}
