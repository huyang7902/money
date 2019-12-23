package com.huyang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/9/11 14:14
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/index.html", "/"})
    public String index() {
        return "redirect:/money/index.html";
    }
}
