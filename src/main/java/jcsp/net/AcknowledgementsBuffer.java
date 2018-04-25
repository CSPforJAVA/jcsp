
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


import jcsp.util.ChannelDataStore;
import java.io.Serializable;

/**
 * This is used to create a buffered object channel that always accepts and
 * never loses any input.
 * <H2>Description</H2>
 * <TT>AcknowledgementsBuffer</TT> is an implementation of <TT>ChannelDataStore</TT> that yields
 * a <I>FIFO</I> buffered semantics for a channel.  When empty, the channel blocks readers.
 * However, its capacity is <I>infinite</I> (expanding to whatever is needed so far as
 * the underlying memory system will permit).  So, it <I>never</I> gets full and blocks
 * a writer.
 * See the static
 * {@link jcsp.lang.One2OneChannel#create(ChannelDataStore) <TT>create</TT>}
 * methods of {@link jcsp.lang.One2OneChannel} etc.
 * <P>
 * The <TT>getState</TT> method returns <TT>EMPTY</TT> or <TT>NONEMPTYFULL</TT>, but
 * never <TT>FULL</TT>.
 * <P>
 * An initial size for the buffer can be specified during construction.
 * <P>
 * This buffer will save memory by changing multiple ChannelMessage.Ack messages into
 * AcknowledgementsBuffer.Acks.
 *
 * @see jcsp.util.InfiniteBuffer
 *
 * @author Quickstone Technologies Limited
 */
//package-private
class AcknowledgementsBuffer implements ChannelDataStore, Serializable
{
   /**
    * The default size of the buffer
    */
   private static final int DEFAULT_SIZE = 8;
   
   /**
    * The initial size of the buffer
    */
   private int initialSize;
   
   /**
    * The storage for the buffered Objects
    */
   private Object[] buffer;
   
   /**
    * The number of Objects stored in the InfiniteBuffer
    */
   private int counter = 0;
   
   /**
    * The index of the first element
    */
   private int firstIndex = 0;
   
   /**
    * The index of the last element
    */
   private int lastIndex = 0;
   
   /**
    * The Acks which is currently in the buffer.
    */
   private Acks acks = null;
   
   /**
    * Compressed form of one or more acknowledgements.
    */
   //package-private
   static class Acks
   {
      /**
       * Count of acknowledgements
       */
      //package-private
      int count = 1;
      long vcn = -1;
      NodeID sourceNodeID = null;
      
      /**
       * Default Constructor.
       */
      //package-private
      Acks()
      {
      }
   }
   
   
   /**
    * Construct a new <TT>InfiniteBuffer</TT> with the default size (of 8).
    */
   //package-private
   AcknowledgementsBuffer()
   {
      this(DEFAULT_SIZE);
   }
   
   /**
    * Construct a new <TT>AcknowledgementsBuffer</TT> with the specified initial size.
    *
    * @param initialSize the number of Objects
    * the <TT>AcknowledgementsBuffer</TT> can initially  store
    */
   //package-private
   AcknowledgementsBuffer(int initialSize)
   {
      this.initialSize = initialSize;
      buffer = new Object[initialSize + 1];
   }
   
   
   /**
    * Returns the oldest <TT>Object</TT> from the <TT>InfiniteBuffer</TT> and removes it.
    * <P>
    * <I>Pre-condition</I>: <TT>getState</TT> must not currently return <TT>EMPTY</TT>.
    *
    * @return the oldest <TT>Object</TT> from the <TT>InfiniteBuffer</TT>
    */
   public Object get()
   {
      Object value = buffer[firstIndex];
      buffer[firstIndex] = null;
      firstIndex = (++firstIndex) % buffer.length;
      counter--;
      if (value == acks)
         acks = null;
      return value;
   }
   
   public Object startGet()
   {
      Object value = buffer[firstIndex];
      if (value == acks)
        acks = null;
      return value;
   }
   
   public void endGet()
   {
      buffer[firstIndex] = null;
      firstIndex = (++firstIndex) % buffer.length;
      counter--;      
   }
   
   /**
    * Puts a new <TT>Object</TT> into the <TT>InfiniteBuffer</TT>.
    * <P>
    * <I>Implementation note:</I> if <TT>InfiniteBuffer</TT> is full, a new internal
    * buffer with double the capacity is constructed and the old data copied across.
    *
    * @param value the Object to put into the InfiniteBuffer
    */
   public void put(Object value)
   {
      if (value instanceof ChannelMessage.Ack)
      {
         if (acks == null)
         {
            // Add new Acks entry to array.
            acks = new Acks();
             /*
              This was put in so that a channel which used a label instead
              of a vcn could obtain the destination's actual vcn.
              This only really needs to be done once so perhaps this
              should be changed.
              */
            acks.vcn = ((ChannelMessage.Ack)value).sourceIndex;
            acks.sourceNodeID = ((ChannelMessage.Ack)value).sourceID;
            value = acks;
         }
         else
         {
            // Just increment Acks counter.
            // Don't add anything to array.
            acks.count++;
            return;
         }
      }
      
      // Add value to array
      if (counter == buffer.length)
      {
         Object[] temp = buffer;
         buffer = new Object[buffer.length * 2];
         System.arraycopy(temp, firstIndex, buffer, 0, temp.length - firstIndex);
         System.arraycopy(temp, 0, buffer, temp.length - firstIndex, firstIndex);
         firstIndex = 0;
         lastIndex = temp.length;
      }
      buffer[lastIndex] = value;
      lastIndex = (++lastIndex) % buffer.length;
      counter++;
   }
   
   /**
    * Returns the current state of the <TT>AcknowledgementsBuffer</TT>.
    *
    * @return the current state of the <TT>AcknowledgementsBuffer</TT> (<TT>EMPTY</TT> or
    * <TT>NONEMPTYFULL</TT>)
    */
   public int getState()
   {
      if (counter == 0)
         return EMPTY;
      else
         return NONEMPTYFULL;
   }
   
   /**
    * Returns a new (and <TT>EMPTY</TT>) <TT>AcknowledgementsBuffer</TT> with the same
    * creation parameters as this one.
    * <P>
    * <I>Note: Only the initial size and structure of the </I><TT>AcknowledgementsBuffer</TT><I>
    * is cloned, not any stored data.</I>
    *
    * @return the cloned instance of this <TT>AcknowledgementsBuffer</TT>.
    */
   public Object clone()
   {
      return new AcknowledgementsBuffer(initialSize);
   }
   
   public void removeAll()
   {	   
	   counter = 0;	   
	   firstIndex = 0;	   
	   lastIndex = 0;	   
	   acks = null;
   }
}