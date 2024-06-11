package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.request.AddressRequest;

public interface AddressServiceImp {
    boolean addAddress(AddressRequest request);
    boolean updateAddress(AddressRequest request);
}
