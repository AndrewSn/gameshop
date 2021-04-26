package com.gameshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = {"description"},
        allowGetters = true)
public class GoodsDto {
    private long id;
    @JsonProperty("name")
    private String brand;
    private Double price;
    private String description;
    @JsonProperty("discountedPrice")
    private Double salePrice;
    @JsonProperty("quantity")
    private Integer numberGoods;

    public GoodsDto(long id, String brand, Double price, String description, Double salePrice, Integer numberGoods) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.salePrice = salePrice;
        this.numberGoods = numberGoods;
    }

    public GoodsDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getNumberGoods() {
        return numberGoods;
    }

    public void setNumberGoods(Integer numberGoods) {
        this.numberGoods = numberGoods;
    }
}
