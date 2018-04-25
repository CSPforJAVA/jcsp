
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
 * to its array of output channels.
 *
 * <H2>Process Diagram</H2>
 * <p><img src="doc-files/Delta1.gif"></p>
 * <H2>Description</H2>
 * The Delta class is a process which has an infinite loop that waits
 * for Objects of any type to be sent down the in Channel. The process then
 * writes the reference to the Object in parallel down each of the Channels
 * in the out array.
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
 *     <TH>out[]</TH>
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
public final class Delta implements CSProcess
{
   /** The input Channel */
   private ChannelInput  in;
   
   /** The output Channels */
   private ChannelOutput[] out;
   
   /**
    * Construct a new Delta process with the input Channel in and the output
    * Channels out. The ordering of the Channels in the out array make
    * no difference to the functionality of this process.
    *
    * @param in the input channel
    * @param out the output Channels
    */
   public Delta(ChannelInput in, ChannelOutput[] out)
   {
      this.in   = in;
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      try {
         ProcessWrite[] procs = new ProcessWrite[out.length];
         for (int i = 0; i < out.length; i++)
            procs[i] = new ProcessWrite(out[i]);
         Parallel par = new Parallel(procs);
      
         while (true)
         {
            Object value = in.read();
            for (int i = 0; i < out.length; i++)
               procs[i].value = value;
            par.run();
         }
      } catch (PoisonException p) {
         // <i>don't know which channel was posioned ... so, poison them all!</i>
         int strength = p.getStrength ();   // <i>use same strength of poison</i>
         in.poison (strength);
         for  (int i = 0; i < out.length; i++) {
            out[i].poison (strength);
         }
      }
   }

}
