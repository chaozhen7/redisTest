package redisson;

import java.util.concurrent.TimeUnit;

import org.redisson.Config;
import org.redisson.RedissonClient;
import org.redisson.SingleServerConfig;
import org.redisson.core.RBucket;
import org.redisson.core.RLock;

/**
 * @author sina Email:chaozhen7@163.com
 * @date 2016年10月12日 下午4:42:04
 * @version 1.0
 */
public class RedissonTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//redisson配置
        Config config = new Config();
        SingleServerConfig singleSerververConfig = config.useSingleServer();
        singleSerververConfig.setAddress("127.0.0.1:6379");
//        singleSerververConfig.setPassword("redis");
        //redisson客户端
        RedissonClient redissonClient = RedisUtils.getInstance().getRedisson(config);
        RBucket<Object> rBucket = RedisUtils.getInstance().getRBucket(redissonClient, "key");
        //rBucket.set("wangnian");
        System.out.println(rBucket.get());

        while (true) {
            RLock lock = redissonClient.getLock("lock");
            lock.tryLock(0, 1, TimeUnit.SECONDS);//第一个参数代表等待时间，第二是代表超过时间释放锁，第三个代表设置的时间制
            try {
                System.out.println("执行");
            } finally {
                lock.unlock();
            }
        }
	}

}
//实际使用时 可以采用 注解aop的方式 写个 lock的注解  比如  @lock("name")