
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

import java.io.*;

import jcsp.util.filter.*;

// Not sure if this class should be public

/**
 * This class is used in the dynamic class loading mechanism.
 * Instances of the class extract <CODE>Message</CODE> objects from <CODE>SerializedMessage</CODE> objects.
 * Dynamic class loading can be enabled by using the constructor which takes a <CODE>ClassManager</CODE>.
 * If dynamic class loading is enabled and a <CODE>DynamicClassLoaderMessage</CODE> is filtered, 
 * the <CODE>ClassManager</CODE> object is supplied to the Message object and the underlying message extracted.
 * Any classes that need loading from the remote Node are requested and loaded as necessary.
 *
 *
 * @author Quickstone Technologies Limited
 */
class DeserializeChannelFilter implements Filter
{
    /* 5 Jun 2002
     *
     * This class was originally used by the dynamic class loading
     * mechanism as well as the LoopBackLink
     *
     * Have now moved dynamic class loading mechanism into separate
     * package and so have modified this class just to be used
     * by LoopbackLink.
     *
        DeserializeChannelFilter(ClassManager cm) {
                this.cm = cm;
                this.dynamic = true;
        }
     */
   
   DeserializeChannelFilter()
   {
   }
   
   /** This method takes an object and substitutes it for another object based upon the following rules:
    *
    * If the object is an instance of the <CODE>DynamicClassLoaderMessage</CODE> class and dynamic class loading is enabled,
    * then the filter will return the object returned by the supplied object's get method. A <CODE>ClassManager</CODE> will be
    * supplied to the method.
    *
    * If the object is an instance of the <CODE>SerializedMessage</CODE> class then the object's get
    * method will be called. The message being held by the <CODE>SerializedMessage</CODE> will be deserialized
    * but classes will not be dynamically loaded.
    *
    * If the object is not a <CODE>SerializedMessage</CODE> object, then the object itself will be returned without
    * modification.
    * @param object The object to filter.
    * @return the substituted object.
    */
   public Object filter(Object object)
   {
      
      try
      {
         if (object instanceof SerializedMessage)
            return ((SerializedMessage)object).get();
         else
            return object;
      }
      catch (ClassNotFoundException e)
      {
         Node.err.log(this, e);
      }
      catch (IOException e)
      {
         Node.err.log(this, e);
      }
      //return object at the moment decide later - this is only prototype version
      return object;
   }
}