package com.tiangong.web.action.activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ModelDriven;
import com.tiangong.bean.RmActivityCardRecord;
import com.tiangong.comm.OperateTab;
import com.tiangong.service.activity.RmActivityCardRecordService;
import com.tiangong.web.action.BaseAction;

public class RmActivityCardRecordAction extends BaseAction implements ModelDriven<RmActivityCardRecord>{
	
	private RmActivityCardRecordService rmActivityCardRecordService;
	
	private RmActivityCardRecord rmActivityCardRecord = new RmActivityCardRecord();
	

	public RmActivityCardRecord getRmActivityCardRecord() {
		return rmActivityCardRecord;
	}


	public void setRmActivityCardRecord(RmActivityCardRecord rmActivityCardRecord) {
		this.rmActivityCardRecord = rmActivityCardRecord;
	}


	public void setRmActivityCardRecordService(
			RmActivityCardRecordService rmActivityCardRecordService) {
		this.rmActivityCardRecordService = rmActivityCardRecordService;
	}

	
	@Override
	public RmActivityCardRecord getModel() {
		// TODO Auto-generated method stub
		return rmActivityCardRecord;
	}
	/**
	 * 查询列表
	 */
	public void find(){
		HttpServletRequest request = this.getHttpServletRequest();
		//姓名
		String name = request.getParameter("name").trim();
		//手机号
		String mobile = request.getParameter("mobile").trim();
		//卡号
		String cardNo = request.getParameter("cardNo").trim();
		rmActivityCardRecord.setName(name);
		rmActivityCardRecord.setMobile(mobile);
		rmActivityCardRecord.setCurrentPage(getCurrentPage());
		rmActivityCardRecord.setPageSize(getPageSize());
		int count = rmActivityCardRecordService.count(rmActivityCardRecord);
		List<RmActivityCardRecord> list = rmActivityCardRecordService.find(rmActivityCardRecord);
		OperateTab.initDatagrid(list, count);
	}
}
