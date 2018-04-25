
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

package jcsp.net;

import java.util.*;

/**
 * <p>
 * An instance of the <CODE>ServiceManager</CODE> is created by the Node when
 * it is initialized. This reference can be obtained from the <CODE>Node</CODE>
 * class.
 * </p>
 * <p>
 * The class allows the local Node's services to be installed and started. The
 * class does not currently allow services to removed. Services can be stopped
 * by obtaining their reference and stopping them directly.
 * </p>
 *
 * @author Quickstone Technologies Limited
 */
public class ServiceManager
{
   private Hashtable services = new Hashtable();
   
   /**
    * Installs a service as specified by its class and a set
    * of settings.
    * @param settings the settings for the service.
    * @param serviceClass the <CODE>Class</CODE> object of the class of the service to install.
    * @return <CODE>true</CODE> iff the service has successfully been installed.
    */
   public synchronized boolean installService(ServiceSettings settings, Class serviceClass)
   {
      try
      {
         Service service = (Service) serviceClass.newInstance();
         if (!service.init(settings))
            return false;
         return installService(service, settings.getServiceName());
      }
      catch (Exception e)
      {
         Node.err.log(this, e);
         return false;
      }
   }
   
   /**
    * Installs a <CODE>Service</CODE> object as a service in this
    * <CODE>ServiceManager</CODE>. The name of the service must be supplied.
    * @param service the <CODE>Service</CODE> object to install.
    * @param name the name of the service.
    * @return <CODE>true</CODE> iff the service has been successfully installed.
    */
   public boolean installService(Service service, String name)
   {
      synchronized (services)
      {
         services.put(name, service);
      }
      return true;
   }
   
   /**
    * Starts the service with the specified name.
    * @param name the name of the service to start.
    * @return <CODE>true</CODE> iff the service has been successfully started.
    */
   public synchronized boolean startService(String name)
   {
      Service service = getService(name);
      if (service != null)
         return service.start();
      return false;
   }
   
   /**
    * <p>
    * This method allows a service that is not running to be
    * uninstalled.
    * </p>
    * <p>
    * A running service can be uninstalled by obtaining the service
    * reference, calling its <code>stop()</code> method and then
    * calling this method with the service's name.
    * </p>
    * @param	name	the name of the service to uninstall.
    * @return <code>true</code> iff the service has been uninstalled.
    */
   public synchronized boolean uninstallService(String name)
   {
      Service service = getService(name);
      if (!service.isRunning())
      {
         services.remove(name);
         return true;
      }
      return false;
   }
   
   /**
    * <p>
    * Returns the <CODE>Service</CODE> object of the service with the specified name.
    * </p>
    * @param name the name of the service
    * @return the <CODE>Service</CODE> object of the service with the specified name.
    */
   public Service getService(String name)
   {
      synchronized (services)
      {
         return (Service) services.get(name);
      }
   }
   
   /**
    * Returns an array containing an array of String objects
    * which represent the names of the currently installed
    * services.
    *
    * @return	an array of <code>String</code> service names.
    */
   public String[] getServiceNames()
   {
      synchronized (services)
      {
         String[] serviceNames = new String[this.services.size()];
         Enumeration en = services.keys();
         int i = 0;
         while (en.hasMoreElements())
         {
            serviceNames[i] = (String) en.nextElement();
            i++;
         }
         return serviceNames;
      }
   }
}