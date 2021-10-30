package com.local.orders.Services;

import com.local.orders.models.Customer;
import com.local.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomerServicesImpl implements CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
