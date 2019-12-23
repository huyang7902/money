package com.huyang.common.utils;

import com.huyang.lib.to.User;
import com.huyang.web.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author yang.hu
 * @Date 2019/12/23 9:54
 */
public class RequestAttrUtil {

    public static User getUser(HttpServletRequest request) {
        return (User) request.getAttribute(Constants.REQ_ATTR_USER);
    }

    public static void setUser(HttpServletRequest request, User user) {
        request.setAttribute(Constants.REQ_ATTR_USER, user);
    }

}
