package com.huyang.common.cache;


import net.sf.ehcache.Ehcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EchcacheManager {


    private static CacheManager manager;

    @Autowired
    public  void setManager(CacheManager manager) {
        EchcacheManager.manager = manager;
    }

    /**
     * 获取缓存中对象
     *
     * @param ecName 缓存名称 对应ehcache.xml中的配置
     * @param key    存储的键名称
     * @return
     */
    public static Object getCacheByKeyAndName(String ecName, String key) {
        Cache cache = manager.getCache(ecName);
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(key);
            return valueWrapper == null ? null : valueWrapper.get();
        }

        return null;
    }

    /**
     * 设置值到缓存中
     *
     * @param ecName 缓存名称 对应ehcache.xml中的配置
     * @param key    存储的键名称
     * @param value
     */
    public static void setCacheByKeyAndName(String ecName, String key, Object value) {
        // 通过manager可以生成指定名称的Cache对象
        Cache cache = manager.getCache(ecName);
        if (cache != null && value != null) {
            cache.put(key, value);
        }
    }

    /**
     * 清除缓存
     *
     * @param ecName
     * @param key
     */
    public static void removeCache(String ecName, String key) {
        Cache cache = manager.getCache(ecName);
        if (cache != null ) {   //将指定key的缓存对象从缓存中清除
            cache.evict(key);
        }
    }

    /**
     * 清除指定cache name下面的所有缓存
     *
     * @param ecName
     */
    public static void removeAllCache(String ecName) {
        Cache cache = manager.getCache(ecName);
        if (cache != null) {
            Ehcache nativeCache = (Ehcache) cache.getNativeCache();
            nativeCache.removeAll();
        }
    }

    /**
     * 清除指定cache name下面的前缀匹配key值的缓存
     *
     * @param ecName
     */
    public static void removeMatchCache(String ecName, String key) {
        Cache cache = manager.getCache(ecName);
        Ehcache nativeCache = (Ehcache) cache.getNativeCache();
        if (cache != null && nativeCache.getKeys() != null && nativeCache.getKeys().size() > 0) {
            for (String existKey : (List<String>)nativeCache.getKeys()) {
                if (existKey.startsWith(key)) {
                    nativeCache.remove(existKey);
                }
            }
        }
    }
}
