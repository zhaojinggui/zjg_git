package com.tiangong.web.action.activity;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ModelDriven;
import com.tiangong.bean.RmUser;
import com.tiangong.bean.RmUserClassDetail;
import com.tiangong.bean.RmUserClassList;
import com.tiangong.bean.RmUserDetail;
import com.tiangong.comm.Configer;
import com.tiangong.comm.MD5Util;
import com.tiangong.comm.MoodleUtil;
import com.tiangong.service.activity.RmUserClassDetailService;
import com.tiangong.service.activity.RmUserClassListService;
import com.tiangong.service.activity.RmUserDetailService;
import com.tiangong.service.activity.RmUserService;
import com.tiangong.web.action.BaseAction;

public class TeacherAction extends BaseAction implements ModelDriven<RmUserClassDetail>{
	
	@Resource
	private RmUserDetailService rmUserDetailService;
	@Resource
	private RmUserService rmUserService;
	@Resource
	private RmUserClassDetailService rmUserClassDetailService;
	@Resource
	private RmUserClassListService rmUserClassListService;
	
	private RmUserClassDetail rmUserClassDetail = new RmUserClassDetail();
	private  RmUserDetail rmUserDetail = new RmUserDetail();	
	public RmUserClassDetail getRmUserClassDetail() {
		return rmUserClassDetail;
	}
	public void setRmUserClassDetail(RmUserClassDetail rmUserClassDetail) {
		this.rmUserClassDetail = rmUserClassDetail;
	}

	
	public RmUserDetail getRmUserDetail() {
		return rmUserDetail;
	}
	public void setRmUserDetail(RmUserDetail rmUserDetail) {
		this.rmUserDetail = rmUserDetail;
	}


	/**
	 * 转向教师信息页面
	 * @return
	 */
	public String toTeacherPageJsp(){
		HttpServletRequest request = this.getHttpServletRequest();
		RmUser rmUser = new RmUser();
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		
		rmUser.setName(name);
		rmUser.setPassword(MD5Util.MD5Encode(pwd, ""));
		
		rmUser.setRole(Configer.RM_USER_ROLE);
		RmUser user = rmUserService.getTeacherUser(rmUser);//teacherId为rm_user中的id
		//教师姓名
		rmUserDetail = rmUserDetailService.getByRmUserDetailId(user.getId());
		//获取学校
		String school = rmUserDetailService.getByRmUserDetailSchoolId(rmUserDetail.getSchoolId()).getSchool();
		//积分
		int point = rmUserDetailService.getPointByTeacherId(user.getId());
		//所带班级集合
		List<RmUserClassDetail> classList = rmUserClassDetailService.getListByTeacherId(user.getId());
		
		request.setAttribute("school", school);
		request.setAttribute("point", point);
		request.setAttribute("classList", classList);
		
		return SUCCESS;
	}
	
	/**
	 * 转向班级学生列表页面
	 * @return
	 */
	public String toClassListPageJsp(){
		
		/*System.out.println(MoodleUtil.getCurrentLevel("4f1803b8522191e0ebf80d0bb02d3ebd")+"==================");
		System.out.println(MoodleUtil.getCurrentLevel("294b56339c322b9937d7b52801c1ce2c")+"==================");*/
		HttpServletRequest request = this.getHttpServletRequest();
		String classNum = request.getParameter("classNum");
		List<RmUserClassList> list = rmUserClassListService.getListByClassNum(Integer.valueOf(classNum));
		
		request.setAttribute("classNum", classNum);
		request.setAttribute("studentList", list);
		return "toClassListJsp";
	}
	

	@Override
	public RmUserClassDetail getModel() {
		// TODO Auto-generated method stub
		return rmUserClassDetail;
	}
	
}
