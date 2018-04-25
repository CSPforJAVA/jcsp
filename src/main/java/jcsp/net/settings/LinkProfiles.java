
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
import jcsp.net.*;

/**
 * Used internally within the JCSP network infrastructure to represent a set of link profiles.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class LinkProfiles
{
   public void addProfile(LinkProfile p)
   {
      if(p != null)
      {
         if(!profiles.contains(p) && !profileNameMap.containsKey(p.getName()))
         {
            profiles.put(p, p);
            profileNameMap.put(p.getName(), p);
            lastProfile = p;
         }
         else
            throw new LinkProfileAlreadyExistsException("Already have a profile named " + p.getName());
      }
      else
         Node.err.log(this, "Error: Null profile");
   }
   
   public void removeProfile(LinkProfile p)
   {
      if(profiles.contains(p))
      {
         profiles.remove(p);
         profileNameMap.remove(p.getName());
      }
   }
   
   public LinkProfile getProfile(String name)
   {
      return (LinkProfile) profileNameMap.get(name);
   }
   
   public LinkProfile[] getProfiles()
   {
      LinkProfile[] toReturn = new LinkProfile[profiles.size()];
      return (LinkProfile[])profiles.keySet().toArray(toReturn);
   }
   
   public LinkProfile getLastProfile()
   {
      return lastProfile;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<LinkProfiles>\n");
      LinkProfile[] profiles = getProfiles();
      for(int i=0; i<profiles.length; i++)
         sb.append(JCSPConfig.tabIn(profiles[i].toString())).append("\n");
      sb.append("</LinkProfiles>");
      return sb.toString();
   }
   
   private Hashtable profiles = new Hashtable();
   private Hashtable profileNameMap = new Hashtable();
   private LinkProfile lastProfile = null;
   
   static class LinkProfileAlreadyExistsException extends RuntimeException
   {
      private LinkProfileAlreadyExistsException(String msg)
      {
         super(msg);
      }
   }
}