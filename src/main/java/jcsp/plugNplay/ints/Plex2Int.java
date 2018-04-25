
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
 * <I>Fair</I> multiplexes two integer streams into one.
 * <H2>Process Diagram</H2>
 * <!-- INCORRECT DIAGRAM: <p><IMG SRC="doc-files/Plex2Int1.gif"></p> -->
 * <PRE>
 *    in0  __________
 *   -->--|          | out
 *    in1 | Plex2Int |-->--
 *   -->--|__________|
 * </PRE>
 * <H2>Description</H2>
 * <TT>Plex2Int</TT> is a process whose output stream is a <I>fair</I> multiplexing
 * of its input streams.  It makes no assumptions about the traffic patterns occuring
 * on the input streams and ensures that no input stream will be starved by busy siblings.
 * It guarantees that if an input stream has data pending, no other stream will be
 * serviced <I>twice</I> before that data is serviced.
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in0, in1</TH>
 *     <TD>int</TD>
 *     <TD>
 *       The input streams.
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out</TH>
 *     <TD>int</TD>
 *     <TD>
 *       The multiplexed output stream.
 *     </TD>
 *   </TR>
 * </TABLE>
 * <H2>Example</H2>
 * The following example shows how to use <TT>Plex2Int</TT> in a small program.
 * The program also uses some of the other <TT>plugNplay</TT> processes.
 *
 * <PRE>
 * import jcsp.lang.*;
 * import jcsp.plugNplay.ints.*;
 * 
 * public class Plex2IntExample {
 * 
 *   public static void main (String[] argv) {
 * 
 *     final One2OneChannelInt a = Channel.one2oneInt ();
 *     final One2OneChannelInt b = Channel.one2oneInt ();
 *     final One2OneChannelInt c = Channel.one2oneInt ();
 * 
 *     new Parallel (
 *       new CSProcess[] {
 *         new FibonacciInt (a.out ()),
 *         new SquaresInt (b.out ()),
 *         new Plex2Int (a.in (), b.in (), c.out ()),
 *         new PrinterInt (c.in (), "--> ", "\n")
 *       }
 *     ).run ();
 * 
 *   }
 * 
 * }
 * </PRE>
 * Note: this example does not produce easily understandable output, since
 * the multiplexed stream contains only numbers -- there is no indication
 * of the streams from which they were sourced.  To get that indication,
 * we can either use {@link MultiplexInt} or <I>sign</I> each <TT>int</TT>
 * stream to be multiplexed with {@link SignInt} and multiplex with
 * {@link jcsp.plugNplay.Plex2}.
 * <P>
 * <H2>Implemntation Note</H2>
 * For information, here is the <TT>run</TT> method for this process:
 *
 * <PRE>
 *   public void run () {
 *     AltingChannelInputInt[] input = {in0, in1};      // in0 and in1 are the input channels
 *     Alternative alt = new Alternative (input);
 *     while (true) {
 *       out.write (input[alt.fairSelect ()].read ());  // out is the output channel
 *     }
 *   }
 * </PRE>
 *
 * @see PlexInt
 * @see MultiplexInt
 *
 * @author P.H. Welch
 */

public final class Plex2Int implements CSProcess
{
   /** The first input Channel */
   private final AltingChannelInputInt in0;
   
   /** The second input Channel */
   private final AltingChannelInputInt in1;
   
   /** The output Channel */
   private final ChannelOutputInt out;
   
   /**
    * Construct a new <TT>Plex2Int</TT> process with the input channels
    * <TT>in0</TT> and <TT>in1</TT> and the output channel <TT>out</TT>.
    * The ordering of the input channels makes no difference
    * to the behaviour of this process.
    *
    * @param in0 an input channel
    * @param in1 an input channel
    * @param out the output channel
    */
   public Plex2Int(AltingChannelInputInt in0, AltingChannelInputInt in1, ChannelOutputInt out)
   {
      this.in0 = in0;
      this.in1 = in1;
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      AltingChannelInputInt[] input = {in0, in1};
      Alternative alt = new Alternative(input);
      while (true)
         out.write(input[alt.fairSelect()].read());
   }
}
