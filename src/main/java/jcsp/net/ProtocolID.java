
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

import java.io.*;
import java.util.*;

/**
 * <p>
 * Abstract class that identifies a protocol.
 * Provides methods for obtaining the protocol's <CODE>Builder</CODE> to be
 * installed in the LinkFactory, starting a <CODE>LinkServer</CODE> for this
 * protocol and for creating a <CODE>NodeAddressID</CODE> for this protocol
 * from a String.
 * </p>
 * <p>
 * Protocols must not make use of the NodeID so that protocols can be installed before the node ID
 * is determined. The current definition of TCPIPv4 protocol meets this criteria. Such a change
 * will allow the UIFactory or other initialisation details to be obtained from a central server.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */

public abstract class ProtocolID implements Serializable
{
   /**
    * This tests whether another object is equal to this object.
    * Two <CODE>ProtocolID</CODE> objects are equal if their classes are the same.
    * This is a default implementation, it is probably more efficient to
    * override this with an implementation that uses the instanceof operator.
    * @param o an object to compare with this object.
    * @return <CODE>true</CODE> iff the supplied object's class is the same as
    * the class of this object.
    */
   public boolean equals(Object o)
   {
      Class thisClass = this.getClass();
      Class otherClass = o.getClass();
      return thisClass.equals(otherClass);
   }
   
   /**
    * Returns a hash code for this object.
    *
    * @return the hash code for the object.
    */
   public final int hashCode()
   {
      return this.getClass().hashCode();
   }
   
   /**
    * <p>
    * Not currently used.
    * </p>
    * <p>
    * This is envisaged to be used if a protocol that is in use and needs to be
    * removed sometime in the future. This will stop any further use of the
    * protocol.
    * </p>
    *
    * @return	boolean indicating whether protocol is active.
    */
   public abstract boolean isActive();
   
   /**
    * <p>
    * Returns whether the protocol requires user interaction in order for
    * data to be delivered.  There could be a protocol that writes data
    * out to floppy disk or CD-R (see Tanenbaum's example of bandwidth and
    * lorry!).
    * </p>
    *
    * @return boolean	indicating whether user interaction is required.
    */
   public abstract boolean requiresUserInteraction();
   
   /**
    * Sets the position of this protocol in the order of general preference.
    *
    * @param	position	the position of this protocol in the order.
    */
   final void setPosition(int position)
   {
      this.position = position;
   }
   
   /**
    * Gets the position of this protocol in the order of preference.
    *
    * @return	the order of preference as an <code>int</code>.
    */
   public final int getPosition()
   {
      return position;
   }
   
   /**
    * Obtains the Builder for this protocol. Provides a Hashtable that can
    * can contain settings needed to construct the Link.
    *
    * @param	settings	<code>Hashtable</code> containing settings.
    * @return	the <code>Builder</code> for constructing Links.
    */
   protected abstract LinkFactory.Builder getLinkBuilder(Hashtable settings);
   
   /**
    * <p>
    * Constructs, starts and returns a <code>LinkServer</code> on a
    * specified <code>NodeAddressID</code>.
    * </p>
    *
    * @return the constructed <code>LinkServer</code>
    * @param addressID The address for the server to listen on.
    * @throws IllegalArgumentException if the supplied <CODE>NodeAddressID</CODE> is invalid.
    */
   protected abstract LinkServer startLinkServer(NodeAddressID addressID) throws IllegalArgumentException;
   
   /**
    * Constructs a <code>NodeAddressID</code> from a <code>String</code>.
    *
    * @return the constructed <code>NodeAddressID</code>
    * @param stAddressID The address in String form.
    * @param uniqueAddress boolean indicating whether address is unique.
    * @throws IllegalArgumentException if the <CODE>String</CODE> supplied is invalid.
    */
   protected abstract NodeAddressID createAddressID(String stAddressID, boolean uniqueAddress) throws IllegalArgumentException;
   
   /**
    * The position in the order of preference of protocols.
    */
   private int position = 0;
}