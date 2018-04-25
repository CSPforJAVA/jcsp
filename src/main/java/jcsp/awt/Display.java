
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

/**
 * Objects implementing this interface provide graphics services to a {@link jcsp.lang.CSProcess}.
 * <H2>Description</H2>
 * <TT>Display</TT> is an interface to a passive object providing graphics
 * services on behalf of a {@link jcsp.lang.CSProcess}.  Such an object provides
 * an <I>occam3</I>-like <TT>CALL</TT> channel between the application
 * <TT>CSProcess</TT> and the active graphics component (such as
 * {@link ActiveCanvas <TT>ActiveCanvas</TT>}) on which it wishes to draw.
 *
 * @see jcsp.awt.DisplayList
 *
 * @author P.H. Welch
 */

public interface Display
{
  /**
   * Sets the array of <TT>GraphicsCommand</TT>s to be interpreted.  The commands will
   * be interpreted in ascending order of index.
   * <P>
   * 
   * @param c the array of GraphicsCommands to be interpreted.
   */
  public void set(final GraphicsCommand[] c);

  /**
   * Sets the <TT>GraphicsCommand</TT> to be interpreted.
   * <P>
   * 
   * @param c the array of GraphicsCommands to be interpreted.
   */
  public void set(final GraphicsCommand c);

  /**
   * Extends the array of <TT>GraphicsCommand</TT>s to be interpreted.
   * <P>
   * 
   * @param c the extra GraphicsCommands to be interpreted.
   * @return the start index of the extension.
   */
  public int extend(final GraphicsCommand[] c);

  /**
   * Extends the array of <TT>GraphicsCommand</TT>s to be executed by one command.
   * <P>
   * 
   * @param c the extra GraphicsCommand to be interpreted.
   * @return the start index of the extension.
   */
  public int extend(final GraphicsCommand c);

  /**
   * Changes the array of <TT>GraphicsCommand</TT>s to be interpreted by replacing elements
   * <TT>i</TT> onwards with the new ones.  There must be at least <TT>(i + c.length)</TT>
   * elements in the original array -- else this method will not change anything and will
   * return false.
   * <P>
   * 
   * @param c the new GraphicsCommands to be interpreted.
   * @param i the start index for the replacement.
   * @return true if and only if the changes are successfully made.
   */
  public boolean change(final GraphicsCommand[] c, final int i);

  /**
   * Changes the array of <TT>GraphicsCommand</TT>s to be executed by replacing element
   * <TT>i</TT> with the new one.  There must be at least <TT>(i + 1)</TT>
   * elements in the original array -- else this method will not change anything and will
   * return false.
   * <P>
   * 
   * @param c the new GraphicsCommand to be interpreted.
   * @param i the index for the replacement.
   * @return true if and only if the changes are successfully made.
   */
  public boolean change(final GraphicsCommand c, final int i);

  /**
   * Returns a copy of the array of <TT>GraphicsCommand</TT>s currently held.
   * <P>
   * 
   * @return a copy of the array of <TT>GraphicsCommand</TT>s currently held.
   */
  public GraphicsCommand[] get();

}
