package com.yzc.shixun.dto;

public class OrderFood {
    private Integer id;
    private Integer orderId;
    private Integer foodId;
    private String remark;

    @Override
    public String toString() {
        return "OrderFood{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", foodId=" + foodId +
                ", remark='" + remark + '\'' +
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

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
