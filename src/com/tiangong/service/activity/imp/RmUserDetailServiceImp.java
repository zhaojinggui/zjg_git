package com.tiangong.service.activity.imp;

import com.tiangong.bean.RmUserDetail;
import com.tiangong.dao.comm.imp.BaseDaoImpl;
import com.tiangong.service.activity.RmUserDetailService;

import com.tiangong.dao.activity.RmUserDetailDao;

public class RmUserDetailServiceImp  implements RmUserDetailService{
	
	private RmUserDetailDao rmUserDetailDao;
	
	public void setRmUserDetailDao(RmUserDetailDao rmUserDetailDao) {
		this.rmUserDetailDao = rmUserDetailDao;
	}

	@Override
	public RmUserDetail getByRmUserDetailId(Integer id) {
		// TODO Auto-generated method stub
		return rmUserDetailDao.getByRmUserDetailId(id);
	}

	@Override
	public RmUserDetail getByRmUserDetailSchoolId(Long schoolId) {
		// TODO Auto-generated method stub
		return rmUserDetailDao.getByRmUserDetailSchoolId(schoolId);
	}

	@Override
	public int getPointByTeacherId(int teacherId) {
		// TODO Auto-generated method stub
		return rmUserDetailDao.getPointByTeacherId(teacherId);
	}

}
