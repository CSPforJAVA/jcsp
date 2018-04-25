
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
 * Used internally within the JCSP network infrastructure to represent the overall configuration.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class JCSPConfig
{
   public void setSettings(Settings settings)
   {
      this.settings = settings;
   }
   
   public Settings getSettings()
   {
      return settings;
   }
   
   public void setServices(Services services)
   {
      this.services = services;
   }
   
   public Services getServices()
   {
      return services;
   }
   
   public void setPlugins(Plugins plugins)
   {
      this.plugins = plugins;
   }
   
   public Plugins getPlugins()
   {
      return plugins;
   }
   
   public void setProtocols(Protocols protocols)
   {
      this.protocols = protocols;
   }
   
   public Protocols getProtocols()
   {
      return protocols;
   }
   
   public void setAddresses(Addresses addresses)
   {
      this.addresses = addresses;
   }
   
   public Addresses getAddresses()
   {
      return addresses;
   }
   
   public void setNodeSpecs(Specs specs)
   {
      this.nodeSpecs = specs;
   }
   
   public Specs getNodeSpecs()
   {
      return nodeSpecs;
   }
   
   public void setLinkProfiles(LinkProfiles linkProfiles)
   {
      this.linkProfiles = linkProfiles;
   }
   
   public LinkProfiles getLinkProfiles()
   {
      return linkProfiles;
   }
   
   public void setNodeProfiles(NodeProfiles nodeProfiles)
   {
      this.nodeProfiles = nodeProfiles;
   }
   
   public NodeProfiles getNodeProfiles()
   {
      return nodeProfiles;
   }
   
   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("<JCSPConfig>\n");
      sb.append(tabIn(settings.toString())).append("\n");
      sb.append(tabIn(services.toString())).append("\n");
      sb.append(tabIn(plugins.toString())).append("\n");
      sb.append(tabIn(protocols.toString())).append("\n");
      sb.append(tabIn(addresses.toString())).append("\n");
      sb.append(tabIn(nodeSpecs.toString())).append("\n");
      sb.append(tabIn(linkProfiles.toString())).append("\n");
      sb.append(tabIn(nodeProfiles.toString())).append("\n");
      sb.append("</JCSPConfig>");
      return sb.toString();
   }
   
   public static String tabIn(String string)
   {
      StringBuffer sb = new StringBuffer(string);
      //tab in first line
      sb.insert(0, "   ");
      for(int i=0; i<sb.length(); i++)
         if(sb.charAt(i) == '\n')
            sb.insert(i+1, "   ");
      return sb.toString();
   }
   
   private Settings settings = new Settings();
   private Services services = new Services();
   private Plugins plugins = new Plugins();
   private Protocols protocols = new Protocols();
   private Addresses addresses = new Addresses();
   private Specs nodeSpecs = new Specs();
   private LinkProfiles linkProfiles = new LinkProfiles();
   private NodeProfiles nodeProfiles = new NodeProfiles();
}