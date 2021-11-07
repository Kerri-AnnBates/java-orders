package com.local.orders.controllers;

import com.local.orders.Services.CustomerServices;
import com.local.orders.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomersController {

    @Autowired
    CustomerServices customerServices;

//    http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllCustomers() {
        List<Customer> list = customerServices.findAllCustomers();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

//    http://localhost:2019/customers/customer/7
    @GetMapping(value = "customer/{id}", produces = "application/json")
    public ResponseEntity<?> getCustomerById(@PathVariable long id) {
        Customer customer = customerServices.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

//    http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{subname}", produces = "application/json")
    public ResponseEntity<?> getCustomersByLikeName(@PathVariable String subname) {
        return new ResponseEntity<>(customerServices.findCustomerByLikeName(subname), HttpStatus.OK);
    }
}
