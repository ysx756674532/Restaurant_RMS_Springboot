package com.yzc.shixun.dto;

import java.util.Date;

public class CommentInfo {

    private Integer id;
    private Integer orderId;
    private String commentType;
    private String commentInfo;
    private Date commentTime;

    @Override
    public String toString() {
        return "CommentInfo{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", commentType='" + commentType + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                ", commentTime='" + commentTime.toString() + '\'' +
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
