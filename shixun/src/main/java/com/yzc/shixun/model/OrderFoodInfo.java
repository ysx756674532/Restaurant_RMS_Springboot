package com.yzc.shixun.model;

public class OrderFoodInfo {
    private String foodName;
    private Float foodPrice;
    private String remark;


    @Override
    public String toString() {
        return "OrderFoodInfo{" +
                "foodName='" + foodName + '\'' +
                ", foodPrice=" + foodPrice +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
