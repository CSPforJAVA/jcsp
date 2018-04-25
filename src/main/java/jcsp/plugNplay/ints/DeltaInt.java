
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
 * This process broadcasts integers arriving on its input channel <I>in parallel</I>
 * to its array of output channels.
 *
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/DeltaInt1.gif"></p>
 * <H2>Description</H2>
 * <TT>Delta2Int</TT> is a process that broadcasts (<I>in parallel</I>) on its
 * array of output channels everything that arrives on its input channel.
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
 *     <TH>out[]</TH>
 *     <TD>int</TD>
 *     <TD>
 *       The output Channels will carry a broadcast of whatever
 *       integers are sent down the in Channel.
 *     </TD>
 *   </TR>
 * </TABLE>
 *
 * @author P.H. Welch and P.D. Austin
 */

public final class DeltaInt implements CSProcess
{
   /** The input Channel */
   private final ChannelInputInt  in;
   
   /** The output Channels */
   private final ChannelOutputInt[] out;
   
   /**
    * Construct a new DeltaInt process with the input Channel in and the output
    * Channels out. The ordering of the Channels in the out array make
    * no difference to the functionality of this process.
    *
    * @param in the input channel
    * @param out the output Channels
    */
   public DeltaInt(final ChannelInputInt in, final ChannelOutputInt[] out)
   {
      this.in = in;
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      final ProcessWriteInt[] procs = new ProcessWriteInt[out.length];
      for (int i = 0; i < out.length; i++)
         procs[i] = new ProcessWriteInt(out[i]);
      Parallel par = new Parallel(procs);
      
      while (true)
      {
         final int value = in.read();
         for (int i = 0; i < out.length; i++)
            procs[i].value = value;
         par.run();
      }
   }
}
