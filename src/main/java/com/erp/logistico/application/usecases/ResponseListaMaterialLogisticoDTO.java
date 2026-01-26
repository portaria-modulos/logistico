package com.erp.logistico.application.usecases;

import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;

import java.time.OffsetDateTime;

public record ResponseListaMaterialLogisticoDTO(
         Long id,
         String tipo,
         String status,
         Integer qtdManutencao,
         OffsetDateTime datacriacao,
         Integer quantidadeTotal,
         Integer qtdAtivo,
         Long usuarioId,
         String usuario
) {



    public ResponseListaMaterialLogisticoDTO(MaterialLogistico e) {
        this(e.getId(),
                e.getTipo(),e.getStatus().name(),
                e.getQuantidadeManutencao(),
                e.getDataCriacao(),
                e.getQuantidadeTotal(),
                e.getQuantiadeAtivo(),
                e.getUsuarioId(),
                e.getUsuario()
        );
    }
}
