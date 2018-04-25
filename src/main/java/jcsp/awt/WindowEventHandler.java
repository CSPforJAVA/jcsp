
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

import jcsp.lang.*;
import java.awt.event.*;

/**
 * @author P.D. Austin and P.H. Welch
 */
class WindowEventHandler implements WindowListener
{
   /**
    * The Channel action event notifications are sent down.
    */
   protected ChannelOutput event;
   
   /**
    * constructs a new WindowEventHandler with the specified output Channel.
    *
    * @param event The Channel to send the event notification down
    */
   public WindowEventHandler(ChannelOutput event)
   {
      this.event  = event;
   }
   
   /**
    * Invoked when the Component the event handler is listening to has the window
    * opened. Notifies the event process that a WindowEvent has
    * occurred by sending the WindowEvent Object. Some notifications will be
    * lost so there are no guarantees that all events generated will be
    * processed.
    *
    * @param e The parameters associated with this event
    */
   public void windowOpened(WindowEvent e)
   {
      event.write(e);
   }
   
   /**
    * Invoked when the Component the event handler is listening to has the window
    * start to close. Notifies the event process that a WindowEvent has
    * occurred by sending the WindowEvent Object. Some notifications will be
    * lost so there are no guarantees that all events generated will be
    * processed.
    *
    * @param e The parameters associated with this event
    */
   public void windowClosing(WindowEvent e)
   {
      event.write(e);
   }
   
   /**
    * Invoked when the Component the event handler is listening to has the window
    * closed. Notifies the event process that a WindowEvent has
    * occurred by sending the WindowEvent Object. Some notifications will be
    * lost so there are no guarantees that all events generated will be
    * processed.
    *
    * @param e The parameters associated with this event
    */
   public void windowClosed(WindowEvent e)
   {
      event.write(e);
   }
   
   /**
    * Invoked when the Component the event handler is listening to has the window
    * iconified. Notifies the event process that a WindowEvent has
    * occurred by sending the WindowEvent Object. Some notifications will be
    * lost so there are no guarantees that all events generated will be
    * processed.
    *
    * @param e The parameters associated with this event
    */
   public void windowIconified(WindowEvent e)
   {
      event.write(e);
   }
   
   /**
    * Invoked when the Component the event handler is listening to has the window
    * deiconified. Notifies the event process that a WindowEvent has
    * occurred by sending the WindowEvent Object. Some notifications will be
    * lost so there are no guarantees that all events generated will be
    * processed.
    *
    * @param e The parameters associated with this event
    */
   public void windowDeiconified(WindowEvent e)
   {
      event.write(e);
   }
   
   /**
    * Invoked when the Component the event handler is listening to has the window
    * activated. Notifies the event process that a WindowEvent has
    * occurred by sending the WindowEvent Object. Some notifications will be
    * lost so there are no guarantees that all events generated will be
    * processed.
    *
    * @param e The parameters associated with this event
    */
   public void windowActivated(WindowEvent e)
   {
      event.write(e);
   }
   
   /**
    * Invoked when the Component the event handler is listening to has the window
    * deactivated. Notifies the event process that a WindowEvent has
    * occurred by sending the WindowEvent Object. Some notifications will be
    * lost so there are no guarantees that all events generated will be
    * processed.
    *
    * @param e The parameters associated with this event
    */
   public void windowDeactivated(WindowEvent e)
   {
      event.write(e);
   }
}