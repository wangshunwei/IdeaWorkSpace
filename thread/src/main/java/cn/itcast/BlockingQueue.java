package cn.itcast;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 阻塞队列基本应用和理解,先理解基本使用场景,找工作的场景去套
 *
 */
public class BlockingQueue {

    /**
     *
     * ArrayBlockingQueue  公平性的锁
     *
     */
    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(25,true);

    /**
     *
     * 内部封装啦内部类 链表的定义
     *
     */
    LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(25);

    /**
     *
     * 用数组来实现
     *
     */
    PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue(25);

    /**
     *
     * 延迟队列 用PriorityQueue来实现
     *
     */
    DelayQueue delayQueue = new DelayQueue();

    /**
     *
     * 消费者和生产者模式的典型事例 JDK事例。等待唤醒机制的典型场景
     * 类似实现是ArrayBolckingQueue   阻塞队列的原型
     *
     */

    // 锁对象
    final Lock lock = new ReentrantLock();
    // 锁对象获取生产者线程的监视器对象 这样的监视器的方法就是生产者线程的
    final Condition notFull  = lock.newCondition();
    // 消费者线程的监视器对象
    final Condition notEmpty = lock.newCondition();

    // 定义数据存放数据，大小为100
    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    // 定义生产者线程方法
    public void put(Object x) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断 数组是否已经满啦
            while (count == items.length)
               // 满啦话，生产者线程进行等待阻塞，要是数组满啦，线程就会在这挂起，不会往下执行下面的代码。
               // 在这判断的时候，之前已经进行唤醒啦消费者线程
                notFull.await();
            // 数组未满的话，将要存放的值按照顺序存入到数组中，在消费者唤醒生产者的时候，就会从这继续执行，继续存放数据从索引0开始
            items[putptr] = x;
            // 判断索引的值，数组是否已经满啦，满足条件的话
            if (++putptr == items.length) putptr = 0;
            ++count;
            // 唤醒消费者线程，保证在put的时候有消费者线程进行消费
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 定义消费者线程的方法
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                // 消费者线程进行等待
                notEmpty.await();
            // 获得索引处的数据
            Object x = items[takeptr];
            // 此索引处置为null,代表数据没有啦 最后return 索引处数据
            items[takeptr] = null;
            if (++takeptr == items.length) takeptr = 0;
            --count;
            // 唤醒生产者线程
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
