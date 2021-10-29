package com.local.orders.models;

import javax.persistence.*;

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

    public Order() {
    }
}
