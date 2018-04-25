
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
 * Used internally within the JCSP network infrastructure to define the XML configuration names.
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface XMLConfigConstants
{
   public static final String SPEC_NAME_MAXSPEED = "MAXSPEED";
   public static final String SPEC_NAME_WIRELESS = "WIRELESS";
   public static final String SPEC_NAME_RELIABLE = "RELIABLE";
   public static final String SPEC_NAME_CONNECTION_ORIENTED = "CONNECTION-ORIENTED";
   public static final String SPEC_NAME_MEMORY = "MEMORY";
   
   //determined automatically.
   public static final String SPEC_NAME_PROTOCOL = "PROTOCOL";
   public static final String SPEC_NAME_PING = "AAAPING";
   //so it is found quickly
   
   public static final String REQ_NAME_MINSPEED = "MINSPEED";
   public static final String REQ_NAME_PROTOCOL = "REQPROTOCOL";
   public static final String REQ_NAME_MAXPING = "MAXPING";
   
   //MINSPEED used for node as well as network
   public static final String REQ_NAME_MINMEMORY = "MINMEMORY";
   
   
   public static final String[] RESERVED_SPEC_NAMES = new String[] 
                                                      {
                                                         SPEC_NAME_MAXSPEED, SPEC_NAME_WIRELESS, SPEC_NAME_RELIABLE, 
                                                         SPEC_NAME_CONNECTION_ORIENTED, SPEC_NAME_PROTOCOL, SPEC_NAME_PING, 
                                                         SPEC_NAME_MEMORY, REQ_NAME_MAXPING, REQ_NAME_MINSPEED, 
                                                         REQ_NAME_PROTOCOL, REQ_NAME_MINMEMORY
                                                      };
   
   public static final String REQ_COMPARATOR_EQUALS = "EQU";
   public static final String REQ_COMPARATOR_GREATER = "GRT";
   public static final String REQ_COMPARATOR_LESS = "LTH";
   
   public static final String XML_BOOLEAN_TRUE = "TRUE";
   public static final String XML_BOOLEAN_FALSE = "FALSE";
   
   public static final String XML_TRISTATE_TRUE = "TRUE";
   public static final String XML_TRISTATE_FALSE = "FALSE";
   public static final String XML_TRISTATE_CANBE = "CANBE";
   
   public static final String DATA_TYPE_INDICATOR_INT = "int";
   public static final String DATA_TYPE_INDICATOR_DOUBLE = "dec";
   public static final String DATA_TYPE_INDICATOR_STRING = "string";
   public static final String DATA_TYPE_INDICATOR_BOOLEAN = "boolean";
   
   public static final String ELEMENT_SETTINGS = "SETTINGS";
   public static final String ELEMENT_SERVICES = "SERVICES";
   public static final String ELEMENT_PLUGINS = "PLUGINS";
   public static final String ELEMENT_PROTOCOLS = "PROTOCOLS";
   public static final String ELEMENT_ADDRESSES = "ADDRESSES";
   public static final String ELEMENT_LINK_PROFILES = "LINK_PROFILES";
   public static final String ELEMENT_NODE_PROFILES = "NODE_PROFILES";
   
   public static final String ELEMENT_NODE_SPECS = "NODE_SPECS";
   public static final String ELEMENT_SETTING = "SETTING";
   
   public static final String ELEMENT_SERVICE = "SERVICE";
   public static final String ELEMENT_ADDRESS_SETTING = "ADDRESS_SETTING";
   
   public static final String ELEMENT_PLUGIN = "PLUGIN";
   
   public static final String ELEMENT_PROTOCOL = "PROTOCOL";
   public static final String ELEMENT_PROTOCOL_SETTINGS = "PROTOCOL_SETTINGS";
   public static final String ELEMENT_PROTOCOL_SETTING = "PROTOCOL_SETTING";
   
   public static final String ELEMENT_SPECS = "SPECS";
   public static final String ELEMENT_MAXSPEED = "MAXSPEED";
   public static final String ELEMENT_WIRELESS = "WIRELESS";
   public static final String ELEMENT_RELIABLE = "RELIABLE";
   public static final String ELEMENT_CONNECTION_ORIENTED = "CONNECTION-ORIENTED";
   public static final String ELEMENT_MEMORY = "MEMORY";
   public static final String ELEMENT_OTHERSPEC = "OTHERSPEC";
   
   public static final String ELEMENT_ADDRESS = "ADDRESS";
   
   public static final String ELEMENT_LINK_PROFILE = "LINK_PROFILE";
   public static final String ELEMENT_LINK_REQS = "LINK_REQS";
   public static final String ELEMENT_LINK_REQ_PROTOCOL = "LINK_REQ_PROTOCOL";
   public static final String ELEMENT_LINK_REQ_MINSPEED = "LINK_MINSPEED";
   public static final String ELEMENT_LINK_REQ_MAXPING = "LINK_MAXPING";
   public static final String ELEMENT_LINK_REQ_OTHER = "LINK_REQ";
   
   public static final String ELEMENT_NODE_PROFILE = "NODE_PROFILE";
   public static final String ELEMENT_NODE_REQS = "NODE_REQS";
   public static final String ELEMENT_NODE_REQ_MINSPEED = "NODE_MINSPEED";
   public static final String ELEMENT_NODE_REQ_MINMEMORY = "NODE_MINMEMORY";
   public static final String ELEMENT_NODE_REQ_OTHER = "NODE_REQ";
}