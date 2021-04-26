package com.gameshop.Controller;

import com.gameshop.Enum.PromoStatus;
import com.gameshop.Enum.PromoUnit;
import com.gameshop.entity.PromoCode;
import com.gameshop.repository.PromoCodeRepo;
import com.gameshop.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/promo")
public class PromoCodeController {

    PromoCodeRepo promoCodeRepo;
    PromoCodeService promoCodeService;

    @Autowired
    public PromoCodeController(PromoCodeRepo promoCodeRepo, PromoCodeService promoCodeService) {
        this.promoCodeRepo = promoCodeRepo;
        this.promoCodeService = promoCodeService;
    }

    @PostMapping("/")
    public PromoCode createPromoCode() {
       return promoCodeService.createPromocode();
    }
}
