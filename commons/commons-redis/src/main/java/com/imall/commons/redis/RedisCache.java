package com.imall.commons.redis;

import com.imall.commons.redis.util.EntityCacheInfo;
import com.imall.commons.redis.util.RedisCacheHitCalcUtil;
import com.imall.commons.redis.util.SerializableObjectUtil;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;


public class RedisCache implements IRedisCache {

    private static final String KEY_PRX = "entity_";

    //Redis
    private RedisTemplate<String, Object> redisTemplate;

    //缓存名称
    private String name;

    //超时时间
    private long timeout;

    //缓存的名字
    @Override
    public String getName() {
        return this.name;
    }

    //到底层使用的缓存，如redis
    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    //根据key得到一个ValueWrapper，然后调用其get方法获取值
    @Override
    public ValueWrapper get(Object key) {

        if (StringUtils.isEmpty(key)) {
            return null;
        } else {
            final String finalKey = buildKey(key);

            // 请求次数+1
            RedisCacheHitCalcUtil.requestCountCalc(finalKey);

            Object object  = redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] key = finalKey.getBytes();
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return null;
                    }
                    // 命中次数+1
                    RedisCacheHitCalcUtil.hitCountCalc(finalKey);
                    return SerializableObjectUtil.toObject(value);
                }
            });
            return (object != null ? new SimpleValueWrapper(object) : null);
        }
    }

    //获取缓存
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> type) {
        if (StringUtils.isEmpty(key) || null == type) {
            return null;
        } else {
            final String finalKey = buildKey(key);

            final Object object = redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] key = finalKey.getBytes();
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return null;
                    }
                    return SerializableObjectUtil.toObject(value);
                }
            });
            if (type.isInstance(object)) {
                return (T) object;
            } else {
                return null;
            }
        }
    }

    //写入缓存，将值存储到同一个key中，忽略重复值 ; 没有失效时间
    @Override
    public void sAdd(Object key, final Object value) {
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            final String finalKey = buildKey(key);

            if (!StringUtils.isEmpty(finalKey)) {
                redisTemplate.execute(new RedisCallback<Boolean>() {
                    @Override
                    public Boolean doInRedis(RedisConnection connection) {
                        connection.sAdd(finalKey.getBytes(), SerializableObjectUtil.toByteArray(value));
                        return true;
                    }
                });
            }
        }
    }

    //通过key同时设置 hash的多个field
    @Override
    public void hmset(Object key, final Map<String, Object> value){
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            final String finalKey = buildKey(key);

            if (!StringUtils.isEmpty(finalKey)) {
                redisTemplate.execute(new RedisCallback<Boolean>() {
                    @Override
                    public Boolean doInRedis(RedisConnection connection) {
                        Map<byte[], byte[]> hashes = new HashMap<byte[], byte[]>();
                        for(Map.Entry<String, Object> entrySet : value.entrySet()){
                            hashes.put(entrySet.getKey().getBytes(),SerializableObjectUtil.toByteArray(entrySet.getValue())) ;
                        }
                        connection.hMSet(finalKey.getBytes(),hashes);
                        return true;
                    }
                });
            }
        }
    }

    //获取Map中多个Field的值
    @Override
    public  List<Object> hmget(Object key, final String... fields) {

        if (!StringUtils.isEmpty(key)) {
            final String finalKey = buildKey(key);

            if (!StringUtils.isEmpty(finalKey)) {
                List<byte[]> result = redisTemplate.execute(new RedisCallback<List<byte[]>>() {
                    @Override
                    public List<byte[]> doInRedis(RedisConnection connection) {

                        byte[][] fieldsbt =  new byte[fields.length][];
                        for(int a=0;a<fields.length;a++){
                            fieldsbt[a] = fields[a].getBytes();
                        }
                        return connection.hMGet(finalKey.getBytes(), fieldsbt);
                    }
                });

                List<Object> resultList = new ArrayList<>();
                for(byte[] bytes : result){
                    resultList.add(SerializableObjectUtil.toObject(bytes));
                }
                return resultList;
            }
        }
        return null;
    }

    //写入缓存，使用默认失效时间
    @Override
    public void put(Object key, Object value) {
        final String finalKey = buildKey(key);
        RedisCacheHitCalcUtil.writeCountCalc(finalKey);

        put(key, value, getTimeoutByKey(finalKey));
    }

    //写入缓存，不使用失效时间
    @Override
    public void putNotEx(Object key, Object value) {
        put(key, value, null);
    }

    //写入缓存，自定义超时时间 ; expireSeconds 超时秒数
    @Override
    public void put(final Object key, final Object value, final Long expireSeconds) {
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            final String finalKey = buildKey(key);
            if (!StringUtils.isEmpty(finalKey)) {
                redisTemplate.execute(new RedisCallback<Boolean>() {
                    @Override
                    public Boolean doInRedis(RedisConnection connection) {
                        connection.set(finalKey.getBytes(), SerializableObjectUtil.toByteArray(value));
                        if (expireSeconds != null) {
                            connection.expire(finalKey.getBytes(), expireSeconds); // 设置超时间
                        }
                        return true;
                    }
                });
            }
        }
    }

    //这个方法在key不存在的时候加入一个值,如果key存在就不放入
    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        put(key, value);
        return new SimpleValueWrapper(value);
    }

    //根据Key 删除缓存
    @Override
    public void evict(Object key) {
        if (null != key) {
            final String finalKey = buildKey(key);
            // 擦除次数+1
            RedisCacheHitCalcUtil.clearCountCalc(finalKey);

            if(finalKey.indexOf("*")>0){
                Set<String> keys = redisTemplate.keys(finalKey);
                redisTemplate.delete(keys);
                return;

            }

            if (!StringUtils.isEmpty(finalKey)) {
                redisTemplate.execute(new RedisCallback<Long>() {
                    public Long doInRedis(RedisConnection connection) throws DataAccessException {
                        return connection.del(finalKey.getBytes());
                    }
                });
            }
        }
    }

    // 获取某个key的value集合（set）
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> sMembers(final Object key, Class<T> type) {
        if (StringUtils.isEmpty(key) || null == type) {
            return null;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }

            final List<T> resultList = new ArrayList<>();
            redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) {
                    Set<byte[]> result  = connection.sMembers(finalKey.getBytes());
                    for(byte[] bytes : result ){
                        resultList.add((T)SerializableObjectUtil.toObject(bytes));
                    }
                    return true;
                }
            });
            return resultList;
        }
    }

    //删除集合中的一个对象
    @Override
    public void sRem(Object key, final Object value){
        if (null != key && value != null) {
            final String finalKey = buildKey(key);
            if (!StringUtils.isEmpty(finalKey)) {
                redisTemplate.execute(new RedisCallback<Long>() {
                    public Long doInRedis(RedisConnection connection) throws DataAccessException {
                        return connection.sRem(finalKey.getBytes(), SerializableObjectUtil.toByteArray(value));
                    }
                });
            }
        }
    }

    @Override
    public Boolean sIsMember(Object key, final Object member){
        if (null != key && member != null) {
            final String finalKey = buildKey(key);
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) {
                    return connection.sIsMember(finalKey.getBytes(), SerializableObjectUtil.toByteArray(member));
                }
            });
        }
        return null;
    }

    //  清除系统缓存
    @Override
    public void clear() {
        redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    private Long getTimeoutByKey(String key){
        if(key.indexOf(KEY_PRX)<=0){
            return timeout; //默认时间
        }
        //key格式例如：iportal:entity_com.imall.iportal.core.entity.SysApp:1-
        //entityKey格式例如：iportal:com.imall.iportal.core.entity.SysApp
        String entityKey = key.substring(0, key.lastIndexOf(":")).replace(KEY_PRX, "");
        EntityCacheInfo entityCacheInfo = (EntityCacheInfo)RedisUtil.get(entityKey);
        if(entityCacheInfo==null){
            return timeout; //默认时间
        }
        return entityCacheInfo.getCacheEntityExpireSeconds().longValue();
    }

    private String buildKey(Object key){
        final String finalKey;
        if (key instanceof String) {
            finalKey = (String) key;
        } else {
            finalKey = key.toString();
        }
        return finalKey;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
