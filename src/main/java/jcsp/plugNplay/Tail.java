
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
 * The output stream is the tail of its input stream.
 * <H2>Process Diagram</H2>
 * <p><img src="doc-files/Tail1.gif"></p>
 * <H2>Description</H2>
 * The first Object (i.e. <i>head</i>) of its input stream is not forwarded.
 * The rest (i.e. <i>tail</i>) is copied through unchanged.
 * <P>
 * Two inputs are needed before any output
 * is produced but, thereafter, one output is produced for each input.
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
 *       The out Channel will send data of the same type
 *       as that sent down the in Channel.
 *     </TD>
 *   </TR>
 * </TABLE>
 * <P>
 * <H2>Implementation Note</H2>
 * The implementation uses an {@link Identity} process for the copy loop:
 * <PRE>
 *   public void run () {
 *     in.read ();                        // accept, but discard, the first item
 *     new Identity (in, out).run ();     // copy the rest of the stream
 *   }
 * </PRE>
 *
 * @author P.H. Welch and P.D. Austin
 */

public final class Tail implements CSProcess
{
   /** The input Channel */
   private ChannelInput in;
   
   /** The output Channel */
   private ChannelOutput out;
   
   /**
    * Construct a new Tail process with the input Channel in and the
    * output Channel out.
    *
    * @param in the input Channel
    * @param out the output Channel
    */
   public Tail(ChannelInput in, ChannelOutput out)
   {
      this.in = in;
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      in.read();
      new Identity(in, out).run();
   }
}
