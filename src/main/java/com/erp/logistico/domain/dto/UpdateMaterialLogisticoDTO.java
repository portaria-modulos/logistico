package com.erp.logistico.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateMaterialLogisticoDTO(
        Long id,
         @NotBlank
         String tipo,
         @NotNull
         Integer qtdManutencao,
         @NotNull
         Integer qtdAtivo
) {

}
