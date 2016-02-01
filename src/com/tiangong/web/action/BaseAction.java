package com.tiangong.web.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1858123616219302824L;
	public String msg;
	public String rows; // 每页显示数
	public String page; // 当前页
	public int total; // 总记录数

	/**
	 * @author 杜辉
	 * @date 2011-11-3 下午12:40:49
	 * @return 当前页
	 */
	public int getCurrentPage() {
		return Integer.parseInt((page == null || page == "0") ? "1" : page);
	}

	/**
	 * @author 杜辉
	 * @date 2011-11-3 下午12:40:49
	 * @return 每页记录总数
	 */
	public int getPageSize() {
		return Integer.parseInt((rows == null || rows == "0") ? "5" : rows);
	}

	/**
	 * @author 杜辉
	 * @date 2011-11-3 下午12:40:49
	 * @return 开始页
	 */
	public int getStart() {
		return (getCurrentPage() - 1) * getPageSize();
	}

	/**
	 * @author 杜辉 获得当前Http Servlet request
	 */
	public HttpServletRequest getHttpServletRequest() {

		return ServletActionContext.getRequest();
	}

	/**
	 * @author 杜辉
	 * @return Http Servlet response
	 */
	public HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * @author 杜辉
	 * @return Session
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public String getRealyPath(String path) {
		return getServletContext().getRealPath(path);
	}

	/**
	 * 构造jquery easyui datagrid(无分页)
	 * 
	 * @author 杜辉
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public void initDatagrid(List list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", list);
		JSONObject rows = JSONObject.fromObject(map);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Charset", "UTF-8");
		PrintWriter out;
		System.out.println(rows.toString());
		try {
			out = response.getWriter();
			out.print(rows);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造jquery easyui datagrid(支持分页)
	 * 
	 * @author 杜辉
	 * @date 2011-11-2 上午11:29:05
	 * @param list
	 * @param total
	 */
	public void initDatagrid(List list, int total) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		JSONObject rows = JSONObject.fromObject(map);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Charset", "UTF-8");
		PrintWriter out;
System.out.println(rows.toString());
		
		try {
			out = response.getWriter();
			out.print(rows);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 创建日期：2011-11-17下午05:34:25
	 * 修改日期：
	 * 作者：高珍珍
	 *TODO构造jquery easyui combobox(支持分页)
	 *return
	 */
	public void initCombobox(List list){
		Map map = new HashMap();
		map.put("list", list);
		JSONObject json = JSONObject.fromObject(map);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Charset", "UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(json.get("list"));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
  public  void ajaxOut(String str){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Charset", "UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  
  	public void ajaxCollection(Collection c){
		JSONArray jsonArray = JSONArray.fromObject(c);
		ajaxOut(jsonArray.toString());
	}
  	
	public void ajaxMap(Map map){
		JSONObject str = JSONObject.fromObject(map);
  		ajaxOut(str.toString());
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
