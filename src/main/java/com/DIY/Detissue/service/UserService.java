package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Address;
import com.DIY.Detissue.entity.User;
import com.DIY.Detissue.entity.UserAddress;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.request.SignupRequest;
import com.DIY.Detissue.payload.request.UserRequest;
import com.DIY.Detissue.payload.response.AddressResponse;
import com.DIY.Detissue.payload.response.UserResponse;
import com.DIY.Detissue.repository.AddressRepository;
import com.DIY.Detissue.repository.UserRoleRepository;
import com.DIY.Detissue.repository.UserAddressRepository;
import com.DIY.Detissue.repository.UserRepository;
import com.DIY.Detissue.service.Imp.UserServiceImp;
import com.DIY.Detissue.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DateHelper dateHelper;
    @Autowired
    private UserRoleRepository userRoleRepository;

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

    @Override
    public List<AddressResponse> findAddressByUserId(int id) {
        List<AddressResponse> responses = new ArrayList<>();
        try {
            List<Address> list = addressRepository.findByUserId(id);
            for (Address address : list) {
                AddressResponse response = new AddressResponse();
                response.setId(address.getId());
                response.setStreet_address(address.getStreetAddress());
                response.setTown_city(address.getTownCity());
                response.setCountry(address.getCountry().getName());
                UserAddress userAddress = userAddressRepository.findByUserIdAndAddressId(id, address.getId());
                if (userAddress == null) throw new CustomException("UserAddress not found");
                response.setDefault(userAddress.getIsDefault());
                response.setShippingFee(address.getCountry().getShippingPrice());
                response.setCompany(address.getCompany());
                responses.add(response);
            }
        } catch (Exception e) {
            throw new CustomException("Error findAddressByUserId in UserService " + e.getMessage());
        }
        return responses;
    }

    @Override
    public User findByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new CustomException("Error findByUsername in UserService " + e.getMessage());
        }
    }

    @Override
    public boolean authorizeAdminByUserId(int id) {
        try {
            User user = userRepository.findById(id).get();
            if (user.getRole().getName().equals("admin")) return true;
        } catch (Exception e) {
            throw new CustomException("Error AuthorizeAdminByUserId in UserService " + e.getMessage());
        }
        return false;
    }

    @Override
    public UserResponse getUserById(int id) {
        try {
            User user = userRepository.findById(id).get();
            UserResponse response = new UserResponse();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setPhone(user.getPhone());
            response.setFullname(user.getFullname());
            response.setCreateTime(user.getCreate_at().toString());
            response.setLastLoginTime(user.getLast_login().toString());
            return response;
        } catch (Exception e) {
            throw new CustomException("Error getUserById in UserService " + e.getMessage());
        }
    }

    @Override
    public boolean updateUser(UserRequest request) {
        try {
            User user = userRepository.findById(request.getId()).get();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setFullname(request.getFullname());

            if (request.getBirthDay() == null) throw new CustomException("BirthDay cannot be null");

            user.setBirthDay(dateHelper.convertStringToDate(request.getBirthDay()));
            if (request.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            } else {
                throw new CustomException("Password cannot be null");
            }
            userRepository.save(user);
        } catch (Exception e) {
            throw new CustomException("Error updateUser in UserService " + e.getMessage());
        }
        return true;
    }
}
