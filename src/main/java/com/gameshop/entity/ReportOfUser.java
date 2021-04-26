package com.gameshop.entity;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReportOfUser {
    private UserInfo userInfo;
    private List<Order> orders;

    public ReportOfUser() {
    }

    public ReportOfUser(UserInfo userInfo, List<Order> orders) {
        this.userInfo = userInfo;
        this.orders = orders;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
