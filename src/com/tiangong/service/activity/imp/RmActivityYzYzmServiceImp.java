package com.tiangong.service.activity.imp;

import com.tiangong.bean.RmActivityYzYzm;
import com.tiangong.dao.activity.RmActivityYzYzmDao;
import com.tiangong.service.activity.RmActivityYzYzmService;

public class RmActivityYzYzmServiceImp implements RmActivityYzYzmService {
	
	private RmActivityYzYzmDao rmActivityYzYzmDao;
	public void setRmActivityYzYzmDao(RmActivityYzYzmDao rmActivityYzYzmDao) {
		this.rmActivityYzYzmDao = rmActivityYzYzmDao;
	}
	@Override
	public void saveYzm(String phone, String yzm) {
		this.rmActivityYzYzmDao.saveYzm(phone, yzm);
	}
	@Override
	public RmActivityYzYzm getByPhone(String phone) {
		return this.rmActivityYzYzmDao.getByPhone(phone);
	}
	
	@Override
	public RmActivityYzYzm getByPhoneAndCode(String phone, String code) {
		return this.rmActivityYzYzmDao.getByPhoneAndCode(phone, code);
	}
}
