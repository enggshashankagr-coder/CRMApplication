package com.crm.crm_backend.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.crm.crm_backend.DTO.CompanyRequest;
import com.crm.crm_backend.entity.Company;
import com.crm.crm_backend.repository.CompanyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl {

    private final CompanyRepository repository;

    @Value("${file.upload-dir:uploads/company}")
    private String uploadDirectory;


    // =========================================================
    // CREATE / UPDATE COMPANY + IMAGE
    // =========================================================

    @Transactional
    public Company save(CompanyRequest request) {

        Company company;

        /*
         * UPDATE
         */
        if (request.getId() != null) {

            company = repository.findById(request.getId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Company not found."));

            company.setUpdatedAt(
                    LocalDateTime.now());
        }

        /*
         * CREATE
         */
        else {

            if (repository.existsByCompanyNameIgnoreCase(
                    request.getCompanyName())) {

                throw new RuntimeException(
                        "Company Name already exists.");
            }

            company = new Company();

            company.setCreatedAt(
                    LocalDateTime.now());
        }


        // =====================================================
        // COMPANY DETAILS
        // =====================================================

        company.setCompanyName(
                request.getCompanyName());

        company.setMobile(
                request.getMobile());

        company.setPhone(
                request.getPhone());

        company.setAddress(
                request.getAddress());

        company.setGstin(
                request.getGstin());

        company.setPanNo(
                request.getPanNo());

        company.setEmail(
                request.getEmail());

        company.setEmail1(
                request.getEmail1());

        company.setCountryId(
                request.getCountryId());

        company.setStateId(
                request.getStateId());

        company.setCityId(
                request.getCityId());

        company.setPinCodeId(
                request.getPinCodeId());

        if (request.getActive() != null) {

            company.setActive(
                    request.getActive());
        }

        if (request.getCreatedBy() != null) {

            company.setCreatedBy(
                    request.getCreatedBy());
        }


        // =====================================================
        // IMAGE
        // =====================================================

        if (request.getFile() != null &&
                !request.getFile().isEmpty()) {

            saveImage(
                    company,
                    request.getFile());
        }


        return repository.save(company);
    }


    // =========================================================
    // SAVE IMAGE
    // =========================================================

    private void saveImage(
            Company company,
            org.springframework.web.multipart.MultipartFile file) {

        try {

            // -------------------------------------------------
            // Validate content type
            // -------------------------------------------------

            String contentType =
                    file.getContentType();

            if (contentType == null ||
                    !contentType.startsWith("image/")) {

                throw new RuntimeException(
                        "Only image files are allowed.");
            }


            // -------------------------------------------------
            // Maximum 5 MB
            // -------------------------------------------------

            long maxSize =
                    5 * 1024 * 1024;

            if (file.getSize() > maxSize) {

                throw new RuntimeException(
                        "Image size must not exceed 5 MB.");
            }


            // -------------------------------------------------
            // Create directory
            // -------------------------------------------------

            Path uploadPath =
                    Paths.get(uploadDirectory)
                            .toAbsolutePath()
                            .normalize();

            Files.createDirectories(
                    uploadPath);


            // -------------------------------------------------
            // Delete old physical image
            // -------------------------------------------------

            if (company.getCompanyImage() != null) {

                deletePhysicalImage(
                        company.getCompanyImage());
            }


            // -------------------------------------------------
            // Generate unique filename
            // -------------------------------------------------

            String originalFileName =
                    file.getOriginalFilename();

            String extension = "";

            if (originalFileName != null &&
                    originalFileName.contains(".")) {

                extension =
                        originalFileName.substring(
                                originalFileName
                                        .lastIndexOf("."));
            }


            String fileName =
                    "company_" +
                    UUID.randomUUID() +
                    extension;


            // -------------------------------------------------
            // Physical file path
            // -------------------------------------------------

            Path filePath =
                    uploadPath.resolve(fileName);


            // -------------------------------------------------
            // Save file to directory
            // -------------------------------------------------

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );


            // -------------------------------------------------
            // Save image in DB
            // -------------------------------------------------

            company.setCompanyImage(
                    fileName);

            company.setCompanyImageData(
                    file.getBytes());

            company.setCompanyImageContentType(
                    contentType);


        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to save company image.",
                    e);
        }
    }


    // =========================================================
    // DELETE PHYSICAL IMAGE
    // =========================================================

    private void deletePhysicalImage(
            String fileName) {

        try {

            Path filePath =
                    Paths.get(uploadDirectory)
                            .toAbsolutePath()
                            .normalize()
                            .resolve(fileName);

            Files.deleteIfExists(
                    filePath);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // GET ALL
    // =========================================================

    public List<Company> getAll() {

        return repository.findAll();
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    public Company getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Company not found."));
    }


    // =========================================================
    // DELETE
    // =========================================================

    @Transactional
    public void delete(Long id) {

        Company company =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Company not found."));

        if (company.getCompanyImage() != null) {

            deletePhysicalImage(
                    company.getCompanyImage());
        }

        repository.delete(company);
    }


    // =========================================================
    // ENABLE / DISABLE
    // =========================================================

    public Company changeStatus(
            Long id,
            Boolean active) {

        Company company =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Company not found."));

        company.setActive(active);

        company.setUpdatedAt(
                LocalDateTime.now());

        return repository.save(company);
    }
}