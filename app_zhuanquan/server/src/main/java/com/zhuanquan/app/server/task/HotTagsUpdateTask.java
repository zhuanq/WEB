//package com.zhuanquan.app.server.task;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import javax.annotation.Resource;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.zhuanquan.app.common.component.cache.RedisKeyBuilder;
//import com.zhuanquan.app.common.component.cache.redis.utils.RedisHelper;
//import com.zhuanquan.app.common.constants.RegisterFlowConstants;
//import com.zhuanquan.app.common.model.common.Tag;
//import com.zhuanquan.app.common.utils.DateUtil;
//import com.zhuanquan.app.common.view.vo.author.SuggestTagVo;
//import com.zhuanquan.app.dal.dao.author.TagDAO;
//import com.zhuanquan.app.dal.dao.user.UserFollowTagsMappingDAO;
//import com.zhuanquan.app.server.cache.TagCache;
//
///**
// * 热点tag自动更新缓存：
// * 
// * top 100的
// * 
// * 
// * @author zhangjun
// *
// */
//@Component
//public class HotTagsUpdateTask {
//
//	@Resource
//	private TagDAO tagDAO;
//
//	@Resource
//	private UserFollowTagsMappingDAO userFollowTagsMappingDAO;
//
//	@Resource
//	private RedisHelper redisHelper;
//
//	@Resource
//	private TagCache tagCache;
//
//	@Scheduled(cron = "0 0/1 * * * ?")
//	public void run() {
//
//		try {
//			exec();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void exec() {
//		
//		System.out.println("HotTagsUpdateTask  run, exe time is :" + DateUtil.date2StringByTimeZone(DateUtil.DATETIME_PATTENT,new Date()));
//		
//		int batchNum = RegisterFlowConstants.REG_SUGGEST_TAG_PAGE_SIZE;
//
//		int recentNumLimit = RegisterFlowConstants.REG_SUGGEST_TAG_RECENT_NUM_LIMIT;
//
//		// 48小时最火的标签
//		List<Long> hotTagRecently = userFollowTagsMappingDAO.queryHotTagsRecently(recentNumLimit);
//
//
//
//		Map<String, Tag> map = tagCache.getTagMapByIds(hotTagRecently);
//
//		int pageNum = 0;
//
//		List<SuggestTagVo> suggestList = new ArrayList<SuggestTagVo>();
//		
//		for (Long id : hotTagRecently) {
//
//			Tag tag = map.get(id.toString());
//
//			if (tag != null) {
//
//				SuggestTagVo vo = new SuggestTagVo();
//
//				vo.setTagId(tag.getTagId());
//				vo.setTagName(tag.getTagName());
//				vo.setTagType(tag.getTagType());
//				suggestList.add(vo);
//
////				// 达到一个分页批次的上线
////				if (suggestList.size() >= batchNum) {
////					pageNum++;
////					String key = RedisKeyBuilder.getPublicHotTagsSuggestKey();
////
////					String hashKey = pageNum + "_" + batchNum;
////
////					redisHelper.hashSet(key, hashKey, JSON.toJSONString(suggestList));
////					redisHelper.expire(hashKey, 1, TimeUnit.HOURS);
////
////					suggestList.clear();
////				}
//
//			}
//		}
//
//		// 最后一个批次有可能不满30
//		if (suggestList.size() > 0) {
//
//			String key = RedisKeyBuilder.getPublicHotTagsSuggestKey();
//			
//			String hashKey = pageNum + "_" + batchNum;
//
//			// 有可能不满30个
//			redisHelper.hashSet(key, hashKey, JSON.toJSONString(suggestList));
//			redisHelper.expire(hashKey, 1, TimeUnit.HOURS);
//
//			suggestList.clear();
//		}
//
//	}
//
//}