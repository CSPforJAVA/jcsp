
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
 * Plugs together a network of low-level <I>stateless</I> components
 * to generate the sequence of natural numbers.
 *
 * <H2>Process Diagram</H2>
 * <H3>External View</H3>
 * <!-- INCORRECT DIAGRAM: <p><img src="doc-files\Numbers1.gif"></p> -->
 * <PRE>
 *         ___________  
 *        |           | out
 *        |  Numbers  |-->----
 *        |___________|
 * </PRE>
 * <H3>Internal View</H3>
 * <!-- <p><img src="doc-files\Numbers2.gif"></p> -->
 * <PRE>
 *         ___________________________________________
 *        |  ____________             ________        |
 *        | |            |           |        |       | out
 *        | | {@link Prefix Prefix (0)} |----->-----| {@link Delta2 Delta2} |---------->--
 *        | |____________|           |________|       |
 *        |     |                        |            |
 *        |     |       ___________      |            |
 *        |     |      |           |     |            |
 *        |     +---<--| {@link Successor Successor} |--<--+            |
 *        |            |___________|                  |
 *        |                                   Numbers |
 *        |___________________________________________|
 * </PRE>
 * <H2>Description</H2>
 * The <TT>Numbers</TT> process generates the sequence of Natural Numbers.
 * <P>
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out</TH>
 *     <TD>java.lang.Integer</TD>
 *     <TD>
 *       The output will always be of type Integer.
 *     </TD>
 *   </TR>
 * </TABLE>
 *
 * @author P.H. Welch and P.D. Austin
 */
public class Numbers implements CSProcess
{
   /** The output Channel */
   private ChannelOutput out;
   
   /**
    * Construct a new Numbers process with the output Channel out.
    *
    * @param out the output channel
    */
   public Numbers(ChannelOutput out)
   {
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      final One2OneChannel a = Channel.one2one();
      final One2OneChannel b = Channel.one2one();
      final One2OneChannel c = Channel.one2one();
      
      new Parallel(new CSProcess[] 
                  {
                     new Delta2(a.in(), b.out(), out),
                     new Successor(b.in(), c.out()),
                     new Prefix(new Integer(0), c.in(), a.out())
                  }).run();
   }
}
