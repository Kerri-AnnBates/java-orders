package com.local.orders.Services;

import com.local.orders.models.Payment;
import com.local.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PaymentServicesImpl implements PaymentServices {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    @Override
    public Payment save(Payment payment) {
        return paymentRepository
                .save(payment);
    }
}
