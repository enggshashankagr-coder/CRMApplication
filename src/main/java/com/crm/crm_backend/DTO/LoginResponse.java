package com.crm.crm_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@AllArgsConstructor
public class LoginResponse {
	

		private String refreshToken;
		@Builder.Default
	    private String accessToken;

	    private String tokenType="Bearer";

	    private Long expiresIn;

	    private String username;

	
}
