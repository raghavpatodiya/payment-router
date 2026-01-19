package com.raghav.paymentrouting.repository;

import com.raghav.paymentrouting.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
 
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    Optional<TransactionEntity> findByReferenceId(String referenceId);

}
