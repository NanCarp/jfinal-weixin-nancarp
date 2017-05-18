package com.nancarp.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.nancarp.controller.HelloWorldController;
import com.nancarp.controller.WeixinApiController;
import com.nancarp.controller.WeixinMsgController;

public class WeixinConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		// 加载配置文件
		PropKit.use("a_little_config.txt");
		// 读取配置文件，判断是否为开发模式，默认 false
		me.setDevMode(PropKit.getBoolean("devMode", false));
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
        ApiConfigKit.setDevMode(me.getDevMode());
        // 默认使用的jackson，下面示例是切换到fastJson
        //me.setJsonFactory(new FastJsonFactory());
	}

	@Override
	public void configRoute(Routes me) {
		// HelloWorld 路由
		me.add("/hello",HelloWorldController.class);
		// 
		me.add("/msg", WeixinMsgController.class);
		//
		me.add("/api", WeixinApiController.class, "/api");
	}

	@Override
	public void configEngine(Engine me) {
		
	}

	@Override
	public void configPlugin(Plugins me) {
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		
	}

}
