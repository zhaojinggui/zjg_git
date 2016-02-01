package com.tiangong.bean;

import java.sql.Timestamp;


/**
 * RmActivityCardList entity. @author MyEclipse Persistence Tools
 */

public class RmActivityCardList  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String activityId;
     private String cardId;
     private String password;
     private String sendStatus;
     private Timestamp usableBefore;
     private String addUser;
     private Timestamp addDate;


    // Constructors

    /** default constructor */
    public RmActivityCardList() {
    }

	/** minimal constructor */
    public RmActivityCardList(String activityId, String cardId, String password, String sendStatus, String addUser, Timestamp addDate) {
        this.activityId = activityId;
        this.cardId = cardId;
        this.password = password;
        this.sendStatus = sendStatus;
        this.addUser = addUser;
        this.addDate = addDate;
    }
    
    /** full constructor */
    public RmActivityCardList(String activityId, String cardId, String password, String sendStatus, Timestamp usableBefore, String addUser, Timestamp addDate) {
        this.activityId = activityId;
        this.cardId = cardId;
        this.password = password;
        this.sendStatus = sendStatus;
        this.usableBefore = usableBefore;
        this.addUser = addUser;
        this.addDate = addDate;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityId() {
        return this.activityId;
    }
    
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCardId() {
        return this.cardId;
    }
    
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSendStatus() {
        return this.sendStatus;
    }
    
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Timestamp getUsableBefore() {
        return this.usableBefore;
    }
    
    public void setUsableBefore(Timestamp usableBefore) {
        this.usableBefore = usableBefore;
    }

    public String getAddUser() {
        return this.addUser;
    }
    
    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public Timestamp getAddDate() {
        return this.addDate;
    }
    
    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }
   








}