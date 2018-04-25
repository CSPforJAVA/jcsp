//////////////////////////////////////////////////////////////////////
//                                                                  //
//  Mobile package for the JCSP ("CSP for Java") Libraries.         //
//  Copyright (C) 2006 Kevin Chalmers.                              //
//                                                                  //
// Licensed under the Apache License, Version 2.0 (the "License");  //
// you may not use this file except in compliance with the License. //
// You may obtain a copy of the License at                          //
//                                                                  //
//      http://www.apache.org/licenses/LICENSE-2.0                  //
//                                                                  //
// Unless required by applicable law or agreed to in writing,       //
// software distributed under the License is distributed on         //
// an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,  //
// either express or implied. See the License for the specific      //
// language governing permissions and limitations under the License.//
//                                                                  //
//                                                                  //
//                                                                  //
//                                                                  //
//  Author contact: K.Chalmers@napier.ac.uk                         //
//                                                                  //
//  Author contact: k.chalmers@napier.ac.uk                         //
//                  j.kerridge@napier.ac.uk                         //
//                                                                  //
//////////////////////////////////////////////////////////////////////


package jcsp.net2.mobile;

import java.io.*;
import jcsp.lang.*;

/**
 *
 * <p>Title: MobileProcess</p>
 *
 * <p>Description: The base class for Mobile Processes</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Napier University</p>
 *
 * <p>
 * This class provides the basic functionality needed for a Mobile Process, by implementing both CSProcess and Serializable,
 * and also providing methods to hook the process into an existing process network when it arrives.  This class does not
 * necessarily have to be used to create a Mobile Process, simply implementing CSProcess and Serializable will do the trick.
 * </p>
 *
 * @author Kevin Chalmers
 * @version 1.0
 */
public abstract class MobileProcess implements CSProcess, Serializable
{
   /**
    * An array of input ends to hook the process into an existing network.
    */
   protected ChannelInput[] inputs = null;

   /**
    * An arrat of output ends to hook the process into an existing network.
    */
   protected ChannelOutput[] outputs = null;

   /**
    * Initialises the mobile process by providing channels to connect it into an existing network.
    * @param inputs ChannelInput[]
    * @param outputs ChannelOutput[]
    */
   public final void init(ChannelInput[] inputs, ChannelOutput[] outputs)
   {
      this.inputs = inputs;
      this.outputs = outputs;
   }

   /**
    * Prepares the process to move again by setting all its channels to null.
    */
   public final void remove()
   {
      inputs = null;
      outputs = null;
   }

   /**
    * Attaches the Mobile Process with an array of input channels.
    * @param inputs ChannelInput[]
    */
   public final void attachInputs(ChannelInput[] inputs)
   {
      this.inputs = inputs;
   }

   /**
    * Attaches the Mobile process with an array of output channels.
    * @param outputs ChannelOutput[]
    */
   public final void attachOutputs(ChannelOutput[] outputs)
   {
      this.outputs = outputs;
   }

   /**
    * Detatches the output ends.
    */
   public final void detachOutputs()
   {
      outputs = null;
   }

   /**
    * Detaches the input ends.
    */
   public final void detachInputs()
   {
      inputs = null;
   }
}
