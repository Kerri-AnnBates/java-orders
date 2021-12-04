package com.local.orders.controllers;

import com.local.orders.Services.OrderServices;
import com.local.orders.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    @PostMapping(value = "/order", consumes = "application/json")
    public ResponseEntity<?> addOrder(@Valid @RequestBody Order order) {
        order.setOrdnum(0);
        order = orderServices.save(order);

        HttpHeaders responseHeader = new HttpHeaders();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{orderId}")
                .buildAndExpand(order.getOrdnum())
                .toUri();
        responseHeader.setLocation(uri);

        return new ResponseEntity<>(null, responseHeader, HttpStatus.CREATED);
    }
}
