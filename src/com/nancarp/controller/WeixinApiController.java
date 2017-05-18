package com.nancarp.controller;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.nancarp.utils.WeixinUtil;

public class WeixinApiController extends ApiController {

	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的 ApiConfig 对象即可 可以通过在请求 url 中挂参数来动态从数据库中获取
	 * ApiConfig 属性值
	 */
	@Override
	public ApiConfig getApiConfig() {
		return WeixinUtil.getApiConfig();
	}
	
	public void index(){
		render("index.html");
	}

}
