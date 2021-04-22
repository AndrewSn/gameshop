package com.gameshop.service;

import com.gameshop.repository.CategoryRepo;
import com.gameshop.repository.GoodsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    GoodsRepo goodsRepo;
}
