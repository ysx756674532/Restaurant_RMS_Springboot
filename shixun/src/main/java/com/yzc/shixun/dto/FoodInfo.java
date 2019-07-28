package com.yzc.shixun.dto;

public class FoodInfo {
    private Integer id;
    private String foodName;
    private Integer foodPrice;
    private String foodType;
    private String foodIntro;
    private Integer restId;


    @Override
    public String toString() {
        return "FoodInfo{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodPrice=" + foodPrice +
                ", foodType='" + foodType + '\'' +
                ", foodIntro='" + foodIntro + '\'' +
                ", restId=" + restId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodIntro() {
        return foodIntro;
    }

    public void setFoodIntro(String foodIntro) {
        this.foodIntro = foodIntro;
    }

    public Integer getRestId() {
        return restId;
    }

    public void setRestId(Integer restId) {
        this.restId = restId;
    }
}
