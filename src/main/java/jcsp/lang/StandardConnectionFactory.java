
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

package jcsp.lang;

    /**
 * <p>
 * Implements a factory for creating connections.
 * </p>
 *
 * @author Quickstone Technologies Limited
 */
public class StandardConnectionFactory
        implements ConnectionFactory, ConnectionArrayFactory
{
    /**
     * @see ConnectionFactory#createOne2One
     */
    public <T> One2OneConnection<T> createOne2One()
    {
        return new One2OneConnectionImpl<T>();
    }

    /**
     * @see ConnectionFactory#createAny2One
     */
    public <T> Any2OneConnection<T> createAny2One()
    {
        return new Any2OneConnectionImpl<T>();
    }

    /**
     * @see ConnectionFactory#createOne2Any
     */
    public <T> One2AnyConnection<T> createOne2Any()
    {
        return new One2AnyConnectionImpl<T>();
    }

    /**
     * @see ConnectionFactory#createAny2Any
     */
    public <T> Any2AnyConnection<T> createAny2Any()
    {
        return new Any2AnyConnectionImpl<T>();
    }

    /**
     * @see ConnectionArrayFactory#createOne2One
     */
    public <T> One2OneConnection<T>[] createOne2One(int n)
    {
        One2OneConnection<T>[] toReturn = (One2OneConnection<T>[]) new One2OneConnection[n];
        for (int i = 0; i < n; i++)
            toReturn[i] = createOne2One();
        return toReturn;
    }

    /**
     * @see ConnectionArrayFactory#createAny2One
     */
    public <T> Any2OneConnection<T>[] createAny2One(int n)
    {
        Any2OneConnection<T>[] toReturn = (Any2OneConnection<T>[]) new Any2OneConnection[n];
        for (int i = 0; i < n; i++)
            toReturn[i] = createAny2One();
        return toReturn;
    }

    /**
     * @see ConnectionArrayFactory#createOne2Any
     */
    public <T> One2AnyConnection<T>[] createOne2Any(int n)
    {
        One2AnyConnection<T>[] toReturn = (One2AnyConnection<T>[]) new One2AnyConnection[n];
        for (int i = 0; i < n; i++)
            toReturn[i] = createOne2Any();
        return toReturn;
    }

    /**
     * @see ConnectionArrayFactory#createAny2Any
     */
    public <T> Any2AnyConnection<T>[] createAny2Any(int n)
    {
        Any2AnyConnection<T>[] toReturn = (Any2AnyConnection<T>[]) new Any2AnyConnection[n];
        for (int i = 0; i < n; i++)
            toReturn[i] = createAny2Any();
        return toReturn;
    }
}
