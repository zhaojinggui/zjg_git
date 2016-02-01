package com.tiangong.web.action.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.sword.wechat4j.token.TokenProxy;

import com.opensymphony.xwork2.ModelDriven;
import com.tiangong.service.system.imp.FunctionServiceImp;
import com.tiangong.service.system.imp.RoleServiceImp;
import com.tiangong.web.action.BaseAction;
import com.tiangong.bean.RmWxMenu;
import com.tiangong.bean.Role;
import com.tiangong.comm.Util;
import com.tiangong.comm.WechatUtil;

public class FunAction extends BaseAction implements ModelDriven<RmWxMenu>{
	Log log =org.apache.commons.logging.LogFactory.getLog(FunAction.class);
	private FunctionServiceImp funService;
	private RoleServiceImp roleService;
	private RmWxMenu wxmenu = new RmWxMenu();
	private String funid;
	private String roleid;
	private Integer id;
	String[] pageInfo = new String[7];
	
	
	public String[] getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(String[] pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RmWxMenu getWxmenu() {
		return wxmenu;
	}

	public void setWxmenu(RmWxMenu wxmenu) {
		this.wxmenu = wxmenu;
	}

	public String getFunid() {
		return funid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public void setFunid(String funid) {
		this.funid = funid;
	}

	public void setFunService(FunctionServiceImp funService) {
		this.funService = funService;
	}
	
	public void setRoleService(RoleServiceImp roleService) {
		this.roleService = roleService;
	}

	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午02:13:53
	 * 方法描述：保存菜单信息
	 * 参数名称：@throws Exception
	 * 返回值：无（异步调用）　
	 */
	public void savefun() throws Exception
	{
		  try {
		    	this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
		    	
			    List<RmWxMenu>  treeList = funService.selPid("0");
			    if(wxmenu.getPid().equals("0"))
	    		{
			    	if(treeList.size()>=3)
			    	{
			    		this.getHttpServletResponse().getWriter().write("最多只能添加三个一级菜单!");
			    		
			    	}else{
			    		 if(funService.save(wxmenu))
		    			 {
		    				 this.getHttpServletResponse().getWriter().write("Y");
		    			 }	
			    	}
	    		}else{
	    			 RmWxMenu menu=funService.getfun(wxmenu.getPid());
	    			 if(menu.getPid().equals("0"))
	    			 {
	    				 List<RmWxMenu>  childlist = funService.selPid(wxmenu.getPid());
			    		 if(childlist.size()>=5)
			    		 {
			    			 this.getHttpServletResponse().getWriter().write("最多只能添加五个二级菜单!");
			    			 
			    		 }else{
			    			 if(funService.save(wxmenu))
			    			 {
			    				 this.getHttpServletResponse().getWriter().write("Y");
			    			 }			    			 
			    		 }
			    		 
	    			 }else{
	    				 this.getHttpServletResponse().getWriter().write("不支持三级菜单!");
	    			 }
	    			 
	    			
	    		}
			   
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午02:15:33
	 * 方法描述：删除权限信息
	 * 返回值：　无（异步调用）
	 * @throws IOException 
	 */
	public void delfun() throws IOException
	{
		RmWxMenu fun=funService.getfun(funid);
		List list =funService.selPid(fun.getId());
		if(list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
				RmWxMenu menu=(RmWxMenu) list.get(i);
				funService.delfun(menu);
			}
		}
		if(funService.delfun(fun))
		{
			this.getHttpServletResponse().getWriter().write("Y");
		}else {
			this.getHttpServletResponse().getWriter().write("N");
		}
		
	}
	
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午02:19:17
	 * 方法描述：权限修改前数据回显到文本框中
	 * 返回值：无（异步调用）
	 */
	public void showfun()
	{
		  this.getHttpServletResponse().setContentType("application/json;charset=utf-8");
		  String funid=this.getHttpServletRequest().getParameter("funid");
		  wxmenu =funService.getfun(funid);
		  JSONArray array =new JSONArray().fromObject(wxmenu);
		  String str=array.toString().substring(1,array.toString().length()-1);
		  try {
			this.getHttpServletResponse().getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午02:21:12
	 * 方法描述：更新权限信息
	 * 返回值：无（异步调用）
	 */
	public void updatefun() throws Exception
	{
	 try {
		    if(funService.updatefun(wxmenu))
		    {
		    	this.getHttpServletResponse().getWriter().write("Y");
		    	
		    }else {
		    	this.getHttpServletResponse().getWriter().write("N");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午04:54:30
	 * 方法描述：查询所有权限以树形菜单显示
	 * 返回值：无（异步调用）
	 */
	public void getAllfunctions()
	  {
		List list =funService.selAllfunid();
	    List<RmWxMenu>  treeList = funService.selPid("-1");
	    List treenodes = new ArrayList();
	    for (int i = 0; i < treeList.size(); i++) {
	    	if(isEquals(list, treeList.get(i).getId()))
	    	{
	    		  Map node = new HashMap();
	    		  node.put("id",treeList.get(i).getId());
	    		  node.put("text",treeList.get(i).getMenuName());
	    		  Map attributes = new HashMap();
	    		  List childrens = findChildren(treeList.get(i).getId(), list);
	    		  if((childrens.size()>0) && childrens!=null)
	    		  {
	    			 node.put("children", childrens);
	    		  }
	    		  treenodes.add(node);
				}
	    }
	    JSONArray treeData = JSONArray.fromObject(treenodes);
	    this.getHttpServletResponse().setContentType("application/json;charset=UTF-8");
	    this.getHttpServletResponse().setCharacterEncoding("UTF-8");
	    this.getHttpServletResponse().setHeader("Charset", "UTF-8");
	    this.getHttpServletResponse().setHeader("Cache-Control", "no-cache");
	    try
	    {
	     this.getHttpServletResponse().getWriter().write(treeData.toString());
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 下午01:48:54
	 * 方法描述：递归遍历权限
	 * 参数名称：@param id　     权限父id
	 * 参数名称：@param list　权限集合
	 */
	 public List<Map<String, Object>> findChildren(String id, List list) {
	    List childrens = new ArrayList();
	    List<RmWxMenu> childList = this.funService.selPid(id);
	    if ((childList.size() > 0) && (childList != null)) {
	      for (int i = 0; i < childList.size(); i++) {
	    		if(isEquals(list,childList.get(i).getId()))
	    		{
	    		  Map children = new HashMap();
	    		  children.put("id",childList.get(i).getId());
	    		  children.put("text", childList.get(i).getMenuName());
	    		  List childrens_childrens = findChildren(childList.get(i).getId(), list);
	    		  if ((childrens_childrens.size() > 0) && (childrens_childrens != null)) {
	    			  children.put("children", childrens_childrens);
	    		  }
	    		  childrens.add(children);
	    	  }
	        }
	    }
	    return childrens;
	  }
	
	 /**
	  * 创建人：赵井桂
	  * 创建时间：2011-11-17 下午01:53:28
	  * 方法描述：遍历所有权限树
	  */
	  public void allfunstree()
	  {
   		    this.getHttpServletResponse().setContentType("application/json;charset=UTF-8");
	   		List list =funService.selAllfunid();
	 	    List<RmWxMenu>  treeList = funService.selPid("-1");
	 	    List treenodes = new ArrayList();
	 	    for (int i = 0; i < treeList.size(); i++) {
	 	    	
    		  Map node = new HashMap();
    		  node.put("id",treeList.get(i).getId());
    		  node.put("text",treeList.get(i).getMenuName());
    		  List<RmWxMenu>  mylist = funService.selPid(treeList.get(i).getId());
    		  if(mylist.size()>0)
    		  {
    			  node.put("state","closed");	 	    			  
    		  }else{
    			 node.put("state","open");
    		  }
    		  Map attributes = new HashMap();
    		  List childrens = findChild(list, treeList.get(i));
    		  if((childrens.size()>0) && childrens!=null)
    		  {
    			 node.put("children", childrens);
    		  }
    		  treenodes.add(node);
	 	    	 
	 	    }
		    JSONArray treeData = JSONArray.fromObject(treenodes);
		    
//		    StringBuilder sb=new StringBuilder();
//		    
//		     sb.append("[{");
//	   		 sb.append("\"id\":" +"-1"+ ",");
//	   		 sb.append("\n");
//	   		 sb.append("\"text\":\"" + "根节点"+ "\"");
//	   		 sb.append(",\"iconCls\":\"" + "root"+ "\"");
//	   		 sb.append("\n");
//	   		 sb.append(",\"state\":\"" + "open"+ "\"");
//	   		 sb.append("\n");
//	   		 sb.append(",\"children\":");
//	   		 sb.append("\n");
//	   		 sb.append(treeData.toString());
//	   		 sb.append("\n");
//	   		 sb.append("}]");
	   		 try {
				this.getHttpServletResponse().getWriter().write(treeData.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  /**
	   * 创建人：赵井桂
	   * 创建时间：2011-11-28 上午10:13:33
	   * 方法描述：判断权限id 是否包含在权限集合内
	   * 参数名称：@param rolePerms 权限集合
	   * 参数名称：@param str　权限id
	   * 返回值：true 存在　false 不存在
	   */
	  public boolean isEquals(List rolePerms, String str)
	  {
		  
	    boolean b = false;
	    for (int i = 0; i < rolePerms.size(); i++) {
	      if (str.equals(rolePerms.get(i).toString())) {
	        b = true;
	      }
	    }
	    return b;
	  }
	  
     /**
	  * 遍历所有菜单节点（FunChecked=0）;
	  * @param fun
	  * @return
	  */
	 public List<Map<String, Object>> findChild(List idList,RmWxMenu fun) {
		 
		    List childrens = new ArrayList();
		    List<RmWxMenu> childList =this.funService.selPid(fun.getId());	
		
		    if ((childList.size() > 0) && (childList != null)) {
		      for (int i = 0; i < childList.size(); i++) {
		    	  if(isEquals(idList, childList.get(i).getId()))
			    	{ 
		    			 Map children = new HashMap();
		    			 children.put("id",childList.get(i).getId());
		    			 children.put("text", childList.get(i).getMenuName());
		    			 List childrens_childrens = findChild(idList,childList.get(i));
		    			 if ((childrens_childrens.size() > 0) && (childrens_childrens != null)) {
		    				 children.put("children", childrens_childrens);
		    			 }
		    			 childrens.add(children);		    			 
		          }
		        }
		    }
		    return childrens;
		  }
	 
	  public void getTreeMenu()
		{
		  try
		    {
			String pid =this.getHttpServletRequest().getParameter("ids");
			System.out.println(pid);
		    List<RmWxMenu>  treeList = funService.selPid(pid);
		    List treenodes = new ArrayList();
		    for (int i = 0; i < treeList.size(); i++) {
    		  List<RmWxMenu>  mylist = funService.selPid(treeList.get(i).getId());
    		  Map node = new HashMap();
    		  node.put("id",treeList.get(i).getId());
    		  node.put("text",treeList.get(i).getMenuName());		    			  
    		  if(mylist.size()>0)
    		  {
    			  node.put("state","closed");	    			  
    		  }else{
    			  node.put("state","open");
    		  }
    		  treenodes.add(node);
		    }
	    	JSONArray treeData = JSONArray.fromObject(treenodes);		    	
	    	System.out.println("TREE::"+treeData.toString());
		    
		    this.getHttpServletResponse().setContentType("application/json;charset=UTF-8");
		    this.getHttpServletResponse().setCharacterEncoding("UTF-8");
		    this.getHttpServletResponse().setHeader("Charset", "UTF-8");
		    this.getHttpServletResponse().setHeader("Cache-Control", "no-cache");
		    this.getHttpServletResponse().getWriter().write(treeData.toString());
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	} 
	  
	public void publishMenu() throws IOException
	{
		List list =funService.selAllfunid();
	    List<RmWxMenu>  treeList = funService.selPid("0");
	    List treenodes = new ArrayList();
	    for (int i = 0; i < treeList.size(); i++) {
	    	if(isEquals(list, treeList.get(i).getId()))
	    	{
	    		  Map node = new HashMap();
	    		  String type=treeList.get(i).getMenuType();
	    		  if(!type.equals("-"))
	    		  {
	    			  node.put("type",type);
	    			  node.put("name",treeList.get(i).getMenuName());
		    		  if(type.equals("click"))
		    		  {
		    			  node.put("key",treeList.get(i).getMenuKey());
		    		  }else if(type.equals("view"))
		    		  {
		    			  node.put("url",treeList.get(i).getMenuUrl());  
		    		  }else if(type.equals("view_limited")){
		    			  
		    			  node.put("media_id",treeList.get(i).getMeunMediaid());  
		    		  }else if(type.equals("media_id")){
		    			  
		    			  node.put("media_id",treeList.get(i).getMeunMediaid());  
		    		  }
	    		  }else{	    			  
	    			  node.put("name",treeList.get(i).getMenuName());
	    		  }
	    			  Map attributes = new HashMap();
	    			  List childrens = findMenuChildren(treeList.get(i).getId(), list);
	    			  if((childrens.size()>0) && childrens!=null)
	    			  {
	    				  node.put("sub_button", childrens);
	    			  }
	    			  treenodes.add(node);
				}
	    }
	    Map result=new HashMap();
	    result.put("button", treenodes);
	    JSONObject treeData = JSONObject.fromObject(result);
	    String token=TokenProxy.accessToken();
	    String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token;
		String data=Util.doPost(url, treeData.toString());
		JSONObject backresult=JSONObject.fromObject(data);
		String flag=backresult.getString("errcode");
		if(flag.equals("0"))
		{
			this.getHttpServletResponse().getWriter().write("Y");
		}else{
			String errmsg=backresult.getString("errmsg");
			this.getHttpServletResponse().getWriter().write(errmsg);
		}
	}
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-17 下午01:48:54
	 * 方法描述：递归遍历权限
	 * 参数名称：@param id　     权限父id
	 * 参数名称：@param list　权限集合
	 */
	 public List<Map<String, Object>> findMenuChildren(String id, List list) {
	    List childrens = new ArrayList();
	    List<RmWxMenu> childList = this.funService.selPid(id);
	    if ((childList.size() > 0) && (childList != null)) {
	      for (int i = 0; i < childList.size(); i++) {
	    		if(isEquals(list,childList.get(i).getId()))
	    		{
	    		  String type=childList.get(i).getMenuType();	
	    		  Map children = new HashMap();
	    		  children.put("type",type);
	    		  children.put("name",childList.get(i).getMenuName());
	    		  if(type.equals("click"))
	    		  {
	    			  children.put("key",childList.get(i).getMenuKey());
	    		  }else if(type.equals("view"))
	    		  {
	    			  children.put("url",childList.get(i).getMenuUrl());  
	    		  }else if(type.equals("view_limited")){
	    			  
	    			  children.put("media_id",childList.get(i).getMeunMediaid());  
	    		  }else if(type.equals("media_id")){
	    			  
	    			  children.put("media_id",childList.get(i).getMeunMediaid());  
	    		  }
	    		  List childrens_childrens = findChildren(childList.get(i).getId(), list);
	    		  if ((childrens_childrens.size() > 0) && (childrens_childrens != null)) {
	    			  children.put("sub_button", childrens_childrens);
	    		  }
	    		  childrens.add(children);
	    	  }
	        }
	    }
	    return childrens;
	  }
	 
	 public void getMedias() throws IOException
	 {
		 this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
		 String token=TokenProxy.accessToken();
		 Map map =new HashMap();
		 map.put("type","news");
		 map.put("offset",0);
		 map.put("count",20);
		 String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+token;
		 JSONObject jo = JSONObject.fromObject(map);
		 String result=Util.doPost(url, jo.toString());
		 JSONObject res=JSONObject.fromObject(result);
		 JSONArray array =res.getJSONArray("item");
		 Map map2 =new HashMap();
		 for(int i=0;i<array.size();i++)
		 {
			 JSONObject obj=(JSONObject) array.get(i);
			 String mediaId=obj.getString("media_id");
			 JSONObject obj2=obj.getJSONObject("content");
			 JSONArray a=obj2.getJSONArray("news_item");
			 JSONObject obj3=(JSONObject) a.get(0);
			 String title=obj3.getString("title");
			 map2.put(mediaId, title);
		 }
		 JSONObject re=JSONObject.fromObject(map2);
		 this.getHttpServletResponse().getWriter().write(re.toString());
	 }
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-16 下午02:22:11
	 * 方法描述：实现modelDriven接口重写getModel()方法
	 * 返回值：权限对象
	 */
	public RmWxMenu getModel() {
		// TODO Auto-generated method stub
		return wxmenu;
	}
}