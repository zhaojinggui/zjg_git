package com.tiangong.dao.activity.imp;

import java.util.ArrayList;
import java.util.List;

import com.tiangong.bean.RmUserDetail;
import com.tiangong.dao.activity.RmUserDetailDao;
import com.tiangong.dao.comm.imp.BaseDaoImpl;

public class RmUserDetailDaoImp extends BaseDaoImpl<RmUserDetail> implements RmUserDetailDao{

	@Override
	public RmUserDetail getByRmUserDetailId(Integer id) {
		// TODO Auto-generated method stub
		return this.get(id);
	}

	@Override
	public RmUserDetail getByRmUserDetailSchoolId(Long schoolId) {
		List<RmUserDetail> list = new ArrayList<RmUserDetail>();
		String sql = "from RmUserDetail ud where ud.schoolId = '"+schoolId+"'";
		list = this.getHibernateTemplate().find(sql);
		if(list.size() > 0){
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public int getPointByTeacherId(int teacherId) {
		String sql = "SELECT point_value FROM rm_point WHERE teacher_id = "+teacherId;
		List list = this.getSession().createSQLQuery(sql).list();
		if(list.size() > 0){
			return Integer.valueOf(list.get(0).toString());
		}
		return -1;
	}
	
}
