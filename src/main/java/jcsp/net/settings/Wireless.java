
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
 * Used internally within the JCSP network infrastructure to represent a wireless protocol.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Wireless extends Spec implements Req
{
   Wireless(int wireless, boolean isReq)
   {
      super(SPEC_NAME_WIRELESS, true, isReq);
      if (wireless > 0)
         this.wireless = 1;
      else if (wireless < 0)
         this.wireless = -1;
      else
         this.wireless = 0;
   }
   
   public String getStringValue()
   {
      return "" + wireless;
   }
   
   public int getValue()
   {
      return wireless;
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
      return getValue();
   }
   
   public double getDoubleValue()
   {
      throw new UnsupportedOperationException("Type is int");
   }
   
   public boolean getBooleanValue()
   {
      throw new UnsupportedOperationException("Type is int");
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof Wireless)
      {
         Wireless other = (Wireless) o;
         return wireless == other.wireless;
      }
      return false;
   }
   
   public int hashCode()
   {
      return wireless;
   }
   
   private int wireless;
}