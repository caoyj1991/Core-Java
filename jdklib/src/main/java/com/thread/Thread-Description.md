#Deprecated

countStackFrames ();<br>
destroy ();<br>
resume ();<br>
stop ();<br>
stop (java.lang.Throwable);
suspend ();

#LifeCycle

activeCount ();
auditSubclass (java.lang.Class);
blockedOn (sun.nio.ch.Interruptible);
checkAccess ();
clone ();
currentThread ();
dispatchUncaughtException (java.lang.Throwable);
dumpStack ();
dumpThreads ([Ljava.lang.Thread;);
enumerate ([Ljava.lang.Thread;);
exit ();
getAllStackTraces ();
getContextClassLoader ();
getDefaultUncaughtExceptionHandler ();
getId ();
getName ();
getPriority ();
getStackTrace ();
getState ();
getThreadGroup ();
getThreads ();
getUncaughtExceptionHandler ();
holdsLock (java.lang.Object);
init (java.lang.ThreadGroup, java.lang.Runnable, java.lang.String, long);
init (java.lang.ThreadGroup, java.lang.Runnable, java.lang.String, long, java.security.AccessControlContext);
interrupt ();
interrupt0 ();
interrupted ();
isAlive ();
isCCLOverridden (java.lang.Class);
isDaemon ();
isInterrupted ();
isInterrupted (boolean);
join ();
join (long);
join (long, int);
nextThreadID ();
nextThreadNum ();
processQueue (java.lang.ref.ReferenceQueue, java.util.concurrent.ConcurrentMap);
registerNatives ();
resume0 ();
run ();
setContextClassLoader (java.lang.ClassLoader);
setDaemon (boolean);
setDefaultUncaughtExceptionHandler (java.lang.Thread$UncaughtExceptionHandler);
setName (java.lang.String);
setNativeName (java.lang.String);
setPriority (int);
setPriority0 (int);
setUncaughtExceptionHandler (java.lang.Thread$UncaughtExceptionHandler);
sleep (long);
sleep (long, int);
start ();
start0 ();
stop0 (java.lang.Object);
suspend0 ();
toString ();
yield ();