package com.neusoft.domain;

import java.math.BigDecimal;

/**
 * @author liuboting
 * @date 2020/8/7 10:14
 */

public class Business {
    private Integer businessId;
    private String password;
    private String businessName;
    private String businessAddress;
    private String businessExplain;
    private BigDecimal starPrice;
    private BigDecimal deliveryPrice;

    public Business() {
    }

    public Business(Integer businessId, String password, String businessName, String businessAddress, String businessExplain, BigDecimal starPrice, BigDecimal deliveryPrice) {
        this.businessId = businessId;
        this.password = password;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessExplain = businessExplain;
        this.starPrice = starPrice;
        this.deliveryPrice = deliveryPrice;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessExplain() {
        return businessExplain;
    }

    public void setBusinessExplain(String businessExplain) {
        this.businessExplain = businessExplain;
    }

    public BigDecimal getStarPrice() {
        return starPrice;
    }

    public void setStarPrice(BigDecimal starPrice) {
        this.starPrice = starPrice;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public String toString() {
        return "business{" +
                "businessId=" + businessId +
                ", password='" + password + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", businessExplain='" + businessExplain + '\'' +
                ", starPrice=" + starPrice +
                ", deliveryPrice=" + deliveryPrice +
                '}';
    }
}
