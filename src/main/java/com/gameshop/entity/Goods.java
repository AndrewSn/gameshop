package com.gameshop.entity;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "goods")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long id;
    @Column(name = "brand")
    private String brandOfGoods;
    @Column(name = "price")
    private double priceOfGoods;
    @Column(name = "descriptions")
    private String descriptionOfGoods;
    @Column(name = "sale_price")
    private double salePriceOfGoods;
    @Column(name = "number_goods")
    private int numberOfGoods;
    //
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ordergoods",
            joinColumns = {@JoinColumn(name = "goods_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private Set<Order> orders = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "goods")
    private Set<Category> categories = new HashSet<>();

    public Goods(Long id, String brandOfGoods, double priceOfGoods, String description, double salePrice, int numberOfGoods) {
        this.id = id;
        this.brandOfGoods = brandOfGoods;
        this.priceOfGoods = priceOfGoods;
        this.descriptionOfGoods = description;
        this.salePriceOfGoods = salePrice;
        this.numberOfGoods = numberOfGoods;
    }

    public Goods() {
    }


    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandOfGoods() {
        return brandOfGoods;
    }

    public void setBrandOfGoods(String brand) {
        this.brandOfGoods = brand;
    }

    public double getPriceOfGoods() {
        return priceOfGoods;
    }

    public void setPriceOfGoods(double price) {
        this.priceOfGoods = price;
    }

    public String getDescriptionOfGoods() {
        return descriptionOfGoods;
    }

    public void setDescriptionOfGoods(String description) {
        this.descriptionOfGoods = description;
    }

    public double getSalePriceOfGoods() {
        return salePriceOfGoods;
    }

    public void setSalePriceOfGoods(double salePrice) {
        this.salePriceOfGoods = salePrice;
    }

    public int getNumberOfGoods() {
        return numberOfGoods;
    }

    public void setNumberOfGoods(int numberOfGoods) {
        this.numberOfGoods = numberOfGoods;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", brand='" + brandOfGoods + '\'' +
                ", price=" + priceOfGoods +
                ", description='" + descriptionOfGoods + '\'' +
                ", salePrice=" + salePriceOfGoods +
                '}';
    }
}
