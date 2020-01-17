package cn.itcast;

import java.util.Map;
import java.util.concurrent.*;

/**
 *
 *模拟银行流水账号计算
 *
 */

public class BankWaterServiceTask implements Runnable{

    // 创建四个线程的屏障 this也是一个线程,在都到达屏障后 ,执行这个线程的任务.
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4,this);

    // 用线程池创建线程,启动4条线程
    private Executor executor = Executors.newFixedThreadPool(4);
    // 保存每个sheet的流水记录
    private ConcurrentHashMap<String,Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    // 定义方法
    public void  count() {
        for (int i = 0;i < 4;i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // 计算每个线程的sheet的流水账
                    sheetBankWaterCount.put(Thread.currentThread().getName(),1);
                    try {
                        // 到达屏障 进行阻塞
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    // 总结计算的线程,等线程都到达屏障之后
    @Override
    public void run() {
        int result = 0;
        // 汇总各个线程的计算的结果,遍历map
        for (Map.Entry<String,Integer> sheet:sheetBankWaterCount.entrySet()) {
            // 得到的结果相加
            result += sheet.getValue();
        }
        // 封装到map中
        sheetBankWaterCount.put("result",result);
        System.out.println(result);
    }
}
