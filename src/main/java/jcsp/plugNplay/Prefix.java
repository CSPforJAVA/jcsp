
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
 * <I>Prefixes</I> a user-supplied object to the <TT>Object</TT> stream
 * flowing through.
 * <H2>Process Diagram</H2>
 * <p><img src="doc-files/Prefix1.gif"></p>
 * <H2>Description</H2>
 * This is a process which first outputs a given Object and then
 * copies its input stream of Objects to its output stream.
 * <P>
 * One output is gererated before any input but,
 * thereafter, one output is produced for each input.
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
 *       The out Channel sends the the same type of data (in
 *       fact, the <I>same</I> data) as is input.
 *     </TD>
 *   </TR>
 * </TABLE>
 * <P>
 * <H2>Implementation Note</H2>
 * The implementation uses an {@link Identity} process for the copy loop:
 * <PRE>
 *   public void run () {
 *     out.write (o);                     // prefix the given object to the stream
 *     new Identity (in, out).run ();     // copy the stream
 *   }
 * </PRE>
 *
 * @author P.H. Welch
 */
public final class Prefix implements CSProcess
{
   /** The input Channel */
   private ChannelInput in;
   
   /** The output Channel */
   private ChannelOutput out;
   
   /** The initial Object to be sent down the Channel. */
   private Object o;
   
   /**
    * Construct a new Prefix process with the input Channel in and the
    * output Channel out.
    *
    * @param o the initial Object to be sent down the Channel.
    * @param in the input Channel
    * @param out the output Channel
    */
   public Prefix(Object o, ChannelInput in, ChannelOutput out)
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
      out.write(o);
      new Identity(in, out).run();
   }
}
