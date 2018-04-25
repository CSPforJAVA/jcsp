
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

package jcsp.awt;

import java.awt.*;
import java.util.Vector;

import jcsp.lang.*;

/**
 * {@link ScrollPane <TT>java.awt.ScrollPane</TT>}
 * with a channel interface.
 * <H2>Process Diagram</H2>
 * <p><img src="doc-files/ActiveScrollPane1.gif"></p>
 * <H2>Description</H2>
 * <TT>ActiveScrollPane</TT> is a process extension of <TT>java.awt.ScrollPane</TT>
 * with channels for run-time configuration and event notification.  The event channels
 * should be connected to one or more application-specific server processes (instead
 * of registering a passive object as a <I>Listener</I> to this component).
 * <P>
 * All channels are optional.  The <TT>configure</TT> channel is settable from a constructor.
 * Event channels can be added to notify the occurrence of any type of <TT>Event</TT>
 * the component generates (by calling the appropriate
 * <TT>add</TT><I>XXX</I><TT>EventChannel</TT> method <I>before</I> the process is run).
 * Messages can be sent down the <TT>configure</TT> channel at any time to configure
 * the component.  See the <A HREF="#Protocols">table below</A> for details.
 * <P>
 * All channels are managed by independent internal handler processes.  It is, therefore,
 * safe for a serial application process both to service an event channel and configure
 * the component -- no deadlock can occur.
 * <P>
 * <I>IMPORTANT: it is essential that event channels from this process are
 * always serviced -- otherwise the Java Event Thread will be blocked and the GUI
 * will stop responding.  A simple way to guarantee this is to use channels
 * configured with overwriting buffers.
 * For example:</I>
 * <PRE>
 *   final One2OneChannel myMouseEvent = Channel.one2one (new OverWriteOldestBuffer (n));
 * 
 *   final ActiveScrollPane myScrollPane = new ActiveScrollPane ();
 *   myScrollPane.addMouseEventChannel (myMouseEvent.out ());
 * </PRE>
 * <I>This will ensure that the Java Event Thread will never be blocked.
 * Slow or inattentive readers may miss rapidly generated events, but
 * the </I><TT>n</TT><I> most recent events will always be available.</I>
 * </P>
 * <H2><A NAME="Protocols">Channel Protocols</A></H2>
 * <CENTER>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH ROWSPAN="1">configure</TH>
 *     <TD>ActiveScrollPane.Configure</TD>
 *     <TD>Invoke the user-defined <TT>Configure.configure</TT> method on the scrollPane.</TD>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>containerEvent</TH>
 *     <TD>ContainerEvent</TD>
 *     <TD>See the {@link #addContainerEventChannel
 *         <TT>addContainerEventChannel</TT>} method.</TD>
 *   </TR>
 *   <TR>
 *     <TH>componentEvent</TH>
 *     <TD>ComponentEvent</TD>
 *     <TD>See the {@link #addComponentEventChannel
 *         <TT>addComponentEventChannel</TT>} method.</TD>
 *   </TR>
 *   <TR>
 *     <TH>focusEvent</TH>
 *     <TD>FocusEvent</TD>
 *     <TD>See the {@link #addFocusEventChannel
 *         <TT>addFocusEventChannel</TT>} method.</TD>
 *   </TR>
 *   <TR>
 *     <TH>keyEvent</TH>
 *     <TD>KeyEvent</TD>
 *     <TD>See the {@link #addKeyEventChannel
 *         <TT>addKeyEventChannel</TT>} method.</TD>
 *   </TR>
 *   <TR>
 *     <TH>mouseEvent</TH>
 *     <TD>MouseEvent</TD>
 *     <TD>See the {@link #addMouseEventChannel
 *         <TT>addMouseEventChannel</TT>} method.</TD>
 *   </TR>
 *   <TR>
 *     <TH>mouseMotionEvent</TH>
 *     <TD>MouseEvent</TD>
 *     <TD>See the {@link #addMouseMotionEventChannel
 *         <TT>addMouseMotionEventChannel</TT>} method.</TD>
 *   </TR>
 * </TABLE>
 * </CENTER>
 * <H2>Example</H2>
 * <PRE>
 * import java.awt.*;
 * import java.awt.event.*;
 * import jcsp.util.*;
 * import jcsp.lang.*;
 * import jcsp.awt.*;
 * 
 * public class ActiveScrollPaneExample {
 * 
 *   public static void main (String argv[]) {
 * 
 *     final Frame root = new Frame ("ActiveScrollPane Example");
 * 
 *     final One2OneChannel mouseEvent = Channel.one2one (new OverWriteOldestBuffer (10));
 * 
 *     final ActiveScrollPane scrollPane = new ActiveScrollPane ();
 *     scrollPane.addMouseEventChannel (mouseEvent.out ());
 * 
 *     root.add (scrollPane);
 *     root.setSize (400, 400);
 *     root.setVisible (true);
 * 
 *     new Parallel (
 *       new CSProcess[] {
 *         scrollPane,
 *         new CSProcess () {
 *           public void run () {
 *             boolean running = true;
 *             while (running) {
 *               final MouseEvent event = (MouseEvent) mouseEvent.in ().read ();
 *               switch (event.getID ()) {
 *                 case MouseEvent.MOUSE_ENTERED:
 *                   System.out.println ("MOUSE_ENTERED");
 *                 break;
 *                 case MouseEvent.MOUSE_EXITED:
 *                   System.out.println ("MOUSE_EXITED");
 *                 break;
 *                 case MouseEvent.MOUSE_PRESSED:
 *                   System.out.println ("MOUSE_PRESSED");
 *                 break;
 *                 case MouseEvent.MOUSE_RELEASED:
 *                   System.out.println ("MOUSE_RELEASED");
 *                 break;
 *                 case MouseEvent.MOUSE_CLICKED:
 *                   if (event.getClickCount() > 1) {
 *                     System.out.println ("MOUSE_DOUBLE_CLICKED ... goodbye!");
 *                     running = false;
 *                   } else {
 *                     System.out.println ("MOUSE_CLICKED ... *double* click to quit!");
 *                   }
 *                 break;
 *               }
 *             }
 *             root.setVisible (false);
 *             System.exit (0);
 *           }
 *         }
 *       }
 *     ).run ();
 *   }
 * 
 * }
 * </PRE>
 *
 * @see ScrollPane
 * @see java.awt.event.ContainerEvent
 * @see java.awt.event.ComponentEvent
 * @see java.awt.event.FocusEvent
 * @see java.awt.event.KeyEvent
 * @see java.awt.event.MouseEvent
 * @see jcsp.util.OverWriteOldestBuffer
 *
 * @author P.D. Austin and P.H. Welch
 */

public class ActiveScrollPane extends ScrollPane implements CSProcess
{
   /**
    * The Vector construct containing the handlers.
    */
   private Vector vec = new Vector();
   
   /**
    * The channel from which configuration messages arrive.
    */
   private ChannelInput configure;
   
   /**
    * Constructs a new <TT>ActiveScrollPane</TT> with a display policy of
    * <TT>java.awt.ScrollPane.SCROLLBARS_AS_NEEDED</TT> and without a <TT>configure</TT>
    * channel.
    */
   public ActiveScrollPane()
   {
      this(SCROLLBARS_AS_NEEDED, null);
   }
   
   /**
    * Constructs a new <TT>ActiveScrollPane</TT> without a <TT>configure</TT>
    * channel.
    *
    * @param scrollbarDisplayPolicy - policy for when scrollbars should be shown
    * (<TT>java.awt.ScrollPane.SCROLLBARS_AS_NEEDED</TT>, <TT>SCROLLBARS_ALWAYS</TT>,
    * <TT>SCROLLBARS_NEVER</TT>).
    */
   public ActiveScrollPane(int scrollbarDisplayPolicy)
   {
      this(scrollbarDisplayPolicy, null);
   }
   
   /**
    * Constructs a new <TT>ActiveScrollPane</TT>.
    *
    * @param scrollbarDisplayPolicy - policy for when scrollbars should be shown
    * (<TT>java.awt.ScrollPane.SCROLLBARS_AS_NEEDED</TT>, <TT>SCROLLBARS_ALWAYS</TT>,
    * <TT>SCROLLBARS_NEVER</TT>).
    * @param configure the channel for configuration events
    * -- can be null if no configuration is required.
    */
   public ActiveScrollPane(int scrollbarDisplayPolicy, ChannelInput configure)
   {
      super(scrollbarDisplayPolicy);
      this.configure = configure;
   }
   
   /**
    * Sets the configuration channel for this <TT>ActiveScrollPane</TT>.
    * This method overwrites any configuration channel set in the constructor.
    *
    * @param configure the channel for configuration events
    * -- can be null if no configuration is required.
    */
   public void setConfigureChannel(ChannelInput configure)
   {
      this.configure = configure;
   }
   
   /**
    * Add a new channel to this component that will be used to notify that
    * a <TT>ContainerEvent</TT> has occurred. <I>This should be used
    * instead of registering a ContainerListener with the component.</I> It is
    * possible to add more than one Channel by calling this method multiple times
    * If the channel passed is <TT>null</TT>, no action will be taken.
    * <P>
    * <I>NOTE: This method must be called before this process is run.</I>
    *
    * @param containerEvent the channel down which to send ContainerEvents.
    */
   public void addContainerEventChannel(ChannelOutput containerEvent)
   {
      if (containerEvent != null)
      {
         ContainerEventHandler handler = new ContainerEventHandler(containerEvent);
         addContainerListener(handler);
         vec.addElement(handler);
      }
   }
   
   /**
    * Add a new channel to this component that will be used to notify that
    * a <TT>ComponentEvent</TT> has occurred. <I>This should be used
    * instead of registering a ComponentListener with the component.</I> It is
    * possible to add more than one Channel by calling this method multiple times
    * If the channel passed is <TT>null</TT>, no action will be taken.
    * <P>
    * <I>NOTE: This method must be called before this process is run.</I>
    *
    * @param componentEvent the channel down which to send ComponentEvents.
    */
   public void addComponentEventChannel(ChannelOutput componentEvent)
   {
      if (componentEvent != null)
      {
         ComponentEventHandler handler = new ComponentEventHandler(componentEvent);
         addComponentListener(handler);
         vec.addElement(handler);
      }
   }
   
   /**
    * Add a new channel to this component that will be used to notify that
    * a <TT>FocusEvent</TT> has occurred. <I>This should be used
    * instead of registering a FocusListener with the component.</I> It is
    * possible to add more than one Channel by calling this method multiple times
    * If the channel passed is <TT>null</TT>, no action will be taken.
    * <P>
    * <I>NOTE: This method must be called before this process is run.</I>
    *
    * @param focusEvent the channel down which to send FocusEvents.
    */
   public void addFocusEventChannel(ChannelOutput focusEvent)
   {
      if (focusEvent != null)
      {
         FocusEventHandler handler = new FocusEventHandler(focusEvent);
         addFocusListener(handler);
         vec.addElement(handler);
      }
   }
   
   /**
    * Add a new channel to this component that will be used to notify that
    * a <TT>KeyEvent</TT> has occurred. <I>This should be used
    * instead of registering a KeyListener with the component.</I> It is
    * possible to add more than one Channel by calling this method multiple times
    * If the channel passed is <TT>null</TT>, no action will be taken.
    * <P>
    * <I>NOTE: This method must be called before this process is run.</I>
    *
    * @param keyEvent the channel down which to send KeyEvents.
    */
   public void addKeyEventChannel(ChannelOutput keyEvent)
   {
      if (keyEvent != null)
      {
         KeyEventHandler handler = new KeyEventHandler(keyEvent);
         addKeyListener(handler);
         vec.addElement(handler);
      }
   }
   
   /**
    * Add a new channel to this component that will be used to notify that
    * a <TT>MouseEvent</TT> has occurred. <I>This should be used
    * instead of registering a MouseListener with the component.</I> It is
    * possible to add more than one Channel by calling this method multiple times
    * If the channel passed is <TT>null</TT>, no action will be taken.
    * <P>
    * <I>NOTE: This method must be called before this process is run.</I>
    *
    * @param mouseEvent the channel down which to send MouseEvents.
    */
   public void addMouseEventChannel(ChannelOutput mouseEvent)
   {
      if (mouseEvent != null)
      {
         MouseEventHandler handler = new MouseEventHandler(mouseEvent);
         addMouseListener(handler);
         vec.addElement(handler);
      }
   }
   
   /**
    * Add a new channel to this component that will be used to notify that
    * a <TT>MouseMotionEvent</TT> has occurred. <I>This should be used
    * instead of registering a MouseMotionListener with the component.</I> It is
    * possible to add more than one Channel by calling this method multiple times
    * If the channel passed is <TT>null</TT>, no action will be taken.
    * <P>
    * <I>NOTE: This method must be called before this process is run.</I>
    *
    * @param mouseMotionEvent the channel down which to send MouseMotionEvents.
    */
   public void addMouseMotionEventChannel(ChannelOutput mouseMotionEvent)
   {
      if (mouseMotionEvent != null)
      {
         MouseMotionEventHandler handler = new MouseMotionEventHandler(mouseMotionEvent);
         addMouseMotionListener(handler);
         vec.addElement(handler);
      }
   }
   
   /**
    * This enables general configuration of this component.  Any object implementing
    * this interface and sent down the <TT>configure</TT> channel to this component will have its
    * <TT>configure</TT> method invoked on this component.
    * <P>
    * For an example, see {@link ActiveApplet.Configure}.
    */
   static public interface Configure
   {
      /**
       * @param scrollPane the ScrollPane being configured.
       */
      public void configure(final ScrollPane scrollPane);
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      if (configure != null)
      {
         while (true)
         {
            Object message = configure.read();
            if (message instanceof Configure)
               ((Configure) message).configure(this);
         }
      }
   }
}
