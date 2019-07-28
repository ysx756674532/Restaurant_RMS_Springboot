package com.yzc.shixun.model;

import java.util.Date;

public class RestCMInfos2 {
    private String custName;
    private String commentType;
    private String commentInfo;
    private String commentTime;

    @Override
    public String toString() {
        return "RestCMInfos2{" +
                ", custName='" + custName + '\'' +
                ", commentType='" + commentType + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                ", commentTime='" + commentTime + '\'' +
                '}';
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

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
