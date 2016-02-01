package com.tiangong.web.action.system;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletResponse;

public class loginFilter  implements Filter {
	
	private String errorpage;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		/** 
		* 页面缓存设定 
		* <确保浏览器不缓存页面数据 
		*/ 
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse rep=(HttpServletResponse) response;

		rep.setHeader("Cache-Control","no-cache"); 
		rep.setHeader("Cache-Control","no-store"); 
		rep.setDateHeader("Expires", 0); 
		rep.setHeader("Pragma","no-cache"); 
		
		String imgpath=req.getRequestURL().toString();
		System.out.println("Path:"+imgpath);
		
		int index=imgpath.lastIndexOf("/");
		imgpath=imgpath.substring(index+1,imgpath.length());

		if (req.getSession().getAttribute("user") == null) {
			if(imgpath.toString().equals("")|| imgpath.toString().equals("login.jsp"))
			{
				filterChain.doFilter(req,rep); 
			}else{
				//req.getRequestDispatcher(errorpage).forward(req,rep);
				String path = req.getContextPath();
				String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
				rep.sendRedirect(basePath+errorpage);
			}
		} else {
			filterChain.doFilter(req,rep); 
        }
	    }
	
	public void init(FilterConfig conf) throws ServletException {
		// TODO Auto-generated method stub
		errorpage=conf.getInitParameter("login");
		
	}

}
