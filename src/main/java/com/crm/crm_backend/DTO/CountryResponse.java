package com.crm.crm_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {

    private Long id;

    private String countryCode;

    private String countryName;

    private Boolean active;
}
