package com.zhuanquan.app.server.cache.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhuanquan.app.common.component.cache.RedisKeyBuilder;
import com.zhuanquan.app.common.component.cache.redis.utils.RedisHelper;
import com.zhuanquan.app.common.model.user.UserFavourite;
import com.zhuanquan.app.dal.dao.user.UserFavouriteDAO;
import com.zhuanquan.app.server.cache.UserFavCache;


@Service
public class UserFavCacheImpl implements UserFavCache {

	@Resource
	private UserFavouriteDAO userFavouriteDAO;
	
	
	@Resource
	private RedisHelper redisHelper;
	
	@Override
	public UserFavourite queryUserFavouriteRecord(long uid, long workId) {

		
		String favKey = RedisKeyBuilder.getUserFavKey(uid);

		String obj = redisHelper.hashGet(favKey, workId+"");
		
		//有记录
		if(obj!=null) {
			return JSON.parseObject(obj, UserFavourite.class);
		}
		
		
		List<UserFavourite> list = userFavouriteDAO.queryAllFavWork(uid);
		
		
	
		//TODO
		
		return null;
	}
	
}