
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
class ChannelID implements Serializable
{
   
   /*-------------------Constructor----------------------------------------------*/
   /**
    * Creates a ChannelID for a channel.
    *
    * @param nodeID The host node.
    * @param index the channel index.
    */
   public ChannelID(NodeID nodeID, long index)
   {
      this.nodeID = nodeID;
      this.index = index;
   }
   
   ChannelID(String stringForm) throws IllegalArgumentException
   {
      if(stringForm == null) 
         throw new IllegalArgumentException("null");
     
      int colonIndex = stringForm.indexOf(":",STRING_FORM_PREFIX.length());
      if(!stringForm.startsWith(STRING_FORM_PREFIX))
         throw new IllegalArgumentException("Invalid String provided");
      
      String channelIndexString = stringForm.substring(STRING_FORM_PREFIX.length(),colonIndex);
      try
      {
         index = Long.parseLong(channelIndexString);
      }
      catch (NumberFormatException  e)
      {
         throw new IllegalArgumentException("Channel Index not a number");
      }
      nodeID = NodeID.createFromStringForm(stringForm.substring(colonIndex + 1));
   }
   
   /*-------------------Public Methods-------------------------------------------*/
   
   /**
    * This returns a ChannelID created from its String form as returned by
    * the getStringForm method.
    *
    * @param	stringForm	the String to use to create the ChannelID.
    * @return	the recreated ChannelID.
    */
   static ChannelID createFromStringForm(String stringForm) throws IllegalArgumentException
   {
      return new ChannelID(stringForm);
   }
   
   /**
    * This returns a String representation of this ChannelID.  The ChannelID
    * can be recreated by calling the createFromString method.
    *
    * @return	the String representing this ChannelID.
    */
   String getStringForm()
   {
      return STRING_FORM_PREFIX + index + ":" + nodeID.getStringForm();
   }
   
   /**
    * Returns the channel's NodeID.
    *
    * @return the channel's NodeID.
    */
   public final NodeID getNodeID()
   {
      return nodeID;
   }
   
   /**
    * Returns the channel's index number.
    *
    * @return the channel's index number.
    */
   public final long getIndex()
   {
      return index;
   }
   
   /**
    * Compares two ChannelIDs for equality.
    *
    * @param obj Another <CODE>Object</CODE> to compare with this <CODE>Object</CODE>.
    * @return true iff obj is a non-null <CODE>ChannelID</CODE> which represents the
    *        same channel.
    */
   public final boolean equals(Object obj)
   {
      if ((obj == null) || !(obj instanceof ChannelID))
         return false;
      ChannelID other = (ChannelID)obj;
      return (index == other.index && (nodeID.equals(other.getNodeID())));
   }
   
   /** Returns a human readable string representation of the current instance of this class.
    * @return a human readable <CODE>String</CODE> object.
    */
   public String toString()
   {
      return nodeID + "--" + index;
   }
   
   /*-------------------Private Attributes---------------------------------------*/
   
   private NodeID nodeID;
   
   private long index;
   
   private static final String STRING_FORM_PREFIX = "ChannelID:";
}