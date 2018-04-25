
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
 * <I>Black holes</I> anything sent to it.
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/BlackHoleInt1.gif"></p>
 * <H2>Description</H2>
 * <TT>BlackHoleInt</TT> is a process that accepts everything sent to it.
 * This class can be used to ignore the output from a process while ensuring
 * that the data is always read from the channel.
 * <P>
 * <I>Note: this functionality is (more efficiently) provided by
 * a {@link BlackHoleChannelInt}.</I>
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
 * </TABLE>
 *
 * @author P.H. Welch and P.D. Austin
 */

public final class BlackHoleInt implements CSProcess
{
   /** The input Channel */
   private final ChannelInputInt in;
   
   /**
    * Construct a new BlackHoleInt process with the input Channel in.
    *
    * @param in the input channel
    */
   public BlackHoleInt(final ChannelInputInt in)
   {
      this.in = in;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      while (true)
         in.read();
   }
}
