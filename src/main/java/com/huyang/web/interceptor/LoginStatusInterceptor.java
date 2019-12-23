package com.huyang.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.huyang.common.cache.EchcacheManager;
import com.huyang.common.type.EhCacheType;
import com.huyang.common.utils.CookieUtils;
import com.huyang.common.utils.RequestAttrUtil;
import com.huyang.lib.to.User;
import com.huyang.web.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * LoginStatusInterceptor 
 * 登陆状态拦截器
 * 
 * @Project: money
 * @Package com.huyang.web.interceptor
 * @author HuYang
 * @date 2017年4月29日 下午3:22:58
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
public class LoginStatusInterceptor extends HandlerInterceptorAdapter implements Constants {

	private static final Logger logger = LoggerFactory.getLogger(LoginStatusInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {

		String contextPath = (String) request.getAttribute("contextPath");

		String uri = request.getRequestURI();
		if (isIgnore(uri)) {
			return true;
		}
		User loginUser = getLoginUser(request);
		if (loginUser == null) {
			setResponse(request, response);
			return false;
		}
		RequestAttrUtil.setUser(request, loginUser);
		return true;
	}

	/**
	 * getLoginUser
	 *
	 * 获取当前用户，并重新设置redis失效时间
	 * 
	 * @param request
	 * @return User
	 */
	public static User getLoginUser(HttpServletRequest request) {
		 //从cookie中取token
        String token = CookieUtils.getCookieValue(request, Constants.USER_TOKEN);
		if (token == null) {
			return null;
		}
		String key = String.format(EhCacheType.USER_LOGIN.getKey(), token);
		User user = (User)EchcacheManager.getCacheByKeyAndName(EhCacheType.USER_LOGIN.getName(),key);
		if (user == null) {
			logger.info("没有找到Echcache用户信息，需要重新登陆...");
			return null;
		} else {
			//logger.info("找到了用户Echcache信息，重新设置redis失效时间，{}，{}，{}"+ redisKey,JSON.toJSONString(json,true), USER_SESSION.expire);
			//JedisClient.expire(Constants.IDS_USER_TOKEN +":"+ redisKey, USER_SESSION.expire);
		}
		return user;
	}

	/**
	 * isIgnore 忽略的url
	 */
	public static boolean isIgnore(String path) {
		if (path != null && (path.contains("/css/") || path.contains("/img/")
				|| path.contains("/resources/") || path.contains("/js/")
				|| path.contains("/themes/") || path.contains("/login"))) {
			return true;
		}
		return false;
	}

	/**
	 * setResponse 设置返回的json或者登陆页
	 *
	 * @param request
	 * @param response
	 * @return void
	 */
	public static void setResponse(HttpServletRequest request, HttpServletResponse response) {
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))
				|| request.getParameter("ajax") != null) {
			response.setContentType("application/json; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("statusCode", "301");
				result.put("message", "会话超时，请重新登录!");
				result.put("href", request.getAttribute("basePath") + "/login/index.html");

				out.println(JSON.toJSONString(result, false));
				out.flush();
			} catch (IOException e) {
			} finally {
				if (out != null) {
					out.close();
					out = null;
				}
			}
		} else {
			try {
				response.sendRedirect(request.getAttribute("basePath") + "/login/index.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
