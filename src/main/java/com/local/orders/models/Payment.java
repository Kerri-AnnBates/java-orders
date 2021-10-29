package com.local.orders.models;

import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = true)
    private long paymentid;

    @Column(nullable = false)
    private String type;

}
