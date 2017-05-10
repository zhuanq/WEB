package com.zhuanquan.app.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zhuanquan.app.common.constants.LoginType;
import com.zhuanquan.app.common.exception.BizErrorCode;
import com.zhuanquan.app.common.exception.BizException;
import com.zhuanquan.app.common.utils.HttpUtil;
import com.zhuanquan.app.server.service.OpenApiService;

@Service
public class OpenApiServiceImpl implements OpenApiService {

	private static Logger logger = LoggerFactory.getLogger(OpenApiServiceImpl.class);

	// 微博url，检测token的uid是哪个
	private final String WEIBO_TOKEN_CHECK_URL = "https://api.weibo.com/oauth2/get_token_info";

	@Override
	public void checkToken(String accessToken, String openId, int channelType) {

		switch (channelType) {
		case LoginType.CHANNEL_WEIBO:

			checkWeiboToken(accessToken, openId);
			return;

		default:

			throw new BizException(BizErrorCode.EX_ILLEGLE_REQUEST_PARM.getCode());

		}

	}

	/**
	 * 检测微博token是否合法
	 * 
	 * https://api.weibo.com/oauth2/get_token_info?access_token=2.
	 * 00TiLlpB0PERQyad7d047a15o8EcVB
	 * 
	 * 返回 {"uid":1680972425,"appkey":"890459019","scope":null,"create_at":
	 * 1491031220,"expire_in":157357699}
	 * 
	 * 
	 * @param accessToken
	 * @param openId
	 */
	private void checkWeiboToken(String accessToken, String openId) {

		try {

			Map<String, String> parmMap = new HashMap<String, String>();
			parmMap.put("access_token", accessToken);

			String str = HttpUtil.sendPostSSLRequest(WEIBO_TOKEN_CHECK_URL, parmMap);

			logger.info("OpenApiServiceImpl checkWeiboToken :str = " + (str == null ? "null" : str));

			if (!StringUtils.isEmpty(str)) {

				JSONObject obj = JSONObject.parseObject(str);
				if (obj != null) {
					String uid = obj.getString("uid");
					if (openId.equals(uid)) {
						return;
					}

				}
			}

			throw new BizException(BizErrorCode.EX_OPEN_ACCOUNT_TOKEN_VALIDATE_ERROR.getCode());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BizException(BizErrorCode.EX_OPEN_ACCOUNT_TOKEN_VALIDATE_ERROR.getCode());
		}

	}

}