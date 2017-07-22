package com.zhuanquan.app.server.controller.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuanquan.app.common.view.ApiResponse;
import com.zhuanquan.app.server.openapi.OpenApiService;

@Controller
@RequestMapping(value = "/openapi")
public class OpenApiController extends BaseController {
	
	
	@Resource
	private OpenApiService openApiService;
	

	/**
	 * 微博登录授权回掉
	 */
	@RequestMapping(value = "/weiboAuthCallback", produces = { "text/html" })
	@ResponseBody
	public String weiboAuthCallback(String code,String state) {
		
		openApiService.parseWeiboAuthCallback(state, code);

		return getCloseCmd();
	}
	
	
	private String  getCloseCmd(){
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("window.opener.loginSuccess();");
		sb.append("window.close();");
		sb.append("</script>");
		
		return sb.toString();
	}
	
	
	
	@RequestMapping(value = "/weiboAuthCallback2", produces = { "text/html" })
	public String weiboAuthCallback2(String code,String state) {

		return "forward:/index.do";
	}
	
	
}