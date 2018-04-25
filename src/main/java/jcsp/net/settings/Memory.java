
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
 * Used internally within the JCSP network infrastructure to represent a memory value.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Memory extends Spec implements XMLConfigConstants
{
   Memory(int memory)
   {
      super(SPEC_NAME_MEMORY, true);
      this.memory = memory;
   }
   
   public String getStringValue()
   {
      return "" + memory;
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof Memory)
      {
         Memory other = (Memory) o;
         return memory == other.memory;
      }
      return false;
   }
   
   public int hashCode()
   {
      return memory;
   }
   
   public int getValue()
   {
      return memory;
   }
   
   private int memory;
}