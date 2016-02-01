package com.tiangong.service.activity;

import java.util.List;

import com.tiangong.bean.RmActivityUser;

public interface RmActivityUserService {
	/**
	 * 查询分页
	 * @param rmActivityUser
	 * @return
	 */
	public List find(RmActivityUser rmActivityUser);
	
	/**
	 * 总记录数
	 * @param rmActivityUser
	 * @return
	 */
	public int count(RmActivityUser rmActivityUser);
}
