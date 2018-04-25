
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
 * Used internally within the JCSP network infrastructure to represent an address setting.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class AddressSetting extends Setting
{
   public AddressSetting(String name, String value, String protocolID)
   {
      super(name, value);
      this.protocolID = protocolID;
   }
   
   public String getProtocolID()
   {
      return protocolID;
   }
   
   public AddressSetting getAlternate()
   {
      return alternate;
   }
   
   public boolean addAlternate(AddressSetting alternate)
   {
      if(alternate == null || !getName().equals(alternate.getName()))
         return false;
      AddressSetting prevRAdd = this;
      AddressSetting rAdd = alternate;
      while(rAdd != null)
      {
         if(rAdd == this || rAdd == alternate) 
            break;
         prevRAdd = rAdd;
         rAdd = rAdd.alternate;
      }
      prevRAdd.alternate = alternate;
      return true;
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof AddressSetting)
      {
         AddressSetting other = (AddressSetting) o;
         if(protocolID.equals(other.protocolID) && getName().equals(other.getName()) && getValue().equals(other.getValue()))
            return true;
      }
      return false;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<Setting name=\"" + getName() + "\" value=\"" + getValue() + "\" protocolID=\"" + protocolID + "\" />");
      if(alternate != null)
         sb.append("\n").append(alternate.toString());
      return sb.toString();
   }
   
   private String protocolID;
   private AddressSetting alternate = null;
}