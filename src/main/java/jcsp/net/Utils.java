
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

/**
 * This class provides some utility methods that developers
 * might find useful when implementing JCSP.NET communication protocol
 * drivers.
 *
 *
 * @author Quickstone Technologies Limited
 */
public class Utils
{
   /*-----------------Array - String Utils---------------------------------------*/
   
   /**
    * <p>
    * This takes a String[] and returns a String that can be converted
    * back into an identical String[] using the stringToArray method.
    * </p>
    * <p>
    * The intended purpose of this is to possibly aid in implementing the
    * getStringForm() method of the abstract NodeAddressID class.
    * </p>
    * @param	array	the String[] to convert into a String.
    * @return	the converted array as a String.
    */
   public static String arrayToString(String[] array)
   {
      StringBuffer sb = new StringBuffer();
      
      for(int i=0; i<array.length; i++)
      {
         sb.append(stuffString(array[i]));
         if(i<array.length - 1) 
            sb.append("\"\\\"");
      }
      return sb.toString();
   }
   
   private static String stuffString(String str)
   {
      StringBuffer sb = new StringBuffer(str);
      int charsInserted = 0;
      int foundIndex1 = str.indexOf("\"", 0);
      int foundIndex2 = str.indexOf("\\", 0);
      int foundIndex = -1;
      if(foundIndex1 > -1 && foundIndex1 < foundIndex2)
         foundIndex = foundIndex1;
      else
         foundIndex = foundIndex2;
      while(foundIndex > -1)
      {
         sb.insert(foundIndex + charsInserted, "\\");
         charsInserted = charsInserted + 1;
         foundIndex1 = str.indexOf("\"", ++foundIndex);
         foundIndex2 = str.indexOf("\\", foundIndex);
         if(foundIndex1 > -1 && (foundIndex1 < foundIndex2 || foundIndex2 == -1))
            foundIndex = foundIndex1;
         else
            foundIndex = foundIndex2;
      }
      return sb.toString();
   }
   
   /**
    * <p>
    * This takes a String in the form of that returned from the
    * arrayToString method and returns a String[].
    * </p>
    * <p>
    * The intended purpose of this is to possibly aid in implementing the
    * getAddressIDFromString() method of the abstract NodeAddressID class.
    * </p>
    * @param	str	the String to convert back into a String[].
    * @return	the converted String as a String[].
    */
   public static String[] stringToArray(String str)
   {
      int count = 1;
      String delim = "\"\\\"";
      int pos = str.indexOf(delim,0);
      while(pos > -1)
      {
         count++;
         pos = str.indexOf(delim, pos+1);
      }
      String[] strings = new String[count];
      int lastPos = 0;
      for(int i = 0; i<count; i++)
      {
         pos = str.indexOf(delim,lastPos);
         if(pos == -1) 
            pos = str.length();
         strings[i] = deStuffString(str.substring(lastPos, pos));
         lastPos = pos + delim.length();
      }
      return strings;
   }
   
   private static String deStuffString(String str)
   {
      // Uses StringBuffer::deleteCharAt
      StringBuffer sb = new StringBuffer(str);
      int charsRemoved = 0;
      int foundIndex1 = str.indexOf("\\\"", 0);
      int foundIndex2 = str.indexOf("\\\\", 0);
      int foundIndex = -1;
      if(foundIndex1 > -1 && foundIndex1 < foundIndex2)
         foundIndex = foundIndex1;
      else
         foundIndex = foundIndex2;
      while(foundIndex > -1)
      {
         sb.deleteCharAt(foundIndex - charsRemoved);
         charsRemoved = charsRemoved + 1;
         foundIndex1 = str.indexOf("\\\"", foundIndex + 2);
         foundIndex2 = str.indexOf("\\\\", foundIndex + 2);
         if(foundIndex1 > -1 && (foundIndex1 < foundIndex2 ||  foundIndex2 == -1) )
            foundIndex = foundIndex1;
         else
            foundIndex = foundIndex2;
      }
      return sb.toString();
   }
}