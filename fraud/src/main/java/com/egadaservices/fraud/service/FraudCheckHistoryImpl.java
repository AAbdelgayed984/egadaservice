package com.egadaservices.fraud.service;

import com.egadaservices.fraud.model.FraudCheckHistory;
import com.egadaservices.fraud.repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckHistoryImpl implements FraudCheckService{

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    @Override
    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
