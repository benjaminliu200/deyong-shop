package com.deyong.rest.jredis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

/**
 * Created by benjamin on 2017/1/17.
 */
public class JredisTest {

    @Test
    public void testJredisSingle() throws Exception{
        // 创建一个jedis 客户端
        Jedis jedis = new Jedis("192.168.0.222", 6379);
        jedis.set("key", "zhangsan");
        System.out.println(jedis.get("key"));
        jedis.close();
    }

    /**
     * 使用连接池
     */
    @Test
    public void testJedisPool() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
        //从连接池中获得Jedis对象
        Jedis jedis = pool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        //关闭jedis对象
        jedis.close();
        pool.close();
    }

    @Test
    public void testJedisCluster() {
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.25.153", 7001));
        nodes.add(new HostAndPort("192.168.25.153", 7002));
        nodes.add(new HostAndPort("192.168.25.153", 7003));
        nodes.add(new HostAndPort("192.168.25.153", 7004));
        nodes.add(new HostAndPort("192.168.25.153", 7005));
        nodes.add(new HostAndPort("192.168.25.153", 7006));

        JedisCluster cluster = new JedisCluster(nodes);

        cluster.set("key1", "1000");
        String string = cluster.get("key1");
        System.out.println(string);

        cluster.close();
    }
}
