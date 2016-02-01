package com.tiangong.service.activity.imp;
import java.util.List;

import com.tiangong.bean.RmActivityCardRecord;
import com.tiangong.dao.activity.RmActivityCardRecordDao;
import com.tiangong.service.activity.RmActivityCardRecordService;

public class RmActivityCardRecordServiceImp implements RmActivityCardRecordService {

	private RmActivityCardRecordDao rmActivityCardRecordDao;
	
	public void setRmActivityCardRecordDao(
			RmActivityCardRecordDao rmActivityCardRecordDao) {
		this.rmActivityCardRecordDao = rmActivityCardRecordDao;
	}

	@Override
	public List find(RmActivityCardRecord rmActivityCardRecord) {
		return rmActivityCardRecordDao.find(rmActivityCardRecord);
	}

	@Override
	public int count(RmActivityCardRecord rmActivityCardRecord) {
		// TODO Auto-generated method stub
		return rmActivityCardRecordDao.count(rmActivityCardRecord);
	}

}
