package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.PaymentMode;
import com.crm.crm_backend.repository.PaymentModeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentModeServiceImpl {

    private final PaymentModeRepository repository;

    public PaymentMode save(PaymentMode paymentMode) {

        if (paymentMode.getId() != null) {

            PaymentMode dbPaymentMode = repository.findById(paymentMode.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Payment Mode not found."));

            dbPaymentMode.setPaymentMode(paymentMode.getPaymentMode());
            dbPaymentMode.setPaymentModeCode(paymentMode.getPaymentModeCode());
            dbPaymentMode.setDescription(paymentMode.getDescription());
            dbPaymentMode.setActive(paymentMode.getActive());

            dbPaymentMode.setUpdatedBy(paymentMode.getUpdatedBy());
            dbPaymentMode.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbPaymentMode);

        } else {

            if (repository.existsByPaymentModeIgnoreCase(paymentMode.getPaymentMode())) {
                throw new RuntimeException("Payment Mode already exists.");
            }

            if (repository.existsByPaymentModeCodeIgnoreCase(paymentMode.getPaymentModeCode())) {
                throw new RuntimeException("Payment Mode Code already exists.");
            }

            paymentMode.setCreatedAt(LocalDateTime.now());

            return repository.save(paymentMode);

        }

    }

    public List<PaymentMode> getAll() {

        return repository.findAll();

    }

    public PaymentMode getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payment Mode not found."));

    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

    public PaymentMode changeStatus(Long id, Boolean active) {

        PaymentMode paymentMode = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payment Mode not found."));

        paymentMode.setActive(active);
        paymentMode.setUpdatedAt(LocalDateTime.now());

        return repository.save(paymentMode);

    }

}