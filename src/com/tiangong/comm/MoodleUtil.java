package com.tiangong.comm;

import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MoodleUtil {
	// 测试
//	public static String URL="https://qa.metricschina.com:443/api/";
	public static String URL="https://lms.metricschina.com:443/api/";
	/**
	* @Title: getNuts
	* @Description:根据token获取坚果数
	* @author zhao_jinggui
	* @param token
	* @return
	 */
	public static String getNuts(String token)
	{
		String url=URL+"achievements/nuts?token="+token;
		String result=Util.httpsGet(url);
		int count = 0;
		if(result != null){
			String read = Util.getValueByKey(result, "read");
			String quiz = Util.getValueByKey(result, "quiz");
			String reward = Util.getValueByKey(result, "reward");
			if(read != null && !read.equals("null") && !read.equals("0")){
				count += Integer.valueOf(read);
			}
			if(quiz != null && !quiz.equals("null") && !quiz.equals("0")){
				count += Integer.valueOf(quiz);
			}
			if(reward != null && !reward.equals("null") && !reward.equals("0")){
				count += Integer.valueOf(reward);
			}
		}
		return count+"";
	}
	
	/**
	* @Title: getAttendance
	* @Description:获取用户累计登陆天数
	* @author zhao_jinggui
	* @return
	 */
	public static String getAttendance(String token)
	{
		Integer limit=Configer.READING_LIMIT;
		String url=URL+"achievements/attendance?token="+token+"&limit="+limit;
		String result=Util.httpsGet(url);
		String days = "0";
		if(result != null){
			days=Util.getValueByKey(result, "days");
			if(days.equals("0"))
			{
				return "1";
			}
		}
		return days;
	}
	
	/**
	* @Title: getCohortList
	* @Description:获取阅读列表
	* @author zhao_jinggui
	* @param token
	* @return
	 */
	public static List getCohortList(String token)
	{
		String url=URL+"cohort/list?token="+token;
		String result=Util.httpsGet(url);
		JSONArray array =JSONArray.fromObject(result);
		List list =array.toList(array);
		return list;
	}
	
	/**
	* @Title: getCurrentLevel
	* @Description:获取当前阅读级别
	* @author zhao_jinggui
	* @return
	 */
	public static String getCurrentLevel(String token)
	{
		List<String> resultlist=new ArrayList();
		String level="B";
		List list=getCohortList(token);
		for(int i=0;list != null && i<list.size();i++)
		{
			if(list.get(i) != null && list.get(i).toString().trim().startsWith("RM"))
			{
				resultlist.add(list.get(i).toString());
			}
		}
		Collections.sort(resultlist);//默认升序
		if(resultlist.size()>0)
		{
			String levelstr=resultlist.get(resultlist.size()-1).toString();
			int index=levelstr.indexOf("-");
			level=levelstr.substring(2, index);			
		}
		return level; 
	}
	
	public static String addMoodleUser(String username,String password,String email,String role)
	{
		String age = "kid";
        if (role == "2") {
            age = "adult";
        }
        // 创建用户的url
        String url_user_create = URL+"user/create?token="+Configer.ADMIN_TOKEN;
        // 创建用户所需的数据
        Map map =new HashMap();
        map.put("username",username);
        map.put("password",password);
        map.put("firstname","a");
        map.put("lastname","b");
        map.put("email",email);
        map.put("idnumber","UNID123");
        map.put("age",age);
        map.put("lang","zh_cn");
        map.put("city","Beijing");
        map.put("country","CH");
        JSONObject entity=JSONObject.fromObject(map);
//        System.out.println(entity.toString());
        String result=doPost(url_user_create, entity.toString());
//        System.out.println(result);
        boolean b=result.startsWith("[");
        if(b)
        {
        	//用户登陆获取token
        	String url_login = URL+ "login?login="+username+"&password="+password;
        	String result1=Util.httpsGet(url_login);
    		String token=Util.getValueByKey(result1, "token");
    		//如果是个人用户和学生用户
    		if(role.equals("1") || role.equals("4"))
    		{
    			  // 为创建的用户添加RM-Demo权限
    			  List<String> list =new ArrayList();
    			  list.add("RM-Demo");
    			  list.add("RMA-001");
    			  list.add("RMB-001");
    			  for(int i=0;i<list.size();i++)
    			  {
    				  String url_cohort_add =URL+"cohort/add?token="+Configer.ADMIN_TOKEN+"&cohort="+list.get(i);
    				  String args="[\""+username+"\"]";
    				  doPost(url_cohort_add, args);
    			  }
    			  //给用户绑定测试书权限
                  String url_user_enroll = URL+"user/enroll?admintoken="+Configer.ADMIN_TOKEN+"&token="+token;
                  String paramter = "[\"OCT-001\",\"ODT-001\",\"OET-001\",\"OFT-001\",\"OGT-001\",\"OHT-001\",\"OIT-001\",\"OJT-001\",\"OKT-001\",\"OLT-001\",\"OMT-001\",\"ONT-001\",\"OOT-001\"]";
                  doPost(url_user_enroll, paramter);
    		}
    		
        	return token;
        }else{
         	return null;
        }
	}
	
	public static String doPost(String url,String param){
		HttpPost httpPost = null;
		String result = null;
		HttpClient httpclient =getHttpsClient();
		try{
			httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
			StringEntity params = new StringEntity(param,"UTF-8");
			httpPost.setEntity(params);
			HttpResponse response = httpclient.execute(httpPost);
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,"utf-8");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {  
            try {   
            	httpclient.getConnectionManager().shutdown();   
            } catch (Exception ex) {  
            	ex.printStackTrace(); 
            }  
        } 
		return result;
	}
	
    /**
    * @Title: getHttpsClient
    * @Description:HTTPS
    * @author zhao_jinggui
    * @return
     */
	public static HttpClient getHttpsClient()
	{
		HttpClient httpclient = new DefaultHttpClient();
        //Secure Protocol implementation.
		SSLContext ctx;
		try {
			ctx = SSLContext.getInstance("SSL");
		
		        //Implementation of a trust manager for X509 certificates
		X509TrustManager tm = new X509TrustManager() {
		
		public void checkClientTrusted(X509Certificate[] xcs,
				String string) throws CertificateException {
		
		}
		
		public void checkServerTrusted(X509Certificate[] xcs,
				String string) throws CertificateException {
		}
		
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
		};
			ctx.init(null, new TrustManager[] { tm }, null);
		
		SSLSocketFactory ssf = new SSLSocketFactory(ctx);
		
		ClientConnectionManager ccm = httpclient.getConnectionManager();
		        //register https protocol in httpclient's scheme registry
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", 443, ssf));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return httpclient;

	}
	public static void main(String args[]) throws MalformedURLException, Exception
	{	
//		String s=getCurrentLevel("6ba69e97a3f212684ddb3b11b0193b44");
//		String token=addMoodleUser("dzj12345890","123456789","dzj12349890@163.com","1");
		String s=getAttendance("2c21c8a5ced0337e83f3e7016f490d17");
		System.out.println(s);
//		2c21c8a5ced0337e83f3e7016f490d17
		
	
	}

}
