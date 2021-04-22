package com.gameshop.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userinfo_id")
    private UserInfo userInfo;
    @Column(name = "status_pay")
    private String statusPay;
    @Column(name = "status_order")
    private String statusOrder;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "total_amount")
    private double totalAmount;
    @Column(name = "create_order")
    //@Convert(converter = LocalDateConverter.class)
    //@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDate createOrder;
    @Column(name = "sale_amount")
    private Double saleAmount;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "orders")
    private Set<Goods> goods = new HashSet<>();
    @Column(name = "personal_discount")
    private Double personalDiscount;
    @ManyToOne
    @JoinColumn(name = "promo_id")
    private PromoCode promoCode;

    public Order() {
    }

    public Order(Long id, UserInfo userInfo, String statusPay, String statusOrder, String paymentMethod, double totalAmount, LocalDate createOrder, Double saleAmount, Double personalDiscount, PromoCode promoCode) {
        this.id = id;
        this.userInfo = userInfo;
        this.statusPay = statusPay;
        this.statusOrder = statusOrder;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.createOrder = createOrder;
        this.saleAmount = saleAmount;
        this.personalDiscount = personalDiscount;
        this.promoCode = promoCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getStatusPay() {
        return statusPay;
    }

    public void setStatusPay(String statusPay) {
        this.statusPay = statusPay;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getOrderDate() {
        return createOrder;
    }

    public void setOrderDate(LocalDate createOrder) {
        this.createOrder = createOrder;
    }

    public Set<Goods> getGoods() {
        return goods;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public LocalDate getCreateOrder() {
        return createOrder;
    }

    public void setCreateOrder(LocalDate createOrder) {
        this.createOrder = createOrder;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Double getPersonalDiscount() {
        return personalDiscount;
    }

    public void setPersonalDiscount(Double personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public PromoCode getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCode promoCode) {
        this.promoCode = promoCode;
    }
}
