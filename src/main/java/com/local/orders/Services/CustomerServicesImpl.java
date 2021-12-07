package com.local.orders.Services;

import com.local.orders.models.Customer;
import com.local.orders.models.Order;
import com.local.orders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CustomerServicesImpl implements CustomerServices {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private AgentServices agentServices;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();

        if(customer.getCustcode() != 0) {
            customersRepository.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer id " + customer.getCustcode() + " not found!"));
            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setAgent(agentServices.findById(customer.getAgent().getAgentcode()));

        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders()) {
            Order order = new Order();

            order.setAdvanceamount(o.getAdvanceamount());
            order.setOrderdescription(o.getOrderdescription());
            order.setOrdamount(o.getOrdamount());
            order.setPayments(o.getPayments());
            order.setCustomer(newCustomer);

            newCustomer.getOrders().add(order);
        }

        return customersRepository.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer update(Customer customer) {
        Customer customerToUpdate = customersRepository.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer id " + customer.getCustcode() + " not found!"));

        if(customer.getCustcity() != null) {
            customerToUpdate.setCustcity(customer.getCustcity());
        }

        if(customer.getCustname() != null) {
            customerToUpdate.setCustname(customer.getCustname());
        }

        if(customer.getCustcountry() != null) {
            customerToUpdate.setCustcountry(customer.getCustcountry());
        }

        if(customer.getGrade() != null) {
            customerToUpdate.setGrade(customer.getGrade());
        }

        if(customer.getPhone() != null) {
            customerToUpdate.setPhone(customer.getPhone());
        }

        if(customer.getWorkingarea() != null) {
            customerToUpdate.setWorkingarea(customer.getWorkingarea());
        }

        if(customer.getAgent() != null) {
            customerToUpdate.setAgent(agentServices.findById(customer.getAgent().getAgentcode()));
        }

        if(customer.hasOutstandingamt) {
            customerToUpdate.setOutstandingamt(customer.getOutstandingamt());
        }

        if(customer.hasOpeningamt) {
            customerToUpdate.setOpeningamt(customer.getOpeningamt());
        }

        if(customer.hasPaymentamt) {
            customerToUpdate.setPaymentamt(customer.getPaymentamt());
        }

        if(customer.hasReceiveamt) {
            customerToUpdate.setReceiveamt(customer.getReceiveamt());
        }

        if(customer.getOrders().size() > 0) {
            customerToUpdate.getOrders().clear();

            for (Order o : customer.getOrders()) {
                Order order = new Order();

                order.setAdvanceamount(o.getAdvanceamount());
                order.setOrderdescription(o.getOrderdescription());
                order.setOrdamount(o.getOrdamount());
                order.setPayments(o.getPayments());
                order.setCustomer(customerToUpdate);

                customerToUpdate.getOrders().add(order);
            }
        }

        return customersRepository.save(customerToUpdate);
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> list = new ArrayList<>();
        customersRepository.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        return customersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " not found!"));
    }

    @Override
    public List<Customer> findCustomerByLikeName(String subname) {
        return customersRepository.findByCustnameContainingIgnoreCase(subname);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if(customersRepository.findById(id).isPresent()) {
            customersRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer " + id + " not found!");
        }
    }
}
