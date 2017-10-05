package com.huyang.web.controller;

import com.huyang.common.cache.EchcacheManager;
import com.huyang.common.type.EhCacheType;
import com.huyang.common.utils.CookieUtils;
import com.huyang.common.utils.ResponseResult;
import com.huyang.lib.to.User;
import com.huyang.service.UserService;
import com.huyang.web.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Project: money
 * @Package com.huyang.web.controller.login
 * @author HuYang
 * @date 2017年4月23日 下午5:34:21
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;



	@RequestMapping(value = { "/index.html", "", "/" })
	public String login(Model model) {
		/*
		 * List<BackMenu> menus =
		 * systemService.selectAllBackMenusWithLevelRelation();
		 * model.addAttribute("menus", menus);
		 */
		return "system/login/login";
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doLogin.html", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

		ResponseResult responseResult = userService.UserLogin(request,response);

		if (responseResult.getStatus() != 200) {
			model.addAttribute("responseResult", responseResult);
			return "system/login/login";
		}
		//User user = (User) responseResult.getData();
		//model.addAttribute("user", user);
		return "redirect:/money/index.html";
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/logout.html")
	public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		User loginUser = getLoginUser(request);
		String token = CookieUtils.getCookieValue(request, Constants.USER_TOKEN);
		String key = String.format(EhCacheType.USER_LOGIN.getKey(), token);
		EchcacheManager.getCacheByKeyAndName(EhCacheType.USER_LOGIN.getName(), key);
		CookieUtils.deleteCookie(request, response, Constants.USER_TOKEN);
		if (loginUser != null) {
			logger.info("用户id：" + loginUser.getId() + " 用户名：" + loginUser.getName() + " ,登出成功！");
		}
		response.sendRedirect(request.getAttribute("basePath") + "/login/index.html");
		return null;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveInfo.html")
	@ResponseBody
	public ResponseResult saveInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String email, @RequestParam(required = true) Long tel) {

		User loginUser = getLoginUser(request);
		String token = CookieUtils.getCookieValue(request, Constants.USER_TOKEN);
		loginUser.setTel(tel);
		loginUser.setEmail(email);
		loginUser.setPassword(userService.findUserById(loginUser.getId()).getPassword());
		ResponseResult idsResult = userService.upDateUser(loginUser, token);
		return idsResult;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param request
	 * @param response
	 * @param originPass
	 * @param newPass
	 * @param newPassConfirm
	 * @return
	 */
	@RequestMapping("/goResetPassword.html")
	@ResponseBody
	public ResponseResult resetPass(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String originPass, @RequestParam(required = true) String newPass,
			@RequestParam(required = true) String newPassConfirm) {


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
