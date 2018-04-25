
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

import java.awt.event.*;
import jcsp.lang.*;

/**
 * @author P.D. Austin and P.H. Welch
 */
class ContainerEventHandler implements ContainerListener
{
   /**
    * The Channel action event notifications are sent down.
    */
   private ChannelOutput event;
   
   /**
    * constructs a new ContainerEventHandler with the specified event output
    * Channel.
    *
    * @param event The Channel to send the event notification down
    */
   public ContainerEventHandler(ChannelOutput event)
   {
      this.event  = event;
   }
   
   /**
    * Invoked when the Container the event handler is listening to has a new
    * Component added to it. Notifies the event process that a
    * ContainerEvent has occurred by sending the ContainerEvent Object.
    * Some notifications will be lost so there are no guarantees that all
    * events generated will be processed.
    *
    * @param e The parameters associated with this event
    */
   public void componentAdded(ContainerEvent e)
   {
      event.write(e);
   }
   
   /**
    * Invoked when the Container the event handler is listening to has a new
    * Component removed from it. Notifies the event process that a
    * ContainerEvent has occurred by sending the ContainerEvent Object.
    * Some notifications will be lost so there are no guarantees that all
    * events generated will be processed.
    *
    * @param e The parameters associated with this event
    */
   public void componentRemoved(ContainerEvent e)
   {
      event.write(e);
   }
}