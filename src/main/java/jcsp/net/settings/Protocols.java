
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
 * Used internally within the JCSP network infrastructure to represent a set of protocols.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Protocols
{
   public void addProtocol(Protocol p)
   {
      if(p != null)
      {
         if(!protocols.contains(p) && !protocolIDMap.containsKey(p.getProtocolID()))
         {
            protocols.put(p, p);
            protocolIDMap.put(p.getProtocolID(), p);
            lastProtocol = p;
         }
      }
      else
         throw new ProtocolAlreadyExistsException("Already have a protocold named " + p.getName());
   }
   
   public void removeProtocol(Protocol p)
   {
      if (protocols.contains(p))
      {
         protocols.remove(p);
         protocolIDMap.remove(p.getProtocolID());
      }
   }
   
   public Protocol getProtocol(String protocolID)
   {
      return (Protocol) protocolIDMap.get(protocolID);
   }
   
   public Protocol getLastProtocol()
   {
      return lastProtocol;
   }
   
   public Protocol[] getProtocols()
   {
      Protocol[] toReturn = new Protocol[protocols.size()];
      return (Protocol[])protocols.keySet().toArray(toReturn);
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<Protocols>\n");
      Protocol[] protocols = getProtocols();
      for(int i=0; i<protocols.length; i++)
         sb.append(JCSPConfig.tabIn(protocols[i].toString())).append("\n");
      sb.append("</Protocols>");
      return sb.toString();
   }
   
   private Hashtable protocols = new Hashtable();
   private Hashtable protocolIDMap = new Hashtable();
   private Protocol lastProtocol = null;
   
   static class ProtocolAlreadyExistsException extends RuntimeException
   {
      private ProtocolAlreadyExistsException(String msg)
      {
         super(msg);
      }
   }
}