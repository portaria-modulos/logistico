package com.erp.logistico.domain.dto.recebimentoDto;

import com.erp.logistico.domain.entities.recebimento.ItensCarregamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ItensCarregamentoDto(
        @NotBlank
        String TipoBloco,
        @NotNull
        Integer qtdPendentes,
        @NotNull
        Integer qtdPorto,
        @NotNull
        Integer qtdDescarregado
) {

    public ItensCarregamentoDto(ItensCarregamento i) {
        this(i.getTipoBloco(),i.getQtdPendentes(),i.getQtdPorto(),i.getQtdDescarregado());
    }
}
