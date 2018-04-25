
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

package jcsp.awt;

/**
 * A state of the {@link ActiveButtonControl} finite state machine.
 * <H2>Description</H2>
 * <TT>ActiveButtonState</TT> is one of the elements of the state table governing
 * the behaviour of the finite state machine within an {@link ActiveButtonControl}.
 * For each controlled button, it holds the index of the label to be displayed in
 * this state, whether that button should be enabled or disabled and which state
 * should be jumped to if that button is pressed.
 * See the <A HREF="ActiveButtonControl.html#Example">example</A> documented in
 * <TT>ActiveButtonControl</TT>.
 * <P>
 * @see ActiveButton
 * @see ActiveButtonControl
 *
 * @author P.H. Welch
 */

public class ActiveButtonState
{
   int[] labelId;     // button labels for this state
   
   boolean[] enable;  // button status for this state
   
   int[] next;        // next state after this button is pushed
   
   private boolean verbose;
   
   /**
    * Constructs a new <TT>ActiveButtonState</TT>, performing consistency
    * checks on its supplied arguments.
    *
    * @param labelId the button labels for this state.
    * @param enable the enable/disable status of each button for this state
    *   (true <==> enabled).
    * @param next the next state after each button is pushed.
    * @throws BadArguments if the consistency check fails.
    *   The exception contains details of the error.
    */
   public ActiveButtonState(int[] labelId, boolean[] enable, int[] next)
      throws BadArguments
   {
      this(labelId, enable, next, false);
   }
   
   /**
    * Constructs a new <TT>ActiveButtonState</TT>, performing consistency
    * checks on its supplied arguments, with a <I>verbose</I> reporting option.
    *
    * @param labelId the button labels for this state.
    * @param enable the enable/disable status of each button for this state
    *   (true <==> enabled).
    * @param next the next state after each button is pushed.
    * @param verbose if true, a running commentary is printed on the consistency checks.
    * @throws BadArguments if the consistency check fails.
    *   The exception contains details of the error.
    */
   public ActiveButtonState(int[] labelId, boolean[] enable, int[] next, boolean verbose)
      throws BadArguments
   {
      if (verbose) 
         System.out.println("ActiveButtonState creating ...");
      
      // sanity checks
      
      if (labelId == null) 
         throw new BadArguments("labelId == null");
      if (enable == null) 
         throw new BadArguments("enable == null");
      if (next == null) 
         throw new BadArguments("next == null");
      if (verbose) 
         System.out.println("ActiveButtonState: arguments not null");
      
      if (labelId.length != enable.length)
         throw new BadArguments("labelId.length != enable.length");
      if (verbose) 
         System.out.println("labelId.length == enable.length");
      
      if (labelId.length != next.length)
         throw new BadArguments("labelId.length != next.length");
      if (verbose) 
         System.out.println("labelId.length == next.length");
      
      for (int i = 0; i < labelId.length; i++)
         if (labelId[i] < 0)
            throw new BadArguments("labelId[" + i + "] is negative");
      if (verbose) 
         System.out.println("all label indices positive");
      
      for (int i = 0; i < next.length; i++)
         if (next[i] < 0)
            throw new BadArguments("next[" + i + "] is negative");
      if (verbose) 
         System.out.println("all next states positive");
      
      if (verbose)
         for (int i = 0; i < next.length; i++)
            System.out.println("  " + labelId[i] + ", " + enable[i] + ", " + next[i]);
      
      // sanity checks complete
      
      this.labelId = labelId;
      this.enable = enable;
      this.next = next;
      
      this.verbose = verbose;
      
      if (verbose) 
         System.out.println("ActiveButtonState created OK");
   }
   
   void check(int i, int nButtons, int nStates, String[][] label)
      throws ActiveButtonControl.BadArguments
   {
      if (verbose) 
         System.out.println("ActiveButtonState " + i + " checking labelId.length (" + 
                 nButtons + ", " + labelId.length + ") ...");
      
      if (labelId.length != nButtons)
         throw new ActiveButtonControl.BadArguments("state[" + i + "]: table lengths != nButtons");
      
      if (verbose) 
         System.out.println("ActiveButtonState " + i + " checking next.length ...");
      
      for (int n = 0; n < next.length; n++)
         if (next[n] >= nStates)
            throw new ActiveButtonControl.BadArguments("state[" + i + "]: next[" + n + "] is too big");
      
      if (verbose) 
         System.out.println("ActiveButtonState " + i + " checking labelId[n] ...");
      
      for (int n = 0; n < labelId.length; n++)
         if (labelId[n] >= label[n].length)
            throw new ActiveButtonControl.BadArguments("state[" + i + "]: labelId[" + n + "] is too big");
      
      if (verbose) 
         System.out.println("ActiveButtonState " + i + " checked");
      
   }
   
   /**
    * This gets thrown if a consistency check fails in the {@link ActiveButtonState}
    * constructor.
    */
   public static class BadArguments extends Exception
   {
      /**
       *
       * @param s details of the consistency failure.
       */
      public BadArguments(String s)
      {
         super(s);
      }  
   }
}