package com.nancarp.controller;

import com.jfinal.weixin.sdk.api.AccessToken;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.CallbackIpApi;
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

	// 主页
	public void index() {
		render("index.html");
	}

	// 获取 access token 字符串
	public void getAccessTokenStr() {
		String accessTokenStr = AccessTokenApi.getAccessTokenStr();
		renderText(accessTokenStr);
	}

	// 获取 access token
	public void getAccessToken() {
		AccessToken accessToken = AccessTokenApi.getAccessToken();
		renderText(accessToken.getAccessToken());
	}

	// 获取微信服务器IP地址
	public void getCallbackIp() {
		ApiResult apiResult = CallbackIpApi.getCallbackIp();
		renderText(apiResult.toString());
	}
}
