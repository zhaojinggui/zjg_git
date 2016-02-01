package com.tiangong.service.activity.imp;

import com.tiangong.bean.RmUser;
import com.tiangong.dao.activity.RmUserDao;
import com.tiangong.service.activity.RmUserService;

public class RmUserServiceImp implements RmUserService {

	private RmUserDao rmUserDao;
	public void setRmUserDao(RmUserDao rmUserDao) {
		this.rmUserDao = rmUserDao;
	}

	@Override
	public boolean rmUserExist(RmUser rmUser) {
		return rmUserDao.rmUserExeist(rmUser);
	}

	@Override
	public boolean rmUserIsBinded(RmUser rmUser) {
		RmUser user = rmUserDao.getRmUser(rmUser);
		if(user != null){
			//如果commId和JzId其中的一个不为空的话，表示已绑定。否则未绑定。
			if((user.getWxOpenCommId() == null ||"".equals(user.getWxOpenCommId()))
					&& (user.getWxOpenJzId() == null||"".equals(user.getWxOpenJzId()))){
				return false;
			}else{
				return true;
			}
			
		}
		return false;
	}

	@Override
	public void bindRmUser(RmUser rmUser) {
		rmUserDao.bindRmUser(rmUser);
	}

	@Override
	public RmUser getRmUser(RmUser rmUser) {
		// TODO Auto-generated method stub
		return rmUserDao.getRmUser(rmUser);
	}

	@Override
	public RmUser getTeacherUser(RmUser rmUser) {
		// TODO Auto-generated method stub
		return rmUserDao.getTeacherRmUser(rmUser);
	}

	@Override
	public boolean isHasPoint(int teacherId) {
		// TODO Auto-generated method stub
		return rmUserDao.isHasPoint(teacherId);
	}

	@Override
	public void addPoint(int pointValue, int teacherId) {
		// TODO Auto-generated method stub
		rmUserDao.addPoint(pointValue, teacherId);
	}

	@Override
	public void addPointDetail(int pointId, String pointType, int pointValue) {
		// TODO Auto-generated method stub
		rmUserDao.addPointDetail(pointId, pointType, pointValue);
	}

	@Override
	public void updatePoint(int teacherId) {
		// TODO Auto-generated method stub
		rmUserDao.updatePoint(teacherId);
	}

	@Override
	public int getPointIdByTeacherId(int teacherId) {
		// TODO Auto-generated method stub
		return rmUserDao.getPointIdByTeacherId(teacherId);
	}

}
