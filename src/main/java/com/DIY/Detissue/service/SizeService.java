package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Size;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.SizeResponse;
import com.DIY.Detissue.repository.SizeRepository;
import com.DIY.Detissue.service.Imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeService implements SizeServiceImp {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<SizeResponse> findByProductId(int id) {
        List<SizeResponse> responses = new ArrayList<>();
        try {
            List<Size> sizes = sizeRepository.findByProductId(id);
            for (Size size : sizes) {
                SizeResponse response = new SizeResponse();
                response.setId(size.getId());
                response.setName(size.getName());
                responses.add(response);
            }
        } catch (Exception e) {
            throw new CustomException("Error findByProductId in SizeService " + e.getMessage());
        }
        return responses;
    }
}
