package com.gameshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderDto {
    private String name;
    private String surname;
    private String city;
    private String phone;
    @JsonProperty("totalPrice")
    private Double totalAmount;
    @JsonProperty("discountedPrice")
    private Double saleAmount;
    @JsonProperty("products")
    private List<GoodsDto> goodsDto;
    @JsonProperty("departmentNumber")
    private String branchNumber;
    @JsonProperty("payment")
    private String paymentMethod;
    private Long userId;
    private String promoCode;

    public OrderDto(String name, String surname, String city, String phone, Double totalAmount, Double saleAmount, List<GoodsDto> goodsDto, String branchNumber, String paymentMethod, Long userId, String promoCode) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.phone = phone;
        this.totalAmount = totalAmount;
        this.saleAmount = saleAmount;
        this.goodsDto = goodsDto;
        this.branchNumber = branchNumber;
        this.paymentMethod = paymentMethod;
        this.userId = userId;
        this.promoCode = promoCode;
    }

    public OrderDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public List<GoodsDto> getGoodsDto() {
        return goodsDto;
    }

    public void setGoodsDto(List<GoodsDto> goodsDto) {
        this.goodsDto = goodsDto;
    }

    public String getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String deliveryMethod) {
        this.paymentMethod = deliveryMethod;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
}