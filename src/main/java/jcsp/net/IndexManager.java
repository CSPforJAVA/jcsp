
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

import jcsp.lang.*;
import jcsp.util.*;
import jcsp.util.filter.*;

/**
 *
 *
 * @author Quickstone Technologies Limited.
 */
class IndexManager
{
   /*-------------------Singleton Class Instance---------------------------------*/
   
   private static IndexManager instance = new IndexManager();
   
   /*-------------------Private Constructor--------------------------------------*/
   
   IndexManager()
   {
   }
   
   /*----------------------Methods-----------------------------------------------*/
   
   public static IndexManager getInstance()
   {
      return instance;
   }
   
   synchronized ChannelAndIndex getNewChannel(String label)
   {
      long index = indexValue++;
      while(channels.get(index) != null)
         index = indexValue++;
      FilteredAny2OneChannel fromNet = FilteredChannel.createAny2One(new InfiniteBuffer());

      channels.put(index, fromNet.out());
      ChannelAndIndex toReturn = new ChannelAndIndex(fromNet, index);
      
      if (label != null)
      {
         // a label must be assigned to the channel index
         if (labelToIndexMap.containsKey(label))
            throw new DuplicateChannelLabelException("Channel already exists with VCN label of " + label);
         Long longVal = new Long(index);
         labelToIndexMap.put(label, longVal);
         indexToLabelMap.put(longVal, label);
      }
      return toReturn;
   }
   
   synchronized ChannelAndIndex getNewReplyChannel(One2NetChannel writer)
   {
      long index = indexValue++;
      while(channels.get(index) != null)
         index = indexValue++;
      FilteredAny2OneChannel fromNet = FilteredChannel.createAny2One(new AcknowledgementsBuffer());
     
      channels.put(index, fromNet.out());
      ChannelAndIndex toReturn = new ChannelAndIndex(fromNet, index);
      replyChannels.put(fromNet.out(), writer);
      return toReturn;
   }
   
   synchronized void removeChannel(long channelIndex, SharedChannelOutput currentChannel)
   {
      replyChannels.remove(currentChannel);
      if (channels.remove(channelIndex, currentChannel))
      {
         try
         {
            FilteredChannelOutput filtered
                    = (FilteredChannelOutput) currentChannel;
            filtered.addWriteFilter(POISON_FILTER);
         }
         catch (ClassCastException e)
         {
            e.printStackTrace();
         }
         
         Object label = indexToLabelMap.remove(new Long(channelIndex));
         if (label != null)
            labelToIndexMap.remove(label);
      }
      else
         Node.err.log(this, "Request to remove channel from IndexManager failed.");
   }
   
   synchronized void addReservedChannel(SharedChannelOutput channel, long channelIndex)
   {
      if (channel == null) 
         throw new NullPointerException();
      
      //This returns false if the channelIndex has already been used
      if(!channels.put(channelIndex, channel))
         throw new IllegalArgumentException("Channel index " + channelIndex + " is not a reserved channel");
   }
   
   synchronized ChannelOutput getRxChannel(long channelIndex)
   {
      return channels.get(channelIndex);
   }
   
   synchronized ChannelOutput getRxChannel(String vcnLabel)
   {
      Long channelIndex = (Long) labelToIndexMap.get(vcnLabel);
      if (channelIndex != null)
         return getRxChannel(channelIndex.longValue());
      return null;
   }
   
   synchronized int broadcast(Object message)
   {
      int count = 0;
      for (Enumeration en = channels.getChannels(); en.hasMoreElements();)
      {
         ChannelOutput ch = (ChannelOutput)en.nextElement();
         if (ch != null)
         {
            count++;
            ch.write(message);
         }
      }
      return count;
   }
   
   /**
    * Reply channels have their destroyWriter method called. Other channels get sent the
    * broadcast message.
    */
   synchronized int broadcastLinkLost(Object message)
   {
      int count = 0;
      for (Enumeration en = channels.getChannels(); en.hasMoreElements();)
      {
         ChannelOutput ch = (ChannelOutput)en.nextElement();
         if (ch != null)
         {
            One2NetChannel nco = (One2NetChannel)replyChannels.get(ch);
            if (nco != null)
               nco.linkFailed(((LinkLost)message).address);
            else
            {
               count++;
               ch.write(message);
            }
         }
      }
      return count;
   }
   
   static boolean checkIndexIsValid(long vcn)
   {
      return (vcn != -1);
   }
   
   static long getInvalidVCN()
   {
      return -1;
   }
   
   /*----------------------Attributes--------------------------------------------*/
   
   //a load factor of three seems quite efficient
   private ChannelIndexMap channels = new ChannelIndexMap(23, 3f);
   
   private long indexValue = 0;
   
   //private DeserializeChannelFilter dcf;
   
   private Hashtable labelToIndexMap = new Hashtable();
   private Hashtable indexToLabelMap = new Hashtable();
   
   private Hashtable replyChannels = new Hashtable();
   
   public static final long CNS_CHANNEL_INDEX = 0;
   
   private static final PoisonFilter POISON_FILTER = new PoisonFilter("Channel disabled by IndexManager");
   
   static class ChannelAndIndex
   {
      public ChannelAndIndex(Any2OneChannel channel, long index)
      {
         this.channel = channel;
         this.index = index;
      }
      
      public final Any2OneChannel channel;
      public final long index; 
   }
}
