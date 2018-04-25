
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
 * Generates the integer stream <TT>1*1, 2*2, 3*3, etc</TT> by a somewhat unusual route.
 *
 * <H2>Process Diagram</H2>
 * <H3>External View</H3>
 * <p><IMG SRC="doc-files/SquaresInt1.gif"></p>
 * <H3>Internal View</H3>
 * <p><IMG SRC="doc-files/SquaresInt2.gif"></p>
 * <H2>Description</H2>
 * <TT>SquaresInt</TT> generates the sequence of squares of the
 * Natural numbers (starting from 1).
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
public final class SquaresInt implements CSProcess
{
   /** The output Channel */
   private final ChannelOutputInt out;
   
   /**
    * Construct a new SquaresInt process with the output Channel out.
    *
    * @param out the output channel
    */
   public SquaresInt(final ChannelOutputInt out)
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
      
      new Parallel(new CSProcess[] 
                  {
                     new NumbersInt(a.out()),
                     new IntegrateInt(a.in(), b.out()),
                     new PairsInt(b.in(), out)
                  }).run();
   }
}
