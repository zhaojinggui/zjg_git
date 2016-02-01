package com.tiangong.web.action.usercenter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.tiangong.bean.RmUser;
import com.tiangong.bean.RmUserDetail;
import com.tiangong.bean.RmView;
import com.tiangong.comm.MD5Util;
import com.tiangong.comm.MoodleUtil;
import com.tiangong.comm.Util;
import com.tiangong.service.activity.RmUserDetailService;
import com.tiangong.service.usercenter.imp.UserCenterServiceImp;
import com.tiangong.util.Constants;
import com.tiangong.web.action.BaseAction;

public class UserCenterAction extends BaseAction {
	
	@Resource
	private RmUserDetailService rmUserDetailService;
	
	private RmUser rmUser;
	private String password;
	private String mobile;
	private String name;
	private String role;
	
	private String Age;
	private String StudyYears;
	private RmView rmView;
	
	public RmView getRmView() {
		return rmView;
	}
	public void setRmView(RmView rmView) {
		this.rmView = rmView;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getStudyYears() {
		return StudyYears;
	}
	public void setStudyYears(String studyYears) {
		StudyYears = studyYears;
	}
	private RmUserDetail rmUserDetail;
	
	public RmUserDetail getRmUserDetail() {
		return rmUserDetail;
	}
	public void setRmUserDetail(RmUserDetail rmUserDetail) {
		this.rmUserDetail = rmUserDetail;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RmUser getRmUser() {
		return rmUser;
	}
	public void setRmUser(RmUser rmUser) {
		this.rmUser = rmUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	private UserCenterServiceImp usercenterService;
	
	public void setUsercenterService(UserCenterServiceImp usercenterService) {
		this.usercenterService = usercenterService;
	}
	
	//判断该手机是否存在
	public void isAvailable() throws IOException{
		String account = this.getHttpServletRequest().getParameter("account");
		RmUser rmUser = usercenterService.getUserCenterById(account);
		if(rmUser!=null)
		{
			this.getHttpServletResponse().getWriter().write("Y");
		}else{
			this.getHttpServletResponse().getWriter().write("N");
		}
		
	}
	
	//判断用户名是否存在
	public void isNameNull() throws IOException{
		String name = this.getHttpServletRequest().getParameter("name");
		RmUser rmUser = usercenterService.getUserCenterByName(name);
		if(rmUser!=null)
		{
			this.getHttpServletResponse().getWriter().write("Y");
		}else{
			this.getHttpServletResponse().getWriter().write("N");
		}
		
	}
	
	//注册账号
	public void save(){
		//String str = rmUser.getPassword();
		String password = this.getHttpServletRequest().getParameter("password");
		 rmUser.setPassword(MD5Util.MD5Encode(password,""));
		 //设置默认值
		 String string = rmUser.getName();
		 rmUser.setRole("1");
		 Date date=new Date();
		 DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String time=format.format(date);
		 Timestamp ts = Timestamp.valueOf(time);
		 rmUser.setSigninTime(ts);
		 rmUser.setIsUse("y");
		 rmUser.setAddUser(string);
		 rmUser.setAddTime(ts);
		 rmUser.setUpdUser(string);
		 rmUser.setUpdTime(ts);	
		 //保存user
		 boolean b;
		 	b = usercenterService.save(rmUser);
		 if(b){
			 rmUserDetail.setValidatyDate(ts);
			 rmUserDetail.setShowMyTask("n");
			 Integer i = rmUser.getId();
			 System.out.println("ID:"+i);
			 rmUserDetail.setId(i);
			 //获得token并保存
			 	String sName = rmUser.getName();
				String sPaw = rmUser.getPassword();
				String sRole = rmUser.getRole();
				String su = Util.getRandomString(6);
				String sEm = su+"@126.com";
				String sm = MoodleUtil.addMoodleUser(sName, sPaw, sEm, sRole);
				rmUser.setMoodleToken(sm);
				usercenterService.saveOrUpdate(rmUser);	
				b=usercenterService.save(rmUserDetail);	
			  if(b)
			  {
			   try {	 
					this.getHttpServletResponse().getWriter().print("Y");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();;
				}
			  }else{
				  try {
					this.getHttpServletResponse().getWriter().print("N");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }	  			
		 }else{
			 try {
				this.getHttpServletResponse().getWriter().print("N");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
	}
	//获取短信	
	public void shortMessage() throws IOException{
		//获取手机号
		String account = this.getHttpServletRequest().getParameter("account");
		//验证手机号
			boolean isTure = true;
			Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");   
			Matcher matcher = pattern.matcher(account);
			isTure = matcher.matches();
			if(isTure){	
				//发送短信获取验证码
				String st = Util.sendMobile(account);
				//String st ="1111";
				this.getSession().setAttribute("st", st);
				this.getHttpServletResponse().getWriter().write("Y");
			}else{
				this.getHttpServletResponse().getWriter().write("N");
			}

	}
	//验证获取到的短信验证码是否和输入的验证码是否匹配
	public void isTure() throws IOException{
		//获取页面输入的验证码
		String mes = this.getHttpServletRequest().getParameter("mes");
		//获取短信的验证码
		String st = (String) this.getSession().getAttribute("st");
		System.out.println(st);
		if(st!=null){
			if(st.equals(mes)){
				System.out.println("验证成功！");
			}else{
				this.getHttpServletResponse().getWriter().write("N");
			}
		}else{
			this.getHttpServletResponse().getWriter().write("N");
		}
			
	}
	//个人信息查询
	public String viewList() throws UnsupportedEncodingException{
		String str = URLDecoder.decode(this.getHttpServletRequest().getParameter("name"),"UTF-8").trim();
		List list = usercenterService.getUserReadingList(str);
		if(list.size()>0)
		{
			Object[] obj=(Object[]) list.get(0);	
			String nuts = MoodleUtil.getNuts(obj[3].toString());
			String att = MoodleUtil.getAttendance(obj[3].toString());
			String cur = MoodleUtil.getCurrentLevel(obj[3].toString());
			rmView = new RmView();
			rmView.setId((Integer) obj[0]);
			//rmView.setHead("");
			rmView.setUserName(obj[1].toString());
			rmView.setUserRole(obj[2].toString());
			rmView.setUserAge(obj[4].toString());
			if(obj[5]==null){
				rmView.setUserYears("-1");
			}else{				
				rmView.setUserYears(obj[5].toString());
			}
			rmView.setUserNum(nuts);
			rmView.setUserDay(att);
			rmView.setUserLevel(cur);
			RmUserDetail rmUserDetail = rmUserDetailService.getByRmUserDetailId(rmView.getId());
			rmView.setPicName(Constants.PIC_PATH + (rmUserDetail.getPicName() != null ? rmUserDetail.getPicName() : Constants.PIC_DEF_NAME));
			/*Object[] obj=(Object[]) list.get(0);
			System.out.println(obj[3]);
			String nuts = MoodleUtil.getNuts(obj[3].toString());
			String att = MoodleUtil.getAttendance(obj[3].toString());
			String cur = MoodleUtil.getCurrentLevel(obj[3].toString());	
			System.out.println(obj[2]);
			String role = obj[2].toString();
			String age = obj[4].toString();
			String sy;
			if(obj[5]==null){
				sy = "";
				this.getHttpServletRequest().setAttribute("sy", sy);	
			}else{
				sy= obj[5].toString();
				this.getHttpServletRequest().setAttribute("sy", sy);			
			}
			//String sy = obj[5].toString();
			
			this.getHttpServletRequest().setAttribute("role", role);
			this.getHttpServletRequest().setAttribute("age", age);
			this.getHttpServletRequest().setAttribute("nuts", nuts);
			this.getHttpServletRequest().setAttribute("att", att);
			this.getHttpServletRequest().setAttribute("cur", cur);*/
		}
		this.getHttpServletRequest().setAttribute("rmView", rmView);
		return "userreadinglist";
	}
	//跳转个人信息修改页
	public String editviewUser() throws UnsupportedEncodingException{
		String str = URLDecoder.decode(this.getHttpServletRequest().getParameter("name"),"UTF-8").trim();
		List list = usercenterService.getUserReadingList(str);
		if(list.size()>0)
		{
			Object[] obj=(Object[]) list.get(0);	
			String nuts = MoodleUtil.getNuts(obj[3].toString());
			String att = MoodleUtil.getAttendance(obj[3].toString());
			String cur = MoodleUtil.getCurrentLevel(obj[3].toString());
			rmView = new RmView();
			rmView.setId((Integer) obj[0]);
			//rmView.setHead("");
			rmView.setUserName(obj[1].toString());
			rmView.setUserRole(obj[2].toString());
			rmView.setUserAge(obj[4].toString());
			if(obj[5]==null){
				rmView.setUserYears("");
			}else{				
				rmView.setUserYears(obj[5].toString());
			}
			rmView.setUserNum(nuts);
			rmView.setUserDay(att);
			rmView.setUserLevel(cur);
			RmUserDetail rmUserDetail = rmUserDetailService.getByRmUserDetailId(rmView.getId());
			rmView.setPicName(Constants.PIC_PATH + (rmUserDetail.getPicName() != null ? rmUserDetail.getPicName() : Constants.PIC_DEF_NAME));
		}
		this.getHttpServletRequest().setAttribute("rmView", rmView);
		return "userreadingedit";
	}
	
	//个人信息修改
	public void editUser() throws UnsupportedEncodingException{
		//获得ID
		String rmId = this.getHttpServletRequest().getParameter("nameid");
		System.out.println(rmId);
		//根据ID查询对象
		RmUser rmUser = (RmUser) usercenterService.gettp(Integer.parseInt(rmId),RmUser.class);
		RmUserDetail rmUserDetail = (RmUserDetail) usercenterService.gettp(Integer.parseInt(rmId),RmUserDetail.class);
		
		//保存或修改
		String name = URLDecoder.decode(this.getHttpServletRequest().getParameter("name"),"UTF-8").trim();
		String age = this.getHttpServletRequest().getParameter("age");
		String studyYears = this.getHttpServletRequest().getParameter("studyYears");
		rmUser.setName(name);
		rmUserDetail.setAge(age);
		rmUserDetail.setStudyYears(studyYears);
		
		usercenterService.saveOrUpdate(rmUser);			
		boolean b = usercenterService.saveOrUpdate(rmUserDetail);		
		 if(b)
		  {
		   try {	 
				this.getHttpServletResponse().getWriter().print("Y");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();;
			}
		  }	  			
	}		
	//学生个人信息
	public String studentList(){
		String str ="dihgzhj001";
		List list = usercenterService.getUserReadingList(str);
		if(list.size()>0)
		{
			Object[] obj=(Object[]) list.get(0);
			System.out.println(obj[3]);
			String nuts = MoodleUtil.getNuts(obj[3].toString());
			String att = MoodleUtil.getAttendance(obj[3].toString());
			String cur = MoodleUtil.getCurrentLevel(obj[3].toString());	
			System.out.println(obj[2]);
			String role = obj[2].toString();
			String age = obj[4].toString();
			String sy;
			if(obj[5]==null){
				sy = "";
				this.getHttpServletRequest().setAttribute("sy", sy);	
			}else{
				sy= obj[5].toString();
				this.getHttpServletRequest().setAttribute("sy", sy);			
			}
			//String sy = obj[5].toString();
			
			this.getHttpServletRequest().setAttribute("role", role);
			this.getHttpServletRequest().setAttribute("age", age);
			this.getHttpServletRequest().setAttribute("nuts", nuts);
			this.getHttpServletRequest().setAttribute("att", att);
			this.getHttpServletRequest().setAttribute("cur", cur);
		}
		this.getHttpServletRequest().setAttribute("list", list);
		return "userreadinglist";
		
	}
}
