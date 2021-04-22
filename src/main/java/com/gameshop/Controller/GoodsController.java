package com.gameshop.Controller;

import com.gameshop.entity.Goods;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.GoodsRepo;
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

    @GetMapping("/")
    public List<Goods> getAllGoods(@RequestParam(value = "search") String search) {
        GoodsSpecificationsBuilder builder = new GoodsSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Goods> spec = builder.build();
        return goodsRepo.findAll(spec);
    }

    @GetMapping("/topprice")
    public List<Goods> getGoodsTopPrice() {
        return goodsRepo.getAllTopPrice();
    }


    @PostMapping("/goods/create")
    public Goods createGoods(@Valid @RequestBody Goods goods) {
        return goodsRepo.save(goods);
    }

    @GetMapping("/goods/{id}")
    @ResponseBody
    public Goods getGoodsById(@PathVariable(value = "id") Long id) {
        return goodsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Goods", "id", id));
    }


    @PutMapping("/update/{id}")
    public Goods updateGoods(@PathVariable(value = "id") Long id, @Valid @RequestBody Goods goodsDetails) {
        Goods goods = goodsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Goods", "id", id));
        goods.setBrandOfGoods(goodsDetails.getBrandOfGoods());
        goods.setPriceOfGoods(goodsDetails.getPriceOfGoods());
        goods.setDescriptionOfGoods(goodsDetails.getDescriptionOfGoods());
        goods.setSalePriceOfGoods(goodsDetails.getSalePriceOfGoods());
        return goodsRepo.save(goods);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGoods(@PathVariable(value = "id") Long id) {
        Goods goods = goodsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Goods", "id", id));
        goodsRepo.delete(goods);
        return ResponseEntity.ok().build();
    }
}

