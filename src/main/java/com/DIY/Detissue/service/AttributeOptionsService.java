package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.AttributeOption;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.AttributeOptionsResponse;
import com.DIY.Detissue.repository.AttributeOptionsRepository;
import com.DIY.Detissue.service.Imp.AttributeOptionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttributeOptionsService implements AttributeOptionsServiceImp {
    @Autowired
    private AttributeOptionsRepository attributeOptionsRepository;
    @Override
    public List<AttributeOptionsResponse> findByProductIdAndAttributeId(int productId, int attributeId) {
        List<AttributeOptionsResponse> responses =  new ArrayList<>();
        try {
            List<AttributeOption> list = attributeOptionsRepository.
                    findByProductIdAndAttributeId(productId, attributeId);
            for (AttributeOption option : list) {
                AttributeOptionsResponse response = new AttributeOptionsResponse();
                response.setId(option.getId());
                response.setValue(option.getValue());
                responses.add(response);
            }
        } catch (Exception e) {
            throw new CustomException("Error findByProductSkusIdAndAttributeId in AttributeOptionsService " + e.getMessage());
        }
        return responses;
    }
}
