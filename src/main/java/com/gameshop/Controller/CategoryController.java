package com.gameshop.Controller;

import com.gameshop.entity.Category;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.CategoryRepo;
import com.gameshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    CategoryRepo categoryRepo;
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryRepo categoryRepo, CategoryService categoryService) {
        this.categoryRepo = categoryRepo;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @PostMapping("/")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepo.save(category);
    }

    @GetMapping("/{categoryId}")
    @ResponseBody
    public Category getCategoryById(@PathVariable(value = "categoryId") Long categoryId) {
        return categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
    }

    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @Valid @RequestBody Category categoryDetails) {
        return categoryService.updateCategory(categoryId, categoryDetails);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
