package cn.itcast;

import java.util.concurrent.CountDownLatch;

/**
 *
 * CountDownLatch的关键:必须在想要等待线程之前进行想要执行人物线程的开启.然后都开启后,等到count为0的时候,主线程才不需要进行等待
 * 然后执行主线程的程序.任务线程调用countDown() 需要阻塞的线程调用await()
 *
 *
 */
public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        try {
        // 开启五个线程任务
        for (int i = 0;i < 5; i++ ) {
            RunnableLatchTask runnableLatchTask1 = new RunnableLatchTask();
            // 任务初始化countDownLatch
            runnableLatchTask1.setLatch(countDownLatch);
            Thread thread = new Thread(runnableLatchTask1);
            thread.start();
            }
            System.out.println("主线程开始进行等待");
            countDownLatch.await();
            System.out.println("主线程等待结束,主线程退出");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
