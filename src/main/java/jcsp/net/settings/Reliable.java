
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

package jcsp.net.settings;

/**
 * Used internally within the JCSP network infrastructure to represent a reliable protocol.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Reliable extends Spec implements Req
{
   Reliable(boolean reliable, boolean isReq)
   {
      super(SPEC_NAME_RELIABLE, true, isReq);
      this.reliable = reliable;
   }
   
   public String getStringValue()
   {
      return "" + reliable;
   }
   
   public boolean getValue()
   {
      return reliable;
   }
   
   public Class getType()
   {
      return Boolean.TYPE;
   }
   
   public String getComparator()
   {
      return REQ_COMPARATOR_EQUALS;
   }
   
   public int getIntValue()
   {
      throw new UnsupportedOperationException("Type is boolean");
   }
   
   public double getDoubleValue()
   {
      throw new UnsupportedOperationException("Type is boolean");
   }
   
   public boolean getBooleanValue()
   {
      return getValue();
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof Reliable)
      {
         Reliable other = (Reliable) o;
         return reliable == other.reliable;
      }
      return false;
   }
   
   public int hashCode()
   {
      if(reliable)
         return 1;
      else
         return 0;
   }
   
   private boolean reliable;
}