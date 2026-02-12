package com.erp.logistico.domain.dto.recebimentoDto;

import com.erp.logistico.domain.entities.recebimento.ItensCarregamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ItensCarregamentoResponseDto(
        Long id,
        String TipoBloco,
        Integer qtdPendentes,
        Integer qtdPorto,
        Integer qtdDescarregado
) {

    public ItensCarregamentoResponseDto(ItensCarregamento i) {
        this(i.getId(),i.getTipoBloco(),i.getQtdPendentes(),i.getQtdPorto(),i.getQtdDescarregado()!=null?i.getQtdDescarregado():null);
    }
}
