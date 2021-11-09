package com.local.orders.Services;

import com.local.orders.models.Order;

import java.util.List;

public interface OrderServices {
    Order save(Order order);
    Order findById(long id);
    List<Order> findAllAdvanceAmountGreaterThan();
}
