package com.tiangong.dao.activity.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.tiangong.bean.RmActivityCardRecord;
import com.tiangong.dao.activity.RmActivityCardRecordDao;
import com.tiangong.dao.comm.imp.BaseDaoImpl;

public class RmActivityCardRecordDaoImp extends BaseDaoImpl<RmActivityCardRecord> implements RmActivityCardRecordDao {

	@Override
	public List find(RmActivityCardRecord reRmActivityCardRecord) {
		List<RmActivityCardRecord> list = new ArrayList<RmActivityCardRecord>();
		Criteria criteria = this.getSession().createCriteria(RmActivityCardRecord.class);
		//姓名
		if(reRmActivityCardRecord.getName() != null && !"".equals(reRmActivityCardRecord.getName())){
			criteria.add(Restrictions.like("name", reRmActivityCardRecord.getName(),MatchMode.START));
		}
		//手机
		if(reRmActivityCardRecord.getMobile() != null && !"".equals(reRmActivityCardRecord.getMobile())){
			criteria.add(Restrictions.eq("mobile", reRmActivityCardRecord.getMobile()));
		}
		//卡号
		if(reRmActivityCardRecord.getCardNo() != null && !"".equals(reRmActivityCardRecord.getCardNo())){
			criteria.add(Restrictions.eq("cardNo", reRmActivityCardRecord.getCardNo()));
		}
		criteria.setFirstResult((reRmActivityCardRecord.getCurrentPage()-1)*reRmActivityCardRecord.getPageSize());
		criteria.setMaxResults(reRmActivityCardRecord.getPageSize());
		list = criteria.list();
		return list;
	}


	@Override
	public int count(RmActivityCardRecord reRmActivityCardRecord) {
		List<RmActivityCardRecord> list = new ArrayList<RmActivityCardRecord>();
		Criteria criteria = this.getSession().createCriteria(RmActivityCardRecord.class);
		//姓名
		if(reRmActivityCardRecord.getName() != null && !"".equals(reRmActivityCardRecord.getName())){
			criteria.add(Restrictions.like("name", reRmActivityCardRecord.getName(),MatchMode.START));
		}
		//手机
		if(reRmActivityCardRecord.getMobile() != null && !"".equals(reRmActivityCardRecord.getMobile())){
			criteria.add(Restrictions.eq("mobile", reRmActivityCardRecord.getMobile()));
		}
		//卡号
		if(reRmActivityCardRecord.getCardNo() != null && !"".equals(reRmActivityCardRecord.getCardNo())){
			criteria.add(Restrictions.eq("cardNo", reRmActivityCardRecord.getCardNo()));
		}
		int count = criteria.list().size();
		return count;
	}

}
