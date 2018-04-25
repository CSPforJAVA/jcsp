
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
 * Generates an infinite (constant) sequence of <TT>int</TT>s.
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/GenerateInt1.gif"></p>
 * <H2>Description</H2>
 * <TT>GenerateInt</TT> is a process that generates an infinite sequence
 * of the integer, <TT>n</TT>, with which it is configured.
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

public final class GenerateInt implements CSProcess
{
   /** The output Channel */
   private final ChannelOutputInt out;
   
   /** The output number */
   private final int n;
   
   /**
    * Construct a new <TT>GenerateInt</TT> process with the output channel <TT>out</TT>.
    *
    * @param out the output channel
    * @param n the number to generate
    */
   public GenerateInt(final ChannelOutputInt out, final int n)
   {
      this.out = out;
      this.n = n;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      while (true)
         out.write(n);
   }
}
