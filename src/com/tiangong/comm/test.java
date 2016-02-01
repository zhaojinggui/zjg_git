package com.tiangong.comm;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public class test {

	/**
	 * @Title: main
	 * @Description:
	 * @author zhao_jinggui
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List treenodes = new ArrayList();
//		  Map node = new HashMap();
//		  node.put("id","234324234");
//		  node.put("text","后台管理");		    			  
//		  node.put("state","closed");	    			  
//		  treenodes.add(node);
//		JSONArray treeData = JSONArray.fromObject(treenodes);		    	
//    	System.out.println("TREE::"+treeData.toString());
		
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		 
		//imageGenerator.loadHtml("<b>Hello World!</b> Please goto <a title=\"Goto Google\" href=\"http://www.google.com\">Google</a>.");
		imageGenerator.loadUrl("http://zhaojinggui.pagekite.me/weChat/jsp/wechat/card.jsp?cardId=123&cardPwd=33333");
		 
		imageGenerator.saveAsImage("D:/hello-world12.png");
		 
//		imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
		System.out.println("success");

	}

}
