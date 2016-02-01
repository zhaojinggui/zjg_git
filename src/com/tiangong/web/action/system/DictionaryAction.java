package com.tiangong.web.action.system;

import java.io.IOException;
import java.util.List;
import com.tiangong.service.system.imp.DictionaryServiceImp;
import com.tiangong.bean.RmWxDictionary;
import com.tiangong.comm.OperateTab;
import net.sf.json.JSONArray;
import com.opensymphony.xwork2.ModelDriven;
import com.tiangong.web.action.BaseAction;


public class DictionaryAction extends BaseAction implements ModelDriven{

	private DictionaryServiceImp dictService;
	private RmWxDictionary dictionary=new RmWxDictionary();
	private String dictid;
	private String dictname;
	private String keyname;
	private String keyvalue;
	
	public RmWxDictionary getRmWxDictionary() {
		return dictionary;
	}
	public void setDictionary(RmWxDictionary dictionary) {
		this.dictionary = dictionary;
	}
	public void setDictService(DictionaryServiceImp dictService) {
		this.dictService = dictService;
	}
	public String getDictid() {
		return dictid;
	}
	public void setDictid(String dictid) {
		this.dictid = dictid;
	}
	
	/**
	 * 分页查询字典信息
	 * @throws IOExceptio 
	 */
	public void selalldict() throws IOException
	{
		this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
		RmWxDictionary dictionary=new RmWxDictionary();
		if(dictname!=null && !"".equals(dictname))
		{
			dictionary.setDictName(dictname);
		}
		if(keyname!=null && !"".equals(keyname))
		{
			dictionary.setDictKeyName(keyname);
		}
		if(keyvalue!=null && !"".equals(keyvalue))
		{
			dictionary.setDictKeyValue(keyvalue);
		}
		dictionary.setCurrentPage(getCurrentPage());
		dictionary.setPageSize(getPageSize());
		List<RmWxDictionary> list =dictService.pagelist(dictionary);
		int count =dictService.count(dictionary);
		OperateTab.initDatagrid(list,count);
	}
	
	/**
	 * 保存字典
	 * @throws IOException
	 */	public void savedic() throws IOException
	{
		if(dictService.save(dictionary))
		{
	        this.getHttpServletResponse().getWriter().write("Y");  
	        
		}else{
			this.getHttpServletResponse().getWriter().write("N");
		}
		
	}
	/**
	 * 删除字典
	 * @throws IOException
	 */
	public void dictdel() throws IOException
	{
		int count =0;
		String ids[]=dictid.split(",");
		for(int i=0;i<ids.length;i++)
		{
			RmWxDictionary dict=dictService.getDict(ids[i]);
			if(dictService.delete(dict))
			{
				  count ++;
			}
		}
		if(count>0)
		{
			this.getHttpServletResponse().getWriter().write("Y");
		}
	}
	/**
	 * 返回字典信息 
	 */
	
	public void getdic()
	{
		this.getHttpServletResponse().setContentType("application/json;charset=utf-8");
		String id =this.getHttpServletRequest().getParameter("id");
		RmWxDictionary dict=dictService.getDict(id);
		JSONArray array =JSONArray.fromObject(dict);
		String dicts=array.toString().substring(1,array.toString().length()-1);
		try {
			this.getHttpServletResponse().getWriter().write(dicts);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 更新字典
	 * @throws IOException
	 */
	public void dictupdate() throws IOException
	{
		if(dictService.update(dictionary))
		{
	        this.getHttpServletResponse().getWriter().write("Y");  
		}
	}
	/**
	 * 变更字典状态
	 * @throws IOException
	 */
	public void changestatus() throws IOException
	{
		int count=0;
		String status=this.getHttpServletRequest().getParameter("status");
		String ids[]=dictid.split(",");
		for(int i=0;i<ids.length;i++)
		{
			RmWxDictionary dictionary =dictService.getDict(ids[i]);
			dictionary.setDictStatus(Boolean.valueOf(status));
			if(dictService.update(dictionary))
			{
                  count++;				
			}
		}
		
		if(count>0)
		{
			this.getHttpServletResponse().getWriter().write("Y");
		}
	}
	
	public void checkdictunique() throws IOException
	{
		
		String dictname =this.getHttpServletRequest().getParameter("dictname");
		String key =java.net.URLDecoder.decode(this.getHttpServletRequest().getParameter("key"));
		List list =dictService.selbynameandkey(dictname, key);
		if(list.isEmpty())
		{
			this.getHttpServletResponse().getWriter().write("Y");
		}else{
			this.getHttpServletResponse().getWriter().write("N");
		
		}
	}
	/**
	 * 得到所有字典文件
	 * @author wangzhe/bdjb0064
	 * date: 2015-3-27 上午10:31:10 <br/>
	 */
	public void getAllDictionary(){
		try {
			List<String> list =dictService.getAllDictionary();
			StringBuilder sb=new StringBuilder();
			sb.append("[");
			for(int i=0;i<list.size();i++)
			{
			    //sb.append("<input type='checkbox' id='add' name='opers' value='"+empList.get(i).getUserId()+"'>"+empList.get(i).getName());
			    sb.append("{");
			    sb.append("\"id\":\""+list.get(i).toString()+"\",");
			    sb.append("\"text\":\""+list.get(i).toString()+"\"");
			    sb.append("},");
			}
			if(sb.toString().length()>2)
			{
			    sb.deleteCharAt(sb.toString().length()-1);
			}
			sb.append("]");
			this.ajaxOut(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
//	/**
//	 * 拼接字典项名称下拉列表框
//	 */
//	public void getitemnames()
//	{
//	     this.getHttpServletResponse().setContentType("application/json;charset=utf-8");
//		 StringBuffer sb =new StringBuffer();
//		 List list =dictService.selItems();
//		 System.out.println(list.isEmpty());
//		 sb.append("[");
//		 for(int i=0;i<list.size();i++)
//		 {
//			 sb.append("{");
//			 sb.append("\"id\":\"" +i+"\"");
//		     sb.append(",\"text\":\"" +list.get(i).toString()+"\"");
//		     sb.append("},");
//		 }
//		 
//		 sb.deleteCharAt(sb.toString().length()-1);
//		 sb.append("]");
//		 try {			
//			 this.getHttpServletResponse().getWriter().write(sb.toString());
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//
	/**
	 * 拼接字典名称下拉列表
	 */
	public void dicnameselect()
	{
		this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
		String level=this.getHttpServletRequest().getParameter("level");
		List<RmWxDictionary> list =dictService.seldictbyname(level);
//		StringBuffer sb=new StringBuffer();
//		sb.append("<select id='dicname' name='dicname' onchange='secselect()'>");
//		sb.append("<option value=''>-请选择-</option>");
//		for(int i=0;i<list.size();i++)
//		{
//			sb.append("<option value='"+list.get(i).getDictKeyValue()+"'>");
//			sb.append(list.get(i).getDictKeyName());
//			sb.append("</option>");	
//		}
//		sb.append("</select>");
		JSONArray array =JSONArray.fromObject(list);
		try {
			this.getHttpServletResponse().getWriter().write(array.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dictvalueselect()
	{
		this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
		String name=this.getHttpServletRequest().getParameter("name");
		String key=this.getHttpServletRequest().getParameter("key");
		
		List<RmWxDictionary> list =dictService.selbynameandkey(name, key);
//		StringBuffer sb=new StringBuffer();
//		if(name.equals("area_level2"))
//		{
//			sb.append("<select id='dictwo' name='dictwo' onchange='threeselect()'>");
//		}else{
//			sb.append("<select id='dictwo' name='dictwo' onchange='setValue()'>");			
//		}
//		sb.append("<option value=''>-请选择-</option>");
//		for(int i=0;i<list.size();i++)
//		{
//			sb.append("<option value='"+list.get(i).getDictKeyValue()+"'>");
//			sb.append(list.get(i).getDictKeyValue());
//			sb.append("</option>");	
//		}
//		sb.append("</select>");
		JSONArray array =JSONArray.fromObject(list);
		try {
			this.getHttpServletResponse().getWriter().write(array.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RmWxDictionary getModel() {
		// TODO Auto-generated method stub
		return dictionary;
	}
	public String getDictname() {
		return dictname;
	}
	public void setDictname(String dictname) {
		this.dictname = dictname;
	}
	public String getKeyname() {
		return keyname;
	}
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	public String getKeyvalue() {
		return keyvalue;
	}
	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}
	
}
