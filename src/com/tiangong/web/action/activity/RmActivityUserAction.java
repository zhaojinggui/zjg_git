package com.tiangong.web.action.activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ModelDriven;
import com.tiangong.bean.RmActivityUser;
import com.tiangong.comm.OperateTab;
import com.tiangong.service.activity.RmActivityUserService;
import com.tiangong.web.action.BaseAction;

public class RmActivityUserAction extends BaseAction implements ModelDriven<RmActivityUser>{
	
	private RmActivityUserService rmActivityUserService;
	
	private RmActivityUser rmActivityUser = new RmActivityUser(); 

	public void setRmActivityUserService(RmActivityUserService rmActivityUserService) {
		this.rmActivityUserService = rmActivityUserService;
	}

	@Override
	public RmActivityUser getModel() {
		// TODO Auto-generated method stub
		return rmActivityUser;
	}

	public RmActivityUser getRmActivityUser() {
		return rmActivityUser;
	}

	public void setRmActivityUser(RmActivityUser rmActivityUser) {
		this.rmActivityUser = rmActivityUser;
	}
	
	/***
	 * 查询列表
	 */
	public void find(){
		HttpServletRequest request = this.getHttpServletRequest();
		//姓名
		String name = request.getParameter("name").trim();
		//手机号
		String mobile = request.getParameter("mobile").trim();
		rmActivityUser.setName(name);
		rmActivityUser.setMobile(mobile);
		rmActivityUser.setCurrentPage(getCurrentPage());
		rmActivityUser.setPageSize(getPageSize());
		int count = rmActivityUserService.count(rmActivityUser);
		List<RmActivityUser> list = rmActivityUserService.find(rmActivityUser);
		OperateTab.initDatagrid(list, count);
	}
	
}
