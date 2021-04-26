package com.gameshop.service;

import com.gameshop.entity.Category;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.CategoryRepo;
import com.gameshop.repository.GoodsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    CategoryRepo categoryRepo;
    GoodsRepo goodsRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo, GoodsRepo goodsRepo) {
        this.categoryRepo = categoryRepo;
        this.goodsRepo = goodsRepo;
    }

    public Category updateCategory(Long categoryId, Category categoryRequest) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        category.setCategoryName(categoryRequest.getCategoryName());
        return categoryRepo.save(category);
    }

    public ResponseEntity<Object> deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepo.delete(category);
        return ResponseEntity.ok().build();
    }
}
