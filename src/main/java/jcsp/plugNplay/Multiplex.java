
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
 * <I>Fair</I> multiplexes its input Object stream array into one output stream
 * (carrying source channel and data pairs).
 * <H2>Process Diagram</H2>
 * <p><img src="doc-files/Multiplex1.gif"></p>
 * <H2>Description</H2>
 * <TT>Multiplex</TT> is a process to convert multiple streams of
 * objects to a single stream in such a way that it can be
 * {@link Demultiplex <i>de-multiplexed</i>} later.
 * The <I>protocol</I> on the outgoing multiplexed stream consists of
 * an <TT>Integer</TT>, that represents the channel identity of the
 * multiplexed data, followed by the multiplexed data.
 * <P>
 * The ordering of the channels in the <TT>in</TT> array makes
 * no difference to the functionality of this process -- the multiplexing
 * services all channels fairly.
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in[]</TH>
 *     <TD>java.lang.Object</TD>
 *     <TD>
 *       The input streams.
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out</TH>
 *     <TD>java.lang.Integer, java.lang.Object</TD>
 *     <TD>
 *       The channel index followed by the multiplexed data.
 *     </TD>
 *   </TR>
 * </TABLE>
 * <P>
 * <H2>Example</H2>
 * The following example shows how to use <TT>MultiplexInt</TT> in a small program.
 * <PRE>
 * import jcsp.lang.*;
 * import jcsp.plugNplay.*;
 * 
 * public class MultiplexExample {
 * 
 *   public static void main (String[] argv) {
 * 
 *     final One2OneChannel[] a = Channel.one2oneArray (3);
 *     final One2OneChannel b = Channel.one2one ();
 * 
 *     new Parallel (
 *       new CSProcess[] {
 *         new Numbers (a[0].out ()),
 *         new Fibonacci (a[1].out ()),
 *         new Squares (a[2].out ()),
 *         new Multiplex (Channel.getInputArray (a), b.out ()),
 *         new CSProcess () {
 *           public void run () {
 *             String[] key = {"Numbers ",
 *                             "            Fibonacci ",
 *                             "                          Squares "};
 *             while (true) {
 *               int channel = ((Integer) b.in ().read ()).intValue ();
 *               System.out.print (key[channel]);     // print channel source
 *               int n = ((Integer) b.in ().read ()).intValue ();
 *               System.out.println (n);              // print multiplexed data
 *             }
 *           }
 *         }
 *       }
 *     ).run ();
 * 
 *   }
 * 
 * }
 * </PRE>
 *
 * @see Demultiplex
 * @see Paraplex
 * @see jcsp.plugNplay.Deparaplex
 * @see Plex
 *
 * @author P.H. Welch and P.D. Austin and P.H. Welch
 */

public final class Multiplex implements CSProcess
{
   /** The input channels */
   private final AltingChannelInput[] in;
   
   /** The output channel */
   private final ChannelOutput out;
   
   /**
    * Construct a new Multiplex process with the input Channel in and the output
    * Channels out.  The ordering of the Channels in the in array make
    * no difference to the functionality of this process -- the multiplexing
    * services all channels fairly.
    *
    * @param in the input channels
    * @param out the output channel
    */
   public Multiplex(final AltingChannelInput[] in, final ChannelOutput out)
   {
      this.in = in;
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      final Alternative alt = new Alternative(in);
      while (true)
      {
         int index = alt.fairSelect();
         out.write(new Integer(index));
         out.write(in[index].read());
      }
   }
}
