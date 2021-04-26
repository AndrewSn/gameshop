package com.gameshop.service;

import com.gameshop.Enum.PromoStatus;
import com.gameshop.Enum.PromoUnit;
import com.gameshop.entity.PromoCode;
import com.gameshop.repository.PromoCodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class PromoCodeService {

    PromoCodeRepo promoCodeRepo;

    @Autowired
    public PromoCodeService(PromoCodeRepo promoCodeRepo){
        this.promoCodeRepo = promoCodeRepo;
    }

    public String createRandomCode() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < 12; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    public PromoCode createPromocode(){
        PromoCode promoCode = new PromoCode();
        promoCode.setPromoUnit(PromoUnit.currency);
        promoCode.setPromoStatus(PromoStatus.terminated);
        promoCode.setPromoValue(555.5);
        promoCode.setPromoCode(createRandomCode());
        return promoCodeRepo.save(promoCode);
    }
}
