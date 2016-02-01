package com.tiangong.dao.activity;

import java.util.List;

import com.tiangong.bean.RmActivityUser;

public interface RmActivityUserDao {
	/**
	 * 查询分页
	 * @param rmActivityUser
	 * @return
	 */
	public List find(RmActivityUser rmActivityUser);
	
	/***
	 * 总记录数
	 * @return
	 */
	public int count(RmActivityUser rmActivityUser);
}
