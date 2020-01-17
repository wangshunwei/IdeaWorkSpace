package cn.itcast.redisLock;
/**
 * Redis实现分布式锁
 * @author 王顺伟
 *
 */
public interface RedisLock {

	// 超时时间设定
	public static final long  TIMEOUT_MILLIS = 30000;
	// 重试次数的设定
	public static final int RETRY_TIMES = 0;
	// 重试间隔
	public static final long SLEEP_MILLIS = 500;
	
	public boolean lock(String key);
	public boolean lock(String key, int retryTimes);
	public boolean lock(String key, int retryTimes, long sleepMilles);
	public boolean lock(String key, long timeOutMillis);
	public boolean lock(String key, long timeOutMillis, int retryTimes);
	public boolean lock(String key, long timeOutMillis, int retryTimes, long sleepMillis);
	public boolean unlock(String key);
	
}
