package com.yzc.shixun.dto;

import java.util.Date;

public class OrderInfo {

    private Integer id;
    private Integer custId;
    private Integer restId;
    private Integer peopleNum;
    private Integer tableId;
    private Date startTime;
    private String orderState;

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", custId=" + custId +
                ", restId=" + restId +
                ", peopleNum=" + peopleNum +
                ", tableId=" + tableId +
                ", startTime=" + startTime +
                ", orderState='" + orderState + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getRestId() {
        return restId;
    }

    public void setRestId(Integer restId) {
        this.restId = restId;
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
}
