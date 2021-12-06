package com.local.orders.Services;

import com.local.orders.models.Order;

import java.util.List;

public interface OrderServices {
    Order save(Order order);
    Order findById(long id);
    List<Order> findAllAdvanceAmountGreaterThan();
    Order update(Order order, long id);
    void delete(long id);
}
