package com.local.orders.repositories;

import com.local.orders.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByAdvanceamountGreaterThan(double amt);
}
