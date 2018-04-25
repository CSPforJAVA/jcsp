
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
import java.util.Vector;
import jcsp.net.*;
import jcsp.util.filter.*;

/**
 * Implements a migratable input channel end that can be used in an <code>Alternative</code>.
 *
 *
 * @author Quickstone Technologies Limited
 */
class MigratableAltingChannelInputImpl extends MigratableAltingChannelInput
{
   /**
    * Factory for creating filtered channel ends.
    */
   static FilteredChannelEndFactory FACTORY = new FilteredChannelEndFactory();
   
   /**
    * The actual channel input. This should not be read from.
    * Use the <code>filteredIn</code> channel.
    *
    */
   private transient NetAltingChannelInput actualIn;
   
   /**
    * A filtered channel input wrapper that wraps the <code>actualIn</code>
    * channel.
    *
    */
   private transient FilteredChannelInput filteredIn;
   
   /**
    * The reconnection manager for the channel.
    */
   private InputReconnectionManager mgr;
   
   /**
    * A Vector of <code>Filter</code> objects that is used during
    * serialization.
    *
    */
   private Vector filters = null;
   
   /**
    * Constructor for MigratableAltingChannelInputImpl.
    * This takes a <code>NetAltingChannelInput</code> and constructs
    * a default <code>InputReconnectionManager</code> object and then uses
    * the constructor taking a single <code>InputReconnectionManager</code>
    * object.
    *
    * <code>MigratableAltingChannelInputImpl</code> objects constructed with
    * this constructor make use of the default channel name service.
    *
    * @param in	the actual <code>NetAltingChannelInput</code> object to
    * 				use.
    */
   public MigratableAltingChannelInputImpl(NetAltingChannelInput in)
   {
      this(new InputReconnectionManagerCNSImpl(in));
   }
   
   /**
    * Constructs a new <code>MigratableAltingChannelInputImpl</code> with a specified reconnection
    * manager object. This should have been given the channel when it was constructed.
    *
    * @param mgr the reconnection manager.
    */
   public MigratableAltingChannelInputImpl(InputReconnectionManager mgr)
   {
      super(mgr.getInputChannel());
      actualIn = mgr.getInputChannel();
      if (actualIn == null)
         throw new NullPointerException("Input channel provided is null");
      filteredIn = FACTORY.createFiltered(actualIn);
      this.mgr = mgr;
   }
   
   /**
    * @see Networked#getChannelLocation()
    */
   public NetChannelLocation getChannelLocation()
   {
      return this.mgr.getCurrentLocation();
   }
   
   /**
    * Reads an object from the underlying channel.
    *
    * @return the object read.
    */
   public Object read()
   {
      if(filteredIn == null)
      {
         actualIn = mgr.getInputChannel();
         super.setChannel(actualIn);
         filteredIn = FACTORY.createFiltered(actualIn);
         if (filters != null)
         {
            for (int i=0; i < filters.size(); i++)
               filteredIn.addReadFilter((Filter)filters.elementAt(i));
            filters = null;
         }
      }
      Object o = filteredIn.read();
      return o;
   }
   
   /**
    * @see ReadFiltered#addReadFilter(Filter)
    */
   public void addReadFilter(Filter filter)
   {
      filteredIn.addReadFilter(filter);
   }
   
   /**
    * @see ReadFiltered#addReadFilter(Filter, int)
    */
   public void addReadFilter(Filter filter, int index)
   {
      filteredIn.addReadFilter(filter, index);
   }
   
   /**
    * @see ReadFiltered#removeReadFilter(Filter)
    */
   public void removeReadFilter(Filter filter)
   {
      filteredIn.removeReadFilter(filter);
   }
   
   /**
    * @see ReadFiltered#removeReadFilter(int)
    */
   public void removeReadFilter(int index)
   {
      filteredIn.removeReadFilter(index);
   }
   
   /**
    * @see ReadFiltered#getReadFilter(int)
    */
   public Filter getReadFilter(int index)
   {
      return filteredIn.getReadFilter(index);
   }
   
   /**
    * @see ReadFiltered#getReadFilterCount()
    */
   public int getReadFilterCount()
   {
      return filteredIn.getReadFilterCount();
   }
   
   /**
    * @see jcsp.net.NetChannelInputManager#destroyReader()
    */
   public void destroyReader()
   {
      actualIn.destroyReader();
   }
   
   /**
    * Returns <code>null</code>.
    */
   public Class getFactoryClass()
   {
      return null;
   }
   
   /**
    * @see jcsp.dynamic.MigratableChannelInput#prepareToMove()
    */
   public void prepareToMove()
   {
      mgr.prepareToMove();
   }
   
   /**
    * When serializing object to the output stream, the filters from the underlying channel are
    * extracted so that they too get serialized.
    *
    * @param out the stream to write the object to.
    */
   private void writeObject(ObjectOutputStream out) throws IOException
   {
      //the filtered channel input object used by this class is a
      //transient field. This code extracts the filters from the
      //filter channel input and stores them into a Filter[] field.
      //They are resinserted into a new filtered channel input on the
      //first call to read()
      if (filteredIn != null)
      {
         filters = new Vector(filteredIn.getReadFilterCount());
         for (int i=0; i < filteredIn.getReadFilterCount(); i++)
            filters.addElement(filteredIn.getReadFilter(i));
      }
      //writes all non-transient variables to the output stream
      out.defaultWriteObject();
   }
   
   /**
    * Reads the serialized object from an input stream. The default read implementation is used.
    *
    * @param in object source stream.
    */
   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
   {
      in.defaultReadObject();
   }
}