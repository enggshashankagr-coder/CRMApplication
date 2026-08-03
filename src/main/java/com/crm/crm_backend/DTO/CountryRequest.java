package com.crm.crm_backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryRequest {

    @NotBlank
    private String countryCode;

    @NotBlank
    private String countryName;

    private Boolean active = true;
}
