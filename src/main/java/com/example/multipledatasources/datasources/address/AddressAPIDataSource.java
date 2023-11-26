package com.example.multipledatasources.datasources.address;

import com.example.multipledatasources.exception.EmployeeNotFoundException;
import com.example.multipledatasources.infrastructure.circuitbreaker.CircuitBreakerName;
import com.example.multipledatasources.model.address.Address;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class AddressAPIDataSource {
    private RestTemplate restTemplate;

    @Autowired
    public AddressAPIDataSource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = CircuitBreakerName.GET_ADDRESS_BY_EMPLOYEE_ID, fallbackMethod = "fallbackGetAddressByEmployeeId")
    public Address findBy(Integer employeeId) {
        try {
            AddressJSON addressJSON = restTemplate.getForObject(addressUrl(employeeId), AddressJSON.class);
            return Address.of(addressJSON);
        } catch(HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new EmployeeNotFoundException(String.format("Employee: %s not found!", employeeId));
            }
            throw e;
        }
    }

    public Address fallbackGetAddressByEmployeeId(Integer employeeId, CallNotPermittedException exception) {
        return new Address();
    }

    private String addressUrl(Integer employeeId) {
        String url = String.format("%s/address/%s", "http://localhost:8082", employeeId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        return builder.toUriString();
    }
}
