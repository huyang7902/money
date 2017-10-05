package com.huyang.service;

import com.huyang.common.utils.ResponseResult;
import com.huyang.lib.to.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户service层
 * 
 * @Project: ids
 * @Package com.huyang.service.system
 * @author HuYang
 * @date 2017年4月30日 下午1:59:34
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
public interface UserService {

	/**
	 * 用户通用登录方法
	 * 
	 * @param request
	 * @return
	 */
	ResponseResult UserLogin(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 根据用户id查找
	 * 
	 * @param id
	 * @return
	 */
	User findUserById(Integer id);

	/**
	 * 修改用户信息(邮箱、电话)
	 * 
	 * @param user
	 * @return
	 */
	ResponseResult upDateUser(User user, String token);

	/**
	 * 修改用户密码
	 * 
	 * @param loginUser
	 * @param originPass
	 * @param newPass
	 * @return
	 */
	ResponseResult resetPass(User loginUser, String originPass, String newPass);

	/**
	 * 得到所有用户
	 * @return
	 */
	List<User> getAllUser();
}
