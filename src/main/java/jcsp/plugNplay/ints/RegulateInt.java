

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
 * This process controls the rate of flow of traffic from its input to output channels.
 * <H2>Process Diagram</H2>
 * <PRE>
 *     in    _______________________________
 *   --->---|                               | out
 *    reset | RegulateInt (initialInterval) |-->--
 *   --->---|_______________________________|
 * </PRE>
 * <H2>Description</H2>
 * <tt>RegulateInt</tt> produces a constant rate of output flow, regardless of
 * the rate of its input.
 * At the end of each time period, it outputs the last object input during that period.
 * If nothing comes in, the previous output will be repeated
 * (note: this is defined to be zero if nothing has ever arrived).
 * If the input flow is greater than the required output flow, data will be discarded.
 * <P>
 * The interval (in msecs) defining the output flow rate is given by a constructor argument.
 * This can be changed at any time by sending a new interval (as a <tt>Long</tt>)
 * down its <tt>reset</tt> channel.
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in</TH>
 *     <TD>int</TD>
 *     <TD>
 *       The input stream.
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH>reset</TH>
 *     <TD>java.lang.Long</TD>
 *     <TD>
 *       This resets the time interval between outputs (milliseconds).
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out</TH>
 *     <TD>int</TD>
 *     <TD>
 *       This carries <i>samples</i> from the input data, sampled at the
 *       defined rate.
 *     </TD>
 *   </TR>
 * </TABLE>
 * <H2>Implementation</H2>
 * See <a href="../../lang/Alternative.html#STFR">here</a> (for the implementation
 * of {@link jcsp.plugNplay.Regulate} &ndash; which is the same apart from regulating <tt>Object</tt>,
 * rather than <tt>int</tt>, traffic).
 *
 * @see jcsp.plugNplay.ints.FixedDelayInt
 * @see RegularInt
 *
 * @author P.H. Welch
 */

public class RegulateInt implements CSProcess {

  private final AltingChannelInputInt in;
  private final AltingChannelInput reset;
  private final ChannelOutputInt out;
  private final long initialInterval;

  /**
    * Construct the process.
    * 
    * @param in the input channel
    * @param out the output channel
    * @param initialInterval the initial interval between outputs (in milliseconds)
    * @param reset send a <tt>Long</tt> down this to change the interval between outputs (in milliseconds)
    */
  public RegulateInt (final AltingChannelInputInt in, final AltingChannelInput reset,
                   final ChannelOutputInt out, final long initialInterval) {
    this.in = in;
    this.reset = reset;
    this.out = out;
    this.initialInterval = initialInterval;
  }

  /**
    * The main body of this process.
    */
  public void run () {

    final CSTimer tim = new CSTimer ();

    final Guard[] guards = {reset, tim, in};              // prioritised order
    final int RESET = 0;                                  // index into guards
    final int TIM = 1;                                    // index into guards
    final int IN = 2;                                     // index into guards

    final Alternative alt = new Alternative (guards);

    int x = 0;                                            // holding object

    long interval = initialInterval;

    long timeout = tim.read () + interval;
    tim.setAlarm (timeout);

    while (true) {
      switch (alt.priSelect ()) {
        case RESET:
          interval = ((Long) reset.read ()).longValue ();
          timeout = tim.read ();                          // fall through
        case TIM:
          out.write (x);
          timeout += interval;
          tim.setAlarm (timeout);
        break;
        case IN:
          x = in.read ();
        break;
      }
    }

  }

}
