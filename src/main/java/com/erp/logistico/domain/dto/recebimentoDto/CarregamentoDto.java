package com.erp.logistico.domain.dto.recebimentoDto;

import com.erp.logistico.domain.entities.recebimento.Carregamento;
import com.erp.logistico.infrastructure.persistence.recebimento.CarregamentoEntity;

import java.time.LocalDateTime;
import java.util.List;

public record CarregamentoDto(

         Long id,
         String nomeUsuario,
         Long usuarioId,
         Integer fIlial,
         String nomeFilial,
         LocalDateTime dataAt,
         List<ItensCarregamentoDto>itens
) {

    public CarregamentoDto(Carregamento e) {
        this(e.getId(),e.getNomeUsuario(),e.getUsuarioId(),e.getfIlial(),e.getNomeFilial(),e.getDataAt(),e.getItens().stream().map(i->new ItensCarregamentoDto(i)).toList());
    }
}
