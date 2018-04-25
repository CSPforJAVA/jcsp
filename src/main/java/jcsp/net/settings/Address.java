
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
 * Used internally within the JCSP network infrastructure to represent a single address.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Address
{
   public Address(String protocolID, String value, boolean unique)
   {
      this.protocolID = protocolID;
      this.value = value;
      this.unique = unique;
   }
   
   public String getProtocolID()
   {
      return protocolID;
   }
   
   public String getValue()
   {
      return value;
   }
   
   public void addSpec(Spec spec)
   {
      specs.addSpec(spec);
   }
   
   public void removeSpec(Spec spec)
   {
      specs.removeSpec(spec);
   }
   
   public Spec[] getSpecs()
   {
      return specs.getSpecs();
   }
   
   public boolean isUnique()
   {
      return unique;
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof Address)
      {
         Address other = (Address) o;
         if(protocolID.equals(other.protocolID) && value.equals(other.value))
            return true;
      }
      return false;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<Address protocolID=\"" + protocolID + "\" value=\"" + value + "\" unique=\"" + unique + "\">\n");
      sb.append(JCSPConfig.tabIn(specs.toString())).append("\n");
      sb.append("</Address>");
      return sb.toString();
   }
   
   private String protocolID;
   private String value;
   private boolean unique;
   private Specs specs = new Specs();
}