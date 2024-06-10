package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Cart;
import com.DIY.Detissue.entity.User;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.repository.CartRepository;
import com.DIY.Detissue.repository.UserRepository;
import com.DIY.Detissue.service.Imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements CartServiceImp {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean addCartByUserId(int id) {
        try {
            cartRepository.findByUserId(id).ifPresentOrElse(cart -> {
            }, () -> {
                User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found"));
                Cart cart = new Cart();
                cart.setUser(user);
                cartRepository.save(cart);
            });
        }catch (Exception e){
            throw new CustomException("Error addCartByUserId in CartService " + e.getMessage());
        }
        return true;
    }
}
