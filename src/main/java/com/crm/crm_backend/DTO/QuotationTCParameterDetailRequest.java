package com.crm.crm_backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuotationTCParameterDetailRequest {

    private Long id;

    private String parameterName;

    private Long parameterHeadId;

    private String defaultValue;

    private String description;

    private Boolean active;

}
