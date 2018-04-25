
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
 * Used internally within the JCSP network infrastructure to represent a set of system specifications.
 *
 *
 * @author Quickstone Technologies Limited
 */
public abstract class Spec implements XMLConfigConstants
{
   Spec(String name, boolean builtin)
   {
      if(!builtin)
         for (int i = 0; i < RESERVED_SPEC_NAMES.length; i++)
            if(name.equals(RESERVED_SPEC_NAMES[i]))
               throw new ReservedNameException(name);
      
      this.name = name;
   }
   
   Spec(String name, boolean builtin, boolean req)
   {
      this(name, builtin);
      this.isReq = req;
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof Spec)
      {
         Spec other = (Spec) o;
         return name.equals(other.name);
      }
      return false;
   }
   
   public int hashCode()
   {
      return name.hashCode();
   }
   
   public String getName()
   {
      return name;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      if(isReq)
         sb.append("<Req name=\"" + name + "\" value=\"" + getStringValue() + "\" />");
      else
         sb.append("<Spec name=\"" + name + "\" value=\"" + getStringValue() + "\" />");
      return sb.toString();
   }
   
   public abstract String getStringValue();
   private String name;
   private boolean isReq = false;
   
   static class ReservedNameException extends RuntimeException
   {
      private ReservedNameException(String name)
      {
         super("\"" + name + "\" is reserved.");
      }
   }
}