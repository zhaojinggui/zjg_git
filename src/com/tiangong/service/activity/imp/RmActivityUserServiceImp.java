package com.tiangong.service.activity.imp;

import java.util.List;

import com.tiangong.bean.RmActivityUser;
import com.tiangong.dao.activity.RmActivityUserDao;
import com.tiangong.service.activity.RmActivityUserService;

public class RmActivityUserServiceImp  implements RmActivityUserService {
	
	private RmActivityUserDao rmActivityUserDao;
	
	public void setRmActivityUserDao(RmActivityUserDao rmActivityUserDao) {
		this.rmActivityUserDao = rmActivityUserDao;
	}

	@Override
	public List find(RmActivityUser rmActivityUser) {
		return rmActivityUserDao.find(rmActivityUser);
	}

	@Override
	public int count(RmActivityUser rmActivityUser) {
		// TODO Auto-generated method stub
		return rmActivityUserDao.count(rmActivityUser);
	}

}
