package com.gameshop.service;

import com.gameshop.Enum.PromoStatus;
import com.gameshop.dto.GoodsDto;
import com.gameshop.dto.GoodsWithPromoCode;
import com.gameshop.dto.OrderDto;
import com.gameshop.entity.*;
import com.gameshop.repository.OrderRepo;
import com.gameshop.repository.PromoCodeRepo;
import com.gameshop.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalystService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserInfoRepo userInfoRepo;
    @Autowired
    PromoCodeRepo promoCodeRepo;

    public DateReport getDateReport1(LocalDate startDAte, LocalDate endDAte) {
        DateReport dateReport = new DateReport();
        ReportOfDay reportOfDay = new ReportOfDay();
        dateReport.setAverageCheck(getAverageCheck(startDAte, endDAte));
        dateReport.setCheapestGoods(getCheapestGoods(startDAte, endDAte));
        dateReport.setExpensiveGoods(getExpensiveGoods(startDAte, endDAte));
        dateReport.setReportOfDay(getReportOfDay(startDAte, endDAte));
        return dateReport;
    }

    public List<ReportOfDay> getReportOfDay(LocalDate startDAte, LocalDate endDAte) {
        List<ReportOfDay> reportOfDays = new ArrayList<>();
        List<LocalDate> localDates = getlocalDates(startDAte, endDAte);
        List<Order> orders = orderRepo.getOrderByDateRange(startDAte, endDAte);
        for (int i = 0; i < localDates.size(); i++) {
            int finalI = i;
            double averageSum = 0;
            long count = 0;
            count = orders.stream().filter(order -> order.getCreateOrder().isEqual(localDates.get(finalI))).count();
            int finalI1 = i;
            averageSum = orders.stream().filter(order -> order.getCreateOrder().isEqual(localDates.get(finalI1))).mapToDouble(Order::getTotalAmount).sum() / count;
            double sum = orders.stream().filter(order -> order.getCreateOrder().isEqual(localDates.get(finalI))).mapToDouble(x -> x.getTotalAmount()).sum();
            ReportOfDay reportOfDay = new ReportOfDay();
            reportOfDay.setDate(localDates.get(i));
            reportOfDay.setAverageSumOfDay(averageSum);
            reportOfDay.setSumOfDay(sum);
            if (reportOfDay.getSumOfDay() != 0)
                reportOfDays.add(reportOfDay);
        }
        return reportOfDays;
    }


    public List<Goods> getCheapestGoods(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepo.getOrderByDateRange(startDate, endDate);
        List<Goods> goodsList = orders.stream().map(Order::getGoods).flatMap(goods -> goods.stream()).collect(Collectors.toList());
        double minPrice = goodsList.stream().mapToDouble(Goods::getPriceOfGoods).min().orElseThrow(NoSuchElementException::new);
        List<Goods> cheapGoodsList = goodsList.stream().filter(goods -> goods.getPriceOfGoods() == minPrice).collect(Collectors.toList());
        return cheapGoodsList;
    }

    public List<Goods> getExpensiveGoods(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepo.getOrderByDateRange(startDate, endDate);
        List<Goods> goodsList = orders.stream().map(Order::getGoods).flatMap(goods -> goods.stream()).collect(Collectors.toList());
        double maxPrice = goodsList.stream().mapToDouble(Goods::getPriceOfGoods).max().orElseThrow(NoSuchElementException::new);
        List<Goods> cheapGoodsList = goodsList.stream().filter(goods -> goods.getPriceOfGoods() == maxPrice).collect(Collectors.toList());
        return cheapGoodsList;
    }

    public List<LocalDate> getlocalDates(LocalDate startDAte, LocalDate endDAte) {
        List<LocalDate> localDate = new ArrayList();
        while (startDAte.isBefore(endDAte)) {
            localDate.add(startDAte);
            LocalDate dateBetween = startDAte.plusDays(1);
            startDAte = dateBetween;
        }
        return localDate;
    }

    public String getAverageCheck(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepo.getOrderByDateRange(startDate, endDate);
        long numberOfDate = getlocalDates(startDate, endDate).stream().count();
        double averageSum = orders.stream().mapToDouble(Order::getTotalAmount).sum() / numberOfDate;
        return "Average check" + averageSum;
    }

    public Map<LocalDate, Double> getAveregeSumOfDay(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepo.getOrderByDateRange(startDate, endDate);
        List<LocalDate> localDates = getlocalDates(startDate, endDate);
        Map<LocalDate, Double> resultMap = new HashMap<>();
        for (int i = 0; i < localDates.size(); i++) {
            long count = 0;
            double averageSum = 0;
            int finalI = i;
            count = orders.stream().filter(order -> order.getCreateOrder().isEqual(localDates.get(finalI))).count();
            int finalI1 = i;
            averageSum = orders.stream().filter(order -> order.getCreateOrder().isEqual(localDates.get(finalI1))).mapToDouble(Order::getTotalAmount).sum() / count;
            if (!Double.isNaN(averageSum)) {
                resultMap.put(localDates.get(i), averageSum);
            }
        }
        return resultMap;
    }

    public Map<LocalDate, Double> getSumOfDay(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepo.getOrderByDateRange(startDate, endDate);
        List<LocalDate> localDates = getlocalDates(startDate, endDate);
        Map<LocalDate, Double> resultMap = new HashMap<>();
        for (int i = 0; i < localDates.size(); i++) {
            double averageSum = 0;
            int finalI1 = i;
            averageSum = orders.stream().filter(order -> order.getCreateOrder().isEqual(localDates.get(finalI1))).mapToDouble(Order::getTotalAmount).sum();
            if (!Double.isNaN(averageSum)) {
                resultMap.put(localDates.get(i), averageSum);
            }
        }
        return resultMap;
    }

    public ReportOfUser getReportOfUser(Long id) {
        ReportOfUser reportOfUser = new ReportOfUser();
        UserInfo userInfo = userInfoRepo.findUserInfoById(id);
        // Set<Order> orderList;
        //orderList = userInfo.getOrders();
        List<Order> orderList = orderRepo.findAll();
        orderList.stream().filter(order -> order.getUserInfo().getId().equals(id)).collect(Collectors.toList());
        reportOfUser.setUserInfo(userInfo);
        reportOfUser.setOrders(orderList);
        return reportOfUser;
    }

    public List<GoodsWithPromoCode> getProductWithPromocodeSale(OrderDto orderDto) {
        List<GoodsDto> goodsList = orderDto.getGoodsDto();
        List<GoodsWithPromoCode> goodsList1 = new ArrayList<>();
        PromoCode promoCode = promoCodeRepo.getPromoCodeIfExist(orderDto.getPromoCode());
        if (promoCode != null && promoCode.getPromoStatus().equals(PromoStatus.active)) {
            String unit = promoCode.getPromoUnit().toString();
            for (int i = 0; i < goodsList.size(); i++) {
                double sum = 0;
                if (goodsList.get(i).getSalePrice() > 0) {
                    switch (unit) {
                        case "percent":
                            sum = goodsList.get(i).getSalePrice() - ((goodsList.get(i).getSalePrice() * promoCode.getPromoValue()) / 100);
                            break;
                        case "currency":
                            sum = goodsList.get(i).getSalePrice() - promoCode.getPromoValue();
                            break;
                    }
                } else {
                    switch (unit) {
                        case "percent":
                            sum = goodsList.get(i).getPrice() - ((goodsList.get(i).getPrice() * promoCode.getPromoValue()) / 100);
                            break;
                        case "currency":
                            sum = goodsList.get(i).getPrice() - promoCode.getPromoValue();
                            break;
                    }
                }

                GoodsWithPromoCode goodsWithPromoCode = new GoodsWithPromoCode();
                goodsWithPromoCode.setId(goodsList.get(i).getId());
                goodsWithPromoCode.setBrand(goodsList.get(i).getBrand());
                goodsWithPromoCode.setDescription(goodsList.get(i).getDescription());
                goodsWithPromoCode.setPriceWithPromoCode(sum);
                goodsWithPromoCode.setIsValidatePromoCode(promoCode.getPromoStatus().toString());
                goodsList1.add(goodsWithPromoCode);
            }
        } else if (promoCode == null) {
            GoodsWithPromoCode goodsWithPromoCode = new GoodsWithPromoCode();
            goodsWithPromoCode.setIsValidatePromoCode("PromoCode is empty");
            goodsList1.add(goodsWithPromoCode);

        } else {
            for (int i = 0; i < goodsList.size(); i++) {
                GoodsWithPromoCode goodsWithPromoCode = new GoodsWithPromoCode();
                goodsWithPromoCode.setId(goodsList.get(i).getId());
                goodsWithPromoCode.setBrand(goodsList.get(i).getBrand());
                goodsWithPromoCode.setDescription(goodsList.get(i).getDescription());
                if (orderDto.getSaleAmount() != 0) {
                    goodsWithPromoCode.setPriceWithPromoCode(goodsList.get(i).getSalePrice());
                } else {
                    goodsWithPromoCode.setPriceWithPromoCode(goodsList.get(i).getPrice());
                }
                goodsWithPromoCode.setIsValidatePromoCode(promoCode.getPromoStatus().toString());
                goodsList1.add(goodsWithPromoCode);
            }

        }

        return goodsList1;
    }
}
