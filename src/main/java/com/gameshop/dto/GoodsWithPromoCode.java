package com.gameshop.dto;

public class GoodsWithPromoCode {
    private Long id;
    private String brand;
    private Double priceWithPromoCode;
    private String description;
    private String isValidatePromoCode;

    public GoodsWithPromoCode() {

    }

    public GoodsWithPromoCode(Long id, String brand, Double priceWithPromoCode, String description, String isValidatePromoCode) {
        this.id = id;
        this.brand = brand;
        this.priceWithPromoCode = priceWithPromoCode;
        this.description = description;
        this.isValidatePromoCode = isValidatePromoCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPriceWithPromoCode() {
        return priceWithPromoCode;
    }

    public void setPriceWithPromoCode(Double priceWithPromoCode) {
        this.priceWithPromoCode = priceWithPromoCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsValidatePromoCode() {
        return isValidatePromoCode;
    }

    public void setIsValidatePromoCode(String isValidatePromoCode) {
        this.isValidatePromoCode = isValidatePromoCode;
    }
}
