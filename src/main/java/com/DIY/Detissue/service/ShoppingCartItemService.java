package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.ProductSkus;
import com.DIY.Detissue.entity.ShoppingCartItem;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.ShoppingCartItemResponse;
import com.DIY.Detissue.repository.*;
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
    private ProductSkusRepository productSkusRepository;
    @Autowired
    private CartRepository cartRepository;


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
//                attributeOptionsRepository.findByShoppingCartItemsId(item.getId()).forEach(attributeOptions -> {
//                    if (attributeOptions.getAttribute().getName().equals("color")) {
//                        response.setColor(attributeOptions.getValue());
//                    } else if (attributeOptions.getAttribute().getName().equals("size")) {
//                        response.setSize(attributeOptions.getValue());
//                    }
//                });
                responses.add(response);
            }
        } catch (Exception e) {
            throw new CustomException("Error findByUserId in ShoppingCartItemService " + e.getMessage());
        }
        return responses;
    }

    @Override
    public boolean addShoppingCartItem(int userId, int productId, int quantity) {
        try {
            ShoppingCartItem item = shoppingCartItemRepository.findByUserIdAndProductId(userId, productId);
            if (item != null) {
                item.setQuantity(item.getQuantity() + 1);
                shoppingCartItemRepository.save(item);
            } else {
                ShoppingCartItem newItem = new ShoppingCartItem();
                cartRepository.findByUserId(userId).ifPresentOrElse(cart -> {
                    newItem.setCart(cart);
                }, () -> {
                    throw new CustomException("Cart not found");
                });
//                productSkusRepository.findByProductIdAndAttributeOptions(productId,attributOptionsId).ifPresentOrElse(productSkus -> {
//                    newItem.setProductSkus(productSkus);
//                }, () -> {
//                    throw new CustomException("Product not found");
//                });
                newItem.setQuantity(quantity);
                shoppingCartItemRepository.save(newItem);
            }
        } catch (Exception e) {
            throw new CustomException("Error addShoppingCartItem in ShoppingCartItemService " + e.getMessage());
        }
        return true;
    }
    @Override
    public boolean deleteShopingCartItemById(int id) {
        try {
            ShoppingCartItem item = shoppingCartItemRepository.findById(id).
                                    orElseThrow(() -> new CustomException("ShoppingCartItem not found"));
            shoppingCartItemRepository.delete(item);
        } catch (Exception e) {
            throw new CustomException("Error deleteShopingCartItemById in ShoppingCartItemService " + e.getMessage());
        }
        return true;
    }
}
