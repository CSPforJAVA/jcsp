
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
 * Unsed internally within the JCSP network infrastructure to represent a set of addresses.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Addresses
{
   public void addAddress(Address a)
   {
      if(a != null)
      {
         if(!addresses.contains(a))
         {
            addresses.put(a, a);
            lastAddress = a;
         }
      }
      else
         throw new AddressAlreadyExistsException("Already have an address of value " + a.getValue() + 
                                                 " for protocol " + a.getProtocolID());
   }
   
   public void removeAddress(Address a)
   {
      if(addresses.contains(a))
         addresses.remove(a);
   }
   
   public Address[] getAddresses()
   {
      Address[] toReturn = new Address[addresses.size()];
      return (Address[])addresses.keySet().toArray(toReturn);
   }
   
   public Address getLastAddress()
   {
      return lastAddress;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<Addresses>\n");
      Address[] addresses = getAddresses();
      for(int i=0; i<addresses.length; i++)
         sb.append(JCSPConfig.tabIn(addresses[i].toString())).append("\n");
      sb.append("</Addresses>");
      return sb.toString();
   }
   
   private Hashtable addresses = new Hashtable();
   private Address lastAddress = null;
   
   static class AddressAlreadyExistsException extends RuntimeException
   {
      private AddressAlreadyExistsException(String msg)
      {
         super(msg);
      }
   }
}