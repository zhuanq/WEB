package com.zhuanquan.app.server.controller.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhuanquan.app.common.component.interceptor.SessionInterceptor;
import com.zhuanquan.app.server.openapi.OpenApiService;
import com.zhuanquan.app.server.service.RegisterService;

@Controller
@RequestMapping(value = "/openapi")
public class OpenApiController extends BaseController {
	
	
	@Resource
	private OpenApiService openApiService;
	

	@Resource
	private RegisterService registerService;
	
	
	/**
	 * 微博登录授权回掉
	 */
	@RequestMapping(value = "/weiboAuthCallback", produces = { "text/html" })
	public String weiboAuthCallback(String code,String state) {
		

		openApiService.parseWeiboAuthCallback(state, code);

		return "forward:/registerappointment/weiboAuthCallback.jsp";
//		return getCloseCmd();
	}
	
	
	private String  getCloseCmd(){
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"/><title>异世遥</title></head><body>");
		sb.append("<script>");
		sb.append("window.opener.loginSuccess();");
		sb.append("window.close();");
		sb.append("</script></body></html>");
		
		return sb.toString();
	}
	
	
	
//	@RequestMapping(value = "/weiboAuthCallback2", produces = { "text/html" })
//	public String weiboAuthCallback2(String code,String state) {
//
//		return "forward:/registerappointment/close.jsp";
//	}
//	
	
}