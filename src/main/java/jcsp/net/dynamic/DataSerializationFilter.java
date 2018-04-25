
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

package jcsp.net.dynamic;

import java.io.*;
import jcsp.net.*;
import jcsp.util.filter.*;

/**
 * <p>A filter to be plugged into the sending end of a channel if dynamic class transfer is to be supported
 * over the channel. The receiving end of the channel should have a <code>DeserializeChannelFilter</code>
 * plugged in. Any objects send by this filter will be wrapped in a <code>DynamicClassLoaderMessage</code>
 * object which includes the <code>NetChannelLocation</code> of a channel for the local node's JFTP
 * service.</p>
 *
 * <p>Instances of this class will be created by the <code>DynamicClassLoader</code> service and should
 * be obtained via its <code>getTxFilter</code> method.</p>
 *
 *
 * @author Quickstone Technologies Limited
 */
class DataSerializationFilter implements Filter
{
   /**
    * Constructs a new <code>DataSerializationFilter</code> object.
    *
    * @param senderLoc the location of the JFTP service's request channel.
    */
   //public DataSerializationFilter(ClassManager cm, NetChannelLocation senderLoc) {
   public DataSerializationFilter(NetChannelLocation senderLoc)
   {
      this.senderLoc = senderLoc;
   }
   
   /**
    * Wraps the object in a <code>DynamicClassLoaderMessage</code> complete with the JFTP channel
    * location passed to the filter's constructor.
    *
    * @param obj the object to wrap up
    * @return the wrapped object
    */
   public Object filter(Object obj)
   {
      try
      {
         obj = new DynamicClassLoaderMessage(obj, senderLoc);
      }
      catch (NotSerializableException e)
      {
         throw new RuntimeException("Unable to serialize " + obj);
      }
      catch (IOException e)
      {
         throw new RuntimeException("IOException while trying to serialize " + obj);
      }
      return obj;
   }
   
   /**
    * Location of the JFTP service's request channel.
    */
   private NetChannelLocation senderLoc;
}