
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

package jcsp.plugNplay;

import jcsp.lang.*;

/**
 * <I>Parallel</I> multiplexes its input Object stream array on to one output stream.
 * <H2>Process Diagram</H2>
 * <p><img src="doc-files/Paraplex1.gif"></p>
 * <H2>Description</H2>
 * <TT>Paraplex</TT> is a process to convert multiple streams of
 * <TT>Object</TT>s to a single stream.  It assumes data will always be available
 * on all its input streams.  In each cycle, it inputs <I>in parallel</I> one
 * <TT>Object</TT> from each of its inputs, packs them into an array and outputs
 * that array as a single communication.
 * <P>
 * The parallel input means that the process will wait until something arrives
 * from every input channel, accepting items in whatever order they turn up.
 * The ordering of the channels in the <TT>in</TT> array, therefore, makes
 * no difference to the functionality of this process.
 * <P>
 * <B>Caution:</B> the process receiving packets from <TT>Paraplex</TT>
 * must agree to the following contract:
 * <BLOCKQUOTE>
 * <I>Input of an array
 * packet means that previously input arrays must not be looked at any more (neither
 * by itself nor any other processes to which they may have been passed).</I>
 * </BLOCKQUOTE>
 * Supporting the above, there is one more rule:
 * <BLOCKQUOTE>
 * <I>There must be only one process receiving array packets
 * from <TT>Paraplex</TT> (i.e. its output channel must <I>not</I> be connected
 * to a {@link One2AnyChannel} or {@link Any2AnyChannel}).</I>
 * </BLOCKQUOTE>
 * The reason for these obligations is to remove the need for <TT>Paraplex</TT>
 * to generate a <TT>new</TT> array packet for each <I>paraplexed</I> communication
 * -- an array that will normally be discarded by the receiving process after
 * dealing with its contents.
 * Instead of that, <TT>Paraplex</TT> operates a <I>double-buffered</I> protocol,
 * constructing and reusing just two array packets.  It switches between them
 * after every cycle.  In this way, it fills one packet while the process
 * receiving its output consumes the other one.  This is safe so long as that
 * receiving process agrees to the above rules.
 * See the <I>Low Level</I> example in {@link Parallel} for the details
 * of this implementation.
 * <P>
 * <I>Note:</I> the above two constraints should work naturally with most applications.
 * However, by converting the first rule into a protocol where the receiving process
 * explicitly returns the packet (through an <I>acknowledgment</I> channel)
 * when it has finished using it and before inputting the next one, the second
 * rule could be dropped.  This is trivial to do by piping the output from
 * <TT>Paraplex</TT> through a simple cyclic process that inputs a packet,
 * forwards it (down a {@link One2AnyChannel} or
 * {@link Any2AnyChannel}) and waits for the acknowledgment
 * (for which only a {@link One2OneChannel} is needed).
 * <P>
 * Of course, avoiding uncontrolled sharing of the <TT>Object</TT> passing
 * through this process is something that must be done.  But that is not
 * the responsibility of this process and must be arranged between the
 * originator and recipient (or recipients).
 *
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in[]</TH>
 *     <TD>Object</TD>
 *     <TD>
 *       Most channels in this package carry integers.
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out</TH>
 *     <TD>Object[]</TD>
 *     <TD>
 *       A packet carrying the <I>paraplexed</I> data.
 *     </TD>
 *   </TR>
 * </TABLE>
 *
 * <H2>Example</H2>
 * <PRE>
 * import jcsp.lang.*;
 * import jcsp.plugNplay.*;
 * 
 * class ParaplexExample {
 * 
 *   public static void main (String[] args) {
 * 
 *     final One2OneChannel[] a = Channel.one2oneArray (3);
 *     final One2OneChannel b = Channel.one2one ();
 * 
 *     new Parallel (
 *       new CSProcess[] {
 *         new Numbers (a[0].out ()),
 *         new Squares (a[1].out ()),
 *         new Fibonacci (a[2].out ()),
 *         new Paraplex (Channel.getInputArray (a), b.out ()),
 *         new CSProcess () {
 *           public void run () {
 *             ChannelInput in = b.in ();
 *             System.out.println ("\n\t\tNumbers\t\tSquares\t\tFibonacci\n");
 *             while (true) {
 *               Object[] data = (Object[]) in.read ();
 *               for (int i = 0; i < data.length; i++) {
 *                 System.out.print ("\t\t" + data[i]);
 *               }
 *               System.out.println ();
 *             }
 *           }
 *         }
 *       }
 *     ).run ();
 *   }
 * 
 * }
 * </PRE>
 *
 * @see jcsp.plugNplay.Deparaplex
 * @see jcsp.plugNplay.Multiplex
 * @see jcsp.plugNplay.Demultiplex
 *
 * @author P.H. Welch
 */

public final class Paraplex implements CSProcess
{
   /** The input channels */
   private final ChannelInput[] in;
   
   /** The output channel */
   private final ChannelOutput out;
   
   /**
    * Construct a new Paraplex process with the input Channel in and the output
    * Channels out.  The ordering of the Channels in the in array make
    * no difference to the functionality of this process.
    *
    * @param in the input channels
    * @param out the output channel
    */
   public Paraplex(final ChannelInput[] in, final ChannelOutput out)
   {
      this.in = in;
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      final ProcessRead[] inputProcess = new ProcessRead[in.length];
      for (int i = 0; i < in.length; i++)
         inputProcess[i] = new ProcessRead(in[i]);
      Parallel parInput = new Parallel(inputProcess);
   
      Object[][] data = new Object[2][in.length];
      int index = 0;
      while (true)
      {
         parInput.run();
         Object[] buffer = data[index];
         for (int i = 0; i < in.length; i++)
            buffer[i] = inputProcess[i].value;
         out.write(buffer);
         index = 1 - index;
      }
   }
}
