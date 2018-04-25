
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

import java.awt.Graphics;
import java.awt.Component;

/**
 * <I>Active</I> components may delegate their <TT>paint</TT> and <TT>update</TT>
 * methods to objects implementing this interface.
 * <H2>Description</H2>
 * Objects implementing <TT>Paintable</TT> may be passed to an <I>Active</I>
 * component (such as {@link ActiveCanvas}).  The <I>Active</I> component will
 * then {@link #register <TT>register</TT>} with the <TT>Paintable</TT> object
 * and delegate to it its <TT>paint</TT> and <TT>update</TT> methods.
 * <P>
 * The <TT>Paintable</TT> object may either be passed <I>statically</I>
 * (via the component's {@link ActiveCanvas#setPaintable <TT>setPaintable</TT>}
 * method, before the component starts running) or <I>dynamically</I>
 * (via the component's
 * {@link ActiveCanvas#setGraphicsChannels <TT>toGraphics/fromGraphics</TT>}
 * channels).
 * <P>
 * <I>Note: these operations are currently supported only for</I>
 * {@link ActiveCanvas <TT>ActiveCanvas</TT>}<I> components.</I>
 * <P>
 * A <TT>CSProcess</TT> may choose to implement <TT>Paintable</TT> itself and take
 * responsibility for its own painting/updating.  However, this would break the JCSP
 * design pattern that the thread(s) of control within a running process have exclusive
 * access to the process state (since painting/updating is actually done by the
 * Java <I>event thread</I>).  It is, therefore,  better to delegate this task to
 * a different (and passive) object such as a {@link DisplayList}.
 *
 * @see ActiveCanvas
 * @see DisplayList
 *
 * @author P.H. Welch
 */

public interface Paintable
{
   /**
    * Register the <TT>Component</TT> that will delegate its <TT>paint</TT> and
    * <TT>update</TT> methods here.
    *
    * @param c the Component that will do the delegating.
    */
   public void register(final Component c);
   
   /**
    * This is the call-back delegated here by the registered <TT>Component</TT>.
    * It will normally be the JVM <I>event thread</I> that is making this call.
    *
    * @param g the graphics context for the painting.
    */
   public void paint(final Graphics g);
   
   /**
    * This is the call-back delegated here by the registered <TT>Component</TT>.
    * It will normally be the JVM <I>event thread</I> that is making this call.
    *
    * @param g the graphics context for the painting.
    */
   public void update(final Graphics g);
}