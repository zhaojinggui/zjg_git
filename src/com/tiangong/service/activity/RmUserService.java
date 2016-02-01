package com.tiangong.service.activity;

import com.tiangong.bean.RmUser;

public interface RmUserService {
	
	/**
	 * 是否存在
	 * @param rmUser
	 * @return
	 */
	public boolean rmUserExist(RmUser rmUser);
	
	
	/***
	 * 是否已经绑定
	 * @param rmUser
	 * @return
	 */
	public boolean rmUserIsBinded(RmUser rmUser);
	
	
	/**
	 * 绑定帐号
	 * @param rmUser
	 */
	public void bindRmUser(RmUser rmUser);
	
	/***
	 * 获取RmUser
	 * @param rmUser
	 * @return
	 */
	public RmUser getRmUser(RmUser rmUser);
	
	/**
	 * 获取教师用户
	 * @param rmUser
	 * @return
	 */
	public RmUser getTeacherUser(RmUser rmUser);
	
	/**
	 * 判断教师是否有积分
	 * @param teacherId
	 * @return
	 */
	public boolean isHasPoint(int teacherId);
	
	/**
	 * 添加教师积分
	 * @param pointValue
	 * @param teacherId
	 */
	public void addPoint(int pointValue,int teacherId);
	
	
	/**
	 * 积分详细记录
	 * @param pointId
	 * @param pointType
	 * @param pointValue
	 */
	public void addPointDetail(int pointId,String pointType,int pointValue);
	
	
	/**
	 * 修改教师积分
	 * @param teacherId
	 */
	public void updatePoint(int teacherId);
	
	/**
	 * 根据教师id获取积分id
	 * @param teacherId
	 * @return
	 */
	public int getPointIdByTeacherId(int teacherId);
}
