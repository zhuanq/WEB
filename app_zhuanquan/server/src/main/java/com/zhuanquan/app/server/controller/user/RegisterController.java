package com.zhuanquan.app.server.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhuanquan.app.common.component.interceptor.SessionInterceptor;
import com.zhuanquan.app.common.constants.user.LoginType;
import com.zhuanquan.app.common.exception.BizErrorCode;
import com.zhuanquan.app.common.exception.BizException;
import com.zhuanquan.app.common.view.ApiResponse;
import com.zhuanquan.app.common.view.vo.user.BindAndChoosePersistRequestVo;
import com.zhuanquan.app.common.view.vo.user.MobileRegisterRequestVo;
import com.zhuanquan.app.common.view.vo.user.RegisterResponseVo;
import com.zhuanquan.app.common.view.vo.user.SelectFollowAuthorRequestVo;
import com.zhuanquan.app.common.view.vo.user.SelectFollowTagsRequestVo;
import com.zhuanquan.app.server.controller.common.BaseController;
import com.zhuanquan.app.server.service.LoginService;
import com.zhuanquan.app.server.service.RegisterService;

@Controller
@RequestMapping(value = "/register")
public class RegisterController extends BaseController {

	@Resource
	private RegisterService registerService;

	@Resource
	private LoginService loginService;

	@RequestMapping(value = "/registerByMobile", produces = { "application/json" })
	@ResponseBody
	public ApiResponse registerByMobile(MobileRegisterRequestVo vo) {

		RegisterResponseVo response = registerService.mobileRegister(vo);

		return ApiResponse.success(response);

	}

	/**
	 * 绑定未注册过的手机号
	 * 
	 * @param uid
	 *            原来账号的uid
	 * @param mobile
	 *            手机号
	 * @param verifycode
	 *            验证码
	 * @return
	 */
	@RequestMapping(value = "/bindUnregisterMobile", produces = { "application/json" })
	@ResponseBody
	public ApiResponse bindUnregisterMobile(long uid, String mobile, String password, String verifycode) {

		checkLoginUid(uid);

		registerService.bindUnregisterMobile(uid, mobile, password, verifycode);

		return ApiResponse.success();
	}

	/**
	 * 如果手机号已经注册了，那么用户需要选择，当前保留哪一个账号
	 * 
	 * @param vo
	 */
	@RequestMapping(value = "/bindMobileAndChoosePersistAccount", produces = { "application/json" })
	@ResponseBody
	public ApiResponse bindMobileAndChoosePersistAccount(BindAndChoosePersistRequestVo vo) {

		checkLoginUid(vo.getUid());

		registerService.mergeMobileAccount(vo.getUid(), vo.getMobile(), vo.getVerifycode(),
				vo.getPersistMobileAccount() == 1);

		return ApiResponse.success();
	}

	/**
	 * 检查手机是否已经被绑定了
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/beforeBindCheck", produces = { "application/json" })
	@ResponseBody
	public ApiResponse beforeBindCheck(String mobile) {

		int result = registerService.beforeBindCheck(mobile);

		return ApiResponse.success(result);
	}

	/**
	 * 如果手机号已经被注册过，用户需要选择当前保留哪个账号的数据.
	 * 
	 * @param uid
	 *            登录的uid
	 * @param mobile
	 *            手机号
	 * @param verifycode
	 *            手机验证码
	 * @param persistMobileAccount
	 *            是否保留手机账号的数据 1-保留 0-不保留
	 */

	@RequestMapping(value = "/mergeMobileAccount", produces = { "application/json" })
	@ResponseBody
	public ApiResponse mergeMobileAccount(long uid, String mobile, String verifycode, int persistMobileAccount) {

		checkLoginUid(uid);

		boolean isPersistMobileAccount = true;

		if (persistMobileAccount == 1) {
			isPersistMobileAccount = true;
		} else if (persistMobileAccount == 0) {
			isPersistMobileAccount = false;
		} else {
			throw new BizException(BizErrorCode.EX_ILLEGLE_REQUEST_PARM.getCode());
		}

		registerService.mergeMobileAccount(uid, mobile, verifycode, isPersistMobileAccount);

		return ApiResponse.success();

	}

	/**
	 * 发送注册的短信验证
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/sendRegSms", produces = { "application/json" })
	@ResponseBody
	public ApiResponse sendRegSms(String mobile) {

		registerService.sendRegisterSms(mobile);

		return ApiResponse.success();
	}

	/**
	 * 登录进来之后，发送修改密码的短信
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/sendChangePwdSms", produces = { "application/json" })
	@ResponseBody
	public ApiResponse sendChangePwdSms(long uid) {

		return ApiResponse.success();
	}

	// /**
	// * 发送忘记密码的短信
	// */
	// @RequestMapping(value = "/sendForgetPwdSms",produces =
	// {"application/json"})
	// @ResponseBody
	// public ApiResponse sendForgetPwdSms(String mobile) {
	//
	//
	//
	// }
	//

	// /**
	// * 注册设置性别
	// *
	// * @param uid
	// * @param gender
	// * 0-男 1-女
	// * @return
	// */
	// @RequestMapping(value = "/setGenderOnRegister")
	// @ResponseBody
	// public ApiResponse setGenderOnRegister(long uid, int gender) {
	//
	// checkLoginUid(uid);
	//
	// registerService.setGenderOnRegister(uid, gender);
	//
	// return ApiResponse.success();
	// }

	/**
	 * 注册设置昵称
	 * 
	 * @param uid
	 * @param nickName
	 * @return
	 */
	@RequestMapping(value = "/setNickNameAndGenderOnRegister", produces = { "application/json" })
	@ResponseBody
	public ApiResponse setNickNameOnRegister(long uid, String nickName, int gender) {
		checkLoginUid(uid);

		registerService.setNickNameAndGenderOnRegister(uid, nickName, gender);

		return ApiResponse.success();
	}

	/**
	 * 注册设置关注的tag
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/setFollowTagsOnRegister", produces = { "application/json" })
	@ResponseBody
	public ApiResponse setFollowTagsOnRegister(SelectFollowTagsRequestVo request) {

		checkLoginUid(request.getUid());

		registerService.setFollowTagsOnRegister(request.getUid(), request.getTagIds());

		return ApiResponse.success();
	}

	/**
	 * 注册设置关注的作者
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/setFollowAuthorsOnRegister", produces = { "application/json" })
	@ResponseBody
	public ApiResponse setFollowAuthorsOnRegister(SelectFollowAuthorRequestVo request) {
		checkLoginUid(request.getUid());

		registerService.setFollowAuthorsOnRegister(request.getUid(), request.getAuthorIds());

		return ApiResponse.success();
	}

	// @RequestMapping(value = "/registerAppoinement")
	// public String registerAppoinement(Model model){
	//
	//// model.put("redirctUrl",loginService.getThirdLoginAuthUrl(LoginType.CHANNEL_WEIBO));
	//
	//
	// return "/registerappointment/welcome.html";
	//// return "/registerappointment/welcome.html";
	// }

	@RequestMapping(value = "/registerAppoinement", produces = { "text/html;charset=utf-8" })
	public ModelAndView registerAppoinement(HttpServletRequest request) {

		String url = loginService.getThirdLoginAuthUrl(LoginType.CHANNEL_WEIBO);

		HttpSession session = request.getSession();
		session.setAttribute("redirectUrl", url);
		
//		
//		int count = registerService.queryRegisterAppointmentCount();
//		session.setAttribute("reg_appoint_count", count);


		Map<String, Object> map = new HashMap<String, Object>();
		map.put("redirectUrl", url);
	
		ModelAndView mv = new ModelAndView("/registerappointment/welcome.jsp", map);

		return mv;
	}

}