package com.gameshop.entity;

import java.util.List;

public class DateReport {
    private String averageCheck;
    private List<Goods> cheapestGoods;
    private List<Goods> expensiveGoods;
    private List<ReportOfDay> reportOfDay;

    public DateReport() {
    }

    public DateReport(String averageCheck, List<Goods> cheapestGoods, List<Goods> expensiveGoods, List<ReportOfDay> reportOfDay) {
        this.averageCheck = averageCheck;
        this.cheapestGoods = cheapestGoods;
        this.expensiveGoods = expensiveGoods;
        this.reportOfDay = reportOfDay;
    }

    public String getAverageCheck() {
        return averageCheck;
    }

    public void setAverageCheck(String averageCheck) {
        this.averageCheck = averageCheck;
    }

    public List<Goods> getCheapestGoods() {
        return cheapestGoods;
    }

    public void setCheapestGoods(List<Goods> cheapestGoods) {
        this.cheapestGoods = cheapestGoods;
    }

    public List<Goods> getExpensiveGoods() {
        return expensiveGoods;
    }

    public void setExpensiveGoods(List<Goods> expensiveGoods) {
        this.expensiveGoods = expensiveGoods;
    }

    public List<ReportOfDay> getReportOfDay() {
        return reportOfDay;
    }

    public void setReportOfDay(List<ReportOfDay> reportOfDay) {
        this.reportOfDay = reportOfDay;
    }
}
