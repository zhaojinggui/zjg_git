package com.tiangong.service.usercenter.imp;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.tiangong.bean.RmUser;
import com.tiangong.bean.RmUserDetail;
import com.tiangong.dao.comm.imp.BaseDaoImpl;
import com.tiangong.dao.usercenter.imp.UserCenterDaoImp;
import com.tiangong.service.usercenter.UserCenterService;

public class UserCenterServiceImp implements UserCenterService {
	private UserCenterDaoImp usercenterDao;
	private BaseDaoImpl<T> baseDao;

	public void setUsercenterDao(UserCenterDaoImp usercenterDao) {
		this.usercenterDao = usercenterDao;
	}

	public RmUser getUserCenterById(String mobile) {
		// TODO Auto-generated method stub
		return usercenterDao.getUserCenterById(mobile);
	}

	public boolean save(Object obj) {
		// TODO Auto-generated method stub
		return usercenterDao.save(obj);
	}

	public List getUserReadingList(String str) {
		// TODO Auto-generated method stub
		return usercenterDao.getUserReadingList(str);
	}

	public boolean saveOrUpdate(Object obj) {
		// TODO Auto-generated method stub
		return usercenterDao.saveOrUpdate(obj);
	}

	/*public RmUser gettp(String id, Class obj) {
		// TODO Auto-generated method stub
		return usercenterDao.gettp(id,obj);
	}
*/
	public RmUser getUserCenterByName(String name) {
		// TODO Auto-generated method stub
		return usercenterDao.getUserCenterByName(name);
	}

	public Object gettp(Integer rmId, Class obj) {
		// TODO Auto-generated method stub
		return usercenterDao.get(obj, rmId);
	}
}
