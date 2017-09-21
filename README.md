# Core-Java

## <Model> framework
* Create a web service container like tomcat 
* Create a web framework like spring

Web Framework Already Exist Events 
> - Request filter
> - AOP
> - File scanner
> - Bean scope controller(only <b><font color="blue">singleton</font></b> now).
> - Annotation 
>   - Controller
>   - Filter
>   - Component
>   - Service
>   - RequestMapping

## <Model> jdklib
### Realize JDK API function
* Lock
* ThreadLocal
* BlockingQueue

Already Exist API
> - Realize Lock action by using <i><b>synchronized</b></i> keyword
>   - <b>com.current.lock.v1.SynchronizedLock</b> unfair lock can block thread
>   - <b>com.current.lock.v2.SynchronizedFairLock</b> fair lock can block thread
>       - when the first thread is not alive, will be removed from the chain.
>   - <b>com.current.lock.v3.SynchronizedShareLock</b> fair lock & and add readLock & writeLock can block thread. <b>readLock()</b>,<b>writeLock()</b>,<b>lock()</b>
>       - when the first thread is not alive, will be removed from the chain.
> - Realize BlockingQueue
>   - <b>com.current.queue.jdkAPI.ArraysSynchronizedQueue</b> use synchronized and array
>   - <b>com.current.queue.projectAPI.ArraysSynchronizedLockQueue</b> use <b><i>SynchronizedLock</i></b> and array