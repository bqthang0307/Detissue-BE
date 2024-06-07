package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.ProductSkus;
import com.DIY.Detissue.entity.ShoppingCartItem;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.ShoppingCartItemResponse;
import com.DIY.Detissue.repository.AttributeOptionsRepository;
import com.DIY.Detissue.repository.ProductSkusRepository;
import com.DIY.Detissue.repository.ShoppingCartItemRepository;
import com.DIY.Detissue.service.Imp.ShoppingCartItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ShoppingCartItemService implements ShoppingCartItemServiceImp {
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;
    @Autowired
    private AttributeOptionsRepository attributeOptionsRepository;
    @Override
    public List<ShoppingCartItemResponse> findByUserId(int id) {
        List<ShoppingCartItemResponse> responses = new ArrayList<>();
        try {
            List<ShoppingCartItem> list = shoppingCartItemRepository.findByUserId(id);
            for (ShoppingCartItem item : list) {
                ShoppingCartItemResponse response = new ShoppingCartItemResponse();
                response.setId(item.getId());
                response.setQuantity(item.getQuantity());
                ProductSkus productSkus = item.getProductSkus();
                response.setName(productSkus.getProduct().getName());
                response.setImage(productSkus.getProduct().getImage());
                response.setPrice(productSkus.getPrice());
                attributeOptionsRepository.findByShoppingCartItemsId(item.getId()).forEach(attributeOptions -> {
                    if (attributeOptions.getAttribute().getName().equals("color")) {
                        response.setColor(attributeOptions.getValue());
                    } else if (attributeOptions.getAttribute().getName().equals("size")) {
                        response.setSize(attributeOptions.getValue());
                    }
                });
                responses.add(response);
            }
        }catch (Exception e) {
            throw new CustomException("Error findByUserId in ShoppingCartItemService " + e.getMessage());
        }
        return responses;
    }
}
