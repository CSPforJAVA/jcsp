
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

package jcsp.plugNplay;

import jcsp.lang.*;

/**
 * This process broadcasts objects arriving on its input channel <I>in parallel</I>
 * to its two output channels.
 *
 * <H2>Process Diagram</H2>
 * <!-- INCORRECT DIAGRAM: <p><img src="doc-files/Delta21.gif"></p> -->
 * <PRE>
 *         ________  out0 
 *    in  |        |--->---
 *   -->--| Delta2 | out1
 *        |________|--->---
 * </PRE>
 * <H2>Description</H2>
 * The Delta2 class is a process which has an infinite loop that waits
 * for Objects of any type to be sent down the in Channel. The process then
 * writes the reference to the Object in parallel down the out0 and out1
 * Channels.
 * <P>
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in</TH>
 *     <TD>java.lang.Object</TD>
 *     <TD>
 *       The in Channel can accept data of any Class.
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out0, out1</TH>
 *     <TD>java.lang.Object</TD>
 *     <TD>
 *       The output Channels will carry a broadcast of whatever
 *       is sent down the in Channel.
 *     </TD>
 *   </TR>
 * </TABLE>
 *
 * @author P.H. Welch and P.D. Austin
 */
public final class Delta2 implements CSProcess
{
   /** The input Channel */
   private ChannelInput  in;
   
   /** The first output Channel */
   private ChannelOutput out0;
   
   /** The second output Channel */
   private ChannelOutput out1;
   
   /**
    * Construct a new Delta2 process with the input Channel in and the output
    * Channels out0 and out1. The ordering of the Channels out0 and out1 make
    * no difference to the functionality of this process.
    *
    * @param in the input channel
    * @param out0 an output Channel
    * @param out1 an output Channel
    */
   public Delta2(ChannelInput in, ChannelOutput out0, ChannelOutput out1)
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
      ProcessWrite[] parWrite = {new ProcessWrite(out0), new ProcessWrite(out1)};
      Parallel par = new Parallel(parWrite);
      while (true)
      {
         Object value = in.read();
         parWrite[0].value = value;
         parWrite[1].value = value;
         par.run();
      }
   }
}
