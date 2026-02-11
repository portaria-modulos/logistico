package com.erp.logistico.application.usecases;

import com.erp.logistico.domain.dto.recebimentoDto.CarregamentoDto;
import com.erp.logistico.domain.entities.registro.RegistroMaterial;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseMaterialDTO(
        Long id,
         String usuario,
         String nomeFilial,
         Integer numeroFIlial,
         LocalDateTime dataCriacao,
         List<ResponseListaMaterialLogisticoDTO> itens
) {

    public ResponseMaterialDTO(RegistroMaterial e) {
        this(e.getId(),e.getUsuario(),e.getFilialName(),e.getFilial(),
                e.getDataCreacao(),
                e.getItens().stream().map(ResponseListaMaterialLogisticoDTO::new).toList()
        );
    }
}
