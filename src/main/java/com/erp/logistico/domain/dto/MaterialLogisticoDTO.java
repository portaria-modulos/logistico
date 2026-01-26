package com.erp.logistico.domain.dto;

import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;
import com.erp.logistico.infrastructure.persistence.materialLogistico.MaterialLogisticoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MaterialLogisticoDTO(
         @NotBlank
         String tipo,
         @NotNull
         Integer qtdManutencao,
         @NotNull
         Integer qtdAtivo
) {

}
