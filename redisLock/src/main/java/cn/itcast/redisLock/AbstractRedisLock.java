package cn.itcast.redisLock;

/**
 * redis分布式锁抽象类
 * @author 王顺伟
 *
 */

public abstract class AbstractRedisLock implements RedisLock{

	@Override
	public boolean lock(String key) {
		return lock(key,TIMEOUT_MILLIS,RETRY_TIMES,SLEEP_MILLIS);
	}

	@Override
	public boolean lock(String key, int retryTimes) {
		return lock(key,TIMEOUT_MILLIS,retryTimes,SLEEP_MILLIS);
	}

	@Override
	public boolean lock(String key, int retryTimes, long sleepMilles) {
		return lock(key,TIMEOUT_MILLIS,retryTimes,sleepMilles);
	}

	@Override
	public boolean lock(String key, long timeOutMillis) {
		return lock(key,timeOutMillis,RETRY_TIMES,SLEEP_MILLIS);
	}

	@Override
	public boolean lock(String key, long timeOutMillis, int retryTimes) {
		return lock(key, timeOutMillis, retryTimes,SLEEP_MILLIS);
	}
	
}
