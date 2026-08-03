package com.crm.crm_backend.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		/*
		 * Later:
		 *
		 * return SecurityContextHolder .getContext() .getAuthentication() .getName();
		 */

		return Optional.of("SYSTEM");

	}

}