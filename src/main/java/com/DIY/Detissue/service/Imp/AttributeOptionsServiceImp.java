package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.response.AttributeOptionsResponse;

import java.util.List;

public interface AttributeOptionsServiceImp {
    List<AttributeOptionsResponse> findByProductIdAndAttributeId(int productId, int attributeId);
}
