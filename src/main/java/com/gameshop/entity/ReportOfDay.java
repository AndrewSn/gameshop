package com.gameshop.entity;

import java.time.LocalDate;
import java.util.Map;

public class ReportOfDay {
    private LocalDate date;
    private Double averageSumOfDay;
    private Double sumOfDay;

    public ReportOfDay() {
    }

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
