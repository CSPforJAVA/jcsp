
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
 * Used internally within the JCSP network infrastructure to represent a maximum speed.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class MaxSpeed extends Spec implements XMLConfigConstants
{
   MaxSpeed(int maxSpeed)
   {
      super(SPEC_NAME_MAXSPEED, true);
      this.maxSpeed = maxSpeed;
   }
   
   public String getStringValue()
   {
      return "" + maxSpeed;
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof MaxSpeed)
      {
         MaxSpeed other = (MaxSpeed) o;
         return maxSpeed == other.maxSpeed;
      }
      return false;
   }
   
   public int hashCode()
   {
      return maxSpeed;
   }
   
   public int getValue()
   {
      return maxSpeed;
   }
   private int maxSpeed;
}