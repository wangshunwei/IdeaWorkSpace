package some;

import java.util.concurrent.CountDownLatch;

public class Thread1 extends Thread {

    private CountDownLatch latch;
    String param ;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("a");
        latch.countDown();

    }
}
