package com.huyang.web;


public interface Constants {

	String JSON_ROOT = "JSON_ROOT";

	String REQ_ATTR_USER = "REQ_ATTR_USER";


	/**
	 * 用户session缓存key
	 */
	String USER_TOKEN = "MONEY_USER_TOKEN";

	/**
	 * 管理员菜单缓存key
	 */
	String IDS_ADMIN_MENU = "IDS_ADMIN_MENU";
	/**
	 * 普通教师菜单缓存key
	 */
	String IDS_USER_MENU = "IDS_USER_MENU";

	/**
	 * redis中缓存的时间
	 */
	int expire = 60 * 15;

	int HTTP_OK_STATUS = 200;


	String CHARSET_CODE = "UTF-8";

	// 返回参数
	String MSG_CODE = "msg_code";
	String MSG_DESC = "msg_desc";


}
