package com.huyang.service;

import com.huyang.common.cache.EchcacheManager;
import com.huyang.common.type.EhCacheType;
import com.huyang.common.utils.CookieUtils;
import com.huyang.common.utils.RequestUtil;
import com.huyang.common.utils.ResponseResult;
import com.huyang.dao.UserMapper;
import com.huyang.lib.to.User;
import com.huyang.web.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * 用户实现类
 * 
 * @Project: ids
 * @Package com.huyang.service.impl.user
 * @author HuYang
 * @date 2017年4月30日 下午2:03:03
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	/**
	 * 用户通用登录方法
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public ResponseResult UserLogin(HttpServletRequest request,HttpServletResponse response) {

		Integer id = RequestUtil.getInteger(request, "id");
		String password = RequestUtil.getString(request, "password");

		User user = userMapper.selectByPrimaryKey(RequestUtil.getInteger(request, "id"));
		if (user == null) {
			logger.info("用户：" + user.getId() + "用户名或密码错误！");
			return ResponseResult.build(400, "用户名不存在！");
		}
		if (!StringUtils.isBlank(user.getPassword())) {
			if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
				logger.info("用户：" + id + "密码错误！");
				return ResponseResult.build(400, "密码错误！");
			}
		}
		// 生成token
		String token = UUID.randomUUID().toString();
		// 保存用户名密码之前清空密码
		user.setPassword(null);
		// 把用户信息写入Echcache
		String key = String.format(EhCacheType.USER_LOGIN.getKey(), token);
		EchcacheManager.setCacheByKeyAndName(EhCacheType.USER_LOGIN.getName(), key, user);
		// 设置session的过期时间
		//jedisClient.expire(Constants.IDS_USER_TOKEN + ":" + token, Constants.expire);

		// 添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效
		CookieUtils.setCookie(request, response, Constants.USER_TOKEN, token);
		// 设置最后登录时间
		user.setLastLogin(new Date());
		userMapper.updateByPrimaryKeySelective(user);

		logger.info("用户：" + user.getName() + "登录成功！");
		return ResponseResult.build(200, user.getName()+"欢迎回来！", user);
	}
//	public static void main(String[] args) {
//		String md5DigestAsHex = DigestUtils.md5DigestAsHex("123".getBytes());
//		System.out.println(md5DigestAsHex);
//	}

	@Override
	public User findUserById(Integer id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public ResponseResult upDateUser(User user,String token) {
		int i = userMapper.updateByPrimaryKey(user);
		if (i != 1) {
			return ResponseResult.build(400, "更新信息失败！");
		}
		User newUser = userMapper.selectByPrimaryKey(user.getId());
		newUser.setPassword(null);
		// 把用户信息写入EchcacheManager
		String key = String.format(EhCacheType.USER_LOGIN.getKey(), token);
		EchcacheManager.removeMatchCache(EhCacheType.USER_LOGIN.getKey(), key);
		EchcacheManager.setCacheByKeyAndName(EhCacheType.USER_LOGIN.getName(), key, newUser);
		return ResponseResult.build(200, "修改成功！", newUser);
	}

	@Override
	public ResponseResult resetPass(User loginUser, String originPass, String newPass) {
		User user = userMapper.selectByPrimaryKey(loginUser.getId());
		if (!DigestUtils.md5DigestAsHex(originPass.getBytes()).equals(user.getPassword())) {
			logger.info("用户：" + loginUser.getId() + "修改密码，原密码错误！");
			return ResponseResult.build(400, "原密码错误！");
		}
		loginUser.setPassword(DigestUtils.md5DigestAsHex(newPass.getBytes()));
		int i = userMapper.updateByPrimaryKey(loginUser);
		if (i != 1) {
			return ResponseResult.build(400, "修改密码失败！");
		}
		return ResponseResult.ok();
	}

}
