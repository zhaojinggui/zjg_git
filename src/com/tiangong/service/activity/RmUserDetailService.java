package com.tiangong.service.activity;

import com.tiangong.bean.RmUserDetail;

public interface RmUserDetailService {
	/**
	 * 根据Id查询
	 * @param id
	 * @return
	 */
	public RmUserDetail getByRmUserDetailId(Integer id);
	
	/**
	 * 根据学校id查询
	 * @param schoolId
	 * @return
	 */
	public RmUserDetail getByRmUserDetailSchoolId(Long schoolId);
	
	
	/**
	 * 根据教师id查询积分
	 * @param teacherId
	 * @return
	 */
	public int getPointByTeacherId(int teacherId);
	
	
}
