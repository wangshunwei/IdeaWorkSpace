package cn.itcast.redisLock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

/**
 * 
 * 分布式锁实现类
 * 
 * @author 王顺伟
 *
 */

public class RedisAtomic extends AbstractRedisLock {

	// 将每把锁的标识存入threadLocl保证线程安全
	private static ThreadLocal<String> lockFlag = new ThreadLocal<String>();
	// 获取到jedis客户端进行操作redis
	private RedisTemplate<String, String> redisTemplate;

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	// 定义LUA脚本,保证锁释放的时候的原子性
	private static final String UNLOCK_LUA;

	static {
		StringBuilder sb = new StringBuilder();
		sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
		sb.append("then");
		sb.append(" return redis.call(\"del\",KEYS[1]) ");
		sb.append(" else");
		sb.append(" return 0");
		sb.append(" end");
		UNLOCK_LUA = sb.toString();
	}

	/**
	 * 获取锁并进行重试
	 * @description
	 * @param key
	 * @param timeOutMillis
	 * @param retryTimes
	 * @param sleepMillis
	 * @author itw_wangsw
	 * @date 2018年12月8日下午2:30:56
	 * 
	 */
	@Override
	public boolean lock(String key, long timeOutMillis, int retryTimes, long sleepMillis) {
		boolean result = this.getLock(key, timeOutMillis);
		// 没获取到锁的时候,重试次数大于0时进行重试操作
		while (!result && retryTimes-- > 0) {
			try {
				System.out.println("重试次数还剩:" + retryTimes + "次");
				// 间隔时间
				Thread.sleep(sleepMillis);
			} catch (Exception e) {
				// 重试获取锁异常
				e.printStackTrace();
				return false;
			}
			// 间隔时间后,再次进行获取锁
			result = this.getLock(key, timeOutMillis);
		}
		// 未重试直接获取到锁
		System.out.println("获取锁成功,剩余" + retryTimes + "次");
		return result;
	}

	/**
	 * 获取锁
	 * @description
	 * @param key
	 * @param timeOutMillis i超时时间/过期时间
	 * @return
	 * @author itw_wangsw
	 * @date 2018年12月8日下午2:32:45
	 * "NX":如果不存在就进行设置
	 * "PX":设置过期时间,timeOutMillis具体参数
	 * uuid:每个线程加锁时,存的唯一的标志,防止锁被任意解掉,只有标志匹配的时候,才能进行解锁,说明是同一条线程
	 * 
	 */
	private boolean getLock(String key, long timeOutMillis) {
		try {
			String result = redisTemplate.execute(new RedisCallback<String>() {

				@Override
				public String doInRedis(RedisConnection connection) throws DataAccessException {
					JedisCommands commands = (JedisCommands) connection.getNativeConnection();
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					lockFlag.set(uuid);
					return commands.set(key, uuid, "NX", "PX", timeOutMillis);
				}
			});
			return !StringUtils.isEmpty(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 通过API执行LUA脚本进行释放锁,保证释放锁时检查标识与del锁的原子性操作
	 * @description
	 * @param key
	 * @param timeOutMillis i超时时间/过期时间
	 * @return
	 * @author itw_wangsw
	 * @date 2018年12月8日下午2:32:45
	 * 
	 */
	@Override
	public boolean unlock(String key) {
		
		try {
			List<String> keys = new ArrayList<>();
			// 获得当前线程的key存入集合
			keys.add(key);
			List<String> args = new ArrayList<>();
			// 获得当前线程里面的存的唯一标识uuid存入集合.存入到redis中的uuid与存入到ThreadLocl中是否一致,一致,说明时一条下称的,然后LUA脚本保证原子性这两部
			args.add(lockFlag.get());
			Long result = redisTemplate.execute(new RedisCallback<Long>() {

				@Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
					Object nativeConnection = connection.getNativeConnection();
					if (nativeConnection instanceof JedisCluster) {
						return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
					} else if (nativeConnection instanceof Jedis) {
						((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
					}
					return (long) 01;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("释放锁异常");
		}
		return false;
	}

	

}
