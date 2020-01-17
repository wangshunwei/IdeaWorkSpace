package cn.itcast;

import java.util.concurrent.CountDownLatch;

public class RunnableLatchTask implements Runnable {

    private CountDownLatch latch;

    public  void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public  CountDownLatch getLatch() {
        return latch;
    }
    @Override
    public void run() {
        try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " sleep 1000ms.");
               // 将CountDownLatch的数值减1
            latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
