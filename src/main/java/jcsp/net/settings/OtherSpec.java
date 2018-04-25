
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
 * Used internally within the JCSP network infrastructure to represent misellaneous specifications.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class OtherSpec extends Spec
{
   OtherSpec(String name, int value, boolean isReq)
   {
      super(name, false, isReq);
      intValue = value;
      strValue = "" + value;
      type = Integer.TYPE;
   }
   
   OtherSpec(String name, double value, boolean isReq)
   {
      super(name, false, isReq);
      dblValue = value;
      strValue = "" + value;
      type = Double.TYPE;
   }
   
   OtherSpec(String name, boolean value, boolean isReq)
   {
      super(name, false, isReq);
      booValue = value;
      strValue = "" + value;
      type = Boolean.TYPE;
   }
   
   OtherSpec(String name, String value, boolean isReq)
   {
      super(name, false, isReq);
      strValue = value;
      type = String.class;
   }
   
   public Class getType()
   {
      return type;
   }
   
   public int getIntValue()
   {
      return intValue;
   }
   
   public double getDoubleValue()
   {
      return dblValue;
   }
   
   public boolean getBooleanValue()
   {
      return booValue;
   }
   
   public String getStringValue()
   {
      return strValue;
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof OtherSpec)
      {
         OtherSpec other = (OtherSpec) o;
         if(getName().equals(other.getName()) && type.equals(other.type))
         {
            if(type.equals(Integer.TYPE))
               return intValue == other.intValue;
            else if(type.equals(Double.TYPE))
               return dblValue == other.dblValue;
            else if(type.equals(Boolean.TYPE))
               return booValue == other.booValue;
            else if(type.equals(String.class))
               return strValue == other.strValue;
         }
      }
      return false;
   }
   
   private Class type;
   private int intValue = -1;
   private double dblValue = -1.0;
   private boolean booValue = false;
   private String strValue = null;
}