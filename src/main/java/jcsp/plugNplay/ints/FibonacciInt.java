
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
 * This generates the <I>Fibonacci</I> sequence on its output channel.
 *
 * <H2>CSProcess Diagram</H2>
 * <H3>External View</H3>
 * <!-- <p><IMG SRC="doc-files/FibonacciInt1.gif"></p> -->
 * <PRE>
 *         ______________  
 *        |              | out
 *        | FibonacciInt |------>
 *        |______________|
 * </PRE>
 * <H3>Internal View</H3>
 * <!-- INCORRECT DIAGRAM: <p><IMG SRC="doc-files/FibonacciInt2.gif"></p> -->
 * <PRE>
 *         _______________________________________
 *        |                                       |
 *        |  _______________       ___________    |
 *        | |               |     |           |   | out
 *        | | {@link PrefixInt PrefixInt (0)} |-->--| {@link Delta2Int Delta2Int} |------>--
 *        | |_______________|     |___________|   |
 *        |        |                    |         |
 *        |        ^                    v         |
 *        |  ______|________       _____|____     |
 *        | |               |     |          |    |
 *        | | {@link PrefixInt PrefixInt (1)} |--<--| {@link PairsInt PairsInt} |    |
 *        | |_______________|     |__________|    |
 *        |                                       |
 *        |                          FibonacciInt |
 *        |_______________________________________|
 * </PRE>
 * <P>
 * <H2>Description</H2>
 * <TT>FibonacciInt</TT> generates the sequence of <I>Fibonacci</I>
 * numbers on its output channel.
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out</TH>
 *     <TD>int</TD>
 *     <TD>
 *       All channels in this package carry integers.
 *     </TD>
 *   </TR>
 * </TABLE>
 *
 * @author P.H. Welch and P.D. Austin
 */

public final class FibonacciInt implements CSProcess
{
   /** The output Channel */
   private final ChannelOutputInt out;
   
   /**
    * Construct a new FibonacciInt process with the output Channel out.
    *
    * @param out the output channel
    */
   public FibonacciInt(final ChannelOutputInt out)
   {
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      final One2OneChannelInt a = Channel.one2oneInt();
      final One2OneChannelInt b = Channel.one2oneInt();
      final One2OneChannelInt c = Channel.one2oneInt();
      final One2OneChannelInt d = Channel.one2oneInt();
      
      new Parallel(new CSProcess[] 
                  {
                     new PrefixInt(1, c.in(), d.out()),
                     new PrefixInt(0, d.in(), a.out()),
                     new Delta2Int(a.in(), b.out(), out),
                     new PairsInt(b.in(), c.out())
                  }).run();
   }
}
