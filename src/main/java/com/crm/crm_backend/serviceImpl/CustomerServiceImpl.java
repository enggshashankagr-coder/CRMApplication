package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.DTO.CustomerRequest;
import com.crm.crm_backend.entity.Customer;
import com.crm.crm_backend.repository.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl {

    private final CustomerRepository repository;


    // =========================================================
    // CREATE / UPDATE
    // =========================================================

    @Transactional
    public Customer save(CustomerRequest request) {

        Customer customer;


        // =====================================================
        // UPDATE
        // =====================================================

        if (request.getId() != null) {

            customer =
                    repository.findById(request.getId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Customer not found."));


            // -------------------------------------------------
            // Enquiry No duplicate check
            // -------------------------------------------------

            if (request.getEnquiryNo() != null &&
                    !request.getEnquiryNo()
                            .equalsIgnoreCase(
                                    customer.getEnquiryNo())) {

                if (repository
                        .existsByEnquiryNoIgnoreCase(
                                request.getEnquiryNo())) {

                    throw new RuntimeException(
                            "Enquiry No already exists.");
                }
            }


            // -------------------------------------------------
            // Customer Name duplicate check
            // -------------------------------------------------

            if (request.getCustomerName() != null &&
                    !request.getCustomerName()
                            .equalsIgnoreCase(
                                    customer.getCustomerName())) {

                if (repository
                        .existsByCustomerNameIgnoreCase(
                                request.getCustomerName())) {

                    throw new RuntimeException(
                            "Customer Name already exists.");
                }
            }


            // -------------------------------------------------
            // Customer Code duplicate check
            // -------------------------------------------------

            if (request.getCustomerCode() != null &&
                    !request.getCustomerCode().isBlank() &&
                    !request.getCustomerCode()
                            .equalsIgnoreCase(
                                    customer.getCustomerCode())) {

                if (repository
                        .existsByCustomerCodeIgnoreCase(
                                request.getCustomerCode())) {

                    throw new RuntimeException(
                            "Customer Code already exists.");
                }
            }


            customer.setUpdatedAt(
                    LocalDateTime.now());

            customer.setUpdatedBy(
                    request.getUpdatedBy());
        }


        // =====================================================
        // CREATE
        // =====================================================

        else {

            // -------------------------------------------------
            // Enquiry No
            // -------------------------------------------------

            if (repository
                    .existsByEnquiryNoIgnoreCase(
                            request.getEnquiryNo())) {

                throw new RuntimeException(
                        "Enquiry No already exists.");
            }


            // -------------------------------------------------
            // Customer Name
            // -------------------------------------------------

            if (repository
                    .existsByCustomerNameIgnoreCase(
                            request.getCustomerName())) {

                throw new RuntimeException(
                        "Customer Name already exists.");
            }


            // -------------------------------------------------
            // Customer Code
            // -------------------------------------------------

            if (request.getCustomerCode() != null &&
                    !request.getCustomerCode().isBlank()) {

                if (repository
                        .existsByCustomerCodeIgnoreCase(
                                request.getCustomerCode())) {

                    throw new RuntimeException(
                            "Customer Code already exists.");
                }
            }


            customer = new Customer();

            customer.setCreatedAt(
                    LocalDateTime.now());

            customer.setCreatedBy(
                    request.getCreatedBy());
        }


        // =====================================================
        // ENQUIRY
        // =====================================================

        customer.setEnquiryDate(
                request.getEnquiryDate());

        customer.setEnquiryNo(
                request.getEnquiryNo());


        // =====================================================
        // CUSTOMER DETAILS
        // =====================================================

        customer.setCustomerName(
                request.getCustomerName());

        customer.setShortName(
                request.getShortName());

        customer.setCustomerCode(
                request.getCustomerCode());

        customer.setAddress(
                request.getAddress());

        customer.setContactPerson(
                request.getContactPerson());

        customer.setMobile(
                request.getMobile());

        customer.setTelephone(
                request.getTelephone());

        customer.setWebAddress(
                request.getWebAddress());

        customer.setEmail(
                request.getEmail());

        customer.setCurrency(
                request.getCurrency());


        // =====================================================
        // LOCATION
        // =====================================================

        customer.setCountryId(
                request.getCountryId());

        customer.setStateId(
                request.getStateId());

        customer.setCityId(
                request.getCityId());

        customer.setSubLocationId(
                request.getSubLocationId());

        customer.setPinCodeId(
                request.getPinCodeId());


        // =====================================================
        // CUSTOMER MANAGEMENT
        // =====================================================

        customer.setAcManagerId(
                request.getAcManagerId());

        customer.setInitiatedById(
                request.getInitiatedById());

        customer.setIndustryId(
                request.getIndustryId());

        customer.setSegmentId(
                request.getSegmentId());

        customer.setSourceId(
                request.getSourceId());

        customer.setReferenceId(
                request.getReferenceId());

        customer.setStatusId(
                request.getStatusId());

        customer.setCategoryId(
                request.getCategoryId());


        // =====================================================
        // ORDER
        // =====================================================

        customer.setOrderExpectedBy(
                request.getOrderExpectedBy());

        customer.setRemarks(
                request.getRemarks());


        // =====================================================
        // ACTIVE
        // =====================================================

        if (request.getActive() != null) {

            customer.setActive(
                    request.getActive());

        } else if (customer.getActive() == null) {

            customer.setActive(true);
        }


        return repository.save(customer);
    }


    // =========================================================
    // GET ALL
    // =========================================================

    public List<Customer> getAll() {

        return repository.findAll();
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    public Customer getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found."));
    }


    // =========================================================
    // ENABLE / DISABLE
    // =========================================================

    @Transactional
    public Customer changeStatus(
            Long id,
            Boolean active) {

        Customer customer =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Customer not found."));

        customer.setActive(active);

        customer.setUpdatedAt(
                LocalDateTime.now());

        return repository.save(customer);
    }


    // =========================================================
    // DELETE
    // =========================================================

    @Transactional
    public void delete(Long id) {

        Customer customer =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Customer not found."));

        repository.delete(customer);
    }
}