
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

package jcsp.net.cns;

import java.util.*;
import java.io.*;

/**
 * Instances of this hold a key for a particular channel.
 * <CODE>ChannelNameKey</CODE> objects are issued by the channel
 * name server.  They must be supplied to the channel name server
 * along with any channel management requests (channel deregistration,
 * relocation etc.).
 *
 * This class has no public constructor.
 *
 *
 * @author Quickstone Technologies Limited
 */
public final class ChannelNameKey implements Serializable
{
   private int hashCode;
   private final long val;
   private final long key;
   
   ChannelNameKey(long val)
   {
      this.val = val;
      Random rnd = new Random(System.currentTimeMillis() - Runtime.getRuntime().freeMemory() + val);
      key = rnd.nextLong();
      hashCode = new Long(val).hashCode();
   }
   
   /**
    * Compares another <CODE>Object</CODE> with this
    * <CODE>ChannelNameKey</CODE>.
    *
    * @param o the other <CODE>Object</CODE> to compare with
    *           this <CODE>Object</CODE>.
    * @return <CODE>true</CODE> iff <CODE>o</CODE> is a non-null
    *          <CODE>ChannelNameKey</CODE> object which holds the
    *          same key as this object.
    */
   public boolean equals(Object o)
   {
      if (o == null || !(o instanceof ChannelNameKey))
         return false;
      ChannelNameKey other = (ChannelNameKey) o;
      return val == other.val && key == other.key;
   }
   
   /**
    * Returns an <CODE>int</CODE> hash code for this object.
    *
    * @return an <CODE>int</CODE> hash code.
    */
   public int hashCode()
   {
      return hashCode;
   }
}