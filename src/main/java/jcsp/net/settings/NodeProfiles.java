
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
 * Used internally within the JCSP network infrastructure to represent a set of node profiles.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class NodeProfiles
{
   public void addProfile(NodeProfile p)
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
            throw new NodeProfileAlreadyExistsException("Already have a profile named " + p.getName());
      }
   }
   
   public void removeProfile(NodeProfile p)
   {
      if(profiles.contains(p))
      {
         profiles.remove(p);
         profileNameMap.remove(p.getName());
      }
   }
   
   public NodeProfile getProfile(String name)
   {
      return (NodeProfile) profileNameMap.get(name);
   }
   
   public NodeProfile[] getProfiles()
   {
      NodeProfile[] toReturn = new NodeProfile[profiles.size()];
      return (NodeProfile[])profiles.keySet().toArray(toReturn);
   }
   
   public NodeProfile getLastProfile()
   {
      return lastProfile;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<NodeProfiles>\n");
      NodeProfile[] profiles = getProfiles();
      for(int i=0; i<profiles.length; i++)
         sb.append(JCSPConfig.tabIn(profiles[i].toString())).append("\n");
      sb.append("</NodeProfiles>");
      return sb.toString();
   }
   
   private Hashtable profiles = new Hashtable();
   private Hashtable profileNameMap = new Hashtable();
   private NodeProfile lastProfile = null;
   
   static class NodeProfileAlreadyExistsException extends RuntimeException
   {
      private NodeProfileAlreadyExistsException(String msg)
      {
         super(msg);
      }
   }
}