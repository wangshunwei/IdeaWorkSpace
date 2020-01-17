package cn.itcast.rabbit.failover;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TKHThreadPoolExecutor {

    // 根据cpu计算线程池的线程的数量
    private static final int DEFAULT_THREAD_NUMS = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(DEFAULT_THREAD_NUMS
            , DEFAULT_THREAD_NUMS
            , 0L, TimeUnit.SECONDS
            , new LinkedBlockingQueue<Runnable>()
            , new TKHThreadFactory(TKHThreadPoolExecutor.class)
            , new ThreadPoolExecutor.AbortPolicy());

    public static void submitTask(Runnable task) {
        EXECUTOR_SERVICE.submit(task);
    }

}
