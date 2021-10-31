package com.local.orders.repositories;

import com.local.orders.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customer, Long> {
}
