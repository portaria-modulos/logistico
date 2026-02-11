package com.erp.logistico.application.usecases.recebimento;

import com.erp.logistico.domain.entities.recebimento.ItensCarregamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ItensCarregamentoUpdateDto(
        Long id,
        @NotBlank
        String TipoBloco,
        @NotNull
        Integer qtdPendentes,
        @NotNull
        Integer qtdChamado,
        Integer qtdDescarregado
) {

    public ItensCarregamentoUpdateDto(ItensCarregamento i) {
        this(i.getId(),i.getTipoBloco(),i.getQtdPendentes(),i.getQtdChamado(), i.getQtdDescarregado());
    }
}
