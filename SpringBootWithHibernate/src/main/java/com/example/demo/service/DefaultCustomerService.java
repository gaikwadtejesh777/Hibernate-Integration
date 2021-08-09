package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.CustomerData;
import com.example.demo.dto.Customer;
import com.example.demo.repository.CustomerRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("customerService")
public class DefaultCustomerService implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

   
    @Override
    public CustomerData saveCustomer(CustomerData customer) {
        Customer customerModel = populateCustomerEntity(customer);
        return populateCustomerData(customerRepository.save(customerModel));
    }

  
    @Override
    public boolean deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
        return true;
    }

   
    @Override
    public List<CustomerData> getAllCustomers() {
        List<CustomerData> customers = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAll();
        customerList.forEach(customer -> {
            customers.add(populateCustomerData(customer));
        });
        return customers;
    }

  
    @Override
    public CustomerData getCustomerById(Long customerId) {
        return populateCustomerData( customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found")));
    }

    private CustomerData populateCustomerData(final Customer customer){
        CustomerData customerData = new CustomerData();
        customerData.setId(customer.getId());
        customerData.setFirstName(customer.getFirstName());
        customerData.setLastName(customer.getLastName());
        customerData.setEmail(customer.getEmail());
        return  customerData;
    }

   
    private Customer populateCustomerEntity(CustomerData customerData){
        Customer customer = new Customer();
        customer.setFirstName(customerData.getFirstName());
        customer.setLastName(customerData.getLastName());
        customer.setEmail(customerData.getEmail());
        return  customer;
    }
}
