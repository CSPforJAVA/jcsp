
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
 * This generates the <I>Fibonacci</I> sequence on its output channel.
 *
 * <H2>CSProcess Diagram</H2>
 * <H3>External View</H3>
 * <!-- <p><img src="doc-files/Fibonacci1.gif"></p> -->
 * <PRE>
 *         ___________  
 *        |           | out
 *        | Fibonacci |------>
 *        |___________|
 * </PRE>
 * <H3>Internal View</H3>
 * <!-- INCORRECT DIAGRAM: <p><img src="doc-files/Fibonacci2.gif"></p> -->
 * <PRE>
 *         ________________________________
 *        |                                |
 *        |  ____________       ________   |
 *        | |            |     |        |  | out
 *        | | {@link Prefix Prefix (0)} |-->--| {@link Delta2 Delta2} |------>--
 *        | |____________|     |________|  |
 *        |        |               |       |
 *        |        ^               V       |
 *        |  ______|_____       ___|___    |
 *        | |            |     |       |   |
 *        | | {@link Prefix Prefix (1)} |--<--| {@link Pairs Pairs} |   |
 *        | |____________|     |_______|   |
 *        |                                |
 *        |                      Fibonacci |
 *        |________________________________|
 * </PRE>
 * <H2>Description</H2>
 * <TT>FibonacciInt</TT> generates the sequence of <I>Fibonacci</I>
 * Numbers on its output channel.
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
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
public class Fibonacci implements CSProcess
{
   /** The output Channel */
   private ChannelOutput out;
   
   /**
    * Construct a new Fibonacci process with the output Channel out.
    *
    * @param out the output channel
    */
   public Fibonacci(ChannelOutput out)
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
      final One2OneChannel d = Channel.one2one();
      
      new Parallel(new CSProcess[] 
                  {
                     new Prefix(new Integer(1), c.in(), d.out()),
                     new Prefix(new Integer(0), d.in(), a.out()),
                     new Delta2(a.in(), b.out(), out),
                     new Pairs(b.in(), c.out())
                  }).run();
   }
}
