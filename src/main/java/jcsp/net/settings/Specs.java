
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

import java.util.*;

/**
 * Used internally within the JCSP network infrastructure to represent a set of system specifications.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Specs
{
   public void addSpec(Spec spec)
   {
      if(spec instanceof MaxSpeed && maxSpeed == null)
         maxSpeed = (MaxSpeed)spec;
      else if(spec instanceof Memory && memory == null)
         memory = (Memory)spec;
      else if(spec instanceof Wireless && wireless == null)
         wireless = (Wireless)spec;
      else if(spec instanceof Reliable && reliable == null)
         reliable = (Reliable)spec;
      else if(spec instanceof ConnectionOriented && connectionOriented == null)
         connectionOriented = (ConnectionOriented)spec;
      else if(spec instanceof OtherSpec && !specs.contains(spec))
         specs.put(spec,spec);
      else
         throw new SpecAlreadyExistsException("Already have a spec named " + spec.getName());
   }
   
   public void removeSpec(Spec spec)
   {
      if(maxSpeed == spec)
         maxSpeed = null;
      else if(memory == spec)
         memory = null;
      else if(wireless == spec)
         wireless = null;
      else if(reliable == spec)
         reliable = null;
      else if(connectionOriented == spec)
         connectionOriented = null;
      else if(spec instanceof OtherSpec && specs.contains(spec))
         specs.remove(spec);
   }
   
   public Spec[] getSpecs()
   {
      int count=0;
      if(maxSpeed != null) 
         count++;
      if(memory != null) 
         count++;
      if(wireless != null) 
         count++;
      if(reliable != null) 
         count++;
      if(connectionOriented != null) 
         count++;
      count += specs.size();
      
      Spec[] toReturn = new Spec[count];
      int pos = 0;
      if(maxSpeed != null)
      {
         toReturn[pos] = maxSpeed;
         pos++;
      }
      if(memory != null)
      {
         toReturn[pos] = memory;
         pos++;
      }
      if(wireless != null)
      {
         toReturn[pos] = wireless;
         pos++;
      }
      if(reliable != null)
      {
         toReturn[pos] = reliable;
         pos++;
      }
      if(connectionOriented != null)
      {
         toReturn[pos] = connectionOriented;
         pos++;
      }
      
      for (Enumeration e = specs.keys(); e.hasMoreElements(); )
      {
         toReturn[pos] = (Spec) e.nextElement();
         pos++;
      }
      return toReturn;
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof Specs)
      {
         Specs other = (Specs) o;
         if((maxSpeed==other.maxSpeed || (maxSpeed!= null && maxSpeed.equals(other.maxSpeed)))
            && (memory == other.memory || (memory != null && memory.equals(other.memory)))
            && (wireless == other.wireless ||(wireless != null && wireless.equals(other.wireless)))
            && (reliable == other.reliable || (reliable != null && reliable.equals(other.reliable)))
            && (connectionOriented == other.connectionOriented || (connectionOriented != null
                                                                  && connectionOriented.equals(other.connectionOriented)))
            && specs.equals(other.specs))
         {
            return true;
         }
      }
      return false;
   }
   
   public int hashCode()
   {
      int count=0;
      if (maxSpeed != null) 
         count++;
      if (memory != null) 
         count++;
      if (wireless != null) 
         count++;
      if (reliable != null) 
         count++;
      if (connectionOriented != null) 
         count++;
      count += specs.size();
      return count;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<Specs>\n");
      
      Spec[] specs = getSpecs();
      for(int i=0; i<specs.length; i++)
         sb.append(JCSPConfig.tabIn(specs[i].toString())).append("\n");
      sb.append("</Specs>");
      return sb.toString();
   }
   
   private MaxSpeed maxSpeed = null;
   private Memory memory = null;
   private Wireless wireless = null;
   private Reliable reliable = null;
   private ConnectionOriented connectionOriented = null;
   private Hashtable specs = new Hashtable();
   
   static class SpecAlreadyExistsException extends RuntimeException
   {
      private SpecAlreadyExistsException(String msg)
      {
         super(msg);
      }
   }
}