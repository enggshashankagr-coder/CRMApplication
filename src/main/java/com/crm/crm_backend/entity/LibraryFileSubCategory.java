package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="mst_library_file_sub_category")
public class LibraryFileSubCategory extends AuditableEntity {


    @Column(name="file_sub_category",nullable=false)
    private String fileSubCategory;

    @Column(name="short_name")
    private String shortName;

    @Column(length=500)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="file_category_id")
    private LibraryFileCategory fileCategory;

 

}