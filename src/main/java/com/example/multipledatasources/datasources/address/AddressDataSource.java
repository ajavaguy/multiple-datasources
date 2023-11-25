package com.example.multipledatasources.datasources.address;

import com.example.multipledatasources.model.address.Address;
import com.example.multipledatasources.model.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDataSource implements AddressRepository {
    private AddressAPIDataSource addressAPIDataSource;

    @Autowired
    public AddressDataSource(AddressAPIDataSource addressAPIDataSource) {
        this.addressAPIDataSource = addressAPIDataSource;
    }

    @Override
    public Address findBy(Integer employeeId) {
        return addressAPIDataSource.findBy(employeeId);
    }
}
