package com.gameshop.Controller;

import com.gameshop.entity.Category;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepo categoryRepo;
    @GetMapping("/category")
    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }

    @PostMapping("/category/create")
    public Category createCategory(@Valid @RequestBody Category category){
        return categoryRepo.save(category);
    }

    @GetMapping("/category/{id}")
    @ResponseBody
    public Category getCategoryById(@PathVariable(value = "id") Long id){
        return categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable(value = "id") Long id, @Valid @RequestBody Category categoryDetails){
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id ));
       category.setCategoryName(categoryDetails.getCategoryName());
        return categoryRepo.save(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Long id){
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepo.delete(category);
        return ResponseEntity.ok().build();
    }
}
