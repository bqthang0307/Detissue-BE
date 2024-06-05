package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.response.CategoryResponse;

import java.util.List;

public interface CategoryServiceImp {
    List<CategoryResponse> findAll();
}
