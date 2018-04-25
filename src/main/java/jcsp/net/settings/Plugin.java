
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
 * Used internally within the JCSP network infrastructure to represent a plug-in.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Plugin
{
   public Plugin(String name, Class pluginClass)
   {
      this.name = name;
      this.pluginClass = pluginClass;
   }
   
   public String getName()
   {
      return name;
   }
   
   public Class getPluginClass()
   {
      return pluginClass;
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof Plugin)
      {
         Plugin other = (Plugin) o;
         return name.equals(other.name) && pluginClass.equals(other.pluginClass);
      }
      return false;
   }
   
   public int hashCode()
   {
      return name.hashCode();
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<Plugin name=\"" + name + "\" pluginClass=\"" + pluginClass + "\" />");
      return sb.toString();
   }
   
   private String name;
   private Class pluginClass;
}