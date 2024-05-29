package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.request.SignupRequest;

public interface UserServiceImp {
    boolean addUser(SignupRequest request);
    boolean updateLoginTime(String username);
}
