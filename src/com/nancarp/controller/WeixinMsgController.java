package com.nancarp.controller;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import com.nancarp.utils.WeixinUtil;

public class WeixinMsgController extends MsgControllerAdapter {
	
	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的 ApiConfig 对象即可 可以通过在请求 url 中挂参数来动态从数据库中获取
	 * ApiConfig 属性值
	 */
	@Override
	public ApiConfig getApiConfig() {
		return WeixinUtil.getApiConfig();
	}

	/* 
	 * 接收文本消息事件
	 */
	@Override
	protected void processInTextMsg(InTextMsg inTextMsg) {
		// 其它文本消息直接返回原值
		OutTextMsg outMsg = new OutTextMsg(inTextMsg);
		outMsg.setContent("\t文本消息已成功接收，内容为： " + inTextMsg.getContent());
		render(outMsg);
	}
	
	@Override
	protected void processInFollowEvent(InFollowEvent inFollowEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
		// TODO Auto-generated method stub
		
	}

}
