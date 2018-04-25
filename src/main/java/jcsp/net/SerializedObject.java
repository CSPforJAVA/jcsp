
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

class SerializedObject implements Externalizable
{
   
   /*-------------------Constructors---------------------------------------------*/
   
   /**
    * This is the public used constructor. It takes an object and attempts
    * to serialize it.
    *
    * @parma obj	the Object to Serialize.
    * @throws	NotSerializableException	If obj is not Serializable.
    * @throws	IOException		if an IO error occurs during Serialization,
    *							should not happen unless there is a bug.
    */
   
   public SerializedObject(Object obj, boolean storeToString) throws NotSerializableException, IOException
   {
      if(obj == null)
      {
         serializedData = null;
         if (storeToString) 
            objectToString = "null";
         return;
      }
      if (storeToString) 
         objectToString = obj.toString();
      
      AccesibleByteArrayOutputStream serializedDataDestination = new AccesibleByteArrayOutputStream();
      ObjectOutputStream objOut = new ObjectOutputStream(serializedDataDestination);
      
      objOut.writeObject(obj);
      objOut.flush();
      serializedData = serializedDataDestination.getByteArray();
      try
      {
         serializedDataDestination.close();
         objOut.close();
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
   }
   
   /**
    * A private constructor used during the deserialization process of this
    * object. Externalizable objects require a no-arg constructor so a
    * replacement object is serialized in this object's place. On
    * deserialization, this object is reconstructed using this constructor.
    *
    * @param	data	a byte[] containing the serialized data of the object
    *					that this object is holding.
    * @param	objectToString	The toString value of the stored object.
    */
   SerializedObject(byte[] data, String objectToString)
   {
      this.serializedData = data;
      this.objectToString = objectToString;
   }
   
   /*-------------------Public Methods-------------------------------------------*/
   
   public byte[] getSerializedData()
   {
      return serializedData;
   }
   
   public Object get() throws ClassNotFoundException, IOException
   {
      return get(new BasicInputStreamFactory());
   }
   
   public Object get(InputStreamFactory factory) throws ClassNotFoundException, IOException
   {
      if (serializedData == null) 
         return null;
      ByteArrayInputStream serializedDataSource = new ByteArrayInputStream(serializedData);
      ObjectInput objIn = factory.create(serializedDataSource);
      Object obj = objIn.readObject();
      try
      {
         serializedDataSource.close();
         objIn.close();
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
      return obj;
   }
  
   public String getObjectToString()
   {
      if (objectToString == null) 
         return "";
      return objectToString;
   }
   
   public void writeExternal(ObjectOutput out) throws IOException
   {
   }
   
   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
   {
   }
   
   public Object writeReplace() throws ObjectStreamException
   {
      ExtClass e = new ExtClass();
      e.serializedData = serializedData;
      e.objectToString = objectToString;
      return e;
   }
   
   /*-------------------Attributes-----------------------------------------------*/
   
   private byte[] serializedData;
   
   private String objectToString = null;
   
   /*-------------------Inner Classes--------------------------------------------*/
   
   /**
    * This class exists purely for performance reasons. It provides an
    * accessor to the internal byte[] and means that it does not need to
    * be copied.
    */
   private static class AccesibleByteArrayOutputStream extends ByteArrayOutputStream
   {
      
      public byte[] getByteArray()
      {
         return buf;
      }
   }
   
   public interface InputStreamFactory
   {
      public ObjectInput create(InputStream in) throws IOException;
   }
   
   private class BasicInputStreamFactory implements InputStreamFactory
   {
      public ObjectInput create(InputStream in) throws IOException
      {
         return new ObjectInputStream(in);
      }
   }
   
   /**
    * This class exists because the main class cannot have a no-arg
    * constructor as required by externalizable.
    *
    * On serialization, this object replaces the SerializedObject.
    */
   
   private static class ExtClass implements Externalizable
   {
      public ExtClass()
      {}
      
      public void writeExternal(ObjectOutput out) throws IOException
      {
         if(serializedData == null)
            out.writeInt(0);
         else
         {
            out.writeInt(serializedData.length);
            out.write(serializedData, 0, serializedData.length);
         }
         out.writeObject(objectToString);
      }
      
      public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
      {
         int arrayLength = in.readInt();
         if(arrayLength > 0)
         {
            serializedData = new byte[arrayLength];
            in.readFully(serializedData);
         }
         else
            serializedData = null;
         objectToString = (String)in.readObject();
      }
      
      public Object readResolve() throws ObjectStreamException
      {
         return new SerializedObject(serializedData, objectToString);
      }
      
      public byte[] serializedData;
      public String objectToString = null;
   }
}