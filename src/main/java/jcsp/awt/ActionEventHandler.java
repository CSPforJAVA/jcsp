
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
class ActionEventHandler implements ActionListener
{
    /** The Channel down which ActionEvent commands are sent. */
    private ChannelOutput event;

    /**
     * constructs a new ActionEventHandler with the specified output Channel.
     *
     * @param event The Channel to send the event notification down
     */
    public ActionEventHandler(ChannelOutput event) 
    {
        this.event = event;
    }

    /**
     * Invoked when an action occurs on the component. It assumes the
     * event channel is being serviced (e.g. by an overwriting channel).
     *
     * @param e the parameter associated with this event
     */
    public void actionPerformed(ActionEvent e) 
    {
        event.write(e.getActionCommand());
    }
}
