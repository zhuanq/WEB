package com.zhuanquan.app.common.component.event.redis;

public enum RedisCacheEnum {

	REDIS_CACHE_USER_OPEN_ACCOUNT("user_open_account"),
	
	REDIS_CACHE_TAG("p_tag"),
	
	
	REDIS_CACHE_AUTHOR_HOT_INDEXES("author_hot_indexes"),


	;

	/**
	 * 表名
	 */
	private String table;

	private RedisCacheEnum(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

}