
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

package jcsp.net;

import jcsp.lang.*;

/**
 * <p>
 * An interface that should be implemented by
 * <CODE>ChannelOutput</CODE> objects which are used for transmitting
 * over the network.
 * </p>
 * <p>
 * As well as usefully combining the <CODE>Networked</CODE> and
 * <CODE>ChannelOutput</CODE> interfaces, this interface adds a
 * <CODE>recreate()</CODE> that requests the implementing class should
 * reinitialize itself.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface NetChannelOutput extends ChannelOutput, Networked
{
   /**
    * <p>
    * Requests that the instance of the implementing class should
    * reinitialize itself.
    * </p>
    */
   public void recreate();
   
   /**
    * <p>
    * Requests that the instance of the implementing class should
    * reinitialize itself with a new location.
    * </p>
    *
    * @param newLoc the new location.
    */
   public void recreate(NetChannelLocation newLoc);
   
   /**
    * <p>
    * Destroys the channel writer end and frees all the
    * underlying JCSP.NET resources.
    * </p>
    */
   public void destroyWriter();
   
   /**
    * <p>
    * Returns the factory class used for constructing this channel
    * end object.
    * </p>
    *
    * @return the <code>Class</code> of the
    */
   public Class getFactoryClass();
   
}