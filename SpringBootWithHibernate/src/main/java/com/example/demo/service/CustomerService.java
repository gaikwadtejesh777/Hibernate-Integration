package com.example.demo.service;
import com.example.demo.data.CustomerData;
import java.util.List;


public interface CustomerService {

    CustomerData saveCustomer(CustomerData customer);
    boolean deleteCustomer(final Long customerId);
    List<CustomerData> getAllCustomers();
    CustomerData getCustomerById(final Long customerId);
}