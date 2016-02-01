package com.tiangong.service.activity;

import java.util.List;

import com.tiangong.bean.RmUserClassDetail;

public interface RmUserClassDetailService {
	
	/**
	 * 根据教师id，查询所带班级
	 * @param schoolId
	 * @return
	 */
	public List<RmUserClassDetail> getListByTeacherId(int teacherId);
}
