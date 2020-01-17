package some;

import java.util.concurrent.CountDownLatch;

public class Thread2 extends Thread{


    private CountDownLatch latch;
    String param ;

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public void run() {
        System.out.println("b");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
