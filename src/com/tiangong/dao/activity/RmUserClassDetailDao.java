package com.tiangong.dao.activity;

import java.util.List;

import com.tiangong.bean.RmUserClassDetail;

public interface RmUserClassDetailDao {
	/**
	 * 根据教师id，查询所带班级
	 * @param schoolId
	 * @return
	 */
	public List<RmUserClassDetail> getListByTeacherId(int teacherId);
}
