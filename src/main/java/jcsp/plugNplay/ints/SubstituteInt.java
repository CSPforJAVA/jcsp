
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
 * Substitutes a user-configured <I>constant</I> for each integer in the stream
 * flowing through.
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/SubstituteInt1.gif"></p>
 * <H2>Description</H2>
 * <TT>SubstituteInt</TT> is a process that substitutes the (constant) <TT>n</TT>
 * with which it is configured for everything recieved on its <TT>in</TT> channel.
 * So, its output stream has constant values but its rate of flow is triggered by
 * its input.
 * <P>
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
 *
 * @author P.H. Welch and P.D. Austin
 */
public final class SubstituteInt implements CSProcess
{
   /** The int to be sent down the out Channel. */
   private final int n;
   
   /** The input Channel */
   private final ChannelInputInt in;
   
   /** The output Channel */
   private final ChannelOutputInt out;
   
   /**
    * Construct a new SubstituteInt process.
    *
    * @param n the integer to be sent down the out Channel.
    * @param in the input Channel
    * @param out the output Channel
    */
   public SubstituteInt(final ChannelInputInt in, final ChannelOutputInt out, final int n)
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
      while (true)
      {
         in.read();
         out.write(n);
      }
   }
}
