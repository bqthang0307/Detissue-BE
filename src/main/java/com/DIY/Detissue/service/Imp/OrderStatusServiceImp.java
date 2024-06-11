package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.response.OrderStatusResponse;

import java.util.List;

public interface OrderStatusServiceImp {
    List<OrderStatusResponse> findAll();
}
