package com.tiangong.dao.activity.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.tiangong.bean.RmUserClassDetail;
import com.tiangong.dao.activity.RmUserClassDetailDao;
import com.tiangong.dao.comm.imp.BaseDaoImpl;

public class RmUserClassDetailDaoImp extends BaseDaoImpl<RmUserClassDetail> implements RmUserClassDetailDao{

	@Override
	public List<RmUserClassDetail> getListByTeacherId(int teacherId) {
		List<RmUserClassDetail> list = new ArrayList<RmUserClassDetail>();
		String sql = "from RmUserClassDetail uc where uc.teacherId = "+teacherId ;
		return this.getSession().createQuery(sql).list();
	}


}
