package com.tiangong.dao.activity;

import com.tiangong.bean.RmUser;

public interface RmUserDao {
	
	/***
	 * 账号是否存在
	 * @param rmUser
	 * @return
	 */
	public boolean rmUserExeist(RmUser rmUser);
	
	
	/***
	 * 获取RmUser
	 * @param rmUser
	 * @return
	 */
	public RmUser getRmUser(RmUser rmUser);
	
	
	/***
	 * 绑定帐号
	 * @param rmUser
	 */
	public void bindRmUser(RmUser rmUser);
	
	
	/***
	 * 获取教师用户
	 * @param rmUser
	 * @return
	 */
	public RmUser getTeacherRmUser(RmUser rmUser);
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public RmUser getById(int id);
	
	
	/**
	 * 判断是否有积分
	 * @param teacherId
	 * @return
	 */
	public boolean isHasPoint(int teacherId);
	
	/**
	 * 更新教师积分
	 * @param teacherId
	 */
	public void updatePoint(int teacherId);
	
	/**
	 * 新添教师积分
	 * @param point
	 * @param teacherId
	 */
	public void addPoint(int pointValue,int teacherId);
	
	
	/**
	 * 添加积分详细
	 */
	public void addPointDetail(int pointId,String pointType,int pointValue);
	
	
	/**
	 * 根据教师id获取积分id
	 * @param teacherId
	 * @return
	 */
	public int getPointIdByTeacherId(int teacherId);
}
