This library contains the specification for the JCSP core API.

Please read the LICENCE as further use of the library implies acceptance of either of the licences (Apache 2.0 or
GNU LPGL 2.1).

**Overview**

JCSP provides a complete library for building complex functionality through layered networks of 
communicating processes. It conforms to the CSP model of communicating systems so that many (>30) years of theory, 
tools and practical experience can be brought to bear in the support of Java multi-threaded applications. 
Newcomers to JCSP should start with the documentation on the CSProcess interface, which outlines and motivates the 
notion of components as communicating processes and the process oriented design pattern.

Processes interact solely via CSP synchronising primitives, such as Channels (One2OneChannel, Any2OneChannel, 
One2AnyChannel, Any2AnyChannel), One2OneCallChannel CALL channels, CSTimer timers, Crew crews, Barrier barriers, 
AltingBarrier alting barriers, Bucket buckets, or other well-defined modes of access to shared passive objects. 
Processes do **not** invoke each other's methods. Processes may be combined to run in sequence or parallel. 
Processes may be combined to wait passively on a number of alternative events, with one of them triggered into action 
only by the external generation of that event. Such collections of events may be serviced either 
fairly (using fairSelect() - guaranteeing no starvation of one event by the repeated arrival of its siblings), 
by any user-defined priority (using priSelect())or in an arbitrary manner (using select()).

JCSP is an alternative to the built-in monitor model for Java threads. JCSP primitives should not normally be mixed 
into designs with synchronized method declarations, instances of the java.lang.Runnable interface or 
java.lang.Thread class, or invocations of the wait/notify/notifyAll methods from java.lang.Object.

However, JCSP is compatible with the built-in model and, with care, can be mixed safely and profitably. In particular, 
process communication via wait-free synchronized method invocations on a shared passive object directly implements a 
common CSP server idiom (see jcsp.awt.DisplayList for an example). Further, existing libraries that interact with 
user software via listener registration and callback (such as the standard AWT and Swing) can be easily tailored to 
operate as processes with channel-based interfaces (for example, org.jcsp.awt).

Finally, we note that the JCSP library reflects the occam-pi realisation of CSP and pi-calculus. An occam-pi PROC 
declaration maps simply into a class implementing CSProcess, whose constructor parameters mirror the PROC parameters 
and whose run method mirrors the PROC body.

**Acknowledgements**

This JCSP library is being developed from work originally carried out and presented by numerous individuals at the 
WoTUG Java Threads Workshop and conferences (WoTUG-20, WoTUG-21, WoTUG-22, WoTUG-23/CPA-2000, WoTUG-24/CPA-2001, 
WoTUG-25/CPA-2002, WoTUG-26/CPA-2003, WoTUG-27/CPA-2004, WoTUG-28/CPA-2005, WoTUG-29/CPA-2006, WoTUG-30/CPA-2007, 
WoTUG-31/CPA-2008) in the period 1996-2008 and continuing. 

Available at:
 http://wotug.org/paperdb/ 

Special thanks are owed to Paul Austin, who developed the original JCSP library.

**Using the Library**

In a build.gradle file you should use:
please note this is yet to be done

repositories {
    jcenter()
}
dependencies {
    compile group: 'jcsp', name: 'jcsp', version: '1.1.0'
}

**Building the Library**

The src folder contains both main and test folders in the format used by Gradle.
To install to your local Maven repo, use

gradle clean install

**References**

`Communicating Sequential Processes', C.A.R. Hoare, CACM, 21-8, pp. 666-677, August 1978.

    This is the original exposition of CSP. Presentation is largely from the programmer's point of view. The Ada 
    tasking model and the occam/transputer process model were derived from this work. 

`Communicating Sequential Processes', C.A.R. Hoare, Prentice Hall, 1985.

    This is `the book'. Presentation is more abstract (i.e. mathematical) and a more general theory is 
    given than in the original CACM paper. One crucial difference is that events (and channels) become 
    concepts that are separate from the processes that engage in them. This difference was adopted in the 
    occam/transputer model, but Ada missed out on it. The JCSP library follows the model presented in this book. 

`The Theory and Practice of Concurrency', A.W. Roscoe, Prentice Hall, ISBN 0-13-674409-5, 1997.

    This brings CSP up to date with refinements developed over the past decade. It is `the new book'. 

`Communicating Sequential Processes and Deadlock' J.M.R.Martin, Chapter 1 from his Ph.D thesis 
("The Design and Construction of Deadlock-Free Concurrent Systems"), University of Buckingham, UK, 1996.

    This gives a crisp, amusing and accurate tour around all the CSP concepts, strongly recommended for 
    beginners and experienced CSP users alike. [Actually only up to (and including) page 11 are for normal 
    mortals - after that it starts to get scary. But those first few pages are a gem.] 

`A Classical Mind - Essays in Honour of C.A.R. Hoare', Edited by A.W. Roscoe, Prentice Hall, ISBN 0-13-294844-3, 1994.

    Serious bedtime reading. 

`Parallel Processing with Communicating Process Architecture', I.R.East, UCL press, ISBN 1-85728-239-6, 1995.

    This is an excellent text on how to design and program with the CSP model - many higher level 
    design issues are addressed. 

`occam 2 -- including occam 2.1', John Galletly, UCL press, ISBN 1-85728-362-7, 1996.

    This is a good textbook on the occam version of the CSP model. A knowledge of occam gives insight that 
    makes multithreading in Java simpler and, hence, safer to manage ;-) ... 

`Parallel and Distributed Computing in Education', P.H.Welch, in Proceedings of VecPar'98, 
Lecture Notes in Computer Science #1573, Springer-Verlag, April 1999.

    This is a tutorial introduction to CSP from the point of view of the programmer/designer. JCSP bindings to key 
    examples are given in an appendix. See Abstract. 

`Java Threads in the Light of occam/CSP', P.H.Welch, in Architectures, Languages and Patterns for 
Parallel and Distributed Applications, Proceedings of WoTUG-21, pp. 259-284, IOS Press (Amsterdam), 
ISBN 90 5199 391 9, April 1998.

    This is a tutorial introduction to the Java monitor model. It raises concern over its ease of use and the 
    safety of its standard design patterns, but shows how it may be used to build the CSP primitives. Finally, 
    it revisits those concerns and shows how they fade in the light of CSP. See Abstract. 

'Using Concurrency and Parallelism Effectively - parts i and ii' by Jon Kerridge, published free of charge by 
Bookboon Copenhagen Sept 2014.  
The links to the books are : 
http://bookboon.com/en/using-concurrency-and-parallelism-effectively-i-ebook
http://bookboon.com/en/using-concurrency-and-parallelism-effectively-ii-ebook

This book makes use of the Apache Groovy programming language to make the use of JCSP much easier to learn by reducing
the amount of code lines that have to be written.

The examples and exercises used in the books is available at
https://github.com/JonKerridge/UCaPE

The groovyJCSP library of helper classes is available from the same repository :
https://github.com/JonKerridge

In particular part ii contains many examples of the use of the jcsp.net2 package which is not reliant 
on a central Channel Name Server.  Networks can be constructed using the TCP/IP addresses of Node.  The
net2 package also contains the ability to create 'mobile' processes, in that processes can be communicated
over net channels as first class objects.


Request for Feedback
Currently, the more complex the system requirements, the less likely it becomes that concurrency (or multi-threading) 
play a major role. This is because concurrency is thought to make system design especially hard - so many additional 
problems (race hazards, deadlock etc.) to worry about. A good model of concurrency, however, should simplify the 
design, implementation, verification and maintenance of systems. A design goal for our CSP-for-Java collaboration is 
to enable concurrency to play that natural role. Feedback on moving towards that goal is always welcome. 
Email messages can be sent to either CSPforJAVA@outlook.com or CSPforJava@gmail.com

Version Comments
jcsp-alpha-1.1.2 removed the win32 folder and removed refernces to it from the jcsp/net package