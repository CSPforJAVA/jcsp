
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
 * Substitutes a user-configured <I>Object</I> for each Object in the stream
 * flowing through.
 * <H2>Process Diagram</H2>
 * <p><img src="doc-files/Substitute1.gif"></p>
 * <H2>Description</H2>
 * <TT>Substitute</TT> is a process that substitutes the (Object) <TT>o</TT>
 * with which it is configured for everything recieved on its <TT>in</TT> channel.
 * So, its output stream repeats the same Object but its rate of flow is triggered by
 * its input.
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
 *     <TH>out</TH>
 *     <TD>java.lang.Object</TD>
 *     <TD>
 *       The object to be sent down the Channel.
 *     </TD>
 *   </TR>
 * </TABLE>
 *
 * @author P.H. Welch and P.D. Austin
 */
public class Substitute implements CSProcess
{
   /** The Object to be sent down the out Channel. */
   private Object o;
   
   /** The input Channel */
   private ChannelInput in;
   
   /** The output Channel */
   private ChannelOutput out;
   
   /**
    * Construct a new Substitute process.
    *
    * @param o the Object to be sent down the out Channel.
    * @param in the input Channel
    * @param out the output Channel
    */
   public Substitute(ChannelInput in, ChannelOutput out, Object o)
   {
      this.in = in;
      this.out = out;
      this.o = o;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      while (true)
      {
         in.read();
         out.write(o);
      }
   }
}
