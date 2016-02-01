package com.tiangong.kdt.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;

import com.tiangong.bean.RmActivityCardList;
import com.tiangong.bean.RmActivityYzOrderRecord;
import com.tiangong.kdt.api.KdtApiClient;
import com.tiangong.kdt.util.KdtConstants;
import com.tiangong.service.activity.RmActivityYzOrderRecordService;
import com.tiangong.util.Constants;
import com.tiangong.web.action.BaseAction;

/*
 * 这是个例子
 */
public class KDTApiAction extends BaseAction{
	
	@Resource
	private RmActivityYzOrderRecordService rmActivityYzOrderRecordService;

	public void setRmActivityYzOrderRecordService(
			RmActivityYzOrderRecordService rmActivityYzOrderRecordService) {
		this.rmActivityYzOrderRecordService = rmActivityYzOrderRecordService;
	}
	
	/**
	 * 获取所有的订单
	 * @method: getAllOrder
	 * @Description: TODO 获取所有的订单
	 * @author: lijianjun
	 * @date 2015年10月21日 下午4:12:29
	 */
	public void getAllOrder(){
		String method = "kdt.trades.sold.get";
		HashMap<String, String> params = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - KdtConstants.TIMER_MINUTE_POOR);
		params.put("status", KdtConstants.WAIT_BUYER_CONFIRM_GOODS);
		// 交易状态更新的开始时间  当前时间的前30分钟
		params.put("start_update", sdf.format(calendar.getTime()));
		// 交易状态更新的结束时间   当前时间
		params.put("end_update", sdf.format(new Date()));
		
		KdtApiClient kdtApiClient;
		HttpResponse response;
		try {
			kdtApiClient = new KdtApiClient(KdtConstants.APP_ID,KdtConstants.APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}
			JSONObject jsonObj = JSONObject.fromObject(result.toString());
			JSONObject responseJsonObj = JSONObject.fromObject(jsonObj.get(KdtConstants.ORDER_RESPONSE));
			JSONArray tradesJsonArry = JSONArray.fromObject(responseJsonObj.get(KdtConstants.ORDER_TRADES));
			// 需要保存的有赞交易记录集合
			List<RmActivityYzOrderRecord> orderList = new ArrayList<RmActivityYzOrderRecord>();
			if(tradesJsonArry != null && tradesJsonArry.size() > 0){
				JSONObject orderJsonObj = null;
				// 有赞交易记录对象
				RmActivityYzOrderRecord orderRecord = null;
				int num = 0;
				// 库存中的用户卡集合
				List<RmActivityCardList> cardList = null;
				for(Object order : tradesJsonArry){
					orderList.clear();
					orderJsonObj = JSONObject.fromObject(order);
					num = StringUtils.isEmpty(orderJsonObj.get(KdtConstants.ORDER_NUM).toString()) ? 0 : Integer.valueOf(orderJsonObj.get(KdtConstants.ORDER_NUM).toString());
					cardList = rmActivityYzOrderRecordService.findCardListByASN(Constants.ACTIVITY_ID, Constants.SEND_STATUS_0, num);
					if(cardList != null && cardList.size() > 0){
						for(RmActivityCardList card : cardList){
							orderRecord = new RmActivityYzOrderRecord();
							orderRecord.setNum(num);
							orderRecord.setTid(StringUtils.isEmpty(orderJsonObj.get(KdtConstants.ORDER_TID).toString()) ? null : orderJsonObj.get(KdtConstants.ORDER_TID).toString());
							orderRecord.setCardType(Constants.CARD_TYPE_12);
							orderRecord.setSendStatus(Constants.SEND_STATUS_1);
							orderRecord.setPayTime(StringUtils.isEmpty(orderJsonObj.get(KdtConstants.ORDER_PAY_TIME).toString()) ? null : sdf.parse(orderJsonObj.get(KdtConstants.ORDER_PAY_TIME).toString()));
							orderRecord.setSendDate(new Date());
							orderRecord.setWeixinUserId(StringUtils.isEmpty(orderJsonObj.get(KdtConstants.ORDER_WEIXIN_USER_ID).toString()) ? null : Integer.valueOf(orderJsonObj.get(KdtConstants.ORDER_WEIXIN_USER_ID).toString()));
							// 获取购买者信息
							JSONArray orderArr = JSONArray.fromObject(orderJsonObj.get(KdtConstants.ORDER_ORDERS));
							JSONArray buyerMessagesArr = JSONArray.fromObject(JSONObject.fromObject(orderArr.get(0)).get(KdtConstants.ORDER_BUYER_MESSAGES));
							JSONObject mesObj = JSONObject.fromObject(buyerMessagesArr.get(0));
							orderRecord.setPhone(StringUtils.isEmpty(mesObj.get(KdtConstants.ORDER_CONTENT).toString()) ? null : mesObj.get(KdtConstants.ORDER_CONTENT).toString());
							
							orderRecord.setUsableBefore(card.getUsableBefore());
							orderRecord.setCardId(card.getCardId());
							orderRecord.setPassword(card.getPassword());
							orderList.add(orderRecord);
						}
						rmActivityYzOrderRecordService.saveOrderList(orderList,cardList);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("----------------------------");
			System.out.println("-----------有赞商城增加记录有异常了------------");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 测试获取单个商品信息
	 */
	private static void sendGet(){
		String method = "kdt.item.get";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("num_iid", "2651514");
		
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(KdtConstants.APP_ID,KdtConstants.APP_SECRET);
			response = kdtApiClient.get(method, params);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 测试获取添加商品
	 */
	private static void sendPost(){
		String method = "kdt.item.add";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("price", "999.01");
		params.put("title", "测试商品");
		params.put("desc", "这是一个号商铺");
		params.put("is_virtual", "0");
		params.put("post_fee", "10.01");
		params.put("sku_properties", "");
		params.put("sku_quantities", "");
		params.put("sku_prices", "");
		params.put("sku_outer_ids", "");
		String fileKey = "images[]";
		List<String> filePaths = new ArrayList<String>();
		filePaths.add("/Users/xuexiaozhe/Desktop/1.png");
		filePaths.add("/Users/xuexiaozhe/Desktop/2.png");
		
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(KdtConstants.APP_ID,KdtConstants.APP_SECRET);
			response = kdtApiClient.post(method, params, filePaths, fileKey);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
