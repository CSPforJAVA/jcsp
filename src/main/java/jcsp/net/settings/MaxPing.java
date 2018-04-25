
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
 * Used internally within the JCSP network infrastructure to represent a maximum ping rate.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class MaxPing extends Spec implements Req, XMLConfigConstants
{
   MaxPing(int maxPing)
   {
      super(REQ_NAME_MAXPING, true, true);
      this.maxPing = maxPing + 1;
   }
   
   public String getStringValue()
   {
      return "" + maxPing;
   }
   
   public String getComparator()
   {
      return REQ_COMPARATOR_LESS;
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
   
   public Class getType()
   {
      return Integer.TYPE;
   }
   
   public int getValue()
   {
      return maxPing;
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof MaxPing)
      {
         MaxPing other = (MaxPing) o;
         return maxPing == other.maxPing;
      }
      return false;
   }
   
   public int hashCode()
   {
      return maxPing;
   }
   
   private int maxPing;
}