
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
 * Reads one <TT>int</TT> from its input channel.
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/ProcessReadInt1.gif"></p>
 * <H2>Description</H2>
 * <TT>ProcessReadInt</TT> is a process that performs a single read
 * from its <TT>in</TT> channel and then terminates.  It stores
 * the read <TT>int</TT> in the public <TT>value</TT> field of
 * this process (which is safe to examine <I>after</I> the process
 * has terminated and <I>before</I> it is next run).
 * <P>
 * <TT>ProcessReadInt</TT> declaration, construction and use should normally
 * be localised within a single method -- so we feel no embarassment about
 * its public field.  Its only (envisaged) purpose is as described in
 * the example below.
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in</TH>
 *     <TD>int</TD>
 *     <TD>
 *       All channels in this package carry integers.
 *     </TD>
 *   </TR>
 * </TABLE>
 * <H2>Example</H2>
 * <TT>ProcessReadInt</TT> is designed to simplify <I>reading in parallel</I>
 * from channels.  Make as many instances as there
 * are channels, binding each instance to a different channel,
 * together with a {@link Parallel} object in which to run them:
 * <PRE>
 *   ChannelInputInt in0, in1;
 *   .
 *   .
 *   .
 *   ProcessReadInt read0 = new ProcessReadInt (in0);
 *   ProcessReadInt read1 = new ProcessReadInt (in1);
 *   CSProcess parRead01 = new Parallel (new CSProcess[] {in0, in1});
 * </PRE>
 * The above is best done <I>once</I>, before any looping over the
 * parallel read commences.  A parallel read can now be performed
 * at any time (and any number of times) by executing:
 * <PRE>
 *     parRead01.run ();
 * </PRE>
 * This terminates when, and only when, both reads have completed --
 * the events may occur in <I>any</I> order.  The values read may then
 * be found in <TT>read0.value</TT> and <TT>read1.value</TT>, where they
 * may be safely accessed up until the time that <TT>parRead01</TT> is run again.
 *
 * @see Parallel
 * @see jcsp.plugNplay.ProcessRead
 * @see jcsp.plugNplay.ProcessWrite
 * @see jcsp.plugNplay.ints.ProcessWriteInt
 *
 * @author P.H. Welch and P.D. Austin
 */

public class ProcessReadInt implements CSProcess
{
   /** The <TT>int</TT> read from the channel */
   public int value;
   
   /** The channel from which to read */
   private ChannelInputInt in;
   
   /**
    * Construct a new <TT>ProcessReadInt</TT>.
    *
    * @param in the channel from which to read
    */
   public ProcessReadInt(ChannelInputInt in)
   {
      this.in = in;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      value = in.read();
   }
}
