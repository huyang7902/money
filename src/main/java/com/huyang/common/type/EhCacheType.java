package com.huyang.common.type;

import com.huyang.web.Constants;

/**
 * ehcache缓存.
 *
 */
public enum EhCacheType {

    USER_LOGIN("userLogin", Constants.USER_TOKEN + "：%s"),
    ;


    private String name;  // 缓存名称 对应ehcache.xml中的配置

    private String key;   // 存储的键名称

    EhCacheType(String name, String key) {
        this.name = name;
        this.key = key;
    }


    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

}
