
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

import java.io.*;

/**
 * Prints each <TT>int</TT> from its input channel to a <TT>PrintStream</TT>.
 * <H2>Process Diagram</H2>
 * <p><IMG SRC="doc-files/PrinterInt1.gif"></p>
 * <H2>Description</H2>
 * <TT>PrinterInt</TT> is a process for printing each <TT>int</TT> from its
 * <TT>in</TT> channel to a <TT>PrintStream</TT> object (by default,
 * <TT>System.out</TT>).
 * <P>
 * For convenience, <TT>PrinterInt</TT> may be configured with prefix and postfix
 * strings with which to decorate its output.
 *
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
 *
 * <H2>Example</H2>
 * See the example in {@link MergeInt}.
 *
 * @see Parallel
 *
 * @author P.H. Welch and P.D. Austin
 */

public class PrinterInt implements CSProcess
{
   /** The channel from which to read */
   private ChannelInputInt in;
   
   /** The stream to which to write */
   private PrintStream printStream;
   
   /** The string to write in front of each integer */
   private String prefix;
   
   /** The string to write after each integer */
   private String postfix;
   
   /**
    * Construct a new <TT>PrinterInt</TT> with <TT>System.out</TT> as
    * its <TT>PrintStream</TT> and empty prefix and postfix strings.
    *
    * @param in the channel from which to read
    */
   public PrinterInt(final ChannelInputInt in)
   {
      this(in, System.out, "", "");
   }
   
   /**
    * Construct a new <TT>PrinterInt</TT> with <TT>System.out</TT> as
    * its <TT>PrintStream</TT>.
    *
    * @param in the channel from which to read
    * @param prefix the string to write in front of each integer
    * @param postfix the string to write after each integer
    */
   public PrinterInt(final ChannelInputInt in, final String prefix, final String postfix)
   {
      this(in, System.out, prefix, postfix);
   }
   
   /**
    * Construct a new <TT>PrinterInt</TT> with empty prefix and postfix strings.
    *
    * @param in the channel from which to read
    * @param printStream the stream to which to write
    */
   public PrinterInt(final ChannelInputInt in, final PrintStream printStream)
   {
      this(in, printStream, "", "");
   }
   
   /**
    * Construct a new <TT>PrinterInt</TT>.
    *
    * @param in he channel from which to read
    * @param printStream the stream to which to write
    * @param prefix the string to write in front of each integer
    * @param postfix the string to write after each integer
    */
   public PrinterInt(final ChannelInputInt in, final PrintStream printStream,
           final String prefix, final String postfix)
   {
      this.in = in;
      this.printStream = printStream;
      this.prefix = prefix;
      this.postfix = postfix;
   }
   
   /**
    * The main body of this process.
    */
   public synchronized void run()
   {
      while (true)
      {
         printStream.print(prefix);
         printStream.print(in.read());
         printStream.print(postfix);
      }
   }
}
