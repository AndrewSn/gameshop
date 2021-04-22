package com.gameshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameshop.converters.LocalDateConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"password"},
        allowGetters = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "name")
    private String nameOfUser;
    @Column(name = "password")
    private String passwordOfUser;
    @Column(name = "email")
    private String emailOfUser;
    @Column(name = "last_update", nullable = false)
    @Convert(converter = LocalDateConverter.class)
    //@Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdate;
    @Column(name = "created", nullable = false, updatable = false)
    @Convert(converter = LocalDateConverter.class)
    //@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDate created;

    @OneToMany(mappedBy = "user")
    private Set<UserInfo> userInfos;
    @Column(name = "personal_discount")
    private Double personalDiscountOfUser;
    @Column(name = "total_sum")
    private Double totalSum;


    public User() {
    }

    public User(Long id, String name, String password, String email, Date lastUpdate, LocalDate created, Set<UserInfo> userInfos, Double personalDiscount, Double totalSum) {
        this.id = id;
        this.nameOfUser = name;
        this.passwordOfUser = password;
        this.emailOfUser = email;
        this.lastUpdate = lastUpdate;
        this.created = created;
        this.userInfos = userInfos;
        this.personalDiscountOfUser = personalDiscount;
        this.totalSum = totalSum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String name) {
        this.nameOfUser = name;
    }

    public String getPasswordOfUser() {
        return passwordOfUser;
    }

    public void setPasswordOfUser(String password) {
        this.passwordOfUser = password;
    }

    public String getEmailOfUser() {
        return emailOfUser;
    }

    public void setEmailOfUser(String email) {
        this.emailOfUser = email;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Set<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(Set<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public Double getPersonalDiscountOfUser() {
        return personalDiscountOfUser;
    }

    public void setPersonalDiscountOfUser(Double personalDiscount) {
        this.personalDiscountOfUser = personalDiscount;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + nameOfUser + '\'' +
                ", password='" + passwordOfUser + '\'' +
                ", email='" + emailOfUser + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", created=" + created +
                '}';
    }
}
