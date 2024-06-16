package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.response.SizeResponse;

import java.util.List;

public interface SizeServiceImp {
    List<SizeResponse> findByProductId(int id);
}
