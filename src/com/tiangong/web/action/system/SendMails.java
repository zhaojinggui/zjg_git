package com.tiangong.web.action.system;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMails {
	private String hosts;
	private String sendforms;
	private String passwords;
	private String tosmail;
	
	public SendMails(){
	   this.hosts="";
	   this.sendforms="";
	   this.passwords="";
	   this.tosmail="";
	}
	
	public void sendMail(String host,String sendfrom,String password,String to,String topic,String content){
	   this.hosts=host;
	   this.sendforms=sendfrom;
	   this.passwords=password;
	   this.tosmail=to;
	   Properties pro=System.getProperties();
	   pro.put("mail.smtp.host",hosts);
	   pro.put("mail.smtp.auth", "true");
	   Session session=Session.getDefaultInstance(pro, new Authenticator(){
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	     return new PasswordAuthentication(sendforms, passwords);
	    }
	   });
	   try {
	    MimeMessage message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(this.sendforms));
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.tosmail));
	    message.setSubject(topic);
	    message.setText(content);
	    Transport.send(message);
	   } catch (AddressException e) {
	    e.printStackTrace();
	   } catch (MessagingException e) {
	    e.printStackTrace();
	   }
	}
}

//  mails.sendMail(map.get("Email.host").toString(),map.get("Email.name").toString(), map.get("Email.pwd").toString(), email,"��ӭʹ����ͨ��ϵͳ","�𾴵�"+username+"���:���Գɹ�ע����ͨ��ϵͳ����ĵ�¼�����ǣ�"+pwd+",������������⣬�������Ա��ϵ!");  mails.sendMail(map.get("Email.host").toString(),map.get("Email.name").toString(), map.get("Email.pwd").toString(), email,"��ӭʹ����ͨ��ϵͳ","�𾴵�"+username+"���:���Գɹ�ע����ͨ��ϵͳ����ĵ�¼�����ǣ�"+pwd+",������������⣬�������Ա��ϵ!");

