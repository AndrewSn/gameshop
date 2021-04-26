package com.gameshop.service;

import com.gameshop.entity.Goods;
import com.gameshop.repository.GoodsRepo;
import com.gameshop.specification.GoodsSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GoodsService {
    @Autowired
    GoodsRepo goodsRepo;

    public List<Goods> getTopGoods() {
        List<Goods> goodsList = new ArrayList<>();
        return goodsList;
    }

    public List<Goods> getAllGoods(String search) {
        GoodsSpecificationsBuilder builder = new GoodsSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<Goods> spec = builder.build();
        return goodsRepo.findAll(spec);
    }
}
