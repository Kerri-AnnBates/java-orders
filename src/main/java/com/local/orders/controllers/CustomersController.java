package com.local.orders.controllers;

import com.local.orders.Services.CustomerServices;
import com.local.orders.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping(value = "/customer", consumes = "application/json")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {
        customer.setCustcode(0);
        customer = customerServices.save(customer);

        // Create location header
        HttpHeaders responseHeader = new HttpHeaders();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{customerid}")
                .buildAndExpand(customer.getCustcode())
                .toUri();
        responseHeader.setLocation(uri);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping(value = "/customer/{id}", consumes = "application/json")
    public ResponseEntity<?> updateFullCustomer(@Valid @RequestBody Customer updateCustomer, @PathVariable long id) {
        updateCustomer.setCustcode(id);
        updateCustomer = customerServices.save(updateCustomer);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
