package cn.itcast;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * 通过中断来停止线程任务
 *
 */
public class InterruptedCancellTask extends Thread {


    private  BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<BigInteger>(10);



    InterruptedCancellTask(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    // 支持可以取消线程任务的场景
    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (Thread.currentThread().isInterrupted()) {
            try {
                queue.put(p = p.nextProbablePrime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // 不支持线程任务取消的场景，一旦捕捉到啦中断异常，将检查的标识，每个线程的状态保存在自己本地法人栈中
    // put等方法支持中断的原因是里面调用啦wait()方法


    /**
     *
     * 可以调用取消方法进行线程的中断
     *
     */

    public void  cancell() {
        // 中断状态的表示每个线程都有，不需要自己进行定义啦就.调用后线程的状态被设置为true
        // 那么线程就可以跳出循环，结束任务。可以进行其他任务的执行。
        // 发送中断请求，并不是马上线程就停止啦。把线程状态设置为true而已，不再进入循环。
        interrupt();
    }

}
