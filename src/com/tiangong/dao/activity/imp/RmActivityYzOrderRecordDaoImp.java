package com.tiangong.dao.activity.imp;

import java.util.List;

import com.tiangong.bean.RmActivityCardList;
import com.tiangong.bean.RmActivityYzOrderRecord;
import com.tiangong.dao.activity.RmActivityYzOrderRecordDao;
import com.tiangong.dao.comm.imp.BaseDaoImpl;
import com.tiangong.kdt.util.KdtConstants;
import com.tiangong.util.Constants;

public class RmActivityYzOrderRecordDaoImp  extends BaseDaoImpl<RmActivityYzOrderRecord> implements RmActivityYzOrderRecordDao{

	@Override
	public void saveOrderList(List<RmActivityYzOrderRecord> list,List<RmActivityCardList> cardList) {
		if(list != null && list.size() > 0 && this.getOrderByTid(list.get(0).getTid()) == null){
			for(RmActivityYzOrderRecord order : list){
				this.save(order);
			}
			KdtConstants.sendMsg(KdtConstants.appendMESContent(list),list.get(0).getPhone());
			this.updateCardListByList(cardList);
		}
	}

	@Override
	public List<RmActivityCardList> findCardListByASN(String activityId,
			String sendStatus, int num) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from RmActivityCardList ");
		hql.append(" where activityId = '");
		hql.append(activityId);
		hql.append("' ");
		hql.append(" and sendStatus = '");
		hql.append(sendStatus);
		hql.append("' ");
		hql.append(" order by card_id asc");
		@SuppressWarnings("unchecked")
		List<RmActivityCardList> list = this.getSession().createQuery(hql.toString()).
				setFirstResult(0).setMaxResults(num).list();
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public void updateCardListByList(List<RmActivityCardList> list) {
		if(list != null && list.size() > 0){
			for(RmActivityCardList card : list){
				card.setSendStatus(Constants.SEND_STATUS_1);
				this.getHibernateTemplate().saveOrUpdate(card);
			}
		}
	}

	@Override
	public RmActivityYzOrderRecord getOrderByTid(String tid) {
		String hql = "from RmActivityYzOrderRecord where tid = '" + tid + "'";
		List<RmActivityYzOrderRecord> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<RmActivityYzOrderRecord> getOrderListByPhone(String phone) {
		String hql = "from RmActivityYzOrderRecord where phone = '" + phone + "'";
		List<RmActivityYzOrderRecord> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<RmActivityYzOrderRecord> getOrderListByPhoneAndTid(
			String phone, String tid) {
		String hql = "from RmActivityYzOrderRecord where phone = '" + phone + "' and tid = '" + tid + "'";
		List<RmActivityYzOrderRecord> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
}
