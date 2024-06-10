package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.response.AttributeOptionsResponse;

import java.util.List;

public interface AttributeOptionsServiceImp {
    List<AttributeOptionsResponse> findByProductSkusIdAndAttributeId(int productSkusId, int attributeId);
}
