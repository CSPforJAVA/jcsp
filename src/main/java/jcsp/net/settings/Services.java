
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
 * Used internally within the JCSP network infrastructure to represent a set of services.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Services
{
   public void addService(Service s)
   {
      if(s != null)
      {
         if(!serviceNameMap.containsKey(s.getName()))
         {
            serviceNameMap.put(s.getName(), s);
            lastService = s;
            return;
         }
      }
      throw new ServiceAlreadyExistsException("Already have a service named " + s.getName());
   }
   
   public void removeService(Service s)
   {
      if(serviceNameMap.containsKey(s.getName()))
         serviceNameMap.remove(s.getName());
   }
   
   public Service getService(String name)
   {
      return (Service) serviceNameMap.get(name);
   }
   
   public Service getLastService()
   {
      return lastService;
   }
   
   public Service[] getServices()
   {
      Service[] toReturn = new Service[serviceNameMap.size()];
      return (Service[])serviceNameMap.values().toArray(toReturn);
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<Services>\n");
      Service[] services = getServices();
      for(int i=0; i<services.length; i++)
         sb.append(JCSPConfig.tabIn(services[i].toString())).append("\n");
      sb.append("</Services>");
      return sb.toString();
   }
   
   private Hashtable serviceNameMap = new Hashtable();
   private Service lastService = null;
   
   static class ServiceAlreadyExistsException extends RuntimeException
   {
      private ServiceAlreadyExistsException(String msg)
      {
         super(msg);
      }
   }
}