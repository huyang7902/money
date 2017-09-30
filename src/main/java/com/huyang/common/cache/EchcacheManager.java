package com.huyang.common.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.List;

public class EchcacheManager {

    /**
     * 获取缓存中对象
     *
     * @param ecName 缓存名称 对应ehcache.xml中的配置
     * @param key    存储的键名称
     * @return
     */
    public static Object getCacheByKeyAndName(String ecName, String key) {
        Element el = null;
        CacheManager manager = CacheManager.create();
        // 通过manager可以生成指定名称的Cache对象
        Cache cache = manager.getCache(ecName);
//        List a = cache.getKeys();
        if (cache != null && cache.isKeyInCache(key)) {
            el = cache.get(key);
            if (el != null)
                return el.getObjectValue();
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
        Element el = null;
        CacheManager manager = CacheManager.create();
        // 通过manager可以生成指定名称的Cache对象
        Cache cache = manager.getCache(ecName);
        if (value != null) {
            el = new Element(key, value);
            cache.put(el);
        }
    }

    /**
     * 清除缓存
     *
     * @param ecName
     * @param key
     */
    public static void removeCache(String ecName, String key) {
        CacheManager manager = CacheManager.create();     //通过manager可以生成指定名称的Cache对象
        Cache cache = manager.getCache(ecName);
        if (cache != null && cache.isKeyInCache(key)) {   //将指定key的缓存对象从缓存中清除
            cache.remove(key);
        }
    }

    /**
     * 清除指定cache name下面的所有缓存
     *
     * @param ecName
     */
    public static void removeAllCache(String ecName) {
        CacheManager manager = CacheManager.create();     //通过manager可以生成指定名称的Cache对象
        Cache cache = manager.getCache(ecName);
        if (cache != null) {
            cache.removeAll();
        }
    }

    /**
     * 清除指定cache name下面的前缀匹配key值的缓存
     *
     * @param ecName
     */
    public static void removeMatchCache(String ecName, String key) {
        CacheManager manager = CacheManager.create();     //通过manager可以生成指定名称的Cache对象
        Cache cache = manager.getCache(ecName);
        if (cache != null && cache.getKeys() != null && cache.getKeys().size() > 0) {
            for (String existKey : (List<String>)cache.getKeys()) {
                if (existKey.startsWith(key)) {
                    cache.remove(existKey);
                }
            }
        }
    }
}
