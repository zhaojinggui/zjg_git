package com.tiangong.web.action.system;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.aspectj.weaver.ast.Var;

import com.tiangong.bean.RmUser;
import com.tiangong.bean.User;
import com.tiangong.bean.Userrole;
import com.tiangong.comm.MD5Util;
import com.tiangong.service.system.imp.DictionaryServiceImp;
import com.tiangong.service.system.imp.UserServiceImp;
import com.tiangong.web.action.BaseAction;

public class UserAction extends BaseAction {

	private UserServiceImp userService;
	private DictionaryServiceImp dictService;
	private RmUser user;
	private String password;
	private String userid;
	public void setUserService(UserServiceImp userService) {
		this.userService = userService;
	}

//	/**
//	 * 创建人：赵井桂
//	 * 创建时间：2013-7-29 下午03:25:34
//	 * 方法描述：用户列表
//	 * 参数名称：
//	 * 返回值：
//	 */
//	public void userList()
//	{
//		String userid=this.getHttpServletRequest().getParameter("userid");
//		String name=this.getHttpServletRequest().getParameter("name");
//		String email=this.getHttpServletRequest().getParameter("email");
//		String state=this.getHttpServletRequest().getParameter("state");
//		if((userid==null || userid.equals(""))&& (name==null || name.equals("")) && (email==null || email.equals("")) && (state==null || state.equals("")))
//		{
//			user=new User();
//		}else{
//			user=new User();
//			user.setUserId(userid);
//			user.setName(name);
//			user.setEmail(email);
////			user.setState(state);
//		}
//		user.setCurrentPage(getCurrentPage());
//		user.setPageSize(getPageSize());		
//		Integer count =userService.getCount(user);
//		List<User> list2 =userService.userList(user);
//		initDatagrid(list2,count);
//	}
//	
//	/**
//	 * 创建人：赵井桂
//	 * 创建时间：2013-7-29 下午03:25:51
//	 * 方法描述：添加用户信息
//	 * 参数名称：
//	 * 返回值：
//	 */
//	public void addUser()
//	{
//	  user.setPassword(MD5Util.MD5Encode("12345678",""));
////	  user.setState("1");
////	  user.setCreateDate(new Date());
//	  boolean b=userService.save(user);
//	  if(b)
//	  {
//	   try {
////		    Userrole userrole=new Userrole();
////			userrole.setRoleId(Configer.ROLE_TEACHER);
////			userrole.setUserId(user.getUserId());
////			userService.save(userrole);
//			this.getHttpServletResponse().getWriter().print("Y");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	  }
//	}
//	/**
//	 * 创建人：赵井桂
//	 * 创建时间：2013-7-29 下午03:26:08
//	 * 方法描述：判断该用户是否存在
//	 * 参数名称：@throws IOException
//	 * 返回值：
//	 */
//	public void isAvailable() throws IOException
//	{
//		String account = this.getHttpServletRequest().getParameter("account");
//		User user =userService.getUserByUserId(account);
//		if(user!=null)
//		{
//			this.getHttpServletResponse().getWriter().write("Y");
//		}else{
//			this.getHttpServletResponse().getWriter().write("N");
//		}
//	}
//	
//	/**
//	 * 创建人：赵井桂
//	 * 创建时间：2013-7-29 下午04:01:47
//	 * 方法描述：删除用户
//	 * 参数名称：@throws IOException
//	 * 返回值：
//	 *
//	 */
//	public void delUser() throws IOException
//	{
//		String id =this.getHttpServletRequest().getParameter("id");
//		User user=(User) userService.gettp(id,User.class);
//		if(userService.delete(user))
//		{
//			this.getHttpServletResponse().getWriter().write("Y");
//		}else{
//			this.getHttpServletResponse().getWriter().write("N");
//		}
//	}
//
//	public void setPwd() throws IOException
//	{
//		String id =this.getHttpServletRequest().getParameter("id");
//		User user=(User) userService.gettp(id,User.class);
//		user.setPassword(MD5Util.MD5Encode("12345678", ""));
//		if(userService.update(user))
//		{
//			this.getHttpServletResponse().getWriter().write("Y");
//		}else{
//			this.getHttpServletResponse().getWriter().write("N");
//		}
//	}
//	
//	public void setState() throws IOException
//	{
//		String id =this.getHttpServletRequest().getParameter("id");
//		String state=this.getHttpServletRequest().getParameter("state");
//		User user=(User) userService.gettp(id,User.class);
////		user.setState(state);
//		if(userService.update(user))
//		{
//			String content="";
////			if(user.getState().equals("1"))
////			{
////				content="尊敬的："+user.getName()+",您好，您的审核已通过!";
////			}else{
////				content="尊敬的："+user.getName()+",您好，对不起,您的审核未通过!";
////			}
//			this.getHttpServletResponse().getWriter().write("Y");
//		}else{
//			this.getHttpServletResponse().getWriter().write("N");
//		}
//	}
//	/**
//	 * 创建人：赵井桂
//	 * 创建时间：2013-7-29 下午04:01:59
//	 * 方法描述：将用户信息回显到修改页面
//	 * 参数名称：@return
//	 * 参数名称：@throws IOException
//	 * 返回值：
//	 */
//	public String perEdit() throws IOException
//	{
//		String id =this.getHttpServletRequest().getParameter("id");
//		user=(User) userService.gettp(id,User.class);
//		return "userEdit";
//	}
//	
//	public void updateUser() throws IOException
//	{
//		boolean b=userService.update(user);
//		if(b)
//		{
//			this.getHttpServletResponse().getWriter().print("Y");
//		}else{
//			this.getHttpServletResponse().getWriter().print("N");
//		}
//	}
	
	public void login() throws IOException
	{
		password=MD5Util.MD5Encode(password, "");
		user=userService.getUser(userid, password);
		if(user!=null)
		{
//			user.setFunctions(userService.getFunctions(user));
			this.getSession().setAttribute("user", user);
			this.getHttpServletResponse().getWriter().print("Y");				
//
		}else {
			this.getHttpServletResponse().getWriter().print("N");
		}
	}
	
//	public void changepwd() throws IOException
//	{
//		String id =this.getHttpServletRequest().getParameter("id");
//		String pwd =this.getHttpServletRequest().getParameter("pwd");
//		user=(User) userService.gettp(id,User.class);
//		user.setPassword(MD5Util.MD5Encode(pwd, ""));
//		boolean b=userService.update(user);
//		if(b)
//		{
//			this.getHttpServletResponse().getWriter().print("Y");
//		}else{
//			this.getHttpServletResponse().getWriter().print("N");
//		}
//	}
//	
//	public void setArea() throws IOException
//	{
//		String id =this.getHttpServletRequest().getParameter("id");
//		String areas =this.getHttpServletRequest().getParameter("areas");
//		user=(User) userService.gettp(id,User.class);
//		user.setJobtitle(areas);
//		boolean b=userService.update(user);
//		if(b)
//		{
//			this.getHttpServletResponse().getWriter().print("Y");
//		}else{
//			this.getHttpServletResponse().getWriter().print("N");
//		}
//	}
//	
//	public void getJobTitle() throws IOException
//	{
//		this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
//		String id =this.getHttpServletRequest().getParameter("id");
//		user=(User) userService.gettp(id,User.class);
//		this.getHttpServletResponse().getWriter().print(user.getJobtitle());
//		
//	}
//	public void pwdCompare() throws IOException
//	{
//		password=MD5Util.MD5Encode(password, "");
//		user=userService.getUser(userid, password);
//		if(user!=null)
//		{
//			this.getHttpServletResponse().getWriter().print("Y");				
//
//		}else {
//			this.getHttpServletResponse().getWriter().print("N");
//		}
//	}
	public String logout() throws IOException
	{
		this.getSession().invalidate();
		return "logout";
	}
	
	public String sessionnull()
	{
		return "logout";
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public RmUser getUser() {
		return user;
	}

	public void setUser(RmUser user) {
		this.user = user;
	}

	public void setDictService(DictionaryServiceImp dictService) {
		this.dictService = dictService;
	}
	
}
