package com.huyang.common.type;


/**
 * ehcache缓存.
 *
 */
public enum MoneyLogType {

    DISABLE((byte)0,"已结算"),
    ENABLE((byte)1,"未结算"),
    ;


    private Byte code;  // 缓存名称 对应ehcache.xml中的配置

    private String key;   // 存储的键名称

    MoneyLogType(Byte code, String key) {
        this.code = code;
        this.key = key;
    }

    public Byte getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }
}
