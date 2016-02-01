package com.tiangong.web.action.wechat;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.sword.wechat4j.token.TokenProxy;

import com.tiangong.bean.RmActivity;
import com.tiangong.bean.RmActivityCardList;
import com.tiangong.bean.RmActivityCardRecord;
import com.tiangong.bean.RmActivityUser;
import com.tiangong.bean.RmUser;
import com.tiangong.bean.RmUserClassDetail;
import com.tiangong.bean.RmUserDetail;
import com.tiangong.bean.RmView;
import com.tiangong.comm.Configer;
import com.tiangong.comm.MoodleUtil;
import com.tiangong.comm.Sign;
import com.tiangong.comm.Util;
import com.tiangong.comm.WeiXinOauth2Token;
import com.tiangong.service.activity.RmUserClassDetailService;
import com.tiangong.service.activity.RmUserDetailService;
import com.tiangong.service.system.imp.DictionaryServiceImp;
import com.tiangong.service.wechat.imp.WechatServiceImp;
import com.tiangong.util.Constants;
import com.tiangong.web.action.BaseAction;

public class WeChatAction extends BaseAction {
	
	private WechatServiceImp wechatService;
	private WeChatService wechatHelper= new WeChatService(this.getHttpServletRequest());
	public RmActivityUser apply;
	private  RmUserDetail rmUserDetail = new RmUserDetail();
	
	@Resource
	private DictionaryServiceImp dictService;
	@Resource
	private RmUserDetailService rmUserDetailService;
	@Resource
	private RmUserClassDetailService rmUserClassDetailService;
	
	
	public RmUserDetail getRmUserDetail() {
		return rmUserDetail;
	}

	public void setRmUserDetail(RmUserDetail rmUserDetail) {
		this.rmUserDetail = rmUserDetail;
	}

	public void setWechatService(WechatServiceImp wechatService) {
		this.wechatService = wechatService;
	}

	public RmActivityUser getApply() {
		return apply;
	}
	
	public void setApply(RmActivityUser apply) {
		this.apply = apply;
	}

	/**
	 * 微信请求入口
	* @Title: index
	* @Description:
	* @author zhao_jinggui
	 */
	public void index()
	{
		
		String result=wechatHelper.execute();
		try {
			this.getHttpServletResponse().getOutputStream().write(result.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* @Title: saveApply
	* @Description:保存报名信息
	* @author zhao_jinggui
	 * @throws IOException 
	 */
	public void saveApply() throws IOException
	{
		synchronized(this){
			
		List list =wechatService.findActivityUser(apply.getWxOpenId());
		if(list.size()==0)
		{
			//保存报名信息
			apply.setAddDate(new Date());
			boolean b=wechatService.save(apply);
			if(b)
			{
				//this.getHttpServletResponse().getWriter().write("Y");
				//判断是否关注公众号
				boolean isSubscribe=Util.isSubscribe(apply.getWxOpenId());
				if(!isSubscribe)//已关注发卡
				{
					boolean flag_result=true;
					
					//判断该微信号是否已经领过卡
//					List listcards =wechatService.findCardRecordList(apply.getWxOpenId());
					//判断已领卡的数量
					Integer count=wechatService.getCardCount();
					String quota=dictService.getDictValue(Configer.CARD_QUOTA);
					
//					if(listcards.size()>0)
//					{
//						this.getHttpServletResponse().getWriter().write("R");
//						
//					}else 
					if(count>=Integer.parseInt(quota)){
						
						this.getHttpServletResponse().getWriter().write("Q");
						
					}else{
						
						RmActivityCardList card=wechatService.getCard(Configer.ACTIVITYID);
//						RmActivity activity=wechatService.getActivity(Configer.ACTIVITYID);
						String frist=dictService.getDictValue(Configer.CARD_FRIST);
						String level=dictService.getDictValue(Configer.CARD_LEVEL);
						String remark=dictService.getDictValue(Configer.CARD_REMARK);
						String detail=dictService.getDictValue(Configer.CARD_DETAIL);
						
						String token=TokenProxy.accessToken();
//						String flag=Util.sendImageMsg(token, apply.getWxOpenId(),card.getCardId(),card.getPassword());
//						String flag=Util.sendTextMsg(token, apply.getWxOpenId(), "恭喜您成功获得一张读伴卡，ID："+card.getCardId()+",密码："+card.getPassword()+",您可以凭此卡去读伴儿官网网站进行注册登录!");
						String flag=Util.sendTemplateMsg(card.getCardId(), card.getPassword(), apply.getWxOpenId(),level, Util.toDate(card.getUsableBefore(), "yyyy-MM-dd"),detail,frist,remark,token);
						
						if(flag.equals("Y"))
						{
							card.setSendStatus("1");//已使用
							wechatService.update(card);
						}
						RmActivityCardRecord  record=new RmActivityCardRecord();
						record.setAddDate(new Date());
						record.setCardNo(card.getCardId());
						record.setMobile(apply.getMobile());
						record.setName(apply.getName());
						record.setWxOpenid(apply.getWxOpenId());
						if(flag.equals("Y"))
						{
							record.setState("Y");
						}else{
							record.setState(flag);
							flag_result=false;
						}
						wechatService.save(record);
						if(flag_result)
						{
							//Util.sendNewMsg(token,apply.getWxOpenId(),"读伴儿图书馆使用指南","恭喜您成功领取读伴儿图书馆登录信息，想知道如何开启读伴儿图书馆的体验之旅吗？读伴儿特别为大家准备的如下小贴士","http://mp.weixin.qq.com/s?__biz=MzAwODUzNDM3Ng==&mid=209635811&idx=1&sn=7d3630b22ac332e12d994871607abc99#rd","http://weixin.readingmate.com.cn/weChat/images/weixin/slt.jpg");
							this.getHttpServletResponse().getWriter().write("Y");
						}else{
							this.getHttpServletResponse().getWriter().write("NL");
						}	
					}
				}else{
					this.getHttpServletResponse().getWriter().write("NY");
				}
			}else{
				this.getHttpServletResponse().getWriter().write("NB");
			}	
		}
	  }
	}
	
	/**
	* @Title: sendCard
	* @Description:活动发卡
	* @author zhao_jinggui
	* @throws IOException 
	*/
	public void sendCard() throws IOException
	{
		try{
			
		boolean isSubscribe=Util.isSubscribe(apply.getWxOpenId());
		if(isSubscribe)//未关注公众号
		{
			this.getHttpServletResponse().getWriter().write("S");
			
		}else{
			
			boolean flag_result=true;
			
			//判断该微信号是否已经领过卡
			List list =wechatService.findCardRecordList(apply.getWxOpenId());
			//判断已领卡的数量
			Integer count=wechatService.getCardCount();
			String quota=dictService.getDictValue(Configer.CARD_QUOTA);
			
			if(list.size()>0)
			{
				this.getHttpServletResponse().getWriter().write("R");
				
			}else if(count>=Integer.parseInt(quota)){
				
				this.getHttpServletResponse().getWriter().write("Q");
				
			}else{
				
				RmActivityCardList card=wechatService.getCard(Configer.ACTIVITYID);
				RmActivity activity=wechatService.getActivity(Configer.ACTIVITYID);
				String frist=dictService.getDictValue(Configer.CARD_FRIST);
				String level=dictService.getDictValue(Configer.CARD_LEVEL);
				String remark=dictService.getDictValue(Configer.CARD_REMARK);
				String detail=dictService.getDictValue(Configer.CARD_DETAIL);
				
				String token=TokenProxy.accessToken();
//				String flag=Util.sendImageMsg(token, apply.getWxOpenId(),card.getCardId(),card.getPassword());
//				String flag=Util.sendTextMsg(token, apply.getWxOpenId(), "恭喜您成功获得一张读伴卡，ID："+card.getCardId()+",密码："+card.getPassword()+",您可以凭此卡去读伴儿官网网站进行注册登录!");
				String flag=Util.sendTemplateMsg(card.getCardId(), card.getPassword(), apply.getWxOpenId(),level, Util.toDate(activity.getStartDate(), "yyyy-MM-dd")+" ~ "+Util.toDate(activity.getEndDate(), "yyyy-MM-dd"),detail,frist,remark,token);
				
				if(flag.equals("Y"))
				{
					card.setSendStatus("1");//已使用
					wechatService.update(card);
				}
				RmActivityCardRecord  record=new RmActivityCardRecord();
				record.setAddDate(new Date());
				record.setCardNo(card.getCardId());
				record.setMobile(apply.getMobile());
				record.setName(apply.getName());
				record.setWxOpenid(apply.getWxOpenId());
				if(flag.equals("Y"))
				{
					record.setState("Y");
				}else{
					record.setState(flag);
					flag_result=false;
				}
				wechatService.save(record);
				if(flag_result)
				{
					this.getHttpServletResponse().getWriter().write("Y");
				}else{
					this.getHttpServletResponse().getWriter().write("N");
				}	
			}
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	* @Title: toApply
	* @Description:通过微信OAuth2授权获取用户OpenId后，跳转到报名页面
	* @author zhao_jinggui
	* @return
	 */
	public String toApply()
	{
		
		String deadline=dictService.getDictValue(Configer.CARD_DEADLINE);
		Integer flag=Util.compare_date(deadline, new Date());
		if(flag==-1 || flag==0)
		{
			this.getHttpServletRequest().setAttribute("deadline", "1");
		}
		//获取用户OpenId
		String openId=(String) this.getSession().getAttribute("openId");
		if(openId==null)
		{
			String code =this.getHttpServletRequest().getParameter("code");
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = Util
						.getOauth2AccessToken(Configer.APPID,
								Configer.APPSECRET, code);
				openId = weiXinOauth2Token.getOpenId();
				this.getSession().setAttribute("openId",openId);
			}			
		}
		//List list =wechatService.findActivityUser(openId);
		String cardFlag=wechatService.findCardFlag(openId);
		if(cardFlag!=null)
		{
			this.getHttpServletRequest().setAttribute("isWxOpenId", cardFlag);
		}
		//js-sdk
		String jsapi_ticket = TokenProxy.jsApiTicket();
		Map<String,String> map =Sign.sign(jsapi_ticket,Configer.APPLY_URL);
		this.getHttpServletRequest().setAttribute("jsMap",map);
		return "apply";
	}
	
	/**
	* @Title: toRegister
	* @Description:通过微信OAuth2授权获取用户OpenId后，跳转到注册页面
	* @author zhao_jinggui
	* @return
	 */
	public String toRegister()
	{
		//获取用户OpenId
		String openId=(String) this.getSession().getAttribute("openId");
		if(openId==null)
		{
			String code =this.getHttpServletRequest().getParameter("code");
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = Util
						.getOauth2AccessToken(Configer.APPID,
								Configer.APPSECRET, code);
				openId = weiXinOauth2Token.getOpenId();
				this.getSession().setAttribute("openId",openId);
			}			
		}
		return "apply";
	}
	
	/**
	* @Title: toUserCenter
	* @Description:我的账号
	* @author zhao_jinggui
	* @return
	 */
	public String toUserCenter()
	{
		//获取用户OpenId
		String openId=(String) this.getSession().getAttribute("openId");
		if(openId==null)
		{
			String code =this.getHttpServletRequest().getParameter("code");
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = Util
						.getOauth2AccessToken(Configer.APPID,
								Configer.APPSECRET, code);
				openId = weiXinOauth2Token != null ? weiXinOauth2Token.getOpenId() : null;
				this.getSession().setAttribute("openId",openId);
			}			
		}
		//判断是否绑定 
		RmUser user=wechatService.isBandAccount(openId);
		if(user!=null)//已绑定
		{
			//教师姓名
			rmUserDetail = rmUserDetailService.getByRmUserDetailId(user.getId());
			
			//判断角色
			if(user.getRole().equals(Configer.RM_USER_ROLE))//教师
			{
				//获取学校
				String school = rmUserDetailService.getByRmUserDetailSchoolId(rmUserDetail.getSchoolId()).getSchool();
				//积分
				int point = rmUserDetailService.getPointByTeacherId(user.getId());
				//所带班级集合
				List<RmUserClassDetail> classList = rmUserClassDetailService.getListByTeacherId(user.getId());
				rmUserDetail.setPicName(Constants.PIC_PATH + (rmUserDetail.getPicName() != null ? rmUserDetail.getPicName() : Constants.PIC_DEF_NAME));

				this.getHttpServletRequest().setAttribute("school", school);
				this.getHttpServletRequest().setAttribute("point", point);
				this.getHttpServletRequest().setAttribute("classList", classList);
				return "teacher";
				
			}else{//个人
					String nuts = MoodleUtil.getNuts(user.getMoodleToken());
					String att = MoodleUtil.getAttendance(user.getMoodleToken());
					String cur = MoodleUtil.getCurrentLevel(user.getMoodleToken());	
					String age = rmUserDetail.getAge();
					String sy= rmUserDetail.getStudyYears();
					
					RmView rmView = new RmView();
					rmView.setId(user.getId());
					//rmView.setHead("");
					rmView.setUserName(user.getName());
					rmView.setUserRole(user.getRole());
					rmView.setUserAge(age);
					rmView.setUserYears(sy);
					rmView.setUserNum(nuts);
					rmView.setUserDay(att);
					rmView.setUserLevel(cur);
					RmUserDetail rmUserDetail = rmUserDetailService.getByRmUserDetailId(rmView.getId());
					rmView.setPicName(Constants.PIC_PATH + (rmUserDetail.getPicName() != null ? rmUserDetail.getPicName() : Constants.PIC_DEF_NAME));
					this.getHttpServletRequest().setAttribute("rmView", rmView);
				return "userreadinglist";
			}
			
		}else{//跳转绑定页面
			
			return "userband";
		}
	}
	
	/**
	* @Title: valdateMobile
	* @Description:验证电话是否已报名
	* @author zhao_jinggui
	* @throws IOException
	 */
	public void valdateMobile() throws IOException
	{
		String mobile=this.getHttpServletRequest().getParameter("mobile");
		List list=wechatService.findActivityUserByMobile(mobile);
		if(list!=null && list.size()>0)
		{
			this.getHttpServletResponse().getWriter().write("Y");
		}else{
			this.getHttpServletResponse().getWriter().write("N");
		}
	}
	
	/**
	* @Title: valdateTeacherNo
	* @Description:验证教师编号是否已报名
	* @author zhao_jinggui
	* @throws IOException
	 */
	public void valdateTeacherNo() throws IOException
	{
		String teacher=this.getHttpServletRequest().getParameter("teacher");
		List list=wechatService.findActivityUserByTeacher(teacher);
		if(list!=null && list.size()>0)
		{
			this.getHttpServletResponse().getWriter().write("Y");
		}else{
			this.getHttpServletResponse().getWriter().write("N");
		}
	}

	

	
	

	
}
