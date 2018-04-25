
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

import java.util.Enumeration;
import java.util.NoSuchElementException;
import jcsp.lang.ChannelOutput;
import jcsp.lang.SharedChannelOutput;

/**
 *
 *
 * @author Quickstone Technologies Limited.
 */
class ChannelIndexMap
{
   
   public ChannelIndexMap(int size, float loadFactor)
   {
      this.size = size;
      this.loadFactor = loadFactor;
      this.threshold = (int)(size * loadFactor);
      this.count = 0;
      data = new Entry[size];
   }
   
   public ChannelOutput get(long key)
   {
      int dataIndex = (int)(key % size);
      if(data[dataIndex] != null)
      {
         for(Entry e = data[dataIndex]; e != null; e = e.next)
            if(e.key == key) 
               return e.value;
      }
      return null;
   }
   
   /**
    * Puts a value into the index if the key does not already exist.
    *
    *
    */
   public boolean put(long key, SharedChannelOutput value)
   {
      count++;
      if(count > threshold)
         rehash();
      final int dataIndex = (int) (key % size);
      final Entry head = data[dataIndex];
      for(Entry e = head; e != null; e = e.next)
         if(e.key == key)
            return false;
      //There is no matching key
      if(entryPool == null)
         //no entries in pool - create one
         data[dataIndex] = new Entry(key,value,head);
      else
      {
         //take an entry from the pool
         Entry e = entryPool;
         entryPool = e.next;
         e.key = key;
         e.value = value;
         e.next = head;
         data[dataIndex] = e;
      }
      return true;
   }
   
   public boolean remove(long key, SharedChannelOutput ch)
   {
      int dataIndex = (int) (key % size);
      Entry prev = null;
      for(Entry e = data[dataIndex]; e != null; e = e.next)
      {
         if(e.key == key)
         {
            if(e.value != ch) 
               return false;
            if(prev == null)
               data[dataIndex] = e.next;
            else
               prev.next = e.next;
            e.next = entryPool;
            entryPool = e;
            entryPool.value = null;
            count--;
            return true;
         }
         else
            prev = e;
      }
      return false;
   }
   
   private void rehash()
   {
      Entry oldMap[] = data;
      int oldCapacity = size;
      
      //update size with the new capacity
      size = oldCapacity * 2 + 1;
      Entry newMap[] = new Entry[size];
      
      threshold = (int)(size * loadFactor);
      data = newMap;
      
      for (int i = oldCapacity ; i-- > 0 ;)
      {
         for (Entry old = oldMap[i] ; old != null ; )
         {
            Entry e = old;
            old = old.next;
            int index = (int)(e.key % size);
            e.next = newMap[index];
            newMap[index] = e;
         }
      }
   }
   
   public Enumeration getChannels()
   {
      return new Enumeration()
      {
         public boolean hasMoreElements()
         {
            return returnCount < count;
         }
         
         public Object nextElement()
         {
            while(nextEntry == null && bucketIndex < data.length)
            {
               bucketIndex++;
               nextEntry = data[bucketIndex];
            }
            if(nextEntry == null)
               throw new NoSuchElementException();
            ChannelOutput value = nextEntry.value;
            nextEntry = nextEntry.next;
            returnCount++;
            return value;
         }
         int bucketIndex = -1;
         int returnCount = 0;
         Entry nextEntry = null;
      };
   }
   
   public void emptyPool()
   {
      entryPool = null;
   }
   
   private int threshold;
   private int count;
   private float loadFactor;
   
   private int size;
   private Entry[] data;
   
   private Entry entryPool = null;
   
   private static class Entry
   {
      long key;
      ChannelOutput value;
      Entry next;
      
      Entry(long key,ChannelOutput value,Entry next)
      {
         this.key = key;
         this.value = value;
         this.next = next;
      }
      
      Entry(long key,ChannelOutput value)
      {
         this.key = key;
         this.value = value;
      }
   }
}