package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.entity.User;
import com.DIY.Detissue.payload.request.SignupRequest;
import com.DIY.Detissue.payload.request.UserRequest;
import com.DIY.Detissue.payload.response.AddressResponse;
import com.DIY.Detissue.payload.response.UserResponse;

import java.util.List;

public interface UserServiceImp {
    boolean addUser(SignupRequest request);
    boolean updateLoginTime(String username);

    List<AddressResponse> findAddressByUserId(int id);
    User findByUsername(String username);
    boolean authorizeAdminByUserId(int id);

    UserResponse getUserById(int id);
    boolean updateUser(UserRequest request);
}
