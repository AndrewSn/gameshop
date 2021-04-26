package com.gameshop.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gameshop.Enum.PromoStatus;
import com.gameshop.Enum.PromoUnit;
import com.gameshop.converters.LocalDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "promocode")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "promoId")
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promo_id")
    private Long promoId;
    @Column(name = "promo_code")
    private String promoCode;
    @Column(name = "start_date")
    @Convert(converter = LocalDateConverter.class)
    @CreatedDate
    private LocalDate startDate;
    @Column(name = "end_date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate endDate;
    @Column(name = "promo_value")
    private Double promoValue;
    @Enumerated(EnumType.STRING)
    @Column(name = "promo_unit", length = 15)
    private PromoUnit promoUnit;
    @Enumerated(EnumType.STRING)
    @Column(name = "promo_status", length = 15)
    private PromoStatus promoStatus;
    @OneToMany(mappedBy = "promoCode")
    @JsonIgnore
    private Set<Order> orders;

    @Autowired
    public PromoCode(Long promoId, String promoCode, LocalDate startDate, LocalDate endDate, Double promoValue, PromoUnit promoUnit, PromoStatus promoStatus) {
        this.promoId = promoId;
        this.promoCode = promoCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promoValue = promoValue;
        this.promoUnit = promoUnit;
        this.promoStatus = promoStatus;
    }

    @Autowired
    public PromoCode() {

    }

    public Long getPromoId() {
        return promoId;
    }

    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getPromoValue() {
        return promoValue;
    }

    public void setPromoValue(Double promoValue) {
        this.promoValue = promoValue;
    }

    public PromoUnit getPromoUnit() {
        return promoUnit;
    }

    public void setPromoUnit(PromoUnit promoUnit) {
        this.promoUnit = promoUnit;
    }

    public PromoStatus getPromoStatus() {
        return promoStatus;
    }

    public void setPromoStatus(PromoStatus promoStatus) {
        this.promoStatus = promoStatus;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
