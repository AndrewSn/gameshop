package com.gameshop.service;

import com.gameshop.entity.Goods;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.GoodsRepo;
import com.gameshop.specification.GoodsSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GoodsService {
    GoodsRepo goodsRepo;

    @Autowired
    public GoodsService(GoodsRepo goodsRepo) {
        this.goodsRepo = goodsRepo;
    }

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

    public Goods updateGoods(Long goodsId, Goods goodsRequest) {
        Goods goods = goodsRepo.findById(goodsId).orElseThrow(() -> new ResourceNotFoundException("Goods", "goodsId", goodsId));
        goods.setBrandOfGoods(goodsRequest.getBrandOfGoods());
        goods.setPriceOfGoods(goodsRequest.getPriceOfGoods());
        goods.setDescriptionOfGoods(goodsRequest.getDescriptionOfGoods());
        goods.setSalePriceOfGoods(goodsRequest.getSalePriceOfGoods());
        return goodsRepo.save(goods);
    }

    public ResponseEntity<Object> deleteGoods(Long goodsId) {
        Goods goods = goodsRepo.findById(goodsId).orElseThrow(() -> new ResourceNotFoundException("Goods", "goodsId", goodsId));
        goodsRepo.delete(goods);
        return ResponseEntity.ok().build();
    }
}
