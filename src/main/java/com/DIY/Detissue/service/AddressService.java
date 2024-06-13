package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Address;
import com.DIY.Detissue.entity.User;
import com.DIY.Detissue.entity.UserAddress;
import com.DIY.Detissue.entity.UserAddressId;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.request.AddressRequest;
import com.DIY.Detissue.repository.AddressRepository;
import com.DIY.Detissue.repository.CountryRepository;
import com.DIY.Detissue.repository.UserAddressRepository;
import com.DIY.Detissue.repository.UserRepository;
import com.DIY.Detissue.service.Imp.AddressServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements AddressServiceImp {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addAddress(AddressRequest request) {
        try {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new CustomException("User not found with id: " + request.getUserId()));

            Address address = new Address();
            address.setStreetAddress(request.getStreet_address());
            address.setCountry(countryRepository.findById(request.getCountryId())
                    .orElseThrow(() -> new CustomException("Country not found with id: " + request.getCountryId())));
            address.setTownCity(request.getTown_city());
            address.setCompany(request.getCompany());
            Address savedAddress = addressRepository.save(address);

            if (savedAddress.getId() == null) {
                throw new CustomException("Error saving address in AddressService");
            }

            UserAddress userAddress = new UserAddress();
            UserAddressId userAddressId = new UserAddressId();
            userAddressId.setAddressId(savedAddress.getId());
            userAddressId.setUserId(user.getId());
            userAddress.setId(userAddressId);
            userAddressRepository.save(userAddress);
        } catch (Exception e) {
            throw new CustomException("Error addAddress in AddressService " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean updateAddress(AddressRequest request) {
        try {
            Address address = addressRepository.findById(request.getId())
                    .orElseThrow(() -> new CustomException("Address not found with id: " + request.getId()));
            address.setStreetAddress(request.getStreet_address());

            address.setCountry(countryRepository.findById(request.getCountryId())
                    .orElseThrow(() -> new CustomException("Country not found with id: " + request.getCountryId())));
            address.setTownCity(request.getTown_city());
            address.setCompany(request.getCompany());
            addressRepository.save(address);
        } catch (Exception e) {
            throw new CustomException("Error updateAddress in AddressService " + e.getMessage());
        }
        return true;
    }
}
