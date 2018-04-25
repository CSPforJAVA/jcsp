
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

package jcsp.net.tcpip;

import java.net.*;
import java.util.*;
import jcsp.net.*;

/**
 * Defines a protocol ID for the TCP/IP link implementation.
 *
 * @see ProtocolID
 *
 *
 * @author Quickstone Technologies Limited
 */
public class TCPIPProtocolID extends ProtocolID
{
   /**
    * Compares two protocol IDs
    */
   public boolean equals(Object o)
   {
      return o instanceof TCPIPProtocolID;
   }
   
   /**
    * Always returns true.
    */
   public boolean isActive()
   {
      return true;
   }
   
   /**
    * Always returns false.
    */
   public boolean requiresUserInteraction()
   {
      return false;
   }
   
   /**
    * Returns the factory builder for this protocol.
    */
   protected LinkFactory.Builder getLinkBuilder(Hashtable settings)
   {
      return new LinkFactory.Builder(this)
      {
         public Link testAndBuild(NodeAddressID addressID)
         {
            if(!(addressID instanceof TCPIPAddressID))
               throw new IllegalArgumentException("Argument not TCP/IP NodeAddressID");
            TCPIPAddressID add = (TCPIPAddressID) addressID;
            return new TCPIPLink(add);
         }
      };
   }
   
   /**
    * Starts the TCP/IP link server at this node using the given address ID.
    */
   protected LinkServer startLinkServer(NodeAddressID addressID)
   throws IllegalArgumentException
   {
      if(addressID == null)
         throw new IllegalArgumentException("addressID is null");
      return TCPIPLinkServer.create(addressID);
   }
   
   /**
    * Creates an address from a string form.
    */
   protected NodeAddressID createAddressID(String stAddressID, boolean uniqueAddress) throws IllegalArgumentException
   {
      if(stAddressID == null)
         throw new IllegalArgumentException("addressID is null");
      if(stAddressID == "")
         throw new IllegalArgumentException("Zero length String supplied");
      int colonIndex = stAddressID.indexOf(':');
      TCPIPAddressID addressID = null;
      if(colonIndex != -1)
      {
         //check that there are characters either side of the colon
         if(colonIndex > 0 && colonIndex < stAddressID.length() - 1)
         {
            String host = stAddressID.substring(0, colonIndex);
            String stPort = stAddressID.substring(colonIndex + 1, stAddressID.length());
            try
            {
               int port = Integer.decode(stPort).intValue();
               addressID = new TCPIPAddressID(host, port, uniqueAddress);
            }
            catch (NumberFormatException e)
            {
               throw new IllegalArgumentException("Port not a number");
            }
            catch (UnknownHostException e)
            {
               throw new IllegalArgumentException("Unknown host supplied");
            }
         }
         else
         {
            throw new IllegalArgumentException("host and port not fully specified");
         }
      }
      else
      {
         //no port specified
         try
         {
            addressID = new TCPIPAddressID(stAddressID, 0, uniqueAddress);
         }
         catch (UnknownHostException e)
         {
            throw new IllegalArgumentException("Unknown host supplied");
         }
      }
      return addressID;
   }
}