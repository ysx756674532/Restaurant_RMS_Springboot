package com.yzc.shixun.dto;

public class RestInfo {
    private Integer id;
    private String restName;
    private String restAddress;
    private String praiseRate;
    private String feature;
    private Integer userId;

    @Override
    public String toString() {
        return "RestInfo{" +
                "id=" + id +
                ", restName='" + restName + '\'' +
                ", restAddress='" + restAddress + '\'' +
                ", praiseRate='" + praiseRate + '\'' +
                ", feature='" + feature + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }

    public String getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(String praiseRate) {
        this.praiseRate = praiseRate;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
