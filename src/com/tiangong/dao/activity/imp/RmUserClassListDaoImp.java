package com.tiangong.dao.activity.imp;

import java.util.ArrayList;
import java.util.List;

import com.tiangong.bean.RmUserClassList;
import com.tiangong.dao.activity.RmUserClassListDao;
import com.tiangong.dao.comm.imp.BaseDaoImpl;

public class RmUserClassListDaoImp extends BaseDaoImpl<RmUserClassList> implements RmUserClassListDao{

	@Override
	public List<RmUserClassList> getListByClassNum(int classNum) {
		List<RmUserClassList> list = new ArrayList<RmUserClassList>();
		String sql = " from RmUserClassList ucl where ucl.classId="+classNum;
		list = this.getSession().createQuery(sql).list();
		return list;
	}

}
