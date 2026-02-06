package com.fiap.saude_transparente.application.presenter;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorRankingResponse(
		LocalDateTime timestamp,
		int status,
		String error,
		String message,
		Map<String, Object> details
) {	}