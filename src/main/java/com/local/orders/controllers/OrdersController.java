package com.local.orders.controllers;

import com.local.orders.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    @Autowired
    OrderServices orderServices;

    //    http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{id}", produces = "application/json")
    public ResponseEntity<?> getOrderById(@PathVariable long id) {
        return new ResponseEntity<>(orderServices.findById(id), HttpStatus.OK);
    }

//    http://localhost:2019/orders/advanceamount
    @GetMapping(value = "/advanceamount", produces = "application/json")
    public ResponseEntity<?> getOrderAdvanceAmount() {
        return new ResponseEntity<>(orderServices.findAllAdvanceAmountGreaterThan(), HttpStatus.OK);
    }
}
