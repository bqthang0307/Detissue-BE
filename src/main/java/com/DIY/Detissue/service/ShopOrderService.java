package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.ShopOrder;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.ShopOrderResponse;
import com.DIY.Detissue.repository.OrderLineRepository;
import com.DIY.Detissue.repository.ShopOrderRepository;
import com.DIY.Detissue.service.Imp.ShopOrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopOrderService implements ShopOrderServiceImp {
    @Autowired
    private ShopOrderRepository shopOrderRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    public List<ShopOrderResponse> findByUserId(int id) {
        List<ShopOrderResponse> responses = new ArrayList<>();
        try {
            List<ShopOrder> list = shopOrderRepository.findByUserId(id);
            for (ShopOrder shopOrder : list) {
                ShopOrderResponse response = new ShopOrderResponse();
                response.setId(shopOrder.getId());
                response.setTotal_price(shopOrder.getOrderTotal());
                response.setStatus(shopOrder.getOrderStatus().getStatus());
                response.setOrderTime(shopOrder.getOrderDate());
                // get the number of items in the order
                response.setItem_count(orderLineRepository.findByShopOrderId(shopOrder.getId()).size());
                responses.add(response);
            }
        } catch (Exception e) {
            throw new CustomException("Error findByUserId in ShopOrderService " + e.getMessage());
        }
        return responses;
    }
}
