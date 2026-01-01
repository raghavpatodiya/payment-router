package com.raghav.paymentrouting.service;

import org.springframework.stereotype.Service;
import com.raghav.paymentrouting.model.*;

@Service
public class PaymentService {

    public PaymentResponse processPayment(PaymentRequest request) {
        // temporary hardcoded response so app runs
        return new PaymentResponse(
                "SUCCESS",
                "BANK_A",
                "Payment processed successfully"
        );
    }
}