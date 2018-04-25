
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

import jcsp.net.settings.*;

/**
 *
 *
 * @author Quickstone Technologies Limited.
 */
class PingRequirement extends Requirement
{
   PingRequirement(int intValue, int acceptableDifference)
   {
      super(XMLConfigConstants.REQ_NAME_MAXPING,
            XMLConfigConstants.SPEC_NAME_PING,
            XMLConfigConstants.REQ_COMPARATOR_LESS,
            intValue);
      if(intValue < 0 || acceptableDifference < 0)
         throw new IllegalArgumentException("Illegal arguments - a value is less than zero.");
      this.acceptableDifference = acceptableDifference;
   }
   
   public boolean matches(Specification spec)
   {
      if(this.specName.equals(spec.name) && this.type.equals(spec.type))
      {
         if(intValue > spec.intValue)
            //This object is greater than the other object - i.e. the other
            //object is less than this object
            return true;
         else if(spec.intValue - intValue <= acceptableDifference)
            //This object is less than the other object - i.e. the other
            //object is greater than this object
            return true;
      }
      return false;
   }
   
   final int acceptableDifference;
}