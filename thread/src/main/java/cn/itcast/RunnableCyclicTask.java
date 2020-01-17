package cn.itcast;

import java.util.concurrent.CyclicBarrier;

public class RunnableCyclicTask implements  Runnable{

    private CyclicBarrier cyclicBarrier;

    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
        try {
            Thread.sleep(5000);
            System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
            // 这到里是达的状态点
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 线程都到达栅栏后,会各自执行自己的剩下的任务
        System.out.println("所有线程写入完毕，继续处理其他任务...");
    }
}
