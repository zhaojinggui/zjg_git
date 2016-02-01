package com.tiangong.comm;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.UnsupportedCharsetException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.sword.wechat4j.token.TokenProxy;
import com.alibaba.fastjson.JSON;

public class WechatUtil {

	private static final String restfulURL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	
	
	private WechatUtil(){
		 
	}
	/**
	 * 
	 * @param openid 关注者ID
	 * @param template_id 模板ID
	 * @param url 点击消息后跳转的地址
	 * @param topcolor 顶部颜色
	 * @param data json
	 * 里面包含value, color等信息
	 * 例如"User": {"value":"黄先生","color":"#173177"},"Date":{"value":"06月07日 19时24分","color":"#173177"},...传入数据需要与模板消息的占位符相同
	 * json部分可用map,例如maps.put("user", new DTO("value值","颜色"));然后统一转换为json传入
	 * @return 生成json字符串
	 */
	public static String getTemplateMsg(String openid, String template_id, String url, String topcolor, String data){
		try {
			if (openid == null || "".equals(openid)) throw new Exception("openId不能为空");
			if (template_id == null || "".equals(template_id)) throw new Exception("template_id不能为空");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (url == null || "".equals(url)) url = "";
		if (topcolor == null || "".equals(topcolor)) topcolor = "#FF0000";
		String json = "{\"touser\":\"" + openid +"\",\"template_id\":\"" + template_id + "\",\"url\":\"" + url + "\",\"topcolor\":\"#FF0000\"";
		if (data != null && !"".equals(data)) {
			json += ",\"data\":" + data + "}"; 
		}
		return json;
	}
	/**
	 * 
	 * @param openid 关注者ID
	 * @param template_id 模板ID
	 * @param url 点击模板消息后将要跳转的地址
	 * @param topcolor 顶部颜色
	 * @param data json
	 * 里面包含value, color等信息
	 * 例如"User": {"value":"黄先生","color":"#173177"},"Date":{"value":"06月07日 19时24分","color":"#173177"},...传入数据需要与模板消息的占位符相同
	 * json部分可用map,例如maps.put("user", new DTO("value值","颜色"));然后统一转换为json传入
	 * @return 响应码
	 */
//	public static String sendTemplateMsg(String openid, String template_id, String url, String topcolor, String data){
//		try {
//			String token = TokenProxy.accessToken();
//			String json = getTemplateMsg(openid, template_id, restfulURL, topcolor, data);
//			HttpClient httpClient = new DefaultHttpClient();
//			HttpPost httpPost = new HttpPost(restfulURL + token);
//			StringEntity entity = new StringEntity(json,"utf-8");
//			entity.setContentType("application/x-www-form-urlencoded");
//			httpPost.setEntity(entity);
//			HttpResponse httpResponse = httpClient.execute(httpPost);
//			HttpEntity entity1 = httpResponse.getEntity();
//			return entity1 != null ? EntityUtils.toString(entity1) : null;
//		} catch (UnsupportedCharsetException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	/**
	 * 
	 * @param json 封装好的json数据,此方法适合本地调用
	 * @return 响应码
	 */
//	public static String sendTemplateMsg(String json){
//		try {
//			String token = TokenProxy.accessToken();
//			HttpClient httpClient = new DefaultHttpClient();
//			HttpPost httpPost = new HttpPost(restfulURL + token);
//			StringEntity entity = new StringEntity(json,"utf-8");
//			entity.setContentType("application/x-www-form-urlencoded");
//			httpPost.setEntity(entity);
//			HttpResponse httpResponse = httpClient.execute(httpPost);
//			HttpEntity entity1 = httpResponse.getEntity();
//			return entity1 != null ? EntityUtils.toString(entity1) : null;
//		} catch (UnsupportedCharsetException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
 
	 public static String sendPost(String url, String param) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	        	
	        	 HostnameVerifier hv = new HostnameVerifier() {
	     			public boolean verify(String urlHostName, SSLSession session) {
	     				// TODO Auto-generated method stub
	     		        System.out.println("Warning: URL Host: " + urlHostName + " vs. "
	                         + session.getPeerHost());
	     				return true;
	     			}
	     	    };
	        	
	        	trustAllHttpsCertificates();
	        	HttpsURLConnection.setDefaultHostnameVerifier(hv);

	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            //conn.setRequestProperty("Content-Type", "application/json");
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }    
	 

		
		private static void trustAllHttpsCertificates() throws Exception {
			javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
			javax.net.ssl.TrustManager tm = new miTM();
			trustAllCerts[0] = tm;
			javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
					.getInstance("SSL");
			sc.init(null, trustAllCerts, null);
			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
					.getSocketFactory());
		}

		static class miTM implements javax.net.ssl.TrustManager,
				javax.net.ssl.X509TrustManager {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public boolean isServerTrusted(
					java.security.cert.X509Certificate[] certs) {
				return true;
			}

			public boolean isClientTrusted(
					java.security.cert.X509Certificate[] certs) {
				return true;
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType)
					throws java.security.cert.CertificateException {
				return;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType)
					throws java.security.cert.CertificateException {
				return;
			}
		}
		
		public static String callBackScore(InputStream inputStream) throws Exception
		{
			System.out.println("Score===============");			
			// create connection
//			String url1="https://testss.languametrics.com:58000/scoringservice/recognize/content";
//			String content="{'key':'G03001'}";
//			String difficulty="{'file':'levels.xml','level':'1'}";
//			String param="?model=adult&content="+content+"&difficulty="+difficulty+"&format=wav&encode=byte";
//			String url=url1+param;
			String url = "https://testss.languametrics.com:58000/scoringservice/recognize/content?model=adult&content={\"key\":\"G03001\"}&difficulty={\"file\":\"levels.xml\",\"level\":\"1\"}&format=mp3&encode=byte";
			HostnameVerifier hv = new HostnameVerifier() {
     			public boolean verify(String urlHostName, SSLSession session) {
     				// TODO Auto-generated method stub
     		        System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                         + session.getPeerHost());
     				return true;
     			}
     	    };
        	
        	trustAllHttpsCertificates();
        	HttpsURLConnection.setDefaultHostnameVerifier(hv);
        	
			
			HttpURLConnection connection = (HttpURLConnection)(new URL(url)).openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			
//			FileInputStream inputStream = new FileInputStream(new File(filepath));
//			InputStream stream=new ByteArrayInputStream(bytes);
//			FileInputStream inputStream= (FileInputStream)stream;
			DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
				
		    int aByte = inputStream.read();
		    while (aByte != -1) {
		        dos.write(aByte);
		        aByte = inputStream.read();
		    }
		    dos.flush();
				
		    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		   
		    String inputLine;
		    while ((inputLine = in.readLine()) != null)
		    {
		    	System.out.println(inputLine);
		    	return inputLine;		    	
		    }
		    
		    return null;
		    

		}

		public static byte[] File2byte(String filePath)
		{
			byte[] buffer = null;
			try
			{
				File file = new File(filePath);
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] b = new byte[1024];
				int n;
				while ((n = fis.read(b)) != -1)
				{
					bos.write(b, 0, n);
				}
				fis.close();
				bos.close();
				buffer = bos.toByteArray();
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return buffer;
		}

		public static void byte2File(byte[] buf, String filePath, String fileName)
		{
			BufferedOutputStream bos = null;
			FileOutputStream fos = null;
			File file = null;
			try
			{
				File dir = new File(filePath);
				if (!dir.exists() && dir.isDirectory())
				{
					dir.mkdirs();
				}
				file = new File(filePath + File.separator + fileName);
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos);
				bos.write(buf);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (bos != null)
				{
					try
					{
						bos.close();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				if (fos != null)
				{
					try
					{
						fos.close();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		public static InputStream downloadFile(String token,String mediaid,String filePath)
		{
			String REMOTE_FILE_URL="https://api.weixin.qq.com/cgi-bin/media/get?access_token="+token+"&media_id="+mediaid;
			
			try {
				HttpClient httpclient = new DefaultHttpClient();
	                        //Secure Protocol implementation.
				SSLContext ctx = SSLContext.getInstance("SSL");
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

				HttpGet httpget = new HttpGet(REMOTE_FILE_URL);
				HttpResponse res = httpclient.execute(httpget);
				HttpEntity entity = res.getEntity();
				FileOutputStream out = new FileOutputStream(new File(filePath));
				 if (entity != null) {

					 System.out.println("===========");
			            InputStream instream = entity.getContent();
			            return instream;
//			            int len;
//			            byte[] tmp = new byte[2048];
//			            while ((len = instream.read(tmp)) != -1) {
//			                out.write(tmp, 0, len);
//			            }
				 }
//				 else{
//			            out.write(new byte[]{'{','}' });
//			        }

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println("download, success!!");
			return null;
			
		}
		
		public static String downloadFile(String REMOTE_FILE_URL,String filePath)
		{
			try {
				HttpClient httpclient = new DefaultHttpClient();
	                        //Secure Protocol implementation.
				SSLContext ctx = SSLContext.getInstance("SSL");
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

				HttpGet httpget = new HttpGet(REMOTE_FILE_URL);
				HttpResponse res = httpclient.execute(httpget);
				HttpEntity entity = res.getEntity();
				FileOutputStream out = new FileOutputStream(new File(filePath));
				 if (entity != null) {

					 System.out.println("===========");
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

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println("download, success!!");
			return null;
			
		}
		
		
		
		public static String getAccessToken()
		{
			String str=Util.httpsGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx74c099e4b07c01fa&secret=1f67a01716b1d33130e2a2402cfddd25");
			String accessToken=Util.getValueByKey(str, "access_token");
			return accessToken;
		}
		
		public static void main(String args[])
		{
//			String url="https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=d-Im1NFyvuCGoYpJmOYzq0HgvA4uEJkDqW-yzyPBmaDadblvXYrcTM9hUHFMpabM8iq4cubCx0LyIWEb_kQMCeIdtV3ARSyAPqk5mYrEKhw";
//			String param="?data={type:'news',offset:0,count:10}";
//			String result=sendPost(url,param);
//			System.out.println(result);
			downloadFile("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHG8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2cwallNNlhscTlYU29Jd2haR2FzAAIEZZC4VQMEgDoJAA==","D:\\voiceTest\\t1.jpg");
			
		}
		
		//获取永久图文素材列表
		public static String getNewsMediaID(String access_token, Map map) throws Exception{
			String media_id = null;
			String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" 
					+ access_token;
			JSONObject jo = JSONObject.fromObject(map);
			
			
			HostnameVerifier hv = new HostnameVerifier() {
     			public boolean verify(String urlHostName, SSLSession session) {
     				// TODO Auto-generated method stub
     		        System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                         + session.getPeerHost());
     				return true;
     			}
     	    };
        	trustAllHttpsCertificates();
        	HttpsURLConnection.setDefaultHostnameVerifier(hv);
			
			  SSLContextBuilder builder = new SSLContextBuilder();
			    builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
			            builder.build(),SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			    CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(
			            sslsf).build();
			    
			HttpPost httpPost = new HttpPost(url);
			StringEntity params = new StringEntity(jo.toString(),"UTF-8");
			httpPost.setEntity(params);
			CloseableHttpResponse httpResponse = null;
			try {
				httpResponse = client.execute(httpPost);
				HttpEntity entity = httpResponse.getEntity();
				String jsonString = EntityUtils.toString(entity);
				JSONArray array =JSONArray.fromObject(jsonString);
				System.out.println(array.toString());
//				JSONObject fromObject = JSONObject.fromObject(jsonString);
//				Object media_idObject = fromObject.get("media_id");
//				if (media_idObject != null) {
//					media_id = media_idObject.toString();
//				}
//				System.out.println("获取视频的media_id-返回值： "
//						+ jsonString);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(httpResponse != null){
					httpResponse.close();
				}
				if(client != null){
					client.close();
				}
			}
			return media_id;
		}
}
