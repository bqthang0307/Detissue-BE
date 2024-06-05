package com.DIY.Detissue.service;

import com.DIY.Detissue.payload.response.CategoryResponse;
import com.DIY.Detissue.repository.CategoryRepository;
import com.DIY.Detissue.service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    private CategoryRepository categoryRepository;
    @Cacheable(value = "categoryList")
    public List<CategoryResponse> findAll() {
        List<CategoryResponse> list = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(category.getId());
            categoryResponse.setName(category.getName());
            list.add(categoryResponse);
        });
        return list;
    }
}
