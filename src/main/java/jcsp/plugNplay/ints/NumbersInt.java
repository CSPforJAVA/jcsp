
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
 * Plugs together a network of low-level <I>stateless</I> components
 * to generate the sequence of natural numbers.
 *
 * <H2>Process Diagram</H2>
 * <H3>External View</H3>
 * <!-- INCORRECT DIAGRAM: <p><IMG SRC="doc-files/NumbersInt1.gif"></p> -->
 * <PRE>
 *         ______________  
 *        |              | out
 *        |  NumbersInt  |-->----
 *        |______________|
 * </PRE>
 * <H3>Internal View</H3>
 * <!-- <p><IMG SRC="doc-files/NumbersInt2.gif"></p> -->
 * <PRE>
 *         ___________________________________________
 *        |  _______________             ___________  |
 *        | |               |           |           | | out
 *        | | {@link PrefixInt PrefixInt (0)} |----->-----| {@link Delta2Int Delta2Int} |-->--
 *        | |_______________|           |___________| |
 *        |     |                              |      |
 *        |     |          ______________      |      |
 *        |     |         |              |     |      |
 *        |     +----<----| {@link SuccessorInt SuccessorInt} |--<--+      |
 *        |               |______________|            |
 *        |                                NumbersInt |
 *        |___________________________________________|
 * </PRE>
 * <P>
 * <H2>Description</H2>
 * The <TT>NumbersInt</TT> process generates the sequence of Natural numbers.
 * <P>
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

public class NumbersInt implements CSProcess
{
   /** The output Channel */
   private final ChannelOutputInt out;
   
   /**
    * Construct a new NumbersInt process with the output Channel out.
    *
    * @param out the output channel
    */
   public NumbersInt(final ChannelOutputInt out)
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
      
      new Parallel(new CSProcess[] 
                  {
                     new Delta2Int(a.in(), b.out(), out),
                     new SuccessorInt(b.in(), c.out()),
                     new PrefixInt(0, c.in(), a.out())
                  }).run();
   }
}
