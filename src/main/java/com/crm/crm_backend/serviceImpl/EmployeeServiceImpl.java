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
import org.springframework.web.multipart.MultipartFile;

import com.crm.crm_backend.DTO.EmployeeRequest;
import com.crm.crm_backend.entity.Employee;
import com.crm.crm_backend.repository.EmployeeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl {

    private final EmployeeRepository repository;

    @Value("${file.employee-upload-dir:uploads/employee}")
    private String uploadDirectory;


    // =========================================================
    // CREATE / UPDATE EMPLOYEE + IMAGE
    // =========================================================

    @Transactional
    public Employee save(EmployeeRequest request) {

        Employee employee;

        // =====================================================
        // UPDATE
        // =====================================================

        if (request.getId() != null) {

            employee = repository.findById(request.getId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Employee not found."));

            /*
             * Check email only when changed
             */
            if (request.getEmail() != null &&
                    !request.getEmail()
                            .equalsIgnoreCase(employee.getEmail())) {

                if (repository.existsByEmailIgnoreCase(
                        request.getEmail())) {

                    throw new RuntimeException(
                            "Email already exists.");
                }
            }

            employee.setUpdatedAt(
                    LocalDateTime.now());
        }

        // =====================================================
        // CREATE
        // =====================================================

        else {

            if (repository.existsByEmailIgnoreCase(
                    request.getEmail())) {

                throw new RuntimeException(
                        "Email already exists.");
            }

            employee = new Employee();

            employee.setCreatedAt(
                    LocalDateTime.now());
        }


        // =====================================================
        // EMPLOYEE DETAILS
        // =====================================================

        employee.setEmployeeName(
                request.getEmployeeName());

        employee.setEmail(
                request.getEmail());

        employee.setMobileNumber(
                request.getMobileNumber());

        employee.setAlternatePhone(
                request.getAlternatePhone());


        // =====================================================
        // ORGANIZATION DETAILS
        // =====================================================

        employee.setDesignationId(
                request.getDesignationId());

        employee.setManagerL1Id(
                request.getManagerL1Id());

        employee.setManagerL2Id(
                request.getManagerL2Id());

        employee.setManagerL3Id(
                request.getManagerL3Id());

        if (request.getStatus() != null) {

            employee.setStatus(
                    request.getStatus());
        }


        // =====================================================
        // JOINING / LEAVING
        // =====================================================

        employee.setDateOfJoining(
                request.getDateOfJoining());

        employee.setDateOfLeaving(
                request.getDateOfLeaving());


        // =====================================================
        // ACCOUNT DETAILS
        // =====================================================

        /*
         * Don't overwrite existing password during update
         * if no password is supplied.
         */
        if (request.getSmtpPassword() != null &&
                !request.getSmtpPassword().isBlank()) {

            employee.setSmtpPassword(
                    request.getSmtpPassword());
        }

        employee.setFunctionalRole(
                request.getFunctionalRole());

        employee.setCompanyId(
                request.getCompanyId());

        employee.setDepartmentId(
                request.getDepartmentId());

        employee.setRegionId(
                request.getRegionId());

        employee.setBranchId(
                request.getBranchId());


        // =====================================================
        // SIGNATURE
        // =====================================================

        employee.setSignature(
                request.getSignature());


        // =====================================================
        // CREATED BY
        // =====================================================

        if (request.getCreatedBy() != null) {

            employee.setCreatedBy(
                    request.getCreatedBy());
        }


        // =====================================================
        // USER IMAGE
        // =====================================================

        if (request.getFile() != null &&
                !request.getFile().isEmpty()) {

            saveImage(
                    employee,
                    request.getFile());
        }


        return repository.save(employee);
    }


    // =========================================================
    // SAVE IMAGE
    // =========================================================

    private void saveImage(
            Employee employee,
            MultipartFile file) {

        try {

            // -------------------------------------------------
            // Validate image
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
                        "User image size must not exceed 5 MB.");
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
            // Delete old image
            // -------------------------------------------------

            if (employee.getUserImage() != null) {

                deletePhysicalImage(
                        employee.getUserImage());
            }


            // -------------------------------------------------
            // Extension
            // -------------------------------------------------

            String originalFileName =
                    file.getOriginalFilename();

            String extension = "";

            if (originalFileName != null &&
                    originalFileName.contains(".")) {

                extension =
                        originalFileName.substring(
                                originalFileName.lastIndexOf("."));
            }


            // -------------------------------------------------
            // Unique filename
            // -------------------------------------------------

            String fileName =
                    "employee_" +
                    UUID.randomUUID() +
                    extension;


            // -------------------------------------------------
            // Physical path
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

            employee.setUserImage(
                    fileName);

            employee.setUserImageData(
                    file.getBytes());

            employee.setUserImageContentType(
                    contentType);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to save employee image.",
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

    public List<Employee> getAll() {

        return repository.findAll();
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    public Employee getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Employee not found."));
    }


    // =========================================================
    // ENABLE / LOCK
    // =========================================================

    public Employee changeStatus(
            Long id,
            String status) {

        Employee employee =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found."));

        employee.setStatus(status);

        employee.setUpdatedAt(
                LocalDateTime.now());

        return repository.save(employee);
    }


    // =========================================================
    // DELETE
    // =========================================================

    @Transactional
    public void delete(Long id) {

        Employee employee =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found."));

        if (employee.getUserImage() != null) {

            deletePhysicalImage(
                    employee.getUserImage());
        }

        repository.delete(employee);
    }
}