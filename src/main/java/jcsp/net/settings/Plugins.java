
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
 * Used internally within the JCSP network infrastructure to represent a set of plug-ins.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Plugins
{
   public void addPlugin(Plugin p)
   {
      if(p != null)
      {
         if(!plugins.contains(p) && !pluginNameMap.containsKey(p.getName()))
         {
            plugins.put(p, p);
            pluginNameMap.put(p.getName(), p);
         }
      }
      else
         throw new PluginAlreadyExistsException("Already have a plugin named " + p.getName());
   }
   
   public void removePlugin(Plugin p)
   {
      if(plugins.contains(p))
      {
         plugins.remove(p);
         pluginNameMap.remove(p.getName());
      }
   }
   
   public Plugin getPlugin(String pluginName)
   {
      return (Plugin) pluginNameMap.get(pluginName);
   }
   
   public Plugin[] getPlugins()
   {
      Plugin[] toReturn = new Plugin[plugins.size()];
      int i = 0;
      for (Enumeration e = plugins.keys(); e.hasMoreElements(); )
         toReturn[i++] = (Plugin)e.nextElement();
      return toReturn;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<Plugins>\n");
      Plugin[] plugins = getPlugins();
      for (int i=0; i<plugins.length; i++)
         sb.append(JCSPConfig.tabIn(plugins[i].toString())).append("\n");
      sb.append("</Plugins>");
      return sb.toString();
   }
   
   private Hashtable plugins = new Hashtable();
   private Hashtable pluginNameMap = new Hashtable();
   
   static class PluginAlreadyExistsException extends RuntimeException
   {
      private PluginAlreadyExistsException(String msg)
      {
         super(msg);
      }
   }
}