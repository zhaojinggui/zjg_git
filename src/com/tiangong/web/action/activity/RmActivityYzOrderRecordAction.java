package com.tiangong.web.action.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.tiangong.bean.RmActivityYzOrderRecord;
import com.tiangong.bean.RmActivityYzYzm;
import com.tiangong.kdt.util.KdtConstants;
import com.tiangong.service.activity.RmActivityYzOrderRecordService;
import com.tiangong.service.activity.RmActivityYzYzmService;
import com.tiangong.web.action.BaseAction;

public class RmActivityYzOrderRecordAction extends BaseAction{

	@Resource
	private RmActivityYzOrderRecordService rmActivityYzOrderRecordService;
	
	@Resource
	private RmActivityYzYzmService rmActivityYzYzmService;
	
	public void setRmActivityYzOrderRecordService(RmActivityYzOrderRecordService rmActivityYzOrderRecordService) {
		this.rmActivityYzOrderRecordService = rmActivityYzOrderRecordService;
	}
	
	public void setRmActivityYzYzmService(RmActivityYzYzmService rmActivityYzYzmService){
		this.rmActivityYzYzmService = rmActivityYzYzmService;
	}
	
	private List<RmActivityYzOrderRecord> orderRecordList;
	private String phone;
	private String tid;
	
	/**
	 * 获取验证码
	 * @method: getYzmCode
	 * @Description: 获取验证码
	 * @author: lijianjun
	 * @date 2015年10月22日 下午5:49:33
	 */
//	public void getYzmCode(){
//		// 获取手机号
//		String phone = this.getHttpServletRequest().getParameter("phone");
//		Map map = new HashMap();
//		if(!StringUtils.isBlank(phone)){
//			int yzm=(new Random()).nextInt(9999);//
//			if(yzm < 1000){
//				yzm += 1000;
//			}
//			this.rmActivityYzYzmService.saveYzm(phone, yzm+"");
//			KdtConstants.sendYzmMsg(yzm+"",phone);
//			map.put("isSend", "Y");
//		}else{
//			map.put("isSend", "N");
//		}
//		this.ajaxMap(map);
//	}
	/**
	 * 获取有赞订单记录
	 * @method: getOrderList
	 * @Description: 获取有赞订单记录
	 * @return 
	 * @author: lijianjun
	 * @date 2015年10月22日 下午5:02:26
	 */
	public String getOrderList(){
		// 获取手机号
		String phone = this.getHttpServletRequest().getParameter("phone");
		String tid = this.getHttpServletRequest().getParameter("tid");
		if(!StringUtils.isBlank(phone) && !StringUtils.isBlank(tid)){
//			RmActivityYzYzm rmYzm = this.rmActivityYzYzmService.getByPhoneAndCode(phone, tid);
//			if(rmYzm != null){
				orderRecordList = rmActivityYzOrderRecordService.getOrderListByPhoneAndTid(phone, tid);
//			}else{
//				orderRecordList = null;
//			}
		}else{
			orderRecordList = null;
		}
		this.phone = phone;
		this.tid = tid;
		return SUCCESS;
	}
	
	
	public List<RmActivityYzOrderRecord> getOrderRecordList() {
		return orderRecordList;
	}

	public void setOrderRecordList(List<RmActivityYzOrderRecord> orderRecordList) {
		this.orderRecordList = orderRecordList;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
}
