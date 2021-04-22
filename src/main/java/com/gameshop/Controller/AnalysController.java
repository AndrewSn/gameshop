package com.gameshop.Controller;

import com.gameshop.dto.GoodsWithPromoCode;
import com.gameshop.dto.OrderDto;
import com.gameshop.entity.*;
import com.gameshop.service.AnalystService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analys")
public class AnalysController {

    @Autowired
    AnalystService analystService;

    @GetMapping("/getReportOfDay")
    public List<ReportOfDay> getReportOfDay(@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @RequestParam(value = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return analystService.getReportOfDay(start, end);
    }


    @GetMapping("/getDateReport")
    public DateReport searchOrder(@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @RequestParam(value = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return analystService.getDateReport1(start, end);
    }

    @GetMapping("/getCheapestGoods")
    public List<Goods> getCheapestGoods(@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @RequestParam(value = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return analystService.getCheapestGoods(start, end);
    }

    @GetMapping("/getAverageCheck")
    public String getAverageCheck(@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @RequestParam(value = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return analystService.getAverageCheck(start, end);
    }

    @GetMapping("/getAverageSumOfDay")
    public Map<LocalDate, Double> getAverageSumOfDay(@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @RequestParam(value = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return analystService.getAveregeSumOfDay(start, end);
    }

    @GetMapping("/getReportOfUser")
    public ReportOfUser getReportOfUSer(@RequestParam(value = "id") Long id) {
        return analystService.getReportOfUser(id);
    }

    @PostMapping("/getProductWithPromocodeSale")
    @ResponseBody
    public List<GoodsWithPromoCode> getProductWithPromocodeSale(@RequestBody OrderDto orderDto) {
        return analystService.getProductWithPromocodeSale(orderDto);
    }


}
