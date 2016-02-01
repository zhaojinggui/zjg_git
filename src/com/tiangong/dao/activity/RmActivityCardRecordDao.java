package com.tiangong.dao.activity;

import java.util.List;

import com.tiangong.bean.RmActivityCardRecord;

public interface RmActivityCardRecordDao {
	/**
	 * 查询分页
	 * @param reRmActivityCardRecord
	 * @return
	 */
	public List find(RmActivityCardRecord reRmActivityCardRecord);
	
	/**
	 * 总记录数
	 * @return
	 */
	public int count(RmActivityCardRecord reRmActivityCardRecord);
}
