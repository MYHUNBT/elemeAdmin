package com.neusoft.domain;

import java.math.BigDecimal;

/**
 * @author liuboting
 * @date 2020/8/7 10:06
 */

public class Food {
    private Integer foodId;
    private String foodName;
    private String foodExplain;
    private BigDecimal foodPrice;
    private Integer businessId;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }



    public Food(Integer foodId, String foodName, String foodExplain, BigDecimal foodPrice, Integer businessId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodExplain = foodExplain;
        this.foodPrice = foodPrice;
        this.businessId = businessId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodExplain() {
        return foodExplain;
    }

    public void setFoodExplain(String foodExplain) {
        this.foodExplain = foodExplain;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodExplain='" + foodExplain + '\'' +
                ", foodPrice=" + foodPrice +
                ", businessId=" + businessId +
                '}';
    }
}
