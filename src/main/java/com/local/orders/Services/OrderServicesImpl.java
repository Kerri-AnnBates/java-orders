package com.local.orders.Services;

import com.local.orders.models.Order;
import com.local.orders.models.Payment;
import com.local.orders.repositories.CustomersRepository;
import com.local.orders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service
public class OrderServicesImpl implements OrderServices {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Transactional
    @Override
    public Order save(Order order) {
        Order newOrder = new Order();

        if (order.getOrdnum() != 0) {
            ordersRepository.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " not found!"));

            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setOrdamount(order.getOrdamount());

        if(customersRepository.findById(order.getCustomer().getCustcode()).isPresent()) {
            newOrder.setCustomer(order.getCustomer());
        } else {
            throw new EntityNotFoundException("Customer " + order.getCustomer().getCustcode() + " not found!");
        }

        newOrder.getPayments().clear();
        for (Payment pymt : order.getPayments()) {
            Payment payment = new Payment();

            payment.setPaymentid(pymt.getPaymentid());
            newOrder.getPayments().add(payment);
        }

        return ordersRepository.save(newOrder);
    }

    @Override
    public Order findById(long id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order id " + id + " not found!"));
    }

    @Override
    public List<Order> findAllAdvanceAmountGreaterThan() {
        return ordersRepository.findAllByAdvanceamountGreaterThan(0.00);
    }

    @Transactional
    @Override
    public Order update(Order order, long id) {
        Order orderToUpdate = ordersRepository.findById(order.getOrdnum())
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " not found!"));

        if(order.getCustomer() != null) {
            orderToUpdate.setCustomer(order.getCustomer());
        }

        if(order.getOrderdescription() != null) {
            orderToUpdate.setOrderdescription(order.getOrderdescription());
        }

        if(order.getPayments().size() > 0) {
            orderToUpdate.getPayments().clear();

            for (Payment pymt : order.getPayments()) {
                Payment payment = new Payment();

                payment.setPaymentid(pymt.getPaymentid());
                orderToUpdate.getPayments().add(payment);
            }
        }

        if(order.hasAdvanceamount) {
            orderToUpdate.setAdvanceamount(order.getAdvanceamount());
        }

        if(order.hasOrdamount) {
            orderToUpdate.setOrdamount(order.getOrdamount());
        }

        return ordersRepository.save(orderToUpdate);
    }
}
