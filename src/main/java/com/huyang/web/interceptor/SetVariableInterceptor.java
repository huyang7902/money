package com.huyang.web.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * SetVariableInterceptor 设置一些额外的信息拦截器
 * 
 * @Project: ids-web
 * @Package com.huyang.web.controller.interceptor
 * @author HuYang
 * @date 2017年4月23日 下午9:01:21
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
public class SetVariableInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String uri = request.getRequestURI();
		if (uri != null
				&& (uri.contains("/css/") || uri.contains("/img/") || uri.contains("/resources/")
						|| uri.contains("/js/") || uri.contains("/themes/"))) {
			return true;
		}

		// 添加上下文
		String contextPath = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + contextPath;
		String resourcesPath = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + contextPath + "/resources";
		request.setAttribute("contextPath", contextPath);///ids-web
		request.setAttribute("basePath", basePath);//http://localhost:8080/ids-web
		request.setAttribute("resourcesPath", resourcesPath);//http://localhost:8080/ids-web/resources
		
		return true;
	}
}
