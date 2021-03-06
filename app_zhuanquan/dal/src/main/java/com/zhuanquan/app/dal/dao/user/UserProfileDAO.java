package com.zhuanquan.app.dal.dao.user;

import java.util.List;

import com.zhuanquan.app.common.model.user.UserProfile;


public interface UserProfileDAO {
	
	/**
	 * 根据uid查询
	 * 
	 * @param uid
	 * @return
	 */
	UserProfile queryById(long uid);
	
	
	
	/**
	 * 根据authorid查询
	 * 
	 * @param uid
	 * @return
	 */
	UserProfile queryByAuthorId(long authorId);
	
	
	/**
	 * 插入记录
	 * @param profile
	 * @return
	 */
	long insertRecord(UserProfile profile);
	
	
	/**
	 * 查询昵称是否被使用了
	 * @param nickName
	 * @return
	 */
	boolean queryNickNameHasBeenUsed(String nickName);
	
    /**
     * 修改nick name 在step1，
     * @param uid
     * @param nickName
     * @return
     */
	int updateNickName(long uid,String nickName);
	
	/**
	 * 更新注册的状态
	 * @param uid 用户id
	 * @param registerStatus 注册状态
	 * @return
	 */
	int updateRegisterStatus(long uid,int registerStatus);
	
	
    /**
     * 修改性别
     * @param uid
     * @param gender 性别 0-男 1-女
     * @return
     */
	int updateGender(long uid,int gender);
	
	
    /**
     * 修改nick name 在step1，同时更新profile的register状态
     * @param uid
     * @param nickName
     * @return
     */
	int updateNickNameAndGenderOnRegister(long uid,String nickName,int gender,int registerStatus);
	
	
	
	List<UserProfile> queryByPage();
	
	
}