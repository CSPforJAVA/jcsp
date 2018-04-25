
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

/**
 *
 *
 * @author Quickstone Technologies Limited.
 */
class SerializedMessage extends Message implements Serializable
{
   public SerializedMessage(Message msg) throws NotSerializableException, IOException
   {
      serializedObject = new SerializedObject(msg, false);
      destIndex = msg.destIndex;
      sourceIndex = msg.sourceIndex;
   }
   
   SerializedMessage(SerializedObject serializedObject, long destIndex, long sourceIndex)
   {
      this.serializedObject = serializedObject;
      this.destIndex = destIndex;
      this.sourceIndex = sourceIndex;
   }
   
   public byte[] getSerializedData()
   {
      return serializedObject.getSerializedData();
   }
   
   /*--------------------------*/
   
   public Object get() throws ClassNotFoundException, IOException
   {
      return get(null);
   }
   
   public Object get(SerializedObject.InputStreamFactory factory) throws ClassNotFoundException, IOException
   {
      Object o = null;
      if(factory == null)
         o = serializedObject.get();
      else
         o = serializedObject.get(factory);
      
      if(o instanceof Message)
      {
         Message msg = (Message) o;
         msg.sourceID = sourceID;
         msg.txReplyChannel = txReplyChannel;
      }
      return o;
   }
   
   private SerializedObject serializedObject;
   
   private boolean isInternalClass;
   
   private static class AccesibleByteArrayOutputStream extends ByteArrayOutputStream
   {
      public byte[] getByteArray()
      {
         return buf;
      }
   }
}