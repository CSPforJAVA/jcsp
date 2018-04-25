
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
 * to its two output channels.
 *
 * <H2>Process Diagram</H2>
 * <!-- INCORRECT DIAGRAM: <p><IMG SRC="doc-files/Delta2Int1.gif"></p> -->
 * <PRE>
 *         ___________  out0 
 *    in  |           |--->---
 *   -->--| Delta2Int | out1
 *        |___________|--->---
 * </PRE>
 * <H2>Description</H2>
 * <TT>Delta2Int</TT> is a process that broadcasts (<I>in parallel</I>) on its two output channels
 * everything that arrives on its input channel.
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
 *     <TH>out0, out1</TH>
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

public final class Delta2Int implements CSProcess
{
   /** The input Channel */
   private final ChannelInputInt  in;
   
   /** The first output Channel */
   private final ChannelOutputInt out0;
   
   /** The second output Channel */
   private final ChannelOutputInt out1;
   
   /**
    * Construct a new Delta2Int process with the input Channel in and the output
    * Channels out0 and out1. The ordering of the Channels out0 and out1 make
    * no difference to the functionality of this process.
    *
    * @param in the input channel
    * @param out0 an output Channel
    * @param out1 an output Channel
    */
   public Delta2Int(final ChannelInputInt in, final ChannelOutputInt out0, final ChannelOutputInt out1)
   {
      this.in   = in;
      this.out0 = out0;
      this.out1 = out1;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      ProcessWriteInt[] parWrite = {new ProcessWriteInt(out0), new ProcessWriteInt(out1)};
      Parallel par = new Parallel(parWrite);
      
      while (true)
      {
         int value = in.read();
         parWrite[0].value = value;
         parWrite[1].value = value;
         par.run();
      }
   }
}
