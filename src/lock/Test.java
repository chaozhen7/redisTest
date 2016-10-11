package lock;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author sina Email:chaozhen7@163.com
 * @date 2016年10月11日 上午11:39:34
 * @version 1.0
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		String key = "";
		RedisLock lock = new RedisLock(redisTemplate, key, 10000, 20000);
		try {
			if (lock.lock()) {
				// 需要加锁的代码
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
			// 操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
			lock.unlock();
		}
	}

}
