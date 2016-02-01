package com.tiangong.web.action.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sword.wechat4j.WechatSupport;
import org.sword.wechat4j.event.MsgType;
import org.sword.wechat4j.token.TokenProxy;

import com.tiangong.bean.RmActivityCardList;
import com.tiangong.bean.RmActivityCardRecord;
import com.tiangong.bean.RmActivityUser;
import com.tiangong.comm.Configer;
import com.tiangong.comm.SpringUtil;
import com.tiangong.comm.Util;
import com.tiangong.service.system.imp.DictionaryServiceImp;
import com.tiangong.service.wechat.imp.WechatServiceImp;
import com.tiangong.util.Constants;
import common.Logger;

/**
 * 微信服务桩
 * @author zhao_jinggui
 */
public class WeChatService extends WechatSupport {
	
	private static Logger logger = Logger.getLogger(WeChatService.class);
	
	public int dcount=0;
	
	public int scount=0;
	public WeChatService(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 文本消息
	 */
	@Override
	protected void onText() {
		String content = super.wechatRequest.getContent().trim();
		if(content.equals("test"))
		{
			responseText("您好，感谢关注读伴儿图书馆！感恩教师活动火热进行中，点击底端菜单“全员惠动”，马上加入！如果您想进一步了解读伴儿图书馆，可以通过如下方式来获取信息。\n1.回复“读伴儿”，马上获取读伴儿图书馆介绍\n2.回复“学生”，马上获取学生用户操作指南\n3.回复“个人”，马上获取个人用户操作指南\n4.回复“教师”，马上获取老师用户操作指南\n5.如有任何问题，欢迎致电全国客服电话400-700-9961");
			
		}else if(content.equals("读伴儿"))
		{
			responseNew("读伴儿图书馆介绍", "读伴儿图书馆是一个在线有声图书馆，它整合了数千本英文原版读物，和斯坦福顶尖的口语测评技术。不但可以为学生提供海量多体裁、跨学科的阅读内容，还可以帮助学生在阅读过程中实现实时的纠音，创造浸入式阅读环境。", "http://weixin.readingmate.com.cn/weChat/images/weixin/slt2.jpg", "http://mp.weixin.qq.com/s?__biz=MzAwODUzNDM3Ng==&mid=210396896&idx=1&sn=422cc7bd98529c7fadaf0fb3a7898ee5#rd");
			
		}else if(content.equals("学生"))
		{
			responseNew("读伴儿图书馆学生使用指南", "读伴儿非常荣幸地邀请您成为读伴儿图书馆的体验用户，如您在使用过程中，有任何需要帮助的地方，欢迎拨打400-700-9961或直接通过读伴儿图书馆首页导航栏-指南，获取帮助信息。", "http://weixin.readingmate.com.cn/weChat/images/weixin/slt2.jpg", "http://mp.weixin.qq.com/s?__biz=MzAwODUzNDM3Ng==&mid=210158996&idx=1&sn=9e57e883c6bce71a5d9b4c319539c2dd#rd");
		}else if(content.equals("个人"))
		{
			responseNew("读伴儿图书馆个人用户使用指南", "欢迎开启读伴儿图书馆的原版英文阅读之旅，如您在使用过程中，有任何需要帮助的地方，欢迎拨打400-700-9961或直接通过读伴儿图书馆首页导航栏-指南，获取帮助信息。", "http://weixin.readingmate.com.cn/weChat/images/weixin/slt2.jpg", "http://mp.weixin.qq.com/s?__biz=MzAwODUzNDM3Ng==&mid=210299029&idx=1&sn=1f6a2289e403ecd2062947d79e226b50#rd");
		}else if(content.equals("教师"))
		{
			responseNew("读伴儿图书馆老师使用指南", "读伴儿非常荣幸地邀请您成为读伴儿图书馆的体验用户，如您在使用过程中，有任何需要帮助的地方，欢迎拨打400-700-9961或直接通过读伴儿图书馆首页导航栏-指南，获取帮助信息。", "http://weixin.readingmate.com.cn/weChat/images/weixin/slt2.jpg", "http://mp.weixin.qq.com/s?__biz=MzAwODUzNDM3Ng==&mid=210295587&idx=1&sn=ed23415b1dbce41eb49c0edfe0710fef#rd");
		}else 
		{
			responseText("您好，感谢关注读伴儿！读伴儿是一个汇集海量英文原版图书的有声图书馆，通过谷歌浏览器输入\nwww.readingmate.com.cn, 就可以开启使用了。如有任何问题，欢迎致电全国客服电话400-700-9961。");			
		}
//		String msgId = wechatRequest.getMsgId();
//		logger.info(content);
		//文本测试
//		if(content.equals("1")){
			//responseText("你好，hello world!<a href=\"http://www.baidu.com\">选择好友</a>");
//			responseText("请读第一句：\nHi,Tom,Nice to meet you!");
			//System.out.println(wechatRequest.getFromUserName());
			//responseText("oaJdSsxUAPlxxd7YnbMnlVKpJ4qw","hello.....");
			//responseText("gh_5b0e95f5569a","hello world");
			
//			CustomerMsg customerMsg = new CustomerMsg("oaJdSs38h0uYLJQSH35CqFZfmWG4");
////
////			//发送文本
//			customerMsg.sendText("9999999222"); 
			
//			String token=TokenProxy.accessToken();
//			String media_id=Util.getMedidByCodeImage(token, "1234");
//			responseImage(media_id);
//			File file=new File("D:\\voiceTest\\test.mp3");
//			String media_id=Util.uploadMedia(file,token,"voice");
//			responseVoice(media_id);
//		}
//		else if(content.equals("2")){
//			responseNew("读伴精华", "快乐分享", "https://www.readingmate.com.cn/resource/img/riseabout3-imga1.jpg", 
//					"http://www.chengn.com");
//			responseNew(title, description, picUrl, url);
//			
//			ArticleResponse item = new ArticleResponse();
//			item.setTitle(title);
//			item.setDescription(description);
//			item.setUrl(url);
//			item.setPicUrl(picUrl);
//			responseNews(item);
//			
//			List<ArticleResponse> items = new ArrayList<ArticleResponse>();
//			items.add(item);
//			responseNews(items);
//		}else if(content.equals("3"))
//		{
//			String str=Util.getRandomString(5);
//			String openId=wechatRequest.getFromUserName();
//			System.out.println(".............!!!!"+openId);
//			List<user> list =new ArrayList();
//			user u1=new user();
//			u1.setOpenId(openId);
//			list.add(u1);
//			Map<String,List<user>> map =new HashMap<String,List<user>>();
//			map.put(str, list);
//			WeChatService.getSession().getServletContext().setAttribute("pkCode", map);
//			String contents="天王盖地虎，宝塔镇蛇妖。PK暗号："+str+"您的朋友回复本公众号"+str+",你们就可以开始PK啦...";
//			responseText(contents);
//		}else if(content.equals("4"))
//		{
//			String contents="天王盖地虎，宝塔镇蛇妖,请你接招...";
//			responseServiceText(contents);
//			try {
//				String result=Util.loadText("http://s3.amazonaws.com/speech-content-test/Library/G03001/G03001.xml");
//				responseText(result);
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			String token=TokenProxy.accessToken();
//			System.out.println(token);
//			Util.getCodeImage(token, "wxhd");
//			WechatServiceImp wechatService=(WechatServiceImp) SpringUtil.getObject("wechatService");
//			System.out.println(wechatService);
//			List list =wechatService.findActivityUser("oaJdSs6TQtDT1HVmdS3RjCgWcbus");
//			responseText("success");
//		}
//		else{
			
//			Map<String,List<user>> map =(Map<String,List<user>>) myrequest.getSession().getServletContext().getAttribute("pkCode");
//			if(map!=null)
//			{
//				List<user> list =map.get(content);
//				//判断是否为Pk
//				if(list!=null && list.size()>0)
//				{
//					//将被挑战这放入List集合
//					user u1=new user();
//					u1.setOpenId(wechatRequest.getFromUserName());
//					list.add(u1);
//					myrequest.getSession().getServletContext().setAttribute("pkCode", map);
//					
//					//给被挑战者发提示
//					user u=list.get(0);
//					CustomerMsg customerMsg = new CustomerMsg(wechatRequest.getFromUserName());
//					customerMsg.sendText("yi cheng gong jie shou tiao zhan!");
//					
//					//给挑战者发消息提示已配对
//					CustomerMsg customerMsg1 = new CustomerMsg(u.getOpenId());
//					customerMsg1.sendText("he hao you "+wechatRequest.getFromUserName()+"pei dui cheng gong!");
//					customerMsg1.sendText("Say:Hello,Nice to meet you!");
//					
//					//给被挑战者发提示
//					responseText("Say:Hello,Nice to meet you!");
//					
//					//set语音阅读序号
//					myrequest.getSession().getServletContext().setAttribute(u.getOpenId(), 0);
//					myrequest.getSession().getServletContext().setAttribute(wechatRequest.getFromUserName(), 0);
//				}
//			}else{
//			responseText("你好，你的输入为 " + content + "\n"
//					+ "请按照如下操作输入:\n"
//					+ "1 文本\n"
//					+ "2 图文\n");
//				
//			}
//		}
	}
	/**
	 * 图片消息
	 */
	@Override
	protected void onImage() {
//		String picUrl = wechatRequest.getPicUrl();
//		String MediaId = wechatRequest.getMediaId();
//		String MsgId = wechatRequest.getMsgId();
//		
//		String result = "图片消息picUrl:" + picUrl + ", MediaId:" + MediaId + ", MsgId:" + MsgId;
//		logger.info(result);
		//responseText(result);
		//responseImage(mediaId);
//		String str=Util.httpsGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx74c099e4b07c01fa&secret=1f67a01716b1d33130e2a2402cfddd25");
//		String accessToken=Util.getValueByKey(str, "access_token");
//		WechatUtil.downloadFile(accessToken, MediaId, "D:\\voiceTest\\t23.jpg");
//		responseText("success");
	}

	/**
	 * 语音消息
	 */
	@Override
	protected void onVoice() {
		responseText("您好，感谢关注读伴儿！读伴儿是一个汇集海量英文原版图书的有声图书馆，通过谷歌浏览器输入\nwww.readingmate.com.cn, 就可以开启使用了。如有任何问题，欢迎致电全国客服电话400-700-9961。");
//		String Format = wechatRequest.getFormat();
//		String MediaId = wechatRequest.getMediaId();//视频消息媒体id，可以调用多媒体文件下载接口拉取数据
//		String MsgId = wechatRequest.getMsgId();
//		
//		String result = "语音消息Format:" + Format + ", MediaId:" + MediaId + ", MsgId:" + MsgId;
//		logger.info(result);
//		responseText(result);	
		//responseVoice(MediaId);
		//下载语音
//		String accessToken=TokenProxy.accessToken();
//		InputStream input=WechatUtil.downloadFile(accessToken, MediaId, "D:\\voiceTest\\read.wav");
//		String myresult;
//		try {
//			myresult = WechatUtil.callBackScore(input);
//			responseText(myresult);	
//			
//			//String score=Util.getValueByKey(myresult, "score");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		logger.info("语音结果："+bytes.toString());
//			try {
//				Integer str=(Integer) myrequest.getSession().getServletContext().getAttribute(wechatRequest.getFromUserName());
//				if(str!=null)
//				{
//					int index=str+1;
//					if(index==3)
//					{
//						responseText("您已经读完，你的总分为：0 ");
//						
//					}else{
//						String voice=getVoice(index);
//						String myresult =WechatUtil.callBackScore(input);
//						String score=Util.getValueByKey(myresult, "score");
//						String str2="您的得分 : "+score+" \n本句还需要加油，请读下一句：\n"+voice;
//						responseText(str2);
//						myrequest.getSession().getServletContext().setAttribute(wechatRequest.getFromUserName(), index);
//					}
//				}else{
//					responseText("不是PK...");
//				}
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//回复音乐消息
//		MusicResponse music = new MusicResponse();
//		music.setTitle(title);
//		music.setDescription(description);
//		music.setMusicURL(musicURL);
//		music.setHQMusicUrl(hQMusicUrl);
//		music.setThumbMediaId(thumbMediaId);
//		responseMusic(music);
//		
//		responseMusic(title, description, musicURL, hQMusicUrl, thumbMediaId);
	}

	/**
	 * 视频消息
	 */
	@Override
	protected void onVideo() {
//		String ThumbMediaId = wechatRequest.getThumbMediaId();
//		String MediaId = wechatRequest.getMediaId();//语音消息媒体id，可以调用多媒体文件下载接口拉取数据
//		String MsgId = wechatRequest.getMsgId();
//		
//		String result = "视频消息ThumbMediaId:" + ThumbMediaId + ", MediaId:" + MediaId + ", MsgId:" + MsgId;
//		logger.info(result);
//		responseText(result);
		
		//回复视频消息
//		VideoResponse video = new VideoResponse();
//		video.setTitle(title);
//		video.setDescription(description);
//		video.setMediaId(mediaId);
//		responseVideo(video);
//		
//		responseVideo(mediaId, title, description);
	}
	
	/**
	 * 地理位置消息
	 */
	@Override
	protected void onLocation() {
//		String Location_X = wechatRequest.getLocation_X();
//		String Location_Y = wechatRequest.getLocation_Y();
//		String Scale = wechatRequest.getScale();
//		String Label = wechatRequest.getLabel();
//		String MsgId = wechatRequest.getMsgId();
//		
//		String result = "地理位置消息Location_X:" + Location_X + ", Location_Y:" + Location_Y + 
//				", Scale:" + Scale + ", Label:" + Label + 
//				", MsgId:" + MsgId;
//		logger.info(result);
//		responseText(result);	
		
	}
	/**
	 * 链接消息
	 */
	@Override
	protected void onLink() {
//		String Title = wechatRequest.getTitle();
//		String Description = wechatRequest.getDescription();
//		String Url = wechatRequest.getUrl();
//		String MsgId = wechatRequest.getMsgId();
//		
//		String result = "链接消息Title:" + Title + ", Description:" + Description + 
//				", Url:" + Url + 
//				", MsgId:" + MsgId;
//		logger.info(result);
//		responseText(result);	
	}
	
	
	/**
	 * 未知消息类型，错误处理
	 */
	@Override
	protected void onUnknown() {
//		String msgType = wechatRequest.getMsgType();
//		
//		String result = "未知消息msgType:" + msgType;
//		logger.info(result);
//		responseText(result);

	}

	/**
	 * 扫描二维码事件
	 */
	@Override
	protected void scan() {
		
	 synchronized(this){
			
		String FromUserName = wechatRequest.getFromUserName();
//		String Ticket = wechatRequest.getTicket();
		String key =wechatRequest.getEventKey();
		System.out.println("key:----"+key);
		if(key.equals("wxhd"))
		{
			scount++;
			if(scount==1)
			{
				
			WechatServiceImp wechatService=(WechatServiceImp)SpringUtil.getObject("wechatService");
			DictionaryServiceImp dictService=(DictionaryServiceImp) SpringUtil.getObject("dictService");
			
			String deadline=dictService.getDictValue(Configer.CARD_DEADLINE);
			Integer endflag=Util.compare_date(deadline, new Date());
			if(endflag==-1 || endflag==0)
			{
				scount=0;
				//很遗憾，读伴儿免费领取活动结束啦!
				responseText("您好，感谢关注读伴儿！读伴儿是一个汇集海量英文原版图书的有声图书馆，通过谷歌浏览器输入\nwww.readingmate.com.cn, 就可以开启使用了。如有任何问题，欢迎致电全国客服电话400-700-9961。\n如果您还没有参与教师节活动，点击屏幕底端菜单”全员惠动“，马上获取活动信息吧。");
			}else{
				//判断是否已报名
				List list =wechatService.findActivityUser(FromUserName);
				List list2=wechatService.findCardRecordList(FromUserName);
				if(list.size()>0 && list2.size()==0)
				{
					
					//判断已领卡的数量
					Integer count=wechatService.getCardCount();
					String quota=dictService.getDictValue(Configer.CARD_QUOTA);
					
					if(count>=Integer.parseInt(quota))
					{
						scount=0;
						//很遗憾，读伴儿卡已经全被领啦!
						responseText("您好，感谢关注读伴儿！读伴儿是一个汇集海量英文原版图书的有声图书馆，通过谷歌浏览器输入\nwww.readingmate.com.cn, 就可以开启使用了。如有任何问题，欢迎致电全国客服电话400-700-9961。\n如果您还没有参与教师节活动，点击屏幕底端菜单”全员惠动“，马上获取活动信息吧。");
						
					}else{
						
						RmActivityUser activityuser=(RmActivityUser) list.get(0);
						RmActivityCardList card=wechatService.getCard(Configer.ACTIVITYID);
						//RmActivity activity=wechatService.getActivity(Configer.ACTIVITYID);
						String frist=dictService.getDictValue(Configer.CARD_FRIST);
						String level=dictService.getDictValue(Configer.CARD_LEVEL);
						String remark=dictService.getDictValue(Configer.CARD_REMARK);
						String detail=dictService.getDictValue(Configer.CARD_DETAIL);
						String token=TokenProxy.accessToken();
						
			//			String flag=Util.sendImageMsg(token, apply.getWxOpenId(),card.getCardId(),card.getPassword());
			//			String flag=Util.sendTextMsg(token, apply.getWxOpenId(), "恭喜您成功获得一张读伴卡，ID："+card.getCardId()+",密码："+card.getPassword()+",您可以凭此卡去读伴儿官网网站进行注册登录!");
						String flag=Util.sendTemplateMsg(card.getCardId(), card.getPassword(), FromUserName,level, Util.toDate(card.getUsableBefore(), "yyyy-MM-dd"),detail,frist,remark,token);
						if(flag.equals("Y"))
						{
							card.setSendStatus("1");//已使用
							wechatService.update(card);
						}
						RmActivityCardRecord  record=new RmActivityCardRecord();
						record.setAddDate(new Date());
						record.setCardNo(card.getCardId());
						record.setMobile(activityuser.getMobile());
						record.setName(activityuser.getName());
						record.setWxOpenid(FromUserName);
						if(flag.equals("Y"))
						{
							record.setState("Y");
						}else{
							record.setState(flag);
						}
						wechatService.save(record);
						
						//发送图文消息：读伴儿小贴士
						//responseNew("读伴儿图书馆使用指南", "恭喜您成功领取读伴儿图书馆登录信息，想知道如何开启读伴儿图书馆的体验之旅吗？读伴儿特别为大家准备的如下小贴士", "http://weixin.readingmate.com.cn/weChat/images/weixin/slt.jpg", "http://mp.weixin.qq.com/s?__biz=MzAwODUzNDM3Ng==&mid=209635811&idx=1&sn=7d3630b22ac332e12d994871607abc99#rd");
						
						responseText("恭喜成功重领读伴儿卡，赶快登录读伴儿图书馆，开启阅读之旅吧！");
					}
				}else{
					scount=0;
					responseText("您好，感谢关注读伴儿！读伴儿是一个汇集海量英文原版图书的有声图书馆，通过谷歌浏览器输入\nwww.readingmate.com.cn, 就可以开启使用了。如有任何问题，欢迎致电全国客服电话400-700-9961。\n如果您还没有参与教师节活动，点击屏幕底端菜单”全员惠动“，马上获取活动信息吧。");
				}
			}
		
		 }
		}
	 }
//		
//		String result = "扫描二维码事件FromUserName:" + FromUserName + ", Ticket:" + Ticket+",Key:"+key;
//		logger.info(result);
//		responseText("");
	}

	/**
	 * 订阅事件
	 */
	@Override
	protected void subscribe() {
		
		//防止重复发生
		synchronized(this){
			
		dcount++;
		System.out.println("订阅事件Count:++++"+dcount);
		if(dcount==1)
		{
		
		String FromUserName = wechatRequest.getFromUserName();
		//用户未关注时扫描二维码事件,会多一个EventKey和Ticket节点
//		String Ticket = wechatRequest.getTicket();
//		String key=wechatRequest.getEventKey();
//		System.out.println("第一次扫描：Ticket:"+Ticket+" Key:"+key);

//		String result = "订阅事件FromUserName:" + FromUserName;
		//result = "扫描带场景值二维码事件FromUserName:" + FromUserName + ", Ticket:" + Ticket+" ,Key:"+key;
		
//		if(key.equals("qrscene_wxhd")){
		
		//用户关注时判断是否参加报名活动，如果已参加则发送读伴儿卡模板消息
		WechatServiceImp wechatService=(WechatServiceImp)SpringUtil.getObject("wechatService");
		DictionaryServiceImp dictService=(DictionaryServiceImp) SpringUtil.getObject("dictService");
		
		String deadline=dictService.getDictValue(Configer.CARD_DEADLINE);
		Integer endflag=Util.compare_date(deadline, new Date());
		if(endflag==-1 || endflag==0)
		{
			dcount=0;
			//很遗憾，读伴儿免费领取活动结束啦!
			responseText(Constants.RESPONSE_TEXT);
		}else{
			//判断是否已报名
			List list =wechatService.findActivityUser(FromUserName);
			List list2=wechatService.findCardRecordList(FromUserName);
			if(list.size()>0 && list2.size()==0)
			{
				//判断已领卡的数量
				Integer count=wechatService.getCardCount();
				String quota=dictService.getDictValue(Configer.CARD_QUOTA);
				
				if(count>=Integer.parseInt(quota))
				{
					dcount=0;
					//很遗憾，读伴儿卡已经全被领啦!
					responseText(Constants.RESPONSE_TEXT);
					
				}else{
					
					RmActivityUser activityuser=(RmActivityUser) list.get(0);
					RmActivityCardList card=wechatService.getCard(Configer.ACTIVITYID);
					//RmActivity activity=wechatService.getActivity(Configer.ACTIVITYID);
					String frist=dictService.getDictValue(Configer.CARD_FRIST);
					String level=dictService.getDictValue(Configer.CARD_LEVEL);
					String remark=dictService.getDictValue(Configer.CARD_REMARK);
					String detail=dictService.getDictValue(Configer.CARD_DETAIL);
					String token=TokenProxy.accessToken();
					
		//			String flag=Util.sendImageMsg(token, apply.getWxOpenId(),card.getCardId(),card.getPassword());
		//			String flag=Util.sendTextMsg(token, apply.getWxOpenId(), "恭喜您成功获得一张读伴卡，ID："+card.getCardId()+",密码："+card.getPassword()+",您可以凭此卡去读伴儿官网网站进行注册登录!");
					String flag=Util.sendTemplateMsg(card.getCardId(), card.getPassword(), FromUserName,level, Util.toDate(card.getUsableBefore(), "yyyy-MM-dd"),detail,frist,remark,token);
					if(flag.equals("Y"))
					{
						card.setSendStatus("1");//已使用
						wechatService.update(card);
					}
					RmActivityCardRecord  record=new RmActivityCardRecord();
					record.setAddDate(new Date());
					record.setCardNo(card.getCardId());
					record.setMobile(activityuser.getMobile());
					record.setName(activityuser.getName());
					record.setWxOpenid(FromUserName);
					if(flag.equals("Y"))
					{
						record.setState("Y");
					}else{
						record.setState(flag);
					}
					wechatService.save(record);
					//发送图文消息：读伴儿小贴士
					//responseNew("读伴儿图书馆使用指南", "恭喜您成功领取读伴儿图书馆登录信息，想知道如何开启读伴儿图书馆的体验之旅吗？读伴儿特别为大家准备的如下小贴士", "http://weixin.readingmate.com.cn/weChat/images/weixin/slt.jpg", "http://mp.weixin.qq.com/s?__biz=MzAwODUzNDM3Ng==&mid=209635811&idx=1&sn=7d3630b22ac332e12d994871607abc99#rd");
					
				}
			}else{
				dcount=0;
				responseText(Constants.RESPONSE_TEXT);
			}
		}
		
//		}else{
//			if(Ticket!=null)
//			{
//				responseNew("感恩教师节|数千本英文原版图书免费领！", "在教师节到来之际，读伴儿为英语老师定制了一款特别的礼物-1间汇集海量英文原版读物的有声图书馆。", "http://weixin.readingmate.com.cn/weChat/images/weixin/slt.jpg", 
//						"http://mp.weixin.qq.com/s?__biz=MzAwODUzNDM3Ng==&mid=209123692&idx=1&sn=7c21f1d2b3695e479fb482597de54a75#rd");
//			responseText("您好，感谢关注读伴儿！读伴儿是一个汇集海量英文原版图书的有声图书馆，通过谷歌浏览器输入\nwww.readingmate.com.cn, 就可以开启使用了。如有任何问题，欢迎致电全国客服电话400-700-9961。\n如果您还没有参与教师节活动，点击屏幕底端菜单”全员惠动“，马上获取活动信息吧。");
//			}
			
//		}
//		logger.info(result);
//		responseText(result);
	  }
	 }
	}	
	/**
	 * 取消订阅事件
	 */
	@Override
	protected void unSubscribe() {
//		String FromUserName = wechatRequest.getFromUserName();
//		String result = "取消订阅事件FromUserName:" + FromUserName;
//		logger.info(result);
//		responseText(result);
	}
	
	/**
	 * 点击菜单跳转链接时的事件推送
	 */
	@Override
	protected void view() {
//		String link = super.wechatRequest.getEventKey();
//		logger.info("点击菜单跳转链接时的事件推送link:" + link);
//		responseText("点击菜单跳转链接时的事件推送link:" + link);
	}

	/**
	 * 自定义菜单事件
	 */
	@Override
	protected void click() {
//		String key = super.wechatRequest.getEventKey();
//		logger.info("自定义菜单事件eventKey:" + key);
//		//PK读
//		if(key.equals("dsty_pkd"))
//		{
//			String str=Util.getRandomString(5);
//			String openId=wechatRequest.getFromUserName();
//			System.out.println(".............!!!!"+openId);
//			List<user> list =new ArrayList();
//			user u1=new user();
//			u1.setOpenId(openId);
//			list.add(u1);
//			Map<String,List<user>> map =new HashMap<String,List<user>>();
//			map.put(str, list);
//			myrequest.getSession().getServletContext().setAttribute("pkCode", map);
//			String contents="天王盖地虎，宝塔镇蛇妖。PK暗号："+str+"您的朋友回复本公众号"+str+",你们就可以开始PK啦...";
//			responseText(contents);
			
//		}else if(key.equals("dsty_pkd")){
//			
//		}else{			
			responseText("自定义菜单开发中...");
//		}
	}
	
	/**
	 * 上报地理位置事件
	 */
	@Override
	protected void location() {
//		String Latitude = wechatRequest.getLatitude();
//		String Longitude = wechatRequest.getLongitude();
//		String Precision = wechatRequest.getPrecision();
//		String result = "上报地理位置事件Latitude:" + Latitude + ", Longitude:" + Longitude + ", Precision:" + Precision;
//		logger.info(result);
//		responseText(result);
	}
	
	/**
	 * 模板消息发送成功推送事件
	 */
	@Override
	protected void templateMsgCallback() {
//		String MsgID = wechatRequest.getMsgId();
//		String Status = wechatRequest.getStatus();
//		String result = "模板消息发送成功推送事件MsgID:" + MsgID + ", Status:" + Status;
//		logger.info(result);
	}
	/**
	 * 弹出地理位置选择器的事件
	 */
	@Override
	protected void locationSelect() {
//		String Location_X = wechatRequest.getSendLocationInfo().getLocation_X();
//		String Location_Y = wechatRequest.getSendLocationInfo().getLocation_Y();
//		String Scale = wechatRequest.getSendLocationInfo().getScale();
//		String Label = wechatRequest.getSendLocationInfo().getLabel();
//		String Poiname = wechatRequest.getSendLocationInfo().getPoiname();
//		String result = "弹出地理位置选择器的事件Location_X:" + Location_X + 
//				", Location_Y:" + Location_Y+ 
//				", Scale:" + Scale+ 
//				", Label:" + Label+ 
//				", Poiname:" + Poiname;
//		logger.info(result);
//		responseText(result);
	}
	/**
	 * 弹出拍照或者相册发图的事件
	 */
	@Override
	protected void picPhotoOrAlbum() {
//		String Count = wechatRequest.getSendPicsInfo().getCount();
//		String PicMd5Sum = "";
//		if(StringUtils.isNotBlank(Count) && !Count.equals("0")){
//			PicMd5Sum = wechatRequest.getSendPicsInfo().getItem().get(0).getPicMd5Sum();
//		}
//		String result = "弹出系统拍照发图的事件Count:" + Count + ", PicMd5Sum:" + PicMd5Sum;
//		logger.info(result);
//		responseText(result);
	}
	/**
	 * 弹出系统拍照发图的事件
	 */
	@Override
	protected void picSysPhoto() {
//		String Count = wechatRequest.getSendPicsInfo().getCount();
//		String result = "弹出系统拍照发图的事件Count:" + Count ;
//		logger.info(result);
//		responseText(result);
	}
	/**
	 * 弹出微信相册发图器的事件推送
	 */
	@Override
	protected void picWeixin() {
//		String Count = wechatRequest.getSendPicsInfo().getCount();
//		String result = "弹出系统拍照发图的事件Count:" + Count ;
//		logger.info(result);
//		responseText(result);
	}
	/**
	 * 扫码推事件
	 * 
	 */
	@Override
	protected void scanCodePush() {
		String ScanType = wechatRequest.getScanCodeInfo().getScanType();
		String ScanResult = wechatRequest.getScanCodeInfo().getScanResult();
		String result = "扫码推事件ScanType:" + ScanType + ", ScanResult:" + ScanResult;
		logger.info(result);
		responseText(result);
	}
	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件
	 */
	@Override
	protected void scanCodeWaitMsg() {
		String ScanType = wechatRequest.getScanCodeInfo().getScanType();
		String ScanResult = wechatRequest.getScanCodeInfo().getScanResult();
		String result = "扫码推事件ScanType:" + ScanType + ", ScanResult:" + ScanResult;
		logger.info(result);
		responseText(result);
	}

	@Override
	protected void kfCloseSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void kfCreateSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void kfSwitchSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onShortVideo() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 回复文本消息
	 * @param content 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	 */
	public void responseText(String FrameUserName,String content){
		wechatResponse.setToUserName(FrameUserName);
		wechatResponse.setFromUserName(wechatRequest.getToUserName());
		wechatResponse.setCreateTime(wechatRequest.getCreateTime());
		wechatResponse.setMsgType(MsgType.text.name());
		wechatResponse.setContent(content);
	}
	
	public void responseServiceText(String content){
		wechatResponse.setToUserName(wechatRequest.getFromUserName());
		wechatResponse.setFromUserName(wechatRequest.getToUserName());
		wechatResponse.setCreateTime(wechatRequest.getCreateTime());
		wechatResponse.setMsgType(MsgType.transfer_customer_service.name());
		wechatResponse.setContent(content);
	}
	
	public String getVoice(int index)
	{
		List<String> voiceList =new ArrayList<String>();
		voiceList.add("Hello,Nice to meet you!");
		voiceList.add("We often listen to music in bed!");
		voiceList.add("Can I help you?");
		String str=voiceList.get(index);
		return str;
	}
	
	public String getOpenId()
	{
		return wechatRequest.getFromUserName();
	}

}
