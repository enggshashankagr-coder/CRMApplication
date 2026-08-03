package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_library_file_category")
public class LibraryFileCategory extends AuditableEntity {


    @Column(name = "file_category", nullable = false, unique = true)
    private String fileCategory;

    @Column(name = "short_name", length = 20)
    private String shortName;

    @Column(length = 500)
    private String description;



}