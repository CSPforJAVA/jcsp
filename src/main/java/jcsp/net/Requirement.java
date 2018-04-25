
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

/**
 *
 *
 * @author Quickstone Technologies Limited.
 */
class Requirement
{
   Requirement(String name, String specName, String comparator, int intValue)
   {
      if(name == null 
            || specName == null 
            || comparator == null
            || !(comparator.equals(COMPARATOR_EQUALS)
            || comparator.equals(COMPARATOR_LESS_THAN)
            || comparator.equals(COMPARATOR_GREATER_THAN)))
         throw new IllegalArgumentException("Illegal arguments - either null parameter or invalid comparator value");
      
      this.name = name;
      this.specName = specName;
      this.comparator = comparator;
      this.intValue = intValue;
      this.strValue = "";
      this.dblValue = -1;
      this.booValue = false;
      type = Integer.TYPE;
   }
   
   Requirement(String name, String specName, String comparator, String strValue)
   {
      if(name == null
            || specName == null 
            || comparator == null
            || !(comparator.equals(COMPARATOR_EQUALS)
            || comparator.equals(COMPARATOR_LESS_THAN)
            || comparator.equals(COMPARATOR_GREATER_THAN)))
         throw new IllegalArgumentException("Illegal arguments - either null parameter or invalid comparator value");
      
      this.name = name;
      this.specName = specName;
      this.comparator = comparator;
      this.strValue = strValue;
      this.intValue = -1;
      this.dblValue = -1;
      this.booValue = false;
      type = String.class;
   }
   
   Requirement(String name, String specName, String comparator, double dblValue)
   {
      if(name == null 
            || specName == null 
            || comparator == null
            || !(comparator.equals(COMPARATOR_EQUALS)
            || comparator.equals(COMPARATOR_LESS_THAN)
            || comparator.equals(COMPARATOR_GREATER_THAN)))
         throw new IllegalArgumentException("Illegal arguments - either null parameter or invalid comparator value");
      
      this.name = name;
      this.specName = specName;
      this.comparator = comparator;
      this.dblValue = dblValue;
      this.intValue = -1;
      this.strValue = "";
      this.booValue = false;
      type = Double.TYPE;
   }
   
   Requirement(String name, String specName, String comparator, boolean booValue)
   {
      if(name == null 
            || specName == null 
            || comparator == null
            || !(comparator.equals(COMPARATOR_EQUALS)
            || comparator.equals(COMPARATOR_LESS_THAN)
            || comparator.equals(COMPARATOR_GREATER_THAN)))
         throw new IllegalArgumentException("Illegal arguments - either null parameter or invalid comparator value");
      
      this.name = name;
      this.specName = specName;
      this.comparator = comparator;
      this.booValue = booValue;
      this.intValue = -1;
      this.strValue = "";
      this.dblValue = -1;
      type = Boolean.TYPE;
   }
   
   public boolean matches(Specification spec)
   {
      if(specName.equals(spec.name) && type.equals(spec.type))
      {
         Comparable cThis = null;
         Comparable cOther = null;
         if(type.equals(String.class))
         {
            cThis = strValue;
            cOther = spec.strValue;
         }
         else if(type.equals(Integer.TYPE))
         {
            cThis = new Integer(intValue);
            cOther = new Integer(spec.intValue);
         }
         else if(type.equals(Double.TYPE))
         {
            cThis = new Double(dblValue);
            cOther = new Double(spec.dblValue);
         }
         else if(type.equals(Boolean.TYPE))
         {
            cThis = new BooleanComparable(booValue);
            cOther = new BooleanComparable(spec.booValue);
         }
         int comparison = cThis.compareTo(cOther);
         
         if(comparator.equals(COMPARATOR_EQUALS) && comparison == 0)
            return true;
         else if(comparator.equals(COMPARATOR_LESS_THAN) && comparison > 0)
            //This object is greater than the other object - i.e. the other
            //object is less than this object
            return true;
         else if(comparator.equals(COMPARATOR_GREATER_THAN) && comparison < 0)
            //This object is less than the other object - i.e. the other
            //object is greater than this object
            return true;
      }
      return false;
   }
   
   final Class type;
   final String name;
   final String specName;
   final String comparator;
   final int intValue;
   final String strValue;
   final double dblValue;
   final boolean booValue;
   
   private static final String COMPARATOR_EQUALS = "EQU";
   private static final String COMPARATOR_LESS_THAN = "LTH";
   private static final String COMPARATOR_GREATER_THAN = "GRT";
   
   private class BooleanComparable implements Comparable
   {
      BooleanComparable(boolean val)
      {
         this.val = val;
      }
      public int compareTo(Object o)
      {
         BooleanComparable other = (BooleanComparable) o;
         if(val == other.val)
            return 0;
         else if(other.val == true)
            return -1;
         return 1;
      }
      private boolean val;
   }
}