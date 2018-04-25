
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
 * Used internally within the JCSP network infrastructure to represent a single protocol.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Protocol
{
   public Protocol(String protocolID, String protocolName, Class idClass, int position)
   {
      this.protocolID = protocolID;
      this.protocolName = protocolName;
      this.idClass = idClass;
      this.position = position;
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
   
   public void addSetting(Setting setting)
   {
      settings.addSetting(setting);
   }
   
   public void removeSetting(Setting setting)
   {
      settings.removeSetting(setting);
   }
   
   public Setting[] getSettings()
   {
      return settings.getSettings();
   }
   
   public Setting getSetting(String name)
   {
      return settings.getSetting(name);
   }
   
   public String getProtocolID()
   {
      return protocolID;
   }
   
   public String getName()
   {
      return protocolName;
   }
   
   public Class getIDClass()
   {
      return idClass;
   }
   
   public int getPosition()
   {
      return position;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<Protocol protocolID=\"" + protocolID + "\" name=\"" + protocolName  + "\" idClass=\"" + 
                idClass + "\" position=\"" + position + "\">\n");
      sb.append(JCSPConfig.tabIn(settings.toString())).append("\n");
      sb.append(JCSPConfig.tabIn(specs.toString())).append("\n");
      sb.append("</Protocol>");
      return sb.toString();
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof Protocol)
      {
         Protocol other = (Protocol) o;
         return protocolID.equals(other.protocolID) && protocolName.equals(other.protocolName) 
                && idClass.equals(other.idClass) && specs.equals(other.specs);
      }
      return false;
   }
   
   public int hashCode()
   {
      return protocolName.hashCode();
   }
   
   private String protocolID;
   private String protocolName;
   private Class idClass;
   private int position;
   private Specs specs = new Specs();
   private Settings settings = new Settings();
}