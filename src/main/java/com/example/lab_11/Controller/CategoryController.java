package com.example.lab_11.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_11.API.ApiResponse;
import com.example.lab_11.Model.Category;
import com.example.lab_11.Service.CategoreyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoreyService categoreyService; 

    @GetMapping
    public ResponseEntity getCategories(){
        return ResponseEntity.status(200).body(categoreyService.getCategories()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategoryById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(categoreyService.getCategoryById(id)); 
    }


    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        categoreyService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added successfully")); 
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid @RequestBody Category category, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        categoreyService.updateCategory(id, category);
        return ResponseEntity.status(200).body(new ApiResponse("Category updated successfully")); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        categoreyService.deleteCategory(id);
        return ResponseEntity.status(200).body(new ApiResponse("Category deleted successfully")); 
    }
}
