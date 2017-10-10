### LifeCycle
##### <b>Note</b> -> SecurityManager of JVM
* Why need SecurityManager.
    * create an safety environment for Java Virtual Machine. Not all class file or right permission is allowed.<br>
    <i>* The default behavior is Java application do not have security manager.</i><br>
    <i>* Java applet is not allowed to call <b>exit()</b> function of JVM.</i>

* create an <b>Thread</b> - init()<br>
````java
    /**
    * from JDK source code
    */
    private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize, AccessControlContext acc) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = name;
        Thread parent = currentThread();//get parent Thread
        //SecurityManager security = System.getSecurityManager();
        if (g == null) {
            /* Determine if it's an applet or not */
            /* If there is a security manager, ask the security manager
               what to do. */
            if (security != null) {
               g = security.getThreadGroup();
            }
            /* If the security doesn't have a strong opinion of the matter
               use the parent thread group. */
            if (g == null) {
                // use parent ThreadGroup option to control all threads.
                g = parent.getThreadGroup();
            }
        }

        /* checkAccess regardless of whether or not threadgroup is
           explicitly passed in. */
        g.checkAccess();
        /*
         * Do we have the required permissions?
         */
        if (security != null) {
            if (isCCLOverridden(getClass())) {
                security.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
            }
        }
        g.addUnstarted(); // increase unstart thread count.

        this.group = g;
        this.daemon = parent.isDaemon(); // if parent is daemon thread. child will be too.
        this.priority = parent.getPriority();// get parent priority
        if (security == null || isCCLOverridden(parent.getClass()))
           this.contextClassLoader = parent.getContextClassLoader(); // get parent classLoader.
        else
            this.contextClassLoader = parent.contextClassLoader;
        this.inheritedAccessControlContext =
                acc != null ? acc : AccessController.getContext();
        this.target = target;
        setPriority(priority);
        if (parent.inheritableThreadLocals != null)
            this.inheritableThreadLocals =
                ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
        /* Stash the specified stack size in case the VM cares */
        this.stackSize = stackSize;

        /* Set thread ID */
        tid = nextThreadID();
    }
````