package com.gameshop.Controller;

import com.gameshop.entity.Goods;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.GoodsRepo;
import com.gameshop.service.GoodsService;
import com.gameshop.specification.GoodsSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsRepo goodsRepo;
    @Autowired
    GoodsService goodsService;

    @GetMapping("/getAll")
    public List<Goods> gettGoods() {
        return goodsRepo.findAll();
    }

    @GetMapping("/")
    public List<Goods> getAllGoods(@RequestParam(value = "search") String search) {
        return goodsService.getAllGoods(search);
    }

    @GetMapping("/goods-top-price")
    public List<Goods> getGoodsTopPrice() {
        return goodsRepo.getAllTopPrice();
    }


    @PostMapping("/")
    public Goods createGoods(@Valid @RequestBody Goods goods) {
        return goodsRepo.save(goods);
    }

    @GetMapping("/{goodsId}")
    @ResponseBody
    public Goods getGoodsById(@PathVariable(value = "goodsId") Long goodsId) {
        return goodsRepo.findById(goodsId).orElseThrow(() -> new ResourceNotFoundException("Goods", "goodsId", goodsId));
    }


    @PutMapping("/{goodsId}")
    public Goods updateGoods(@PathVariable(value = "goodsId") Long goodsId, @Valid @RequestBody Goods goodsDetails) {
        Goods goods = goodsRepo.findById(goodsId).orElseThrow(() -> new ResourceNotFoundException("Goods", "goodsId", goodsId));
        goods.setBrandOfGoods(goodsDetails.getBrandOfGoods());
        goods.setPriceOfGoods(goodsDetails.getPriceOfGoods());
        goods.setDescriptionOfGoods(goodsDetails.getDescriptionOfGoods());
        goods.setSalePriceOfGoods(goodsDetails.getSalePriceOfGoods());
        return goodsRepo.save(goods);
    }

    @DeleteMapping("/{goodsId}")
    public ResponseEntity<?> deleteGoods(@PathVariable(value = "goodsId") Long goodsId) {
        Goods goods = goodsRepo.findById(goodsId).orElseThrow(() -> new ResourceNotFoundException("Goods", "goodsId", goodsId));
        goodsRepo.delete(goods);
        return ResponseEntity.ok().build();
    }
}

