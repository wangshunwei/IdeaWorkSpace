package cn.itcast.rabbit.failover;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class RetryTaskManager implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryTaskManager.class);
    private static final BlockingQueue<RetryTask<? extends RetryResult>> queue = new PriorityBlockingQueue<>();

    public static boolean addTask(RetryTask<? extends RetryResult> task) {
        return queue.offer(task);
    }


    @Override
    public void run() {

        for (;;) {
            RetryTask<? extends RetryResult> t = queue.peek();
            if (t != null && t.getDeadLineMillis() - RetryTask.millisTime() <= 0) {
                queue.remove();
                TKHThreadPoolExecutor.submitTask(t);
                continue;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e ) {
                e.printStackTrace();
            }
        }

    }
}
