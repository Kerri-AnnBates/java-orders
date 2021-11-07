package com.local.orders.Services;

import com.local.orders.models.Customer;

import java.util.List;

public interface CustomerServices {
    Customer save(Customer customer);
    List<Customer> findAllCustomers();
    Customer findCustomerById(long id);
    List<Customer> findCustomerByLikeName(String subname);
}
