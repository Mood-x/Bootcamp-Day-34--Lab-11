package com.example.lab_11.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lab_11.API.ApiExeption;
import com.example.lab_11.Model.Category;
import com.example.lab_11.Repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoreyService {

    private final CategoryRepository categoryRepository; 

    public List<Category> getCategories(){
        if(categoryRepository.findAll().isEmpty()){
            throw new ApiExeption("Not found Categories"); 
        }
        return categoryRepository.findAll(); 
    }

    public Category getCategoryById(Integer id){
        Category category = categoryRepository.findCategoryById(id); 

        if(category == null){
            throw new ApiExeption("Category with this ID: " + id + ", Not found"); 
        }
        return category; 
    }


    public void addCategory(Category category){
        categoryRepository.save(category); 
    }
    

    public void updateCategory(Integer id, Category updateCategory){
        Category category = categoryRepository.findCategoryById(id); 

        if(category == null){
            throw new ApiExeption("Category with this ID: " + id + ", Not found"); 
        }

        category.setCategoryName(updateCategory.getCategoryName());
        categoryRepository.save(category); 
    }


    public void deleteCategory(Integer id){
        Category category = categoryRepository.findCategoryById(id); 

        if(category == null){
            throw new ApiExeption("Category with this ID: " + id + ", Not found"); 
        }
        categoryRepository.delete(category); 
    }
}
