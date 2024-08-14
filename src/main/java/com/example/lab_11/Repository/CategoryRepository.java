package com.example.lab_11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab_11.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

    Category findCategoryById(Integer id);
    Category findCategoryByCategoryName(String categoryName); 
}
