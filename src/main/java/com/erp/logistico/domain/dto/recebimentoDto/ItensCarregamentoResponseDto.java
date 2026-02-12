package com.erp.logistico.domain.dto.recebimentoDto;

import com.erp.logistico.domain.entities.recebimento.ItensCarregamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ItensCarregamentoResponseDto(
        Long id,
        String TipoBloco,
        @NotNull
        Integer qtdPortariaDescarregada,
        @NotNull
        Integer qtdPortoDescarregado,
        @NotNull
        Integer qtdDescargasPendentes,
        @NotNull
        Long gmBlocoId
) {
    public ItensCarregamentoResponseDto(ItensCarregamento i) {
        this(i.getId(),i.getTipoBloco(),i.getQtdDescarregado(),i.getQtdPorto(),i.getQtdPendentes()!=null?i.getQtdPendentes():null,i.getGmBlocoId());
    }
}
