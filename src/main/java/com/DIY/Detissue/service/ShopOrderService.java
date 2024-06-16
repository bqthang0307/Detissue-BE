package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.OrderLine;
import com.DIY.Detissue.entity.ShopOrder;
import com.DIY.Detissue.entity.ShoppingCartItem;
import com.DIY.Detissue.entity.User;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.request.CreateShopOrderRequest;
import com.DIY.Detissue.payload.response.ShopOrderResponse;
import com.DIY.Detissue.repository.*;
import com.DIY.Detissue.service.Imp.ShopOrderServiceImp;
import com.DIY.Detissue.utils.DateHelper;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private DateHelper dateHelper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProductSkusRepository productSkusRepository;
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

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

    @Override
    public boolean addShopOrder(CreateShopOrderRequest request) {
        // check if the user has a default address
        if (addressRepository.findDefaultAddressByUserId(request.getUserId()) == null) {
            throw new CustomException("No default address found for user");
        }
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemRepository.findByUserId(request.getUserId());
        if (shoppingCartItems.size() == 0) {
            throw new CustomException("No items found in the shopping cart");
        }
        try {
            ShopOrder shopOrder = new ShopOrder();
            // set the order details
            shopOrder.setShipingAddress(addressRepository.findDefaultAddressByUserId(request.getUserId()));
            shopOrder.setUser(userRepository.findById(request.getUserId()).get());
            shopOrder.setOrderTotal(request.getOrderTotal());
            shopOrder.setOrderStatus(orderStatusRepository.findById(1).get());
            shopOrder.setOrderDate(dateHelper.getInternetTime());
            shopOrder.setNote(request.getNote());
            shopOrderRepository.save(shopOrder);

            // get the shopping cart items
            List<OrderLine> orderLines = new ArrayList<>();
            long shippingFee = addressRepository.findDefaultAddressByUserId(request.getUserId()).getCountry().getShippingPrice();
            for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
                OrderLine orderLine = new OrderLine();
                orderLine.setProductSkus(shoppingCartItem.getProductSkus());
                orderLine.setQuantity(shoppingCartItem.getQuantity());
                orderLine.setShopOrder(shopOrder);
                orderLine.setPrice(shoppingCartItem.getProductSkus().getPrice() * shoppingCartItem.getQuantity() + shippingFee);
                orderLines.add(orderLine);
            }

            orderLineRepository.saveAll(orderLines);
            shoppingCartItemRepository.deleteAll(shoppingCartItems);
        } catch (Exception e) {
            throw new CustomException("Error addShopOrder in ShopOrderService " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean updateShopOrderStatus(int id, int status, int userId) {
        ShopOrder shopOrder = shopOrderRepository.findById(id).get();
        User user = userRepository.findById(userId).get();

        // check if the user has permission to update the order
        if (user.getRole().getId() != 1 && shopOrder.getUser().getId() != userId) {
            throw new CustomException("User does not have permission to update this order");
        }

        try {
            shopOrder.setOrderStatus(orderStatusRepository.findById(status).get());
            shopOrderRepository.save(shopOrder);
        } catch (Exception e) {
            throw new CustomException("Error updateShopOrderStatus in ShopOrderService " + e.getMessage());
        }
        return true;
    }

    @Override
    public List<ShopOrderResponse> findAllShopOrder(int page, int size) {
        List<ShopOrderResponse> responses = new ArrayList<>();
        try {
            List<ShopOrder> list = shopOrderRepository.findAll();

            for (ShopOrder shopOrder : list) {
                ShopOrderResponse response = new ShopOrderResponse();
                response.setId(shopOrder.getId());
                response.setTotal_price(shopOrder.getOrderTotal());
                response.setStatus(shopOrder.getOrderStatus().getStatus());
                response.setOrderTime(shopOrder.getOrderDate());
                response.setItem_count(orderLineRepository.findByShopOrderId(shopOrder.getId()).size());
                response.setUserName(shopOrder.getUser().getUsername());
                response.setUserId(shopOrder.getUser().getId());
                response.setShippingAddress(shopOrder.getShipingAddress().getCountry().getName() +
                        ", " + shopOrder.getShipingAddress().getTownCity());
                response.setShippingAddressId(shopOrder.getShipingAddress().getId());
                response.setPaymentMethod(shopOrder.getPaymentMethod().getPaymentType().getValue());
                response.setPaymentMethodId(shopOrder.getPaymentMethod().getId());
                responses.add(response);
            }
        } catch (Exception e) {
            throw new CustomException("Error findAllShopOrder in ShopOrderService " + e.getMessage());
        }
        return responses;
    }

    @Override
    public ShopOrderResponse findById(int id) {
        ShopOrderResponse response = new ShopOrderResponse();
        try {
            ShopOrder shopOrder = shopOrderRepository.findById(id).get();
            response.setId(shopOrder.getId());
            response.setTotal_price(shopOrder.getOrderTotal());
            response.setStatus(shopOrder.getOrderStatus().getStatus());
            response.setOrderTime(shopOrder.getOrderDate());
            response.setItem_count(orderLineRepository.findByShopOrderId(shopOrder.getId()).size());
            response.setUserName(shopOrder.getUser().getUsername());
            response.setUserId(shopOrder.getUser().getId());
            response.setShippingAddress(shopOrder.getShipingAddress().getCountry().getName() +
                    ", " + shopOrder.getShipingAddress().getTownCity());
            response.setShippingAddressId(shopOrder.getShipingAddress().getId());
            response.setPaymentMethod(shopOrder.getPaymentMethod().getPaymentType().getValue());
            response.setPaymentMethodId(shopOrder.getPaymentMethod().getId());
        } catch (Exception e) {
            throw new CustomException("Error findById in ShopOrderService " + e.getMessage());
        }
        return response;
    }
}
