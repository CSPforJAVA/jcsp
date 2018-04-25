
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

package jcsp.net.settings;

/**
 * Used internally within the JCSP network infrastructure to represent a connection oriented protocol.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class ConnectionOriented extends Spec implements Req
{
   ConnectionOriented(boolean connectionOriented, boolean isReq)
   {
      super(SPEC_NAME_CONNECTION_ORIENTED, true, isReq);
      this.connectionOriented = connectionOriented;
   }
   
   public String getStringValue()
   {
      return "" + connectionOriented;
   }
   
   public boolean getValue()
   {
      return connectionOriented;
   }
   
   public Class getType()
   {
      return Boolean.TYPE;
   }
   
   public String getComparator()
   {
      return REQ_COMPARATOR_EQUALS;
   }
   
   public int getIntValue()
   {
      throw new UnsupportedOperationException("Type is boolean");
   }
   
   public double getDoubleValue()
   {
      throw new UnsupportedOperationException("Type is boolean");
   }
   
   public boolean getBooleanValue()
   {
      return getValue();
   }
   
   public boolean equals(Object o)
   {
      if(o instanceof ConnectionOriented)
      {
         ConnectionOriented other = (ConnectionOriented) o;
         return connectionOriented == other.connectionOriented;
      }
      return false;
   }
   
   public int hashCode()
   {
      if(connectionOriented)
         return 1;
      else
         return 0;
   }
   
   private boolean connectionOriented;
}