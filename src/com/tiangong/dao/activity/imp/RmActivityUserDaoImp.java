package com.tiangong.dao.activity.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.tiangong.bean.RmActivityUser;
import com.tiangong.dao.activity.RmActivityUserDao;
import com.tiangong.dao.comm.imp.BaseDaoImpl;

public class RmActivityUserDaoImp extends BaseDaoImpl<RmActivityUser> implements RmActivityUserDao{

	@Override
	public List find(RmActivityUser rmActivityUser) {
		List<RmActivityUser> list = new ArrayList<RmActivityUser>();
		Criteria criteria = this.getSession().createCriteria(RmActivityUser.class);
		//姓名
		if(rmActivityUser.getName() != null && !"".equals(rmActivityUser.getName())){
			criteria.add(Restrictions.like("name", rmActivityUser.getName(),MatchMode.START));
		}
		//手机
		if(rmActivityUser.getMobile() != null && !"".equals(rmActivityUser.getMobile())){
			criteria.add(Restrictions.eq("mobile", rmActivityUser.getMobile()));
		}
		criteria.setFirstResult((rmActivityUser.getCurrentPage()-1)*rmActivityUser.getPageSize());
		criteria.setMaxResults(rmActivityUser.getPageSize());
		list = criteria.list();
		//int count = list.size();
		return list;
	}

	@Override
	public int count(RmActivityUser rmActivityUser) {
		List<RmActivityUser> list = new ArrayList<RmActivityUser>();
		Criteria criteria = this.getSession().createCriteria(RmActivityUser.class);
		//姓名
		if(rmActivityUser.getName() != null && !"".equals(rmActivityUser.getName())){
			criteria.add(Restrictions.like("name", rmActivityUser.getName(),MatchMode.START));
		}
		//手机
		if(rmActivityUser.getMobile() != null && !"".equals(rmActivityUser.getMobile())){
			criteria.add(Restrictions.eq("mobile", rmActivityUser.getMobile()));
		}
		
		int count = criteria.list().size();
		return count;
	}

}
