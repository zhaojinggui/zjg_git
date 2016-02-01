package com.tiangong.dao.activity.imp;

import java.util.List;

import com.tiangong.bean.RmActivityYzYzm;
import com.tiangong.dao.activity.RmActivityYzYzmDao;
import com.tiangong.dao.comm.imp.BaseDaoImpl;

public class RmActivityYzYzmDaoImp  extends BaseDaoImpl<RmActivityYzYzm> implements RmActivityYzYzmDao{

	@Override
	public void saveYzm(String phone, String yzm) {
		RmActivityYzYzm rmYzm = this.getByPhone(phone);
		if(rmYzm != null){
			rmYzm.setCode(yzm);
			this.saveOrUpdate(rmYzm);
		}else{
			rmYzm = new RmActivityYzYzm();
			rmYzm.setCode(yzm);
			rmYzm.setPhone(phone);
			this.save(rmYzm);
		}
		
	}

	@Override
	public RmActivityYzYzm getByPhone(String phone) {
		String hql = "from RmActivityYzYzm where phone = '" + phone + "'";
		List<RmActivityYzYzm> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public RmActivityYzYzm getByPhoneAndCode(String phone, String code) {
		String hql = "from RmActivityYzYzm where phone = '" + phone + "' and code = '" + code + "'";
		List<RmActivityYzYzm> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	

}
