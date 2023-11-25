package com.example.multipledatasources.model.address;

public interface AddressRepository {
    Address findBy(Integer employeeId);
}
