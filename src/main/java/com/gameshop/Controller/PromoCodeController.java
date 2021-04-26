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

    @Autowired
    PromoCodeRepo promoCodeRepo;
    @Autowired
    PromoCodeService promoCodeService;

    @PostMapping("/")
    public PromoCode createPromoCode() {
        PromoCode promoCode = new PromoCode();
        promoCode.setPromoUnit(PromoUnit.currency);
        promoCode.setPromoStatus(PromoStatus.terminated);
        promoCode.setPromoValue(555.5);
        promoCode.setPromoCode(promoCodeService.createRandomCode());
        return promoCodeRepo.save(promoCode);
    }
}
