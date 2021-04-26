package com.gameshop.entity;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class ReportOfDay {
    private LocalDate date;
    private Double averageSumOfDay;
    private Double sumOfDay;

    @Autowired
    public ReportOfDay() {
    }

    @Autowired
    public ReportOfDay(LocalDate date, Double averageSumOfDay, Double sumOfDay) {
        this.date = date;
        this.averageSumOfDay = averageSumOfDay;
        this.sumOfDay = sumOfDay;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAverageSumOfDay() {
        return averageSumOfDay;
    }

    public void setAverageSumOfDay(Double averageSumOfDay) {
        this.averageSumOfDay = averageSumOfDay;
    }

    public Double getSumOfDay() {
        return sumOfDay;
    }

    public void setSumOfDay(Double sumOfDay) {
        this.sumOfDay = sumOfDay;
    }
}
