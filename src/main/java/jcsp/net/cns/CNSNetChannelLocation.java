
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

package jcsp.net.cns;

import jcsp.net.*;

/**
 * <p>
 * Instances of this class are returned by the resolve methods
 * of <code>{@link CNSService}</code>. JCSP.NET users cannot create
 * the objects directly.
 * </p>
 * <p>
 * The objects returned by the <code>{@link CNSService}</code> can be used
 * in place of normal <code>NetChannelLocation</code> objects.
 * </p>
 *
 * @author Quickstone Technologies Limited
 */
public class CNSNetChannelLocation extends NetChannelLocation
{
   
   /*-----------Constructors----------------------------------------------------*/
   
   CNSNetChannelLocation(NetChannelLocation locToClone, String name, NameAccessLevel accessLevel,
                         CNSService cnsService, String cnsServiceName)
   {
      super(locToClone);
      this.name = name;
      this.accessLevel = accessLevel;
      this.cnsResolver = cnsService;
      this.cnsServiceName = cnsServiceName;
   }
   
   /*-----------Private fields--------------------------------------------------*/
   
   private String name;
   
   private NameAccessLevel accessLevel;
   
   private transient CNSUser cnsResolver = null;
   
   private String cnsServiceName = null;
   
   /*-----------Overriden methods from NetChannelLocation-----------------------*/
   
   /**
    * This method requests that the instance of this class refresh
    * its information.
    *
    * The method will re-resolve the location of the
    * <code>NetChannelInput</code> from the channel name server.
    *
    * @return  <code>true</code> if any information has changed, otherwise
    *           <code>false</code>.
    *
    */
   public boolean refresh()
   {
      NetChannelLocation refreshed = null;
      if (cnsResolver == null)
         //obtain a reference to the installed CNS
         this.cnsResolver = (CNSUser) Node.getInstance().getServiceUserObject(this.cnsServiceName);
      if (accessLevel == null)
         refreshed = cnsResolver.resolve(name);
      else
         refreshed = cnsResolver.resolve(name, accessLevel);
      if (!this.equals(refreshed))
      {
         //CNS has updated the location object
         refreshFrom(refreshed);
         return true;
      }
      else
         //nothing has changed
         return false;
   }
   /*-----------Other public methods--------------------------------------------*/
}