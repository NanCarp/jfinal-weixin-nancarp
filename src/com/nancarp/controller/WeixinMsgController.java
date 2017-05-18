package com.nancarp.controller;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;

public class WeixinMsgController extends MsgControllerAdapter {

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

	@Override
	public ApiConfig getApiConfig() {
		// TODO Auto-generated method stub
		return null;
	}


}
