
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

package jcsp.net.cns;

import java.io.*;
import jcsp.net.*;

/**
 * This class allows name spaces to be identified around
 * <code>AbstractID</code> objects (currently including
 * {@link ApplicationID},
 * {@link NodeID},
 * {@link DomainID} and
 * {@link GlobalID}
 * ). An <code>AbstractID</code> object can have a single parent
 * <code>AbstractID</code> object. This allows a tree-like hierarchy
 * to be formed which in turn allows a name space hierarchy.
 * </p>
 * <p>
 * For example, two <code>ApplicationID</code> objects, A and B, may each
 * have a parent <code>NodeID</code> Z. The namespaces created around
 * A and B will each be a superset of the namespace of Z. Channels registered
 * in Z's namespace can be resolved in either A's or B's namespace. A channel
 * registered in A's namespace may neither be resolved in Z's namespace nor
 * B's.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public final class NameAccessLevel implements Serializable
{
   /*------------Attributes-----------------------------------------------------*/
   private final AbstractID abstractID;

   /**
    * The <code>NameAccessLevel</code> for the global namespace.
    */
   public static final NameAccessLevel GLOBAL_ACCESS_LEVEL = new NameAccessLevel(GlobalID.instance);
   
   /*------------Constructor----------------------------------------------------*/
   
   /**
    * <p>
    * Constructor which takes an <code>AbstractID</code> to use
    * for identifying the namespace.
    *
    */
   public NameAccessLevel(AbstractID abstractID)
   {
      this.abstractID = abstractID;
   }
   
   /*------------Methods overriden from Object----------------------------------*/
   
   /**
    * Compares an object with this object.
    *
    * @return <code>ture</code> iff the other object is a
    *          <code>NameAccessLevel</code> object which has an
    *          equal underlying <code>AbstractID</code>.
    *
    * @see AbstractID
    */
   public boolean equals(Object o)
   {
      if(o == null || !(o instanceof NameAccessLevel)) 
         return false;
      NameAccessLevel other = (NameAccessLevel) o;
      return abstractID.equals(other.abstractID);
   }
   
   /**
    * Returns a hash code for this object obeying the standard rules
    * for a hash code.
    *
    * @see Object#hashCode()
    */
   public int hashCode()
   {
      return abstractID.hashCode();
   }
   
   /**
    * Returns a string representation of this object.
    *
    * @return a human readable string.
    */
   public String toString()
   {
      return abstractID.toString();
   }
   
   /*------------Package level methods------------------------------------------*/
   
   /**
    * Accessor for the underlying <code>AbstractID</code> object.
    *
    * @return the underlying <code>AbstractID</code>.
    */
   AbstractID getLevelAbstractID()
   {
      return abstractID;
   }
}