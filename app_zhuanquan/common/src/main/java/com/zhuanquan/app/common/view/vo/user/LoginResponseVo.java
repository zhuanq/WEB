package com.zhuanquan.app.common.view.vo.user;

import java.io.Serializable;

import com.zhuanquan.app.common.component.sesssion.SessionAttrbute;
import com.zhuanquan.app.common.component.sesssion.SessionHolder;


public class LoginResponseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6364221231951960984L;
	
	/**
	 * openid
	 */
	private String openId;
	
	/**
	 * 登录渠道类型
	 */
	private int channelType;
	
	/**
	 * uid
	 */
	private long uid;


	/**
	 * 是否允许关注
	 */
	private Integer allowAttation;
	
	
	/**
	 * 头像url
	 */
	private String headUrl;

	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 *  注册状态  3-正常状态(注册引导完成) 0-注册第一步未完成 1-注册第二步未完成  2-注册第三步未完成
	 */
	private int regStat;

    /**
     * 是否知名大v
     */
	private int isVip;
	
	/**
	 * 会话id
	 */
	private String sessionId;
	
//	
//	/**
//	 * 1-开启验证码 0-不用开启
//	 */
//	private int verifycodeFlag;
	

	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public int getIsVip() {
		return isVip;
	}


	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}


	public long getUid() {
		return uid;
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


	public void setUid(long uid) {
		this.uid = uid;
	}



	public Integer getAllowAttation() {
		return allowAttation;
	}


	public void setAllowAttation(Integer allowAttation) {
		this.allowAttation = allowAttation;
	}


	public String getHeadUrl() {
		return headUrl;
	}


	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public int getRegStat() {
		return regStat;
	}


	public void setRegStat(int regStat) {
		this.regStat = regStat;
	}

//
//	public int getVerifycodeFlag() {
//		
//		String flag = (String) SessionHolder.getGlobalSession().getAttribute(SessionAttrbute.VERIFY_CODE_FLAG);
//		return flag == null?0:Integer.parseInt(flag);
//	}


//	public void setVerifycodeFlag(int verifycodeFlag) {
//		this.verifycodeFlag = verifycodeFlag;
//	}
//	
	
	
}