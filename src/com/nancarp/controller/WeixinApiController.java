package com.nancarp.controller;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.weixin.sdk.api.AccessToken;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.CallbackIpApi;
import com.jfinal.weixin.sdk.api.GroupsApi;
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
	
	// 获取用户分组列表
	public void getGroups () {
		ApiResult apiResult = GroupsApi.get();
		renderJson(apiResult.getList("groups"));
	}
	
	// 创建分组
	public void createGroup () {
		Date date = new Date();
		GroupsApi.create(date.toString());
		ApiResult apiResult = GroupsApi.get();
		renderJson(apiResult.getList("groups"));
	}
	
	// 删除分组
	public void deleteGroup () {
		ApiResult apiResult = null;
		// 获取当前用户分组列表
		apiResult = GroupsApi.get();
		// 结果转化成 JSONArray 对象
		JSONArray jsonArray = JSON.parseObject(apiResult.toString()).getJSONArray("groups");
		// 删除 id >= 100 的用户分组
		for(int i = 0, size = jsonArray.size(); i < size; i++){
			// 转化成 JSONObject 对象
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			// 获取分组 id
			int id = jsonObject.getInteger("id");
			if (id >= 100){
				// 删除用户分组
				GroupsApi.delete(id);
			}
		}
		// 获取删除后的用户数组
		apiResult = GroupsApi.get();
		renderJson(apiResult.getList("groups"));
	}
	
	// 修改分组名
	public void updateGroup(){
		String msg = new String();
		
		// 更新前
		GroupsApi.create("原分组名");
		ApiResult apiResult = GroupsApi.get();
		msg += "更新前： " + apiResult.toString() + "\n";
		
		// 结果转化成 JSONArray 对象
		JSONArray jsonArray = JSON.parseObject(apiResult.toString()).getJSONArray("groups");
		// 更新用户分组
		for(int i = 0, size = jsonArray.size(); i < size; i++){
			// 转化成 JSONObject 对象
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			// 获取分组名
			String origin = jsonObject.getString("name");
			if ("原分组名".equals(origin)){
				// 更新用户分组
				GroupsApi.update(jsonObject.getInteger("id"), "新分组名");
				break;
			}
		}
		
		// 更新后
		msg += "更新后： " + GroupsApi.get().toString() + "\n";
		renderText(msg);
	}
	
	// 获取用户GroupID
	public void getGroupId(){
		String openId = "oVOX00_xo-tSjfPE5ySJuUe7OywI";
		GroupsApi.membersUpdate(openId, 0);
		renderText(GroupsApi.getId(openId).toString());
	}
	
	// 移动用户分组
	public void membersUpdate(){
		//Random groupid = new Random();
		//GroupsApi.membersUpdate(openId, 1);
		ApiResult apiResult = null;
		String openId = "oVOX00_xo-tSjfPE5ySJuUe7OywI";
		GroupsApi.membersUpdate(openId, 109);
		apiResult = GroupsApi.getId(openId);
		renderText(apiResult.toString());
	}
		
	// 批量移动用户分组
	public void membersBatchUpdate(){
		String openId = "oVOX00_xo-tSjfPE5ySJuUe7OywI";
		GroupsApi.membersUpdate(openId, 0);
		renderText(GroupsApi.getId(openId).toString());
	}
}
