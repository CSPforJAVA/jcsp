
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

/**
 * <p>
 * An interface that should be implemented by classes that
 * are intended to be Node level JCSP.NET services.
 * </p>
 * <p>
 * Services should be initialized, then started and then stopped.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public interface Service
{
   /**
    * This should start the service when called and return.
    * @return <CODE>true</CODE> iff the service has successfully started.
    */
   public boolean start();
   
   /** Should stop the service and then return.
    * @return <CODE>true</CODE> iff the service has successfully stopped.
    */
   public boolean stop();
   
   /** Initialize the service with the specified service settings.
    * @param settings The settings used by the service.
    * @return <CODE>true</CODE> iff the service has been initialized.
    */
   public boolean init(ServiceSettings settings);
   
   /**
    * Indicates whether or not a service is running.
    * @return <CODE>true</CODE> iff the service is currently running.
    */
   public boolean isRunning();
   
   /**
    * Obtains a <code>ServiceUserObject</code> from a Service.
    * This allows Services to expose functionality to users that
    * it does not want to be able to access admin features.
    *
    * @return a <code>ServiceUserObject</code>.
    * @throws SecurityException if the calling Thread does not have
    *                            access to the object.
    */
   public ServiceUserObject getUserObject() throws SecurityException;
}