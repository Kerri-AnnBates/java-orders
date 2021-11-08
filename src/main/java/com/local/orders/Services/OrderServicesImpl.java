package com.local.orders.Services;

import com.local.orders.models.Order;
import com.local.orders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class OrderServicesImpl implements OrderServices {

    @Autowired
    private OrdersRepository ordersRepository;

    @Transactional
    @Override
    public Order save(Order order) {
        return ordersRepository.save(order);
    }

    @Override
    public Order findById(long id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order id " + id + " not found!"));
    }
}
