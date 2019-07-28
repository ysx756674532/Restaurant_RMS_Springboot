package com.yzc.shixun.model;

import java.util.Date;

public class CustOrderInfo {
    private Integer orderid;
    private String restName;
    private Integer peopleNum;
    private Integer tableId;
    private Date startTime;
    private String orderState;
    private String restAddress;

    @Override
    public String toString() {
        return "CustOrderInfo{" +
                "orderid=" + orderid +
                ", restName='" + restName + '\'' +
                ", peopleNum=" + peopleNum +
                ", tableId=" + tableId +
                ", startTime=" + startTime+
                ", orderState='" + orderState + '\'' +
                ", restAddress='" + restAddress + '\'' +
                '}';
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }
}
