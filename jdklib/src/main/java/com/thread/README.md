### Deprecated 
countStackFrames ()<br>
destroy ()<br>
resume ()<br>
stop ()<br>
stop (java.lang.Throwable)<br>
suspend ()<br>

### LifeCycle

### Thread Status
1. NEW : Thread state for a thread which has <b>not yet started.</b>
2. RUNNABLE : Thread state for a runnable thread.  A thread in the runnable state is <b>executing</b> in the Java virtual machine but it maybe waiting for other resources from the operating system such as processor.
3. BLOCKED : Thread state for a thread blocked waiting for <b>monitor lock</b>. to enter a <b>synchronized</b> block/method or reenter a <b>synchronized</b> block/method after calling <b><u>Object.waiting()</u></b>
4. WAITING : Thread state for a waiting thread. one thread due to call following method.
    <ul>
        <li> Object.waiting() {with no timeout}
        <li> Thread object -> thread.join() {with no timeout}
        <li> LockSupport.park()
    </ul>
5. TIMED_WAITING : Thread state for a waiting thread with a specified waiting time.A thread is in the timed waiting state due to calling one of the following methods with a specified positive waiting time
    <ul>
        <li> Thread.sleep()
        <li> Object.waiting(xxx) {with timeout}
        <li> Thread object -> thread.join(xxx) {with timeout}
        <li> LockSupport.parkNanos()
        <li> LockSupport.parkUntil()
    </ul>
6. TERMINATED : Thread state for a terminated thread. The thread has been completed execution.

activeCount ()<br>
auditSubclass (java.lang.Class)<br>
blockedOn (sun.nio.ch.Interruptible)<br>
checkAccess ()<br>
clone ()<br>
currentThread ()<br>
dispatchUncaughtException (java.lang.Throwable)<br>
dumpStack ()<br>
dumpThreads ([Ljava.lang.Thread;)<br>
enumerate ([Ljava.lang.Thread;)<br>
exit ()<br>
getAllStackTraces ()<br>
getContextClassLoader ()<br>
getDefaultUncaughtExceptionHandler ()<br>
getId ()<br>
getName ()<br>
getPriority ()<br>
getStackTrace ()<br>
getState ()<br>
getThreadGroup ()<br>
getThreads ()<br>
getUncaughtExceptionHandler ()<br>
holdsLock (java.lang.Object)<br>
init (java.lang.ThreadGroup, java.lang.Runnable, java.lang.String, long)<br>
init (java.lang.ThreadGroup, java.lang.Runnable, java.lang.String, long, java.security.AccessControlContext)<br>
interrupt ()<br>
interrupt0 ()<br>
interrupted ()<br>
isAlive ()<br>
isCCLOverridden (java.lang.Class)<br>
isDaemon ()<br>
isInterrupted ()<br>
isInterrupted (boolean)<br>
join ()<br>
join (long)<br>
join (long, int)<br>
nextThreadID ()<br>
nextThreadNum ()<br>
processQueue (java.lang.ref.ReferenceQueue, java.util.concurrent.ConcurrentMap)<br>
registerNatives ()<br>
resume0 ()<br>
run ()<br>
setContextClassLoader (java.lang.ClassLoader)<br>
setDaemon (boolean)<br>
setDefaultUncaughtExceptionHandler (java.lang.Thread$UncaughtExceptionHandler)<br>
setName (java.lang.String)<br>
setNativeName (java.lang.String)<br>
setPriority (int)<br>
setPriority0 (int)<br>
setUncaughtExceptionHandler (java.lang.Thread$UncaughtExceptionHandler)<br>
sleep (long)<br>
sleep (long, int)<br>
start ()<br>
start0 ()<br>
stop0 (java.lang.Object)<br>
suspend0 ()<br>
toString ()<br>
yield ()<br>