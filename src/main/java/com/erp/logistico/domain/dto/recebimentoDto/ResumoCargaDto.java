package com.erp.logistico.domain.dto.recebimentoDto;

import com.erp.logistico.domain.entities.recebimento.ResumoCarga;

public record ResumoCargaDto(
         Long portoTotalConsolidado,
         Long portariaTotalConsolidada,
         Long pendentesTotalConsolidada,
         Long volumeTotalConsolidado
) {
    public ResumoCargaDto(ResumoCarga e) {
        this(e.getPortoTotalConsolidado(),
                e.getPortariaTotalConsolidada(),
                e.getPendentesTotalConsolidada(),
                e.getVolumeTotalConsolidado()
                );
    }

//    public ResumoCargaDto(ResumoCarga e) {
//        e.
//    }
}
