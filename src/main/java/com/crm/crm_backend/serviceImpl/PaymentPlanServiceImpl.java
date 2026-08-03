package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.PaymentPlan;
import com.crm.crm_backend.repository.PaymentPlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentPlanServiceImpl {

    private final PaymentPlanRepository repository;

    public PaymentPlan save(PaymentPlan paymentPlan) {

        if (paymentPlan.getId() != null) {

            PaymentPlan dbPaymentPlan = repository.findById(paymentPlan.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Payment Plan not found."));

            dbPaymentPlan.setPaymentPlan(paymentPlan.getPaymentPlan());
            dbPaymentPlan.setShortName(paymentPlan.getShortName());
            dbPaymentPlan.setDescription(paymentPlan.getDescription());
            dbPaymentPlan.setActive(paymentPlan.getActive());

            dbPaymentPlan.setUpdatedBy(paymentPlan.getUpdatedBy());
            dbPaymentPlan.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbPaymentPlan);

        } else {

            if (repository.existsByPaymentPlanIgnoreCase(paymentPlan.getPaymentPlan())) {
                throw new RuntimeException("Payment Plan already exists.");
            }

            if (repository.existsByShortNameIgnoreCase(paymentPlan.getShortName())) {
                throw new RuntimeException("Short Name already exists.");
            }

            paymentPlan.setCreatedAt(LocalDateTime.now());

            return repository.save(paymentPlan);

        }

    }

    public List<PaymentPlan> getAll() {

        return repository.findAll();

    }

    public PaymentPlan getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payment Plan not found."));

    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

    public PaymentPlan changeStatus(Long id, Boolean active) {

        PaymentPlan paymentPlan = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payment Plan not found."));

        paymentPlan.setActive(active);
        paymentPlan.setUpdatedAt(LocalDateTime.now());

        return repository.save(paymentPlan);

    }

}