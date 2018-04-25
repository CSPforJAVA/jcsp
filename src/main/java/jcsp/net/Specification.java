
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
 *
 *
 * @author Quickstone Technologies Limited.
 */
class Specification implements Serializable
{
   Specification(String name, int intValue)
   {
      this.name = name;
      this.intValue = intValue;
      strValue = "";
      dblValue = -1;
      booValue = false;
      type = Integer.TYPE;
   }
   
   Specification(String name, String strValue)
   {
      this.name = name;
      this.strValue = strValue;
      intValue = -1;
      dblValue = -1;
      booValue = false;
      type = String.class;
   }
   
   Specification(String name, double dblValue)
   {
      this.name = name;
      this.dblValue = dblValue;
      intValue = -1;
      strValue = "";
      booValue = false;
      type = Double.TYPE;
   }
   
   Specification(String name, boolean booValue)
   {
      this.name = name;
      this.booValue = booValue;
      intValue = -1;
      strValue = "";
      dblValue = -1;
      type = Boolean.TYPE;
   }
   
   public boolean equals(Object o)
   {
      if(o == null || !(o instanceof Specification)) 
         return false;
      Specification other = (Specification) o;
      return type.equals(other.type) 
             && name.equals(other.name) 
             && intValue == other.intValue 
             && strValue.equals(other.strValue)
             && dblValue == other.dblValue 
             && booValue == other.booValue;
   }
   
   public int hashCode()
   {
      return name.hashCode();
   }
   
   final Class type;
   final String name;
   final int intValue;
   final String strValue;
   final double dblValue;
   final boolean booValue;
}