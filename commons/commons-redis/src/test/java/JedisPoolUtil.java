import com.alibaba.druid.util.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class JedisPoolUtil {

    private static Logger logger = LoggerFactory.getLogger(JedisPoolUtil.class);
    private static JedisSentinelPool pool = null;

    @Test
    public void sub(){
        String path = "iportal:entity_com.imall.commons.redis.keygenerator.DefaultKeyGenerator:1-";

        System.out.println(path.substring(0,path.lastIndexOf(":")));
    }

    @Test
    public void substring(){
        String key ="iportal:entity_com.imall.iportal.core.entity.SysApp:1-";
        String entityKey = key.substring(0,key.lastIndexOf(":")).replace("entity_","");
        System.out.println(entityKey);
    }

    @Test
    public void test(){
        Jedis jedis = getJedis();
        jedis.flushAll();
        /*jedis.sadd("sname", "wobby");
        jedis.sadd("sname", "kings");
        jedis.sadd("sname", "demon");
        System.out.println(String.format("is member: %B", jedis.sismember("sname", "wob*")));*/

//        jedis.sadd("sname", "wobby");
//        jedis.sadd("sname", "kings");
//        jedis.sadd("sname", "demon");
//        jedis.sadd("sname", "demon");


//        Set<String> keys = jedis.keys("*");
//        jedis.set("user_1","xiaochi");
//        System.out.println(jedis.get("user_1"));
//        Set<String> result =  jedis.smembers("redisCacheableEntity");
//        for(String table : result){
//            System.out.println(table);
//        }


//        Map<String,String> user = new HashMap<String,String>();
//        user.put("name", "cd");
//        user.put("password", "123456");
//
//        jedis.hmset("user", user);
//
//        user = new HashMap<String,String>();
//        user.put("name", "xiaochi");
//        user.put("password", "654321");
//        jedis.hmset("user", user);
//
//        System.out.println(jedis.hvals("user"));

    }

    /**
     * 创建连接池
     */
    private static void createJedisPool() {
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(8);
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(-1);
        // 设置空间连接
        config.setMaxIdle(8);
        // jedis实例是否可用
        config.setTestOnBorrow(true);

        String masterName = "master-1";
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.1.27:63791");
        sentinels.add("192.168.1.27:63792");
        pool = new JedisSentinelPool(masterName, sentinels, config,"redis@1");
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    /**
     * 获取一个jedis 对象
     *
     * @return
     */
    public static Jedis getJedis() {
        if (pool == null)
            poolInit();
        return pool.getResource();
    }

    /**
     * 释放一个连接
     *
     * @param jedis
     */
    public static void returnRes(Jedis jedis) {
        pool.returnResource(jedis);
    }

    /**
     * 销毁一个连接
     *
     * @param jedis
     */
    public static void returnBrokenRes(Jedis jedis) {
        pool.returnBrokenResource(jedis);
    }


    public static void main(String[] args){
        Jedis jedis=getJedis();

    }

} 