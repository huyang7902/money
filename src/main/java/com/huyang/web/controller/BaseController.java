package com.huyang.web.controller;

import com.alibaba.fastjson.JSON;
import com.huyang.common.cache.EchcacheManager;
import com.huyang.common.type.EhCacheType;
import com.huyang.common.utils.CookieUtils;
import com.huyang.lib.to.User;
import com.huyang.web.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * BaseController 基础控制器
 */
@Controller
public class BaseController implements Constants {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);


	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception e) {
		logger.info("[URL:]" + request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + "/" + request.getRequestURI() + "?"
				+ request.getQueryString());
		logger.info("[ERROR:]" + e.getMessage(), e);

		String contentType = request.getHeader("Content-Type");

		if (StringUtils.isNotBlank(contentType) && contentType.contains("json")) {
			return JSON.toJSONString("");
		}
		request.setAttribute("msg", e.getMessage());
		return "error";
	}

	/**
	 * getLoginUser 获得当前登录的用户实体
	 *
	 * @param
	 * @return User
	 */
	protected User getLoginUser(HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, Constants.USER_TOKEN);
		//String key = (String) request.getSession().getAttribute(Constants.IDS_USER_TOKEN);
		if (token == null) {
			return null;
		}
		String key = String.format(EhCacheType.USER_LOGIN.getKey(), token);
		User user = (User) EchcacheManager.getCacheByKeyAndName(EhCacheType.USER_LOGIN.getName(),key);
		if (user == null) {
			return null;
		}
		return user;
	}

}
