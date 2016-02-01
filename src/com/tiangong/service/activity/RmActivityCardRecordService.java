package com.tiangong.service.activity;

import java.util.List;

import com.tiangong.bean.RmActivityCardRecord;

public interface RmActivityCardRecordService {
	/**
	 * 分页查询
	 * @param rmActivityCardRecord
	 * @return
	 */
	public List find(RmActivityCardRecord rmActivityCardRecord);
	
	/**
	 * 总记录数
	 * @param rmActivityCardRecord
	 * @return
	 */
	public int count(RmActivityCardRecord rmActivityCardRecord);
	
	
}
