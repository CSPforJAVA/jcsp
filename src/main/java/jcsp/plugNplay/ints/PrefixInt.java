
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
 * <I>Prefixes</I> a user-supplied integer to the <TT>int</TT> stream
 * flowing through.
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/PrefixInt1.gif"></p>
 * <H2>Description</H2>
 * This is a process which first outputs a given integer and then
 * copies its input stream of integers to its output stream.
 * <P>
 * One output is gererated before any input but,
 * thereafter, one output is produced for each input.
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in</TH>
 *     <TD>int</TD>
 *     <TD>
 *       All channels in this package carry integers.
 *     </TD>
 *   </TR>
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
 * <P>
 * <H2>Implementation Note</H2>
 * The implementation uses an {@link IdentityInt} process for the copy loop:
 * <PRE>
 *   public void run () {
 *     out.write (n);                        // prefix the given integer to the stream
 *     new IdentityInt (in, out).run ();     // copy the stream
 *   }
 * </PRE>
 *
 * @author P.H. Welch and P.D. Austin
 */
public final class PrefixInt implements CSProcess
{
   /** The input Channel */
   private final ChannelInputInt in;
   
   /** The output Channel */
   private final ChannelOutputInt out;
   
   /** The initial int to be sent down the Channel. */
   private final int n;
   
   /**
    * Construct a new PrefixInt process with the input Channel in and the
    * output Channel out.
    *
    * @param n the initial int to be sent down the Channel.
    * @param in the input Channel
    * @param out the output Channel
    */
   public PrefixInt(final int n, final ChannelInputInt in, final ChannelOutputInt out)
   {
      this.in = in;
      this.out = out;
      this.n = n;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      out.write(n);
      new IdentityInt(in, out).run();
   }
}
