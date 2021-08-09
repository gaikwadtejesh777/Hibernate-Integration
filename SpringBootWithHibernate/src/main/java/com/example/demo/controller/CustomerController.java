package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.data.CustomerData;
import com.example.demo.service.CustomerService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Resource(name = "customerService")
    private CustomerService customerService;

    @GetMapping
    public List < CustomerData > getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public CustomerData getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customer")
    public CustomerData saveCustomer(final @RequestBody CustomerData customerData) {
        return customerService.saveCustomer(customerData);
    }

    @DeleteMapping("/customer/{id}")
    public Boolean deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

}
