package com.zhuanquan.app.common.view.vo.user;


import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.zhuanquan.app.common.constants.user.LoginType;
import com.zhuanquan.app.common.exception.BizErrorCode;
import com.zhuanquan.app.common.exception.BizException;
import com.zhuanquan.app.common.utils.MD5;

public class LoginByOpenIdRequestVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5312866948364795822L;
	
	
	/**
	 * token 
	 */
	private String token;
	
	/**
	 * 第三方id
	 */
	private String openId;
	
	/**
	 * 频道类型   1-微博
	 * @see com.zhuanquan.app.common.constants.LoginType
	 */
	private int channelType;
	
	/**
	 * 
	 */
	private int loginType;
	
	
	
	/**
	 * 签名
	 */
	private String sign;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}
	
	

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	/**
	 * 
	 */
	public void validat() {
		
		if(StringUtils.isEmpty(token) || StringUtils.isEmpty(openId) ){
			throw new BizException(BizErrorCode.EX_ILLEGLE_REQUEST_PARM.getCode());
		}
		
		//-1是 vip账号初始化的默认值
		if("-1".equals(token)) {
			throw new BizException(BizErrorCode.EX_ILLEGLE_REQUEST_PARM.getCode());
		}
		
		
		if(channelType!=LoginType.CHANNEL_WEIBO) {
			throw new BizException(BizErrorCode.EX_ILLEGLE_REQUEST_PARM.getCode());
		}


//		//验证签名
//		String msg = openId+"_"+token+"_"+channelType;
//		String msgSign = MD5.md5(msg);
//		
//		if(!sign.equals(msgSign)) {
//			throw new BizException(BizErrorCode.EX_ILLEGLE_REQUEST_PARM.getCode());
//		}

		
	}
	
	
	
	
}