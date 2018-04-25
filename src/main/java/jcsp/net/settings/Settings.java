
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
 * Used internally within the JCSP network infrastructure to represent a set of system settings.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Settings
{
   
   public Settings()
   {
      name = "Settings";
   }
   
   public Settings(String name)
   {
      this.name = name;
   }
   
   public void addSetting(Setting s)
   {
      if(s != null)
      {
         if(!settings.contains(s) && !settingNameMap.containsKey(s.getName()))
         {
            settings.put(s, s);
            settingNameMap.put(s.getName(), s);
         }
      }
      else
         throw new SettingAlreadyExistsException("Already have a setting named " + s.getName());
   }
   
   public void removeSetting(Setting s)
   {
      if(settings.contains(s))
      {
         settings.remove(s);
         settingNameMap.remove(s.getName());
      }
   }
   
   public Setting getSetting(String name)
   {
      return (Setting) settingNameMap.get(name);
   }
   
   public Setting[] getSettings()
   {
      Setting[] toReturn = new Setting[settings.size()];
      return (Setting[])settings.keySet().toArray(toReturn);
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<" + this.name + ">\n");
      Setting[] settings = getSettings();
      for(int i=0; i<settings.length; i++)
         sb.append(JCSPConfig.tabIn(settings[i].toString())).append("\n");
      sb.append("</" + this.name + ">");
      return sb.toString();
   }
   
   private Hashtable settings = new Hashtable();
   private Hashtable settingNameMap = new Hashtable();
   String name;
   
   static class SettingAlreadyExistsException extends RuntimeException
   {
      private SettingAlreadyExistsException(String msg)
      {
         super(msg);
      }
   }
}