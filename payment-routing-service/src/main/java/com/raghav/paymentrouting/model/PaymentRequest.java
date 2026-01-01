package com.raghav.paymentrouting.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private String referenceId;
    private double amount;
    private String currency;
    private String customerId;
}