package cn.itcast.rabbit.failover;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class TKHThreadFactory implements ThreadFactory {

    private static final char PACKAGE_SEPARATOR_CHAR = '.';
    private static final AtomicInteger poolId = new AtomicInteger();
    private static final AtomicInteger nextId = new AtomicInteger();
    private final String prefix;
    protected final ThreadGroup threadGroup;
    private final boolean daemon;
    private final int priority;

    public TKHThreadFactory(Class<?> poolType) {
        this(poolType,false,Thread.NORM_PRIORITY);
    }
    public TKHThreadFactory(String poolName) {
        this(poolName,false,Thread.NORM_PRIORITY);
    }
    public TKHThreadFactory(Class<?> poolType,boolean daemon) {
        this(poolType,daemon,Thread.NORM_PRIORITY);
    }
    public TKHThreadFactory(String poolName,boolean daemon) {
        this(poolName,daemon,Thread.NORM_PRIORITY);
    }
    public TKHThreadFactory(Class<?> poolType,int priority) {
        this(poolType,false,priority);
    }
    public TKHThreadFactory(String poolName,int priority) {
        this(poolName,false,priority);
    }

    public TKHThreadFactory(Class<?> poolType,boolean daemon,int priority) {
        this(toPoolName(poolType),daemon,priority);
    }

    public static String toPoolName(Class<?> poolType) {
        if (poolType == null) {
            throw new NullPointerException("poolType");
        }
        String className = poolType.getName();
        final int lastDotIdx = className.lastIndexOf(PACKAGE_SEPARATOR_CHAR);
        if (lastDotIdx > -1) {
            className = className.substring(lastDotIdx + 1);
        }
        switch (className.length()) {
            case 0 :
                return "unknown";
            case 1 :
                return className.toLowerCase(Locale.US);
            default:
                if (Character.isUpperCase(className.charAt(0)) && Character.isLowerCase(className.charAt(1))) {
                    return Character.toLowerCase(className.charAt(0)) + className.substring(1);
                } else {
                    return className;
                }
        }


    }

    public TKHThreadFactory(String poolName, boolean daemon, int priority) {
        this(poolName, daemon, priority, System.getSecurityManager() == null ? Thread.currentThread().getThreadGroup() :
                System.getSecurityManager().getThreadGroup());
    }

    public TKHThreadFactory(String poolName, boolean daemon, int priority, ThreadGroup threadGroup) {
        if (poolName == null) {
            throw new NullPointerException("poolName");
        }
        if (priority < Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException("priority:" + priority + "(expected:Thread.MIN_PRIORITY <= priority <= MAX_PRIORITY)");
        }

        this.prefix = poolName + '_' + poolId.incrementAndGet();
        this.daemon = daemon;
        this.priority = priority;
        this.threadGroup = threadGroup;


    }
    @Override
    public Thread newThread(Runnable r) {
        Thread t = newThread(new DefaultRunnableDecorator(r), prefix + nextId.incrementAndGet());
        try {

            if (t.isDaemon()) {
                if (!daemon) {
                    t.setDaemon(false);
                }
            } else {
                if (daemon) {
                    t.setDaemon(true);
                }
            }
            if (t.getPriority() != priority) {
                t.setPriority(priority);
            }

        } catch (Exception e) {
            //
        }
        return t;
    }

    private Thread newThread(Runnable r, String name) {
        return new Thread(threadGroup, r, name);
    }

    private static final class DefaultRunnableDecorator implements Runnable{
        private final Runnable r;
        public DefaultRunnableDecorator(Runnable r) {
            this.r = r;
        }

        @Override
        public void run() {
            try {
                r.run();
            } finally {
                //
            }
        }
    }
}
