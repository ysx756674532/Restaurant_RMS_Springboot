package com.yzc.shixun.dto;

public class CustInfo {
    private Integer id;
    private String custName;
    private String custPhonenum;
    private String custFlavor;
    private String custAddress;
    private Integer userId;

    public CustInfo(Integer id, String custName, String custPhonenum, String custFlavor, String custAddress, Integer userId) {
        this.id = id;
        this.custName = custName;
        this.custPhonenum = custPhonenum;
        this.custFlavor = custFlavor;
        this.custAddress = custAddress;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CustInfo{" +
                "id=" + id +
                ", custName='" + custName + '\'' +
                ", custPhonenum='" + custPhonenum + '\'' +
                ", custFlavor='" + custFlavor + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", userId=" + userId +
                '}';
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhonenum() {
        return custPhonenum;
    }

    public void setCustPhonenum(String custPhonenum) {
        this.custPhonenum = custPhonenum;
    }

    public String getCustFlavor() {
        return custFlavor;
    }

    public void setCustFlavor(String custFlavor) {
        this.custFlavor = custFlavor;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
