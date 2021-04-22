package com.gameshop.service;

import com.gameshop.entity.Goods;
import com.gameshop.repository.GoodsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsRepo goodsRepo;

    public List<Goods> getTopGoods(){
        List<Goods> goodsList = new ArrayList<>();
        return goodsList;
    }
}
