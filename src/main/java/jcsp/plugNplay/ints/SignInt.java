
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
 * Converts each input <TT>int</TT> to a <TT>String</TT>, prefixing it
 * with a user-defined <TT>sign</TT>.
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/SignInt1.gif"></p>
 * <P>
 * <H2>Description</H2>
 * <TT>Sign</TT> converts each input <TT>int</TT> to a <TT>String</TT>,
 * prefixing it with a user-defined <TT>sign</TT>.
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
 *       Almost all channels in this package carry integers.
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out</TH>
 *     <TD>java.lang.String</TD>
 *     <TD>
 *       The output will be of type String.
 *     </TD>
 *   </TR>
 * </TABLE>
 * <H2>Example</H2>
 * The following example shows how to use <TT>SignInt</TT> in a small program.
 * <PRE>
 * import jcsp.lang.*;
 * import jcsp.plugNplay.ints.*;
 * import jcsp.plugNplay.*;
 * 
 * public class SignIntExample {
 * 
 *   public static void main (String[] argv) {
 * 
 *     final One2OneChannelInt[] a = Channel.one2oneIntArray (3);
 *     final One2OneChannel[] b = Channel.one2oneArray (3);
 *     final One2OneChannel c = Channel.one2one ();
 * 
 *     new Parallel (
 *       new CSProcess[] {
 *         new NumbersInt (a[0].out ()),
 *         new FibonacciInt (a[1].out ()),
 *         new SquaresInt (a[2].out ()),
 *         new SignInt ("Numbers ", a[0].in (), b[0].out ()),
 *         new SignInt ("            Fibonacci ", a[1].in (), b[1].out ()),
 *         new SignInt ("                          Squares ", a[2].in (), b[2].out ()),
 *         new Plex (Channel.getInputArray (b), c.out ()),
 *         new Printer (c.in (), "", "\n")
 *       }
 *     ).run ();
 * 
 *   }
 * 
 * }
 * </PRE>
 *
 * @see jcsp.plugNplay.Sign
 *
 * @author P.H. Welch and P.D. Austin
 */

public final class SignInt implements CSProcess
{
   /** The user-defined sign to attach to each item */
   private final String sign;
   
   /** The input Channel */
   private final ChannelInputInt in;
   
   /** The output Channel */
   private final ChannelOutput out;
   
   /**
    * Construct a new Sign process with the input Channel in and the
    * output Channel out.
    *
    * @param sign the user-defined signature to attach to each item.
    * @param in the input Channel.
    * @param out the output Channel.
    */
   public SignInt(final String sign, final ChannelInputInt in, final ChannelOutput out)
   {
      this.sign = sign;
      this.in = in;
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      while (true)
         out.write(sign + in.read());
   }
}
