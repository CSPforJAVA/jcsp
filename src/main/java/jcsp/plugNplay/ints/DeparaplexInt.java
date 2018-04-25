
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

package jcsp.plugNplay.ints;

import jcsp.lang.*;

/**
 * This demultiplexes data from its input channel to its output channel array.
 *
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/DeparaplexInt1.gif"></p>
 * <H2>Description</H2>
 * <TT>DeparaplexInt</TT> is a process to convert the single stream of
 * <TT>int[]</TT> packets sent from a {@link ParaplexInt} process on the other
 * end of its <TT>in</TT> channel back to separate streams (its <TT>out</TT>
 * channels).  It assumes that {@link ParaplexInt} operates on the same
 * size array of channels as its <TT>out</TT> array.  It conforms to
 * contract required by {@link ParaplexInt} for a process receiving its packets.
 * <P>
 * In each cycle, it inputs one packet and outputs its contents <I>in parallel</I>
 * to each of its output channels.
 * The parallel output means that the process will wait until each item is accepted
 * by every channel -- in whatever order is demanded by its environment.
 * The ordering of the channels in the <TT>out</TT> array, therefore, makes
 * no difference to the functionality of this process.
 *
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in</TH>
 *     <TD>int[]</TD>
 *     <TD>
 *       A packet carrying the <I>paraplexed</I> data.
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out[]</TH>
 *     <TD>int</TD>
 *     <TD>
 *       Most channels in this package carry integers.
 *     </TD>
 *   </TR>
 * </TABLE>
 *
 * <H2>Example</H2>
 * <PRE>
 * import jcsp.lang.*;
 * import jcsp.plugNplay.ints.*;
 * 
 * public class DeparaplexIntExample {
 * 
 *   public static void main (String[] args) {
 * 
 *     final One2OneChannelInt[] a = Channel.one2oneIntArray (3);
 *     final One2OneChannel b = Channel.one2one ();
 *     final One2OneChannelInt[] c = Channel.one2oneIntArray (3);
 *     final One2OneChannel d = Channel.one2one ();
 * 
 *     new Parallel (
 *       new CSProcess[] {
 *         new NumbersInt (a[0].out ()),
 *         new SquaresInt (a[1].out ()),
 *         new FibonacciInt (a[2].out ()),
 *         new ParaplexInt (Channel.getInputArray (a), b.out ()),
 *         new DeparaplexInt (b.in (), Channel.getOutputArray (c)),
 *         new ParaplexInt (Channel.getInputArray (c), d.out ()),
 *         new CSProcess () {
 *           public void run () {
 *             System.out.println ("\n\t\tNumbers\t\tSquares\t\tFibonacci\n");
 *             while (true) {
 *               int[] data = (int[]) d.in ().read ();
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
 * @see ParaplexInt
 * @see MultiplexInt
 * @see jcsp.plugNplay.ints.DemultiplexInt
 *
 * @author P.H. Welch
 */

public final class DeparaplexInt implements CSProcess
{
   /** The input Channel */
   private final ChannelInput in;
   
   /** The output Channels */
   private final ChannelOutputInt[] out;
   
   /**
    * Construct a new DeparaplexInt process with the input Channel in and the output
    * Channels out. The ordering of the Channels in the out array make
    * no difference to the functionality of this process.
    *
    * @param in the input channel
    * @param out the output Channels
    */
   public DeparaplexInt(final ChannelInput in, final ChannelOutputInt[] out)
   {
      this.in = in;
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      final ProcessWriteInt[] outputProcess = new ProcessWriteInt[out.length];
      for (int i = 0; i < out.length; i++)
         outputProcess[i] = new ProcessWriteInt(out[i]);
      Parallel parOutput = new Parallel(outputProcess);
      
      while (true)
      {
         int[] data = (int[]) in.read();
         for (int i = 0; i < data.length; i++)
            outputProcess[i].value = data[i];
         parOutput.run();
      }
   }
}
