package com.yzc.shixun.model;

import java.util.Date;

public class RestCMInfos {

    private Integer id;
    private Integer orderId;
    private String custName;
    private String commentType;
    private String commentInfo;
    private Date commentTime;


    @Override
    public String toString() {
        return "RestCMInfos{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", custName='" + custName + '\'' +
                ", commentType='" + commentType + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
