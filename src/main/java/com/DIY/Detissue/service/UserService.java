package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.User;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.request.SignupRequest;
import com.DIY.Detissue.repository.UserRepository;
import com.DIY.Detissue.service.Imp.UserServiceImp;
import com.DIY.Detissue.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DateHelper dateHelper;

    @Override
    public boolean addUser(SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null)
            throw new CustomException("Username existed");
        try {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setFullname(request.getFullname());
            user.setCreate_at(dateHelper.getInternetTime());
            user.setLast_login(dateHelper.getInternetTime());
            userRepository.save(user);
        } catch (Exception e) {
            throw new CustomException("Error addUser in UserService " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean updateLoginTime(String username) {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) throw new CustomException("User not found");
            user.setLast_login(dateHelper.getInternetTime());
            userRepository.save(user);
        } catch (Exception e) {
            throw new CustomException("Error updateLoginTime in UserService " + e.getMessage());
        }
        return true;
    }
}
