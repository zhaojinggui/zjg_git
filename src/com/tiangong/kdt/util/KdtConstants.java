package com.tiangong.kdt.util;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.tiangong.bean.RmActivityYzOrderRecord;
import com.tiangong.comm.MD5Util;

public class KdtConstants {
	
	/** 有赞的应用IDapp_id */
	public static final String APP_ID = "b35b5fe2cfb3472e44";
	
	/** 有赞的应用密钥app_secret */
	public static final String APP_SECRET = "f38b38e78362d859b0976ebae28d1e59";
	
	/** 当前时间的前3分钟时间差 分钟数 */
	public static final int TIMER_MINUTE_POOR = 3;
	
	/** 短信通道URL */
	public static final String MES_URL = "http://sdk2.entinfo.cn:8061/mdsmssend.ashx";
	
	/** 短信通道ID */
	public static final String MES_CHANNEL_ID = "SDK-BBX-010-22122";
	
	/** 短信通道密码 */
	public static final String MES_CHANNEL_SECRET = MD5Util.MD5Encode(KdtConstants.MES_CHANNEL_ID + "3@1ced@4","").toUpperCase();
	
	/** -------------订单状态--------------*/
	/** 没有创建支付交易 */
	public static final String TRADE_NO_CREATE_PAY = "TRADE_NO_CREATE_PAY";
	/** 等待买家付款 */
	public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
	/** 等待卖家发货，即：买家已付款 */
	public static final String WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
	/** 等待买家确认收货，即：卖家已发货 */
	public static final String WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";
	/** 买家已签收 */
	public static final String TRADE_BUYER_SIGNED = "TRADE_BUYER_SIGNED";
	/** 付款以后用户退款成功，交易自动关闭 */
	public static final String TRADE_CLOSED = "TRADE_CLOSED";
	/** 包含：WAIT_BUYER_PAY、TRADE_NO_CREATE_PAY */
	public static final String ALL_WAIT_PAY = "ALL_WAIT_PAY";
	/** 所有关闭订单 */
	public static final String ALL_CLOSED = "ALL_CLOSED";
	
	/** 获取订单的信息key */
	/** 最外层key-- response*/
	public static final String ORDER_RESPONSE = "response";  
	/** 订单集合key-- trades*/
	public static final String ORDER_TRADES = "trades";  
	/** 购买数量 -- num*/
	public static final String ORDER_NUM = "num";  
	/** 订单编号 -- tid*/
	public static final String ORDER_TID = "tid";  
	/** 微信用户ID  -- weixin_user_id*/
	public static final String ORDER_WEIXIN_USER_ID = "weixin_user_id";  
	/** 订单详情-- orders */
	public static final String ORDER_ORDERS = "orders";  
	/** 购买者留言信息-- buyer_messages */
	public static final String ORDER_BUYER_MESSAGES = "buyer_messages";  
	/** 购买者手机号-- content */
	public static final String ORDER_CONTENT = "content"; 
	/** 购买时间-- pay_time */
	public static final String ORDER_PAY_TIME = "pay_time"; 
	
	
	
	
	/**
	 * 发送短信到用户
	 * @method: sendMsg
	 * @Description: 发送短信到用户
	 * @param msg 短信内容
	 * @param phone 手机号
	 * @author: lijianjun
	 * @date 2015年10月22日 下午3:53:24
	 */
	public static void sendMsg(String msg,String phone){
		HttpClient client = new HttpClient();  
        PostMethod post = new PostMethod(MES_URL);
        post.addRequestHeader("Content-Type",  
                "application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码  
        NameValuePair[] data = { new NameValuePair("sn", MES_CHANNEL_ID), // 注册的用户名  
                new NameValuePair("pwd", MES_CHANNEL_SECRET), // 注册成功后,登录网站使用的密钥  
                new NameValuePair("mobile", phone), // 手机号码  
                new NameValuePair("Content", msg) };//设置短信内容
        post.setRequestBody(data);  
        try {
			client.executeMethod(post);  
			Header[] headers = post.getResponseHeaders();  
			int statusCode = post.getStatusCode();  
//			System.out.println("statusCode:" + statusCode);  
//			for (Header h : headers) {  
//			    System.out.println(h.toString());  
//			}  
			String result = new String(post.getResponseBodyAsString().getBytes(  
			        "utf-8"));  
//			System.out.println(result);  
			post.releaseConnection();
		} catch (Exception e) {
			System.out.println("---------------------");
			System.out.println("-------有赞发送给 " + phone + "用户失败-------");
			e.printStackTrace();
		} 
	}
	
	
	
	/**
	 * 拼接短信内容
	 * @method: appendMESContent
	 * @Description: TODO
	 * @return
	 * @author: lijianjun
	 * @date 2015年10月22日 下午2:14:25
	 */
	public static String appendMESContent(List<RmActivityYzOrderRecord> orderList){
		StringBuffer msg = new StringBuffer();
		msg.append("测试环境，非真实购买。\n");
		msg.append("恭喜您成功购买读伴儿卡" + orderList.get(0).getNum() + "张，");
		String fh = orderList.size() > 1 ? "；" : "，"; 
		for(RmActivityYzOrderRecord order : orderList){
			msg.append("卡号：" + order.getCardId() + "，");
			msg.append("密码：" + order.getPassword() + fh);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		msg.append("请于" + sdf.format(orderList.get(0).getUsableBefore()) + "前登录www.readingmate.com.cn激活使用，逾期无效。读伴儿使用指南，登录http://dwz.cn/2645c1获取，咨询热线400-700-9961");
		msg.append("【读伴儿图书馆】");
		return msg.toString();
	}
	
	/**
	 * 发送短信验证码到用户
	 * @method: sendMsg
	 * @Description: 发送短信验证码到用户
	 * @param yzm 短信验证码
	 * @param phone 手机号
	 * @author: lijianjun
	 * @date 2015年10月22日 下午3:53:24
	 */
	public static void sendYzmMsg(String yzm,String phone){
		HttpClient client = new HttpClient();  
        PostMethod post = new PostMethod(MES_URL);
        String msgContent = "您的验证码是：" + yzm + "【读伴儿图书馆】";
        post.addRequestHeader("Content-Type",  
                "application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码  
        NameValuePair[] data = { new NameValuePair("sn", MES_CHANNEL_ID), // 注册的用户名  
                new NameValuePair("pwd", MES_CHANNEL_SECRET), // 注册成功后,登录网站使用的密钥  
                new NameValuePair("mobile", phone), // 手机号码  
                new NameValuePair("Content", msgContent) };//设置短信内容
        post.setRequestBody(data);  
        try {
			client.executeMethod(post);  
			Header[] headers = post.getResponseHeaders();  
			int statusCode = post.getStatusCode();  
//			System.out.println("statusCode:" + statusCode);  
//			for (Header h : headers) {  
//			    System.out.println(h.toString());  
//			}  
			String result = new String(post.getResponseBodyAsString().getBytes(  
			        "utf-8"));
//			System.out.println(result);  
			post.releaseConnection();
		} catch (Exception e) {
			System.out.println("---------------------");
			System.out.println("-------有赞发送给 " + phone + "用户失败-------");
			e.printStackTrace();
		} 
	}
}
