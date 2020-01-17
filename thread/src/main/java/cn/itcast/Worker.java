package cn.itcast;

import java.util.LinkedList;

/**
 * 工作者线程,负责消费任务
 *
 */
public class Worker implements Runnable {

    public final LinkedList<Job> jobs = new LinkedList<Job>();
    // 是否工作
    private volatile boolean running = true;
    @Override
    public void run() {
        while(running) {
            Job job = null;
            synchronized (jobs) {
                while (jobs.isEmpty()) {
                    try {
                        jobs.wait();
                    } catch (InterruptedException e) {
                        // 感知到外部对WorkerThread的中断操作，返回
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                // 取出一个Job
                job = jobs.removeFirst();
            }
            if (job != null) {
                try {
                    job.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 关闭线程池
    public void shutdown() {
        running = false;
    }
}
