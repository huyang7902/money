package com.huyang.web.controller;

import com.huyang.common.utils.RequestUtil;
import com.huyang.common.utils.ResponseResult;
import com.huyang.lib.to.User;
import com.huyang.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/10/2 20:10
 */
@Controller
@RequestMapping("/setting")
public class SettingController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * 跳转重置密码界面
     * @return
     */
    @RequestMapping("/resetPassword.html")
    public String resetPassword(){
        return "setting/resetPassword";
    }

    /**
     * 重置密码
     * @param request
     * @return
     */
    @RequestMapping("/doResetPassword.html")
    @ResponseBody
    public ResponseResult resetPassword(HttpServletRequest request) {

        String originPass = RequestUtil.getString(request, "originPass");
        String newPass = RequestUtil.getString(request, "newPass");
        String newPassConfirm = RequestUtil.getString(request, "newPassConfirm");
        User loginUser = getLoginUser(request);
        if (loginUser == null) {
            return ResponseResult.build(400, "请先登录");
        }

        if (StringUtils.isBlank(originPass) || StringUtils.isBlank(newPass) || StringUtils.isBlank(newPassConfirm)) {
            return ResponseResult.build(400, "输入不完整");
        }

        if (!newPass.equals(newPassConfirm)) {
            return ResponseResult.build(400, "两次密码输入不一致！");
        }

        ResponseResult responseResult = userService.resetPass(loginUser,originPass, newPass);

        return responseResult;

    }


}
