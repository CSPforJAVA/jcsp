
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
class MouseEventHandler implements MouseListener
{
   /**
    * The channel down which action event notifications are sent.
    */
   private ChannelOutput event;
   
   /**
    * constructs a new MouseEventHandler with the specified output channel
    *
    * @param event the Channel to which to send the event notification
    */
   public MouseEventHandler(ChannelOutput event)
   {
      this.event  = event;
   }
   
   /**
    * Assumes the event channel is being serviced (eg by an overwriting channel).
    *
    * @param e The parameters associated with this event
    */
   public void mouseClicked(MouseEvent e)
   {
      event.write(e);
   }
   
   /**
    * Assumes the event channel is being serviced (eg by an overwriting channel).
    *
    * @param e The parameters associated with this event
    */
   public void mousePressed(MouseEvent e)
   {
      event.write(e);
   }
   
   /**
    * Assumes the event channel is being serviced (eg by an overwriting channel).
    *
    * @param e The parameters associated with this event
    */
   public void mouseReleased(MouseEvent e)
   {
      event.write(e);
   }
   
   /**
    * Assumes the event channel is being serviced (eg by an overwriting channel).
    *
    * @param e The parameters associated with this event
    */
   public void mouseEntered(MouseEvent e)
   {
      event.write(e);
   }
   
   /**
    * Assumes the event channel is being serviced (eg by an overwriting channel).
    *
    * @param e The parameters associated with this event
    */
   public void mouseExited(MouseEvent e)
   {
      event.write(e);
   }
}