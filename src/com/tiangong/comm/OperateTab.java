/**
 * 
 */
package com.tiangong.comm;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**   
 *    
 * 项目名称：rwoa   
 * 类名称：OperateTab   
 * 类描述：操作对象相关工具类
 * 创建人：赵井桂   
 * 创建时间：2011-11-10 下午05:55:44   
 * 修改人：赵井桂  
 * 修改时间：2011-11-10 下午05:55:44   
 * 修改备注：   
 * @version    
 *    
 */
public class OperateTab {

	/**
	 * 
	 * 创建人：赵井桂
	 * 创建时间：2011-11-10 下午06:19:09
	 * 方法描述：分页查询表的信息
	 * 参数名称：@param session　Hibernate　session对象
	 * 参数名称：@param currentPage　　当前页
	 * 参数名称：@param pageSize　　　　总页数
	 * 参数名称：@param hql　　　　　　Hql语句
	 * 返回值：List  查询集合
	 *
	 */
	public static List showtab(Session session,int currentPage,int pageSize,String hql)
	{
		Query query= session.createQuery(hql);
		
		try {
			query.setFirstResult((currentPage-1)*pageSize);
			query.setMaxResults(pageSize);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return query.list();
	}
	
	/**
	 * 
	 * 创建人：赵井桂
	 * 创建时间：2011-11-10 下午06:23:39
	 * 方法描述：查询表的数目
	 * 参数名称：@param session　Hibernate　session对象
	 * 参数名称：@param hql　　　Hql语句
	 * 返回值：int 查询数量
	 *
	 */
	public static int count(Session session,String hql)
	{
		Query query =session.createQuery(hql);
		int count =Integer.parseInt(query.uniqueResult().toString());
		return count;
	}
	
	/**
	 * 创建人：赵井桂
	 * 创建时间：2011-11-10 下午06:57:13
	 * 方法描述：保存实例
	 * 参数名称：@param session　Hibernate　session对象
	 * 参数名称：@param obj　对象实例
	 * 返回值：成功返回true,失败返回false
	 */
	public static boolean save(Session session,Object obj)
	{
		boolean flag=false;
		Transaction tr=session.beginTransaction();
		try {
			session.save(obj);
			tr.commit();
			session.close();
			flag=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 创建人：赵井桂
	 * 创建时间：2011-11-10 下午06:59:08
	 * 方法描述：删除实例信息
	 * 参数名称：@param session　Hibernate　session对象
	 * 参数名称：@param obj　对象实例
	 * 返回值：　成功返回true,失败返回false
	 */
	public static boolean delete(Session session,Object obj)
	{
		boolean flag=false;
		Transaction tr=session.beginTransaction();
		try {
			session.delete(obj);
			tr.commit();
			session.close();
			flag=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return flag;
	}
	
	/**
	 * 
	 * 创建人：赵井桂
	 * 创建时间：2011-11-10 下午07:00:37
     * 方法描述：修改实例信息
	 * 参数名称：@param session　Hibernate　session对象
	 * 参数名称：@param obj　对象实例
	 * 返回值：　成功返回true,失败返回false
	 */
	public static boolean update(Session session,Object obj)
	{
		boolean flag=false;
		Transaction tr=session.beginTransaction();
		try {
			session.update(obj);
			tr.commit();
			session.close();
			flag=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return flag;
	}
	
	/**
	 * 
	 * 创建人：赵井桂
	 * 创建时间：2011-11-14 上午10:03:28
	 * 方法描述：将list封装成json
	 * 参数名称：@param list  List集合
	 * 参数名称：@param total　集合总个数
	 * 返回值：无
	 *
	 */
	  public static void initDatagrid(List list,int total){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("rows", list);
			JSONObject rows = JSONObject.fromObject(map);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Charset", "UTF-8");
			PrintWriter out;
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
	   * 创建人：赵井桂
	   * 创建时间：2011-11-14 上午10:07:11
	   * 方法描述：将输入流转换成字节数组
	   * 参数名称：@param inStream　输入流
	   * 返回值：字节数组
	   *
	   */
	  public static final byte[] input2byte(InputStream inStream) throws IOException{
			
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[100];
			int rc = 0;
			while((rc = inStream.read(buff,0,100))>0){
				swapStream.write(buff,0,rc);
			}
			byte[] in2b = swapStream.toByteArray();
			return in2b;
		}
		 
	  /**
	   * 创建人：赵井桂
	   * 创建时间：2011-11-14 上午10:08:21
	   * 方法描述：将字节数据转换成输入流
	   * 参数名称：@param buf　字节数组
	   * 返回值：　输入流
	   */
		public static final InputStream byte2Input(byte[] buf){
			 return new ByteArrayInputStream(buf);   
		}
		
	  /**
	   * 创建人：张义欣
	   * 创建时间：2011-12-14 上午09:38:48
	   * 方法描述：更新
	   * 参数名称：@param session
	   * 参数名称：@param hql
	   * 参数名称：@return
	   */
	  public static boolean hqlUpdate(Session session,String hql)
		{
			boolean flag=false;
			Transaction tr=session.beginTransaction();
			try {
				session.createQuery(hql).executeUpdate();
				
				tr.commit();
				flag=true;
				
			} catch (Exception e) {
				// TODO: handle exception
				tr.rollback();
			}
			
			return flag;
		}
}
