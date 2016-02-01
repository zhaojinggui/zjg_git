package com.tiangong.service.activity.imp;

import java.util.List;

import com.tiangong.bean.RmActivityCardList;
import com.tiangong.bean.RmActivityYzOrderRecord;
import com.tiangong.dao.activity.RmActivityYzOrderRecordDao;
import com.tiangong.service.activity.RmActivityYzOrderRecordService;

public class RmActivityYzOrderRecordServiceImp implements RmActivityYzOrderRecordService {
	
	private RmActivityYzOrderRecordDao rmActivityYzOrderRecordDao;
	public void setRmActivityYzOrderRecordDao(RmActivityYzOrderRecordDao rmActivityYzOrderRecordDao) {
		this.rmActivityYzOrderRecordDao = rmActivityYzOrderRecordDao;
	}
	
	@Override
	public void saveOrderList(List<RmActivityYzOrderRecord> list,List<RmActivityCardList> cardList) {
		this.rmActivityYzOrderRecordDao.saveOrderList(list,cardList);
	}

	@Override
	public List<RmActivityCardList> findCardListByASN(String activityId,
			String sendStatus, int num) {
		return this.rmActivityYzOrderRecordDao.findCardListByASN(activityId, sendStatus, num);
	}
	
	@Override
	public void updateCardListByList(List<RmActivityCardList> list) {
		this.rmActivityYzOrderRecordDao.updateCardListByList(list);
	}

	@Override
	public List<RmActivityYzOrderRecord> getOrderListByPhone(String phone) {
		return this.rmActivityYzOrderRecordDao.getOrderListByPhone(phone);
	}

	@Override
	public List<RmActivityYzOrderRecord> getOrderListByPhoneAndTid(
			String phone, String tid) {
		return this.rmActivityYzOrderRecordDao.getOrderListByPhoneAndTid(phone, tid);
	}
}
