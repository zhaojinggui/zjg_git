package com.tiangong.service.activity;

import java.util.List;

import com.tiangong.bean.RmUserClassList;

public interface RmUserClassListService {
	/**
	 * 根据班级号查找学生集合
	 * @param classNum
	 * @return
	 */
	public List<RmUserClassList> getListByClassNum(int classNum);
}
