
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
 * Used internally within the JCSP network infrastructure to represent miscellaneous requirements.
 *
 *
 * @author Quickstone Technologies Limited
 */
class OtherReq extends OtherSpec implements Req
{
   
   OtherReq(String name, int value, String comparator)
   {
      super(name, value, true);
      this.comparator = comparator;
   }
   
   OtherReq(String name, double value, String comparator)
   {
      super(name, value, true);
      this.comparator = comparator;
   }
   
   OtherReq(String name, boolean value, String comparator)
   {
      super(name, value, true);
      this.comparator = comparator;
   }
   
   OtherReq(String name, String value, String comparator)
   {
      super(name, value, true);
      this.comparator = comparator;
   }
   
   public String getComparator()
   {
      return comparator;
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof OtherReq)
      {
         OtherReq other = (OtherReq) o;
         if (getName().equals(other.getName()) && getType().equals(other.getType()) && comparator.equals(other.comparator))
         {
            if (getType().equals(Integer.TYPE))
               return getIntValue() == other.getIntValue();
            else if (getType().equals(Double.TYPE))
               return getDoubleValue() == other.getDoubleValue();
            else if (getType().equals(Boolean.TYPE))
               return getBooleanValue() == other.getBooleanValue();
            else if (getType().equals(String.class))
               return getStringValue() == other.getStringValue();
         }
      }
      return false;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<Req name=\"" + getName() + "\" comparator=\"" + comparator + "\" value=\"" + getStringValue() + "\" />");
      return sb.toString();
   }
   
   private String comparator;
}