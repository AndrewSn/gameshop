package com.gameshop.Controller;

import com.gameshop.dto.GoodsWithPromoCode;
import com.gameshop.dto.OrderDto;
import com.gameshop.entity.*;
import com.gameshop.service.AnalystService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analys")
public class AnalysController {

    AnalystService analystService;

    @Autowired
    public AnalysController(AnalystService analystService) {
        this.analystService = analystService;
    }

    @GetMapping("/report-of-day")
    public List<ReportOfDay> getReportOfDay(@RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return analystService.getReportOfDay(startDate, endDate);
    }


    @GetMapping("/report-of-day1")
    public DateReport searchOrder(@RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return analystService.getDateReport1(startDate, endDate);
    }

    @GetMapping("/cheapest-goods")
    public List<Goods> getCheapestGoods(@RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return analystService.getCheapestGoods(startDate, endDate);
    }

    @GetMapping("/average-check")
    public String getAverageCheck(@RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return analystService.getAverageCheck(startDate, endDate);
    }

    @GetMapping("/average-sum-of-day")
    public Map<LocalDate, Double> getAverageSumOfDay(@RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return analystService.getAveregeSumOfDay(startDate, endDate);
    }

    @GetMapping("/report-of-user")
    public ReportOfUser getReportOfUSer(@RequestParam(value = "userId") Long userId) {
        return analystService.getReportOfUser(userId);
    }

    @PostMapping("/product-with-promocode-sale")
    @ResponseBody
    public List<GoodsWithPromoCode> getProductWithPromocodeSale(@RequestBody OrderDto orderDto) {
        return analystService.getProductWithPromocodeSale(orderDto);
    }


}
