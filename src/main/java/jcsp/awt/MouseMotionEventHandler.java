
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
class MouseMotionEventHandler implements MouseMotionListener
{
   /**
    * The Channel action event notifications are sent down.
    */
   private ChannelOutput event;
   
   /**
    * constructs a new MouseMotionEventHandler with the specified output Channel.
    *
    * @param event The Channel to send the event notification down
    */
   public MouseMotionEventHandler(ChannelOutput event)
   {
      this.event  = event;
   }
   
   /**
    * Invoked when the Component the event handler is listening to has a mouse
    * dragged. Notifies the event process that a MouseEvent has
    * occurred by sending the MouseEvent Object. Some notifications will be
    * lost so there are no guarantees that all events generated will be
    * processed.
    *
    * @param e The parameters associated with this event
    */
   public void mouseDragged(MouseEvent e)
   {
      event.write(e);
   }
   
   /**
    * Invoked when the Component the event handler is listening to has a mouse
    * moved. Notifies the event process that a MouseEvent has
    * occurred by sending the MouseEvent Object. Some notifications will be
    * lost so there are no guarantees that all events generated will be
    * processed.
    *
    * @param e The parameters associated with this event
    */
   public void mouseMoved(MouseEvent e)
   {
      event.write(e);
   }
}