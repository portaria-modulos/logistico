package com.erp.logistico.domain.dto.recebimentoDto;

import com.erp.logistico.domain.entities.recebimento.Carregamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record RequestCarregamentoDto(
         Long id,
         String nomeUsuario,
         Long usuarioId,
         Integer filial,
         String nomeFilial,
         LocalDateTime dataAt,
         List<ItensCarregamentoResponseDto>itens
) {
    public RequestCarregamentoDto(Carregamento e) {
        this(e.getId(),e.getNomeUsuario(),e.getUsuarioId(),e.getfIlial(),e.getNomeFilial(),e.getDataAt(),e.getItens().stream().map(ItensCarregamentoResponseDto::new).toList());
    }
}
