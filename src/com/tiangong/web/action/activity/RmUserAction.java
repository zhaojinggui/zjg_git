package com.tiangong.web.action.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ModelDriven;
import com.tiangong.bean.RmUser;
import com.tiangong.bean.RmUserDetail;
import com.tiangong.bean.RmView;
import com.tiangong.comm.Configer;
import com.tiangong.comm.MoodleUtil;
import com.tiangong.service.activity.RmUserDetailService;
import com.tiangong.service.activity.RmUserService;
import com.tiangong.util.Constants;
import com.tiangong.web.action.BaseAction;

public class RmUserAction extends BaseAction implements ModelDriven<RmUser>{
	
	@Resource
	private RmUserDetailService rmUserDetailService;
	
	private RmUserService rmUserService;
	
	private RmUser rmUser = new RmUser();

	public RmUser getRmUser() {
		return rmUser;
	}

	public void setRmUser(RmUser rmUser) {
		this.rmUser = rmUser;
	}

	public void setRmUserService(RmUserService rmUserService) {
		this.rmUserService = rmUserService;
	}

	@Override
	public RmUser getModel() {
		// TODO Auto-generated method stub
		return rmUser;
	}
	
	/**
	 * 绑定帐号
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String bindRmUser() throws UnsupportedEncodingException{
		HttpServletRequest request = this.getHttpServletRequest();
		String openId = request.getParameter("openId");
		String name = URLDecoder.decode(request.getParameter("name"),"UTF-8").trim();
		String password = request.getParameter("password").trim();
		String role = request.getParameter("role").trim();
		rmUser.setName(name);
		rmUser.setPassword(password);
		RmUser user = new RmUser();
		user = rmUserService.getRmUser(rmUser);
		if(user != null){
		/*	user.setName(name);
			user.setPassword(MD5Util.MD5Encode(password, ""));*/
			//班级管理员JzId
			if(role .equals(Configer.RM_USER_ROLE)){
				user.setWxOpenJzId(openId);
			}else{
				user.setWxOpenCommId(openId);
			}
			//绑定
			rmUserService.bindRmUser(user);
		}
		//教师
		if(user.getRole().equals(Configer.RM_USER_ROLE)){
			//添加积分
			//如果该教师有积分，则更新积分（原有基础上加100）
			if(rmUserService.isHasPoint(user.getId())){
				rmUserService.updatePoint(user.getId());
			}else{//否则新增教师积分
				rmUserService.addPoint(100, user.getId());
			}
			//添加rm_point_detail
			rmUserService.addPointDetail(rmUserService.getPointIdByTeacherId(user.getId()), "80", 100);
			return "teacher";
			
		}else{//个人
			
			RmUserDetail rmUserDetail = rmUserDetailService.getByRmUserDetailId(user.getId());
			String nuts = MoodleUtil.getNuts(user.getMoodleToken());
			String att = MoodleUtil.getAttendance(user.getMoodleToken());
			String cur = MoodleUtil.getCurrentLevel(user.getMoodleToken());	
			String age = rmUserDetail.getAge();
			String sy= rmUserDetail.getStudyYears();
			
			RmView rmView = new RmView();
			rmView.setId(user.getId());
			//rmView.setHead("");
			rmView.setUserName(user.getName());
			rmView.setUserRole(user.getRole());
			rmView.setUserAge(age);
			rmView.setUserYears(sy);
			rmView.setUserNum(nuts);
			rmView.setUserDay(att);
			rmView.setUserLevel(cur);
			rmView.setPicName(Constants.PIC_PATH + (rmUserDetail.getPicName() != null ? rmUserDetail.getPicName() : Constants.PIC_DEF_NAME));
			this.getHttpServletRequest().setAttribute("rmView", rmView);
			return "userreadinglist";
			
		}
		
	}
	
	/**
	 * 判断帐号是否存在
	 */
	public void rmUserExist(){
		HttpServletRequest request = this.getHttpServletRequest();
		String name = request.getParameter("name").trim();
		String password = request.getParameter("password").trim();
		rmUser.setName(name);
		rmUser.setPassword(password);
		//获取
		
		boolean flag = rmUserService.rmUserExist(rmUser);
		if(flag){
			ajaxOut("Y");
		}else{
			ajaxOut("N");
		}
	}
	
	/**
	 * 帐号是否已经绑定
	 */
	public void rmUserIsBinded(){
		HttpServletRequest request = this.getHttpServletRequest();
		String name = request.getParameter("name").trim();
		String password = request.getParameter("password").trim();
		rmUser.setName(name);
		rmUser.setPassword(password);
		boolean flag = rmUserService.rmUserIsBinded(rmUser);
		if(flag){
			ajaxOut("Y");
		}else{
			ajaxOut("N");
		}
	}
	
	
	
}
