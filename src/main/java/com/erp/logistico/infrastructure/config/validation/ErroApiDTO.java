package com.erp.logistico.infrastructure.config.validation;

import java.time.OffsetDateTime;

public record ErroApiDTO(OffsetDateTime timestamp, int status, String error, String message, String path) {
}
