package cn.itcast.rabbit.failover;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryTask<V extends RetryResult> implements Runnable,Comparable<RetryTask<V>> {

    private static final long START_TIME = System.currentTimeMillis();

    public static long millisTime() {
        return  System.currentTimeMillis() - START_TIME;
    }
    public static long deadLineMillis(Long delay) {
        return millisTime() + delay;
    }
    // 执行任务的起始时间点
    private long deadLineMillis;
    // 任务执行周期
    private final long periodMillis;
    // 执行次数,0表示永久的周期任务
    private int expireTime;
    private  final RetryHandler<V> handler;
    // 任务执行结果
    private V result;
    // 任务执行过程中出现的异常信息
    private Throwable cause;
    // 关注结果而阻塞的线程
    private short waiters;
    // 已经重试的次数
    private AtomicInteger retryTime = new AtomicInteger();

    public RetryTask(RetryHandler<V> handler,V result,long millisTime,long period) {

        this.handler = handler;
        this.result = result;
        this.deadLineMillis = deadLineMillis(millisTime);
        this.periodMillis = period;

    }

    public RetryTask(RetryHandler<V> handler,V result,int expireTime,long millisTime,long period) {
        if (expireTime < 0 || millisTime < 0) {
            throw new IllegalArgumentException("expireTIme/millisTIme");
        }
        this.handler = handler;
        this.result = result;
        this.deadLineMillis = deadLineMillis(millisTime);
        this.periodMillis = period;
        this.expireTime = expireTime;
    }


    @Override
    public int compareTo(RetryTask<V> o) {
        if (this == o) {
            return 0;
        }
        long d = this.deadLineMillis - o.deadLineMillis;
        if (d < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public void run() {

        try {
            long p = periodMillis;
            if (expireTime == 0) {
                // 永久执行任务
                result = handler.doHandler(result);
                if (p > 0) {
                    deadLineMillis += p;
                } else {
                    deadLineMillis = millisTime() - p;
                }
                RetryTaskManager.addTask(this);
            } else {
                if (retryTime.getAndIncrement() < expireTime) {
                    result = handler.doHandler(result);
                    if (!result.isSuccess()) {
                        if (p > 0) {
                            deadLineMillis += p;
                        } else {
                            deadLineMillis = millisTime() - p;
                        }
                        RetryTaskManager.addTask(this);
                    } else {
                        setResult(result);
                    }
                } else {
                    result = handler.doFailed(result);
                    setResult(result);
                }
            }
        } catch (Throwable e) {
            setCause(e);
        }
    }

    public boolean isDone() {
        return result != null && result.isSuccess();
    }

    public V get() throws InterruptedException, ExecutionException {
        if (isDone()) {
            return result;
        }
        if (Thread.interrupted()) {
            throw new InterruptedException(toString());
        }
        synchronized (this) {
            while (!isDone()) {
                incWaiters();
                try {
                    wait();
                } finally {
                    decWaiters();
                }
            }
        }
        Throwable cause =cause();
        if (cause == null) {
            return result;
        }
        if (cause instanceof CancellationException) {
            throw (CancellationException) cause;
        }
        throw new ExecutionException(cause);
    }

    public V get(long timeout, TimeUnit unit) throws InterruptedException,ExecutionException,TimeoutException {
        if (await0(unit.toMillis(timeout), true)) {
            Throwable cause = cause();
            if (cause == null) {
                return result;
            }
            if (cause instanceof CancellationException) {
                throw (CancellationException) cause;
            }
            throw new ExecutionException(cause);
        }
        throw new TimeoutException();
    }

    private boolean await0(long timeoutMillis, boolean interruptable) throws InterruptedException{

        if (isDone()) {
            return true;
        }
        if (timeoutMillis <= 0) {
            return isDone();
        }
        if (interruptable && Thread.interrupted()) {
            throw new InterruptedException(toString());
        }
        long startTime = System.currentTimeMillis();
        long waitTime = timeoutMillis;
        boolean interrupted = false;
        try {

            for (;;) {
                synchronized (this) {
                    if (isDone()) {
                        return true;
                    }
                    incWaiters();
                    try {
                        wait(waitTime / 1000000, (int) waitTime % 1000000);
                    } catch (InterruptedException e) {
                        if (interruptable) {
                            throw e;
                        } else {
                            interrupted = true;
                        }

                    } finally {
                        decWaiters();
                    }
                }

                if (isDone()) {
                    return true;
                } else {
                    waitTime = timeoutMillis - (System.currentTimeMillis() - startTime);
                    if (waitTime <= 0) {
                        return isDone();
                    }

                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }

    }

    private synchronized void checkNotifyWaiters() {
        if (waiters > 0) {
            notifyAll();
        }
    }

    private void incWaiters() {
        if (waiters == Short.MAX_VALUE) {
            throw new IllegalStateException("too many waiters:" + this);
        }
        ++waiters;
    }

    private void decWaiters() {
        --waiters;
    }

    public static long getStartTime() {
        return START_TIME;
    }

    public long getDeadLineMillis() {
        return deadLineMillis;
    }

    public void setDeadLineMillis(long deadLineMillis) {
        this.deadLineMillis = deadLineMillis;
    }

    public long getPeriodMillis() {
        return periodMillis;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public RetryHandler<V> getHandler() {
        return handler;
    }

    public V getResult() {
        return result;
    }

    public void setResult(V result) {
        this.result = result;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public short getWaiters() {
        return waiters;
    }

    public void setWaiters(short waiters) {
        this.waiters = waiters;
    }

    public AtomicInteger getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(AtomicInteger retryTime) {
        this.retryTime = retryTime;
    }

    public Throwable cause() {
        return cause;
    }
}
