
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
 * Writes one <TT>int</TT> to its output channel.
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/ProcessWriteInt1.gif"></p>
 * <H2>Description</H2>
 * <TT>ProcessWriteInt</TT> is a process that performs a single write
 * to its <TT>out</TT> channel and then terminates.  The <TT>int</TT>
 * that is written must first be placed in the public <TT>value</TT> field
 * of this process (which is safe to set <I>before</I> and <I>in between</I>
 * process runs).
 * <P>
 * <TT>ProcessWriteInt</TT> declaration, construction and use should normally
 * be localised within a single method -- so we feel no embarassment about
 * its public field.  Its only (envisaged) purpose is as described in
 * the example below.
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out</TH>
 *     <TD>int</TD>
 *     <TD>
 *       All channels in this package carry integers.
 *     </TD>
 *   </TR>
 * </TABLE>
 * <H2>Example</H2>
 * <TT>ProcessWriteInt</TT> is designed to simplify <I>writing in parallel</I>
 * to channels.  Make as many instances as there
 * are channels, binding each instance to a different channel,
 * together with a {@link Parallel} object in which to run them:
 * <PRE>
 *   ChannelOutputInt out0, out1;
 *   .
 *   .
 *   .
 *   ProcessWriteInt write0 = new ProcessWriteInt (out0);
 *   ProcessWriteInt write1 = new ProcessWriteInt (out1);
 *   CSProcess parWrite01 = new Parallel (new CSProcess[] {out0, out1});
 * </PRE>
 * The above is best done <I>once</I>, before any looping over the
 * parallel read commences.  A parallel write can now be performed
 * at any time (and any number of times) by executing:
 * <PRE>
 *     write0.value = ...;   // whatever we want sent down out0
 *     write1.value = ...;   // whatever we want sent down out1
 *     parWrite01.run ();
 * </PRE>
 * This terminates when, and only when, both writes have completed --
 * the events may occur in <I>any</I> order.
 *
 * @see Parallel
 * @see jcsp.plugNplay.ProcessRead
 * @see jcsp.plugNplay.ProcessWrite
 * @see ProcessReadInt
 *
 * @author P.H. Welch and P.D. Austin
 */

public class ProcessWriteInt implements CSProcess
{
   /** The <TT>int</TT> to be written to the channel */
   public int value;
   
   /** The channel to which to write */
   private ChannelOutputInt out;
   
   /**
    * Construct a new <TT>ProcessWriteInt</TT>.
    *
    * @param out the channel to which to write
    */
   public ProcessWriteInt(ChannelOutputInt out)
   {
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      out.write(value);
   }
}
