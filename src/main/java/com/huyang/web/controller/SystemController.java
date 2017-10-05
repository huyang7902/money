package com.huyang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/10/2 20:09
 */
@RequestMapping("/system")
@Controller
public class SystemController {

    @RequestMapping("/updateLog.html")
    public String updateLog(){
        return "/system/updateLog/updateLog";
    }
}
