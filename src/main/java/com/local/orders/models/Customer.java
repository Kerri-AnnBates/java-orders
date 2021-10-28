package com.local.orders.models;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long custcode;

    @Column(nullable = false)
    private String custname;

    private String custcity;
    private String workingarea;
    private String grade;
    private double openingamt;
    private double receiveamt;
    private double outstandingamt;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "agent", nullable = false)
    private Agent agent;

    public Customer() {}

    public Customer(String custname,
                    String custcity,
                    String workingarea,
                    String grade,
                    double openingamt,
                    double receiveamt,
                    double outstandingamt,
                    String phone,
                    Agent agent) {
        this.custname = custname;
        this.custcity = custcity;
        this.workingarea = workingarea;
        this.grade = grade;
        this.openingamt = openingamt;
        this.receiveamt = receiveamt;
        this.outstandingamt = outstandingamt;
        this.phone = phone;
        this.agent = agent;
    }
}
