package com.raghav.paymentrouting.service;

import com.raghav.paymentrouting.entity.TransactionEntity;
import com.raghav.paymentrouting.model.PaymentRequest;
import com.raghav.paymentrouting.model.PaymentResponse;
import com.raghav.paymentrouting.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final TransactionRepository transactionRepository;

    public PaymentService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public PaymentResponse processPayment(PaymentRequest request) {

        TransactionEntity transaction = new TransactionEntity();
        transaction.setReferenceId(request.getReferenceId());
        transaction.setAmount(request.getAmount());
        transaction.setCurrency(request.getCurrency());
        transaction.setSelectedGateway("BANK_A");
        transaction.setStatus("SUCCESS");

        transactionRepository.save(transaction);

        return new PaymentResponse(
                "SUCCESS",
                "BANK_A",
                "Payment processed and saved successfully"
        );
    }

    public PaymentResponse getPaymentStatus(String referenceId) {

        return transactionRepository.findByReferenceId(referenceId)
                .map(txn -> new PaymentResponse(
                        txn.getStatus(),
                        txn.getSelectedGateway(),
                        "Payment status fetched successfully"
                ))
                .orElseGet(() -> new PaymentResponse(
                        "NOT_FOUND",
                        null,
                        "No transaction found for given referenceId"
                ));
    }
}