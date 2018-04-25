
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
 * Generates the <TT>Integer</TT> stream <TT>1*1, 2*2, 3*3, etc</TT> by a somewhat unusual route.
 *
 * <H2>Process Diagram</H2>
 * <H3>External View</H3>
 * <p><img src="doc-files/Squares1.gif"></p>
 * <H3>Internal View</H3>
 * <p><img src="doc-files/Squares2.gif"></p>
 * <H2>Description</H2>
 * The Squares class is a process which generates a sequence of squares of
 * Natural numbers that are output down the out Channel.
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
public class Squares implements CSProcess
{
   /** The output Channel */
   private ChannelOutput out;
   
   /**
    * Construct a new Squares process with the output Channel out.
    *
    * @param out the output channel
    */
   public Squares(ChannelOutput out)
   {
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      One2OneChannel a = Channel.one2one();
      One2OneChannel b = Channel.one2one();
      
      new Parallel(new CSProcess[] 
                  {
                     new Numbers(a.out()),
                     new Integrate(a.in(), b.out()),
                     new Pairs(b.in(), out)
                  }).run();
   }
}
