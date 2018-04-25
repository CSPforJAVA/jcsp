
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

/**
 * <p>
 * A Class whose instances represent the global domain.
 * There is only ever a need to have one instance of this class
 * per JVM so a static instance is supplied.
 * </p>
 * <p>
 * The <CODE>GlobalID</CODE> object is the parent
 * <CODE>AbstractID</CODE> to all top level <CODE>DomainID</CODE> objects.
 * </p>
 * <p>
 * See <code>{@link AbstractID}</code> for a fully explanation of
 * this class.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public final class GlobalID extends AbstractID implements Serializable
{
   /**
    * <p>
    * Returns <code>null</code> as there is no parent
    * <code>AbstractID</code> of instances of this class.
    * </p>
    * @return <code>null</code>.
    */
   public AbstractID getParentID()
   {
      return null;
   }
   
   /**
    * <p>
    * Compares another object with this <CODE>GlobalID</CODE> object.
    * </p>
    * @param o an object to compare with object.
    * @return <CODE>true</CODE> iff the other object is a <CODE>GlobalID</CODE>.
    */
   public boolean equals(Object o)
   {
      if (o == null || !(o instanceof GlobalID))
         return false;
      //o is an instance of GlobalID, therefore equal
      return true;
   }
   
   /**
    * <p>
    * Returns an <CODE>int</CODE> hash code for this object.
    * </p>
    * @return an <CODE>int</CODE> hash code.
    */
   public int hashCode()
   {
      return ("GlobalID").hashCode();
   }
   
   /**
    * <p>
    * Returns a human readable string representation of a
    * <CODE>GlobalID</CODE>.
    * </p>
    *
    * @return The human readable <CODE>String</CODE> - currently "Global".
    */
   public String toString()
   {
      return "Global";
   }
   
   boolean onSameBranch(AbstractID abstractID)
   {
      if (abstractID != null && abstractID instanceof GlobalID)
         return true;
      return false;
   }
   
   /**
    * <p>
    * A static instance of <CODE>GlobalID</CODE>.
    * Instead of creating <CODE>GlobalID</CODE> objects, it
    * is better to use this instance as only once instance is
    * ever required.
    * </p>
    *
    */
   public static final GlobalID instance = new GlobalID();
}