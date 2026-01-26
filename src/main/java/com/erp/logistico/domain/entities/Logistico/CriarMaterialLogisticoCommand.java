package com.erp.logistico.domain.entities.Logistico;

import java.time.OffsetDateTime;

public record CriarMaterialLogisticoCommand(
        Long id,
        String tipo,
        String status,
        Integer qtdManutencao,
        Integer qtdAtivo,
        Integer filial,
        String usuario,
        Long usuarioId,
        OffsetDateTime offsetDateTime,
        Integer qtdTotal
) {
}
