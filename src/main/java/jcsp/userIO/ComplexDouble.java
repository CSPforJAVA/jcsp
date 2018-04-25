
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

package jcsp.userIO;

/**
 * @author Quickstone Technologies Limited
 */
public class ComplexDouble implements Cloneable {

    public ComplexDouble(double d, double d1) {
        real = d;
        imag = d1;
    }

    public ComplexDouble add(ComplexDouble complexdouble) {
        real += complexdouble.real;
        imag += complexdouble.imag;
        return this;
    }

    public ComplexDouble addImag(double d) {
        imag += d;
        return this;
    }

    public ComplexDouble addReal(double d) {
        real += d;
        return this;
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException clonenotsupportedexception) {
            System.out.println(
                String.valueOf(clonenotsupportedexception)
                    + " -- can't be happening !!!");
        }
        return obj;
    }

    public ComplexDouble div(ComplexDouble complexdouble) {
        double d =
            complexdouble.real * complexdouble.real
                + complexdouble.imag * complexdouble.imag;
        double d1 = (real * complexdouble.real + imag * complexdouble.imag) / d;
        imag = (imag * complexdouble.real - real * complexdouble.imag) / d;
        real = d1;
        return this;
    }

    public double getImag() {
        return imag;
    }

    public double getReal() {
        return real;
    }

    public double modulus() {
        return Math.sqrt(real * real + imag * imag);
    }

    public double modulusSquared() {
        return real * real + imag * imag;
    }

    public ComplexDouble mult(ComplexDouble complexdouble) {
        double d = real * complexdouble.real - imag * complexdouble.imag;
        imag = imag * complexdouble.real + real * complexdouble.imag;
        real = d;
        return this;
    }

    public ComplexDouble scale(double d) {
        real *= d;
        imag *= d;
        return this;
    }

    public ComplexDouble set(double d, double d1) {
        real = d;
        imag = d1;
        return this;
    }

    public ComplexDouble set(ComplexDouble complexdouble) {
        real = complexdouble.real;
        imag = complexdouble.imag;
        return this;
    }

    public ComplexDouble setImag(double d) {
        imag = d;
        return this;
    }

    public ComplexDouble setReal(double d) {
        real = d;
        return this;
    }

    public ComplexDouble sub(ComplexDouble complexdouble) {
        real -= complexdouble.real;
        imag -= complexdouble.imag;
        return this;
    }

    private double real;
    private double imag;
}
