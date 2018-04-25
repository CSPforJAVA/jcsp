
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
 * This holds on to data from its input channel for a fixed delay before passing
 * it on to its output channel.
 *
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/FixedDelayInt1.gif"></p>
 * <H2>Description</H2>
 * <TT>FixedDelayInt</TT> is a process that delays passing on input to its output
 * by a constant delay.
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
 * @see RegulateInt
 * @see RegularInt
 * 
 * @author P.H. Welch and P.D. Austin
 */

public final class FixedDelayInt implements CSProcess
{
   /** The input Channel */
   private final ChannelInputInt in;
   
   /** The output Channel */
   private final ChannelOutputInt out;
   
   /**
    * The time the process is to wait in milliseconds between receiving a
    * message and then sending it.
    */
   private final long delayTime;
   
   /**
    * Construct a new FixedDelayInt process with the input Channel in and the
    * output Channel out.
    *
    * @param delayTime the time the process is to wait in milliseconds
    *   between receiving a message and then sending it (a negative
    *   <TT>delayTime</TT> implies no waiting).
    * @param in the input Channel
    * @param out the output Channel
    */
   public FixedDelayInt(final long delayTime, final ChannelInputInt in, final ChannelOutputInt out)
   {
      this.in = in;
      this.out = out;
      this.delayTime = delayTime;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      final CSTimer tim = new CSTimer();
      while  (true)
      {
         final int i = in.read();
         tim.sleep(delayTime);
         out.write(i);
      }
   }
}
