package com.erp.logistico.domain.dto;

import com.erp.logistico.domain.entities.Logistico.CriarMaterialLogisticoCommand;
import com.erp.logistico.infrastructure.persistence.materialLogistico.MaterialLogisticoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record ResponseMaterialLogisticoDTO(
         String tipo,
         Integer qtdManutencao,
         OffsetDateTime dataCriacao,
         Integer qtdAtivo
) {
    public ResponseMaterialLogisticoDTO(CriarMaterialLogisticoCommand c) {
     this(c.tipo(),c.qtdManutencao(),c.offsetDateTime(),c.qtdAtivo());
    }
}
