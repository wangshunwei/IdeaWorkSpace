package cn.itcast;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * 将线程等待到某个状态后,等待其他线程到达状态点,都到啦后然后开始执行之后各自的任务逻辑.
 * 并不是并发的发起线程,这样的话,可以用Jmeter测试
 *
 */
public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4);
        for (int i = 0;i < 4;i++) {
            RunnableCyclicTask cyclicTask = new RunnableCyclicTask();
            cyclicTask.setCyclicBarrier(barrier);
            Thread thread = new Thread(cyclicTask);
            // 开启线程
            thread.start();
        }
    }
}
