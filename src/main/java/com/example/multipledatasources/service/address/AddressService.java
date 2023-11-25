package com.example.multipledatasources.service.address;

import com.example.multipledatasources.model.address.Address;
import com.example.multipledatasources.model.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address findBy(Integer employeeId) {
        return addressRepository.findBy(employeeId);
    }
}
