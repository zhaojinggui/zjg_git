package com.tiangong.comm;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.sword.wechat4j.token.TokenProxy;


public class Util {
	
	public static String str =Util.class.getResource("").toString().substring(6);
	public static int j=str.indexOf("weChat");
	public static String path=str.substring(0, j);
	public static String DATE_FOLDER = path + "cards";
	
	public static int count=0;
	public static int count2=0;
	public static String getCurrentTimeMillis()
	{
		 Date dt= new Date();
		 Long time= dt.getTime();//这就是距离1970年1月1日0点0分0秒的毫秒数
		 return time.toString();
	}
	public static String toDate(Timestamp time,String format)
	{
		SimpleDateFormat df = new SimpleDateFormat(format);  
		String result=df.format(time);
		return result;
	}
	
	public static int compare_date(String DATE1, Date now) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			String d2=df.format(now);
			Date dt2=df.parse(d2);
//			System.out.println(dt1.getTime());
//			System.out.println(dt2.getTime());
		if (dt1.getTime() > dt2.getTime()) {
			//System.out.println("dt1 在dt2前");
			return 1;
		} else if (dt1.getTime() < dt2.getTime()) {
			//System.out.println("dt1在dt2后");
			return -1;
		} else {
			//System.out.println("0");
			return 0;
		}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	
	public static String httpsGet(String url)
	{
		HttpClient httpclient =getHttpsClient();
		try {
			
			HttpGet httpget = new HttpGet(url);
			HttpParams params = httpclient.getParams();
//			System.out.println("REQUEST:" + httpget.getURI());
			ResponseHandler responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
//			System.out.println(responseBody);
			return responseBody;
			// Create a response handler

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {  
            try {   
            	httpclient.getConnectionManager().shutdown();   
            } catch (Exception ex) {  
            	ex.printStackTrace(); 
            }  
        } 
		return null;
	}
	
	public static String httpsPost(String url)
	{
		HttpClient httpclient =getHttpsClient();
		try {
			HttpPost httpPost = new HttpPost(url);
			System.out.println("REQUEST:" + httpPost.getURI());
			ResponseHandler responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpPost, responseHandler);
			System.out.println(responseBody);
			return responseBody;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {  
            try {   
            	httpclient.getConnectionManager().shutdown();   
            } catch (Exception ex) {  
            	ex.printStackTrace(); 
            }  
        } 
		return null;
	}
	/**
	* @Title: downloadFile
	* @Description:httpclient下载文件
	* @author zhao_jinggui
	* @param REMOTE_FILE_URL
	* @param filePath
	* @return
	 */
	public static boolean downloadFile(String REMOTE_FILE_URL,String filePath)
	{
		boolean b=true;
		HttpClient httpclient =getHttpsClient();
		try {
			HttpGet httpget = new HttpGet(REMOTE_FILE_URL);
			HttpResponse res = httpclient.execute(httpget);
			HttpEntity entity = res.getEntity();
			FileOutputStream out = new FileOutputStream(new File(filePath));
			 if (entity != null) {

		            InputStream instream = entity.getContent();
		            int len;
		            byte[] tmp = new byte[2048];
		            while ((len = instream.read(tmp)) != -1) {
		                out.write(tmp, 0, len);
		            }
			 }
			 else{
		            out.write(new byte[]{'{','}' });
		        }

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b=false;
		} catch (Exception ex) {
			ex.printStackTrace();
			b=false;
		}finally {  
            try {   
            	httpclient.getConnectionManager().shutdown();   
            } catch (Exception ex) {  
            	ex.printStackTrace(); 
            }  
        } 
		return b;
		
	}
	
	public static String getValueByKey(String content,String key)
	{
		JSONObject obj =JSONObject.fromObject(content);
		String result =obj.getString(key);
		return result;
	}
	
	public static String getValueByKey(JSONObject obj,String key)
	{
		String result =obj.getString(key);
		return result;
	}
	
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	public static String doPost(String url,String param){
		long start=System.currentTimeMillis();   //获取开始时间
		HttpPost httpPost = null;
		String result = null;
		HttpClient httpclient =getHttpsClient();
		try{
			httpPost = new HttpPost(url);
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
            	long end=System.currentTimeMillis();   //获取开始时间
            	 System.out.println("HTTP___请求时间： "+(end-start)+"ms");
            } catch (Exception ex) {  
            	ex.printStackTrace(); 
            }  
        } 
		return result;
	}
	
	/**
     * 微信服务器临时素材上传
     * @param file  表单名称media
     * @param token access_token
     * @param type  type只支持四种类型素材(video/image/voice/thumb)
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
     */
    public static String uploadMedia(File file, String token, String type) {
        if(file==null||token==null||type==null){
            return null;
        }
        if(!file.exists()){
          //  logger.info("上传文件不存在,请检查!");
            return null;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload";
        JSONObject jsonObject = null;
        HttpClient httpclient =getHttpsClient();
        HttpPost httppost = new HttpPost(url);

        try {
			
        	FileBody media = new FileBody(file);
        	StringBody mytoken = new StringBody(token);  
        	StringBody mytype = new StringBody(type);
        	MultipartEntity reqEntity = new MultipartEntity(); 
        	reqEntity.addPart("access_token",mytoken);
        	reqEntity.addPart("type",mytype);
        	reqEntity.addPart("media",media);
        	httppost.setEntity(reqEntity);

        	HttpResponse response = httpclient.execute(httppost); 
        	int statusCode = response.getStatusLine().getStatusCode();  
        	if(statusCode == HttpStatus.SC_OK){  
                System.out.println("服务器正常响应.....");  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){
					String text = EntityUtils.toString(resEntity,"utf-8");
					jsonObject = JSONObject.fromObject(text);
	                String media_id=jsonObject.getString("media_id");
	                return media_id;
				}
            }  
            
        } catch (Exception execption) {
        	execption.printStackTrace();
        }finally {  
            try {   
                httpclient.getConnectionManager().shutdown();   
            } catch (Exception ignore) {  
                  
            }  
        }   
        return null;
    }

   /**
   * @Title: getMedidByCodeImage
   * @Description: 生成临时二维码
   * @author zhao_jinggui
   * @param token
   * @param scene_id
   * @return
    */
    
    public static String getMedidByCodeImage(String token,String scene_id)
    {
    	String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token;
		String json="{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+scene_id+"}}}";
		try {
			String r=doPost(url,json);
			String ticket=Util.getValueByKey(r, "ticket");
			boolean b=downloadFile("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket,"D:\\voiceTest\\t211.jpg");
			if(b)
			{
				File file=new File("D:\\voiceTest\\t211.jpg");
				String media_id=Util.uploadMedia(file, token, "image");
				file.delete();
				return media_id;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
	
    /**
     * @Title: getMedidByCodeImage
     * @Description: 生成永久二维码
     * @author zhao_jinggui
     * @param token
     * @param scene_id
     * @return
      */
      
      public static String getCodeImage(String token,String scene_id)
      {
      	String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token;
  		String json="{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+scene_id+"\"}}}";
  		try {
  			String r=doPost(url,json);
  			System.out.println(r);
  			String ticket=Util.getValueByKey(r, "ticket");
  			boolean b=downloadFile("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket,"D:\\voiceTest\\yjewm2.jpg");
//  			if(b)
//  			{
//  				File file=new File("D:\\voiceTest\\t211.jpg");
//  				String media_id=Util.uploadMedia(file, token, "image");
//  				file.delete();
//  				return media_id;
//  			}
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
      	return null;
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
	
	/**
	* @Title: urlEnodeUTF8
	* @Description:字符串转码
	* @author zhao_jinggui
	* @param str
	* @return
	 */
	public static String urlEnodeUTF8(String str){ 

        String result = str; 
        try { 
            result = URLEncoder.encode(str,"UTF-8"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return result; 
    } 
	/**
	* @Title: getOauth2AccessToken
	* @Description:网页授权
	* @author zhao_jinggui
	* @param appId
	* @param appSecret
	* @param code
	* @return
	 */
	
	public static WeiXinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeiXinOauth2Token wat = new WeiXinOauth2Token();
        String oauth2Url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        String requestUrl = oauth2Url.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code != null ? code : "");
        
        String result=httpsGet(requestUrl);
        JSONObject jsonObject =JSONObject.fromObject(result);
        if (null != jsonObject) {
                try {
                        wat = new WeiXinOauth2Token();
                        wat.setAccessToken(jsonObject.getString("access_token"));
                        wat.setExpiresIn(jsonObject.getInt("expires_in"));
                        wat.setRefeshToken(jsonObject.getString("refresh_token"));
                        wat.setOpenId(jsonObject.getString("openid"));
                        wat.setScope(jsonObject.getString("scope"));
                } catch (Exception e) {
                        wat = null;
                        String errorCode = jsonObject.getString("errcode");
                        String errorMsg = jsonObject.getString("errmsg");
                        System.out.println("获取网页授权凭证失败"+errorCode+errorMsg);
                }

        }
        return wat;
	}
	
	/**
	* @Title: sendTextMsg
	* @Description:利用客服接口发送文本消息
	* @author zhao_jinggui
	* @param token
	* @param openId
	* @param contentText
	* @return
	 */
	public static String sendTextMsg(String token,String openId,String contentText)
	{
		String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+token;
		Map map =new HashMap();
		map.put("touser", openId);
		map.put("msgtype", "text");
		map.put("text", "text");
		Map map2=new HashMap();
		map2.put("content", contentText);
		map.put("text", map2);
		JSONObject obj =JSONObject.fromObject(map);
		String result=doPost(url,obj.toString());
		JSONObject resobj=JSONObject.fromObject(result);
		String errmsg=resobj.getString("errmsg");
		if(errmsg.equals("ok"))
		{
			return "Y";
		}else{
			return errmsg;
		}
	}
	/**
	* @Title: sendTextMsg
	* @Description:利用客服接口发送图文消息
	* @author zhao_jinggui
	* @param token
	* @param openId
	* @param contentText
	* @return
	 */
	public static String sendNewMsg(String token,String openId,String title,String description,String myurl,String picUrl)
	{
		 String msg=null;
		 count2++;
		 if(count2==1)
		 {
			 String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+token;
			 StringBuilder sb=new StringBuilder();
			 sb.append("{");
			 sb.append("\"touser\":\"" +openId+ "\",");
			 sb.append("\"msgtype\":\"" + "news"+ "\",");
			 sb.append("\"news\":");
			 sb.append("{");
			 sb.append("\"articles\":");
			 sb.append("[{");
			 sb.append("\"title\":\"" +title+ "\",");
			 sb.append("\"description\":\"" +description+ "\",");
			 sb.append("\"url\":\"" +myurl+ "\",");
			 sb.append("\"picurl\":\"" +picUrl+ "\"");
			 sb.append("}]");
			 sb.append("}");
			 sb.append("}");
			 String result=doPost(url,sb.toString());
			 JSONObject resobj=JSONObject.fromObject(result);
			 msg=resobj.getString("errmsg");
			 count2=0;
		 }
		 if(msg.equals("ok"))
		 {
			 return "Y";
		 }else{
			 return msg;
		 }
	  }
	/**
	* @Title: sendImageMsg
	* @Description:利用客服接口发送图片消息
	* @author zhao_jinggui
	* @param token
	* @param openId
	* @param cardId
	* @param cardpwd
	* @return
	 */
	public static String sendImageMsg(String token,String openId,String cardId,String cardpwd)
	{
		boolean b=createImageCard(cardId,cardpwd);
		if(b){
			
			File file =new File(getPath()+cardId+".png");
			String media_id=uploadMedia(file,token,"image");
			String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+token;
			Map map =new HashMap();
			map.put("touser", openId);
			map.put("msgtype", "image");
			Map map2=new HashMap();
			map2.put("media_id", media_id);
			map.put("image", map2);
			JSONObject obj =JSONObject.fromObject(map);
			String result=doPost(url,obj.toString());
			JSONObject resobj=JSONObject.fromObject(result);
			String errmsg=resobj.getString("errmsg");
			if(errmsg.equals("ok"))
			{
				return "Y";
			}else{
				return errmsg;
			}
		}
		return "";
	}

	/**
	* @Title: createImageCard
	* @Description:生成网页图片
	* @author zhao_jinggui
	* @param cardId
	* @param cardPwd
	* @return
	 */
	public static boolean createImageCard(String cardId,String cardPwd)
	{
		boolean b=true;
		try{
			HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
			imageGenerator.loadUrl("http://zhaojinggui.pagekite.me/weChat/jsp/wechat/card.jsp?cardId="+cardId+"&cardPwd="+cardPwd+"");
			imageGenerator.saveAsImage(getPath()+cardId+".png");
		}catch(Exception e)
		{
			b=false;
		}
		return b;
	}
	
	/**
	* @Title: sendMobile
	* @Description:发送手机验证码
	* @author zhao_jinggui
	* @param mobile
	* @return
	 */
	public static String sendMobile(String mobile)
	{
		String randomnumber=getRandomString(6);
		String pwd=MD5Util.MD5Encode(Configer.MES_ID+Configer.MES_CODE_SECRET, "").toUpperCase();
		String url="http://sdk2.entinfo.cn:8061/mdsmssend.ashx?sn="+Configer.MES_ID+"&pwd="+pwd+"&mobile="+mobile+"&content="+randomnumber+"【读伴儿图书馆】&ext=&stime=&rrid=&msgfmt=";
		String result=httpsPost(url);
		if(result!=null && !result.equals(""))
		{
			return randomnumber;			
		}
		return null;
	}
	
	
	public static String getPath() {
		
		String accountRoot="";
		String os_name = System.getProperty("os.name").toLowerCase();
		if (os_name.indexOf("windows") != -1) {   //windows//根据不同的系统名称获得应用程序的位置
			
			 accountRoot = DATE_FOLDER + "/";
		} else {
			 accountRoot = "/"+DATE_FOLDER + "/";
		}
		File file =new File(accountRoot);
		if(!file.exists())
		{
			file.mkdir();
		}
		
		return accountRoot;
	}
	
	/**
	* @Title: sendTemplateMsg
	* @Description:发送模板消息
	* @author zhao_jinggui
	* @param cardId
	* @param cardPwd
	* @param openId
	* @param level
	* @param limit
	* @param token
	* @return
	 */
	public static String sendTemplateMsg(String cardId,String cardPwd,String openId,String level,String limit,String link,String frist,String remark,String token)
	{
		 String msg=null;
		 count++;
		 System.out.println("Count:+++++:"+count);
		 if(count==1)
		 {
		 String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;
	     String templateId="MIT6M8k7HE0nEivnRdFYl1Z_ihVsAf2MXA-LzCQaR8U";
	     StringBuilder sb=new StringBuilder();
	     sb.append("{");
		 sb.append("\"touser\":\"" +openId+ "\",");
//		 sb.append("\n");
		 sb.append("\"template_id\":\"" + templateId+ "\"");
//		 sb.append("\n");
		 sb.append(",\"url\":\"" + link+ "\"");
//		 sb.append("\n");
		 sb.append(",\"topcolor\":\"" + "#FF0000"+ "\"");
//		 sb.append("\n");
		 sb.append(",\"data\":");
		 sb.append("{");
//		 sb.append("\n");
		 sb.append("\"first\":");
		 sb.append("{");
		 sb.append("\"value\":\"" +frist+ "\"");
		 sb.append(",\"color\":\"" + "#173177"+ "\"");
		 sb.append("},");
//		 sb.append("\n");
		 sb.append("\"keyword1\":");
		 sb.append("{");
		 sb.append("\"value\":\"" +cardId+ "\"");
		 sb.append(",\"color\":\"" + "#173177"+ "\"");
		 sb.append("},");
//		 sb.append("\n");
		 sb.append("\"keyword2\":");
		 sb.append("{");
		 sb.append("\"value\":\"" +cardPwd+ "\"");
		 sb.append(",\"color\":\"" + "#173177"+ "\"");
		 sb.append("},");
//		 sb.append("\n");
		 sb.append("\"keyword3\":");
		 sb.append("{");
		 sb.append("\"value\":\"" +level+ "\"");
		 sb.append(",\"color\":\"" + "#173177"+ "\"");
		 sb.append("},");
//		 sb.append("\n");
		 sb.append("\"keyword4\":");
		 sb.append("{");
		 sb.append("\"value\":\"" +"自激活之日起，1年内有效"+ "\"");
		 sb.append(",\"color\":\"" + "#173177"+ "\"");
		 sb.append("},");
//		 sb.append("\n");
		 sb.append("\"remark\":");
		 sb.append("{");
		 sb.append("\"value\":\"" +"请于"+limit+"前激活本卡，过期将无法激活，"+remark+ "\"");
		 sb.append(",\"color\":\"" + "#173177"+ "\"");
		 sb.append("}");
//		 sb.append("\n");
		 sb.append("}");
		 sb.append("}");
//		 System.out.println(sb.toString());
		 String result=Util.doPost(url, sb.toString());
		 msg=Util.getValueByKey(result, "errmsg");
		 }
		 if(msg.equals("ok"))
		 {
			 return "Y";
		 }else{
			 count=0;
			 return msg;	 
		 }
	}
	
	/**
	* @Title: isSubscribe
	* @Description:判断该微信号是否已关注读伴儿微信号
	* @author zhao_jinggui
	* @param openId
	* @return
	 */
	public static boolean isSubscribe(String openId)
	{
		String token=TokenProxy.accessToken();
		String url="https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&next_openid=";
		String result=Util.httpsGet(url);
		JSONObject obj=JSONObject.fromObject(result);
		String next_openid=obj.getString("next_openid");
		Integer count=obj.getInt("count");
		JSONArray openids=obj.getJSONObject("data").getJSONArray("openid");
		if(count>10000)
		{
			String url2="https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&next_openid="+next_openid;
			String result2=Util.httpsGet(url2);
			JSONObject obj2=JSONObject.fromObject(result2);
			JSONArray openidss=obj2.getJSONObject("data").getJSONArray("openid");
			openids.addAll(openidss);
		}
		if(openids.contains(openId))
		{
			return false;
		}else{
			return true;
		}
	}
	
	
	public static void main(String args[]) throws MalformedURLException, Exception
	{
//		String str =urlEnodeUTF8("http://weixin.readingmate.com.cn/weChat/wechat!toUserCenter.action");
		//String str =loadText("http://s3.amazonaws.com/speech-content-test/Library/G03001/G03001.xml");
//		String str =loadText("https://storage.metricschina.com/qa/Library/BL3E-05045/BL3E-05045.xml");
//		System.out.println(str);
//		String token=TokenProxy.accessToken();
//		String api_ticket=Util.httpsGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+token+"&type=wx_card");
//		System.out.println(api_ticket);
//		sendMobile("15901249589");
//		String sr=sendTemplateMsg("cardid","pwd","oaJdSs6TQtDT1HVmdS3RjCgWcbus","213","12321","aiJ9Pg8ivRBWoEu7GpHrpqh5WKOldzqvP6-WEj79_6HBC6RiK17Jv7BiLukXRZ9KQsnNmQjz56KY7ikJZ7ORfmoEK8aEkRCBL8jTK3b-aZY");
//		isSubscribe("");
//		System.out.println(sr);
//		Integer d=compare_date("2015-8-29",new Date());
		//String token,String openId,String title,String description,String myurl,String picUrl
		sendNewMsg("K0-TyI2N69e1viotZukIi6Z1NqoEAHWFwR5aUWtue3_oUsKRY6FGyfls7tFOvKTjenGWeDH5KG9Ps_CDrIv9vni3wBqwxust8glX99czI-g","oaJdSsxUAPlxxd7YnbMnlVKpJ4qw","读伴儿图书馆使用小贴士","恭喜您成功领取读伴儿图书馆登录信息，想知道如何开启读伴儿图书馆的体验之旅吗？读伴儿特别为大家准备的如下小贴士","http://mp.weixin.qq.com/s?__biz=MzAwODUzNDM3Ng==&mid=209635811&idx=1&sn=7d3630b22ac332e12d994871607abc99#rd","http://weixin.readingmate.com.cn/weChat/images/weixin/slt.jpg");
	}


}
