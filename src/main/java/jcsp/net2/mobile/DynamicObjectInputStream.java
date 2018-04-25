
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

package jcsp.net2.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * @author Kevin
 */
final class DynamicObjectInputStream
    extends ObjectInputStream
{
    final DynamicClassLoader dcl;

    DynamicObjectInputStream(InputStream is, DynamicClassLoader loader)
        throws IOException
    {
        super(is);
        this.dcl = loader;
    }

    protected Class resolveClass(ObjectStreamClass desc)
        throws IOException, ClassNotFoundException
    {
        return dcl.loadClass(desc.getName());
    }
}
