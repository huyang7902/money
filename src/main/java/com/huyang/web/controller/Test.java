package com.huyang.web.controller;

import org.springframework.util.DigestUtils;

/**
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/10/2 21:10
 */
public class Test {
    public static void main(String[] args) {
        String s = "123";//202cb962ac59075b964b07152d234b70
        String md5 = DigestUtils.md5DigestAsHex(s.getBytes());
        System.out.println(md5);//
    }
}
