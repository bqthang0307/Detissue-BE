package com.DIY.Detissue.service;

import com.DIY.Detissue.payload.response.OrderStatusResponse;
import com.DIY.Detissue.repository.OrderStatusRepository;
import com.DIY.Detissue.repository.ShopOrderRepository;
import com.DIY.Detissue.service.Imp.OrderStatusServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusService implements OrderStatusServiceImp {
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Override
    public List<OrderStatusResponse> findAll() {
        List<OrderStatusResponse> responses = new ArrayList<>();
        try {
            orderStatusRepository.findAll().forEach(shopOrder -> {
                OrderStatusResponse response = new OrderStatusResponse();
                response.setId(shopOrder.getId());
                response.setStatus(shopOrder.getStatus());
                responses.add(response);
            });
        } catch (Exception e) {
            throw new RuntimeException("Error findAllByShopOrderId in OrderStatusService " + e.getMessage());
        }
        return responses;
    }
}
