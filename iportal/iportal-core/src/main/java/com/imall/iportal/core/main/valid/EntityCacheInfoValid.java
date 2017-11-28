package com.imall.iportal.core.main.valid;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class EntityCacheInfoValid implements Cloneable, java.io.Serializable{
    /**
     *  例如：EntityCacheInfo
     */
    @NotBlank
    private String cacheEntityName;
    /**
     * 例如:  com.imall.commons.redis.util.EntityCacheInfo
     */
    @NotBlank
    private String cacheEntityPath;
    /**
     * 失效时间（秒）
     */
    @NotNull
    private Integer cacheEntityExpireSeconds;
    /**
     *  应用名称
     */
    @NotBlank
    private String appName;

    /**
     * 请求次数
     */
    @NotNull
    private Integer requestCount;
    /**
     * 写入次数
     */
    @NotNull
    private Integer writeCount;
    /**
     * 命中次数
     */
    @NotNull
    private Integer hitCount;
    /**
     * 清除次数
     */
    @NotNull
    private Integer clearCount;

    @NotBlank
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCacheEntityName() {
        return cacheEntityName;
    }

    public void setCacheEntityName(String cacheEntityName) {
        this.cacheEntityName = cacheEntityName;
    }

    public Integer getCacheEntityExpireSeconds() {
        return cacheEntityExpireSeconds;
    }

    public void setCacheEntityExpireSeconds(Integer cacheEntityExpireSeconds) {
        this.cacheEntityExpireSeconds = cacheEntityExpireSeconds;
    }

    public void setCacheEntityPath(String cacheEntityPath) {
        this.cacheEntityPath = cacheEntityPath;
    }

    public String getCacheEntityPath() {
        return cacheEntityPath;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    public Integer getWriteCount() {
        return writeCount;
    }

    public void setWriteCount(Integer writeCount) {
        this.writeCount = writeCount;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    public Integer getClearCount() {
        return clearCount;
    }

    public void setClearCount(Integer clearCount) {
        this.clearCount = clearCount;
    }
}
