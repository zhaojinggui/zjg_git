package com.tiangong.service.activity.imp;

import java.util.List;

import com.tiangong.bean.RmUserClassDetail;
import com.tiangong.dao.activity.RmUserClassDetailDao;
import com.tiangong.service.activity.RmUserClassDetailService;

public class RmUserClassDetailServiceImp implements RmUserClassDetailService{

	private RmUserClassDetailDao rmUserClassDetailDao;
	public void setRmUserClassDetailDao(RmUserClassDetailDao rmUserClassDetailDao) {
		this.rmUserClassDetailDao = rmUserClassDetailDao;
	}
	@Override
	public List<RmUserClassDetail> getListByTeacherId(int teacherId) {
		// TODO Auto-generated method stub
		return rmUserClassDetailDao.getListByTeacherId(teacherId);
	}

	
}
