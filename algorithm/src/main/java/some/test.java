package some;

import java.util.concurrent.CountDownLatch;

/**
 * 顺序线程打印
 *
 */
public class test {

    public static void main(String[] args) throws Exception {

        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);

        Thread1 thread1 = new Thread1();
        thread1.setLatch(latch1);


        Thread2 thread2 = new Thread2();
        thread2.setLatch(latch1);

        /*Thread3 thread3 = new Thread3();
        thread3.setLatch(latch3);*/

        thread1.start();
        //latch1.await();
        thread2.start();
        //latch2.await();
        /*thread3.start();
        latch3.await();*/
        System.out.println("全都执行完");



    }


}
