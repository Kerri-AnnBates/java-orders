package com.local.orders.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long ordnum;

    private double ordamount;
    private double advanceamount;

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    private Customer custcode;
    private String orderdescription;

    @ManyToMany
    @JoinTable(name = "orderspayment",
                joinColumns = @JoinColumn(name = "ordnum"),
                inverseJoinColumns = @JoinColumn(name = "paymentid"))
    Set<Payment> payments = new HashSet<>();

    public Order() {
    }
}
