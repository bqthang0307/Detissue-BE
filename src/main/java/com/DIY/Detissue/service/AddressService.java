package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Address;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.request.AddressRequest;
import com.DIY.Detissue.repository.AddressRepository;
import com.DIY.Detissue.repository.CountryRepository;
import com.DIY.Detissue.service.Imp.AddressServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements AddressServiceImp {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public boolean addAddress(AddressRequest request) {
        try {
            Address address = new Address();
            address.setStreetAddress(request.getStreet_address());
            address.setCountry(countryRepository.findById(request.getCountryId()).orElseThrow());
            address.setTownCity(request.getTown_city());
            address.setCompany(request.getCompany());
            addressRepository.save(address);


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
