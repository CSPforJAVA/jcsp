
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
 * <p>
 * This class is a Factory that construct a unique identify
 * for this Node. There is only ever one Node per JVM.
 * </p>
 * <p>
 * This class can subclassed and the subclass can be set to be
 * used in place of this class. See the documentation for the
 * <CODE>Node</CODE> class.
 * </p>
 *
 *
 * @author Quickstone Technologies Limited
 */
public class UIFactory
{
   /**
    * <p>
    * Returns a Serializable object that is a unique identifier for this
    * Node. The object returned should support the
    * <CODE>equals(Object)</CODE> and <CODE>hashCode()</CODE> methods
    * of Object.
    * </p>
    *
    * @return a Serializable unique identifier for this JVM
    */
   public NodeUI getUIForThisJVM()
   {
      return new NodeUIImpl();
   }
}