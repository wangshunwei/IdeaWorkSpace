
package cn.itcast;

/**
 *
 * 业务线程
 *
 */
public class Job  implements Runnable {

    @Override
    public void run() {
        System.out.println("业务");
    }
}
