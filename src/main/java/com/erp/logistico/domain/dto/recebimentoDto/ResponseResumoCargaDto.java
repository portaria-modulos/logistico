package com.erp.logistico.domain.dto.recebimentoDto;

import com.erp.logistico.domain.entities.recebimento.ResumoCarga;

public record ResponseResumoCargaDto(
         Long id,
         Long portoTotalConsolidado,
         Long portariaTotalConsolidada,
         Long pendentesTotalConsolidada,
         Long volumeTotalConsolidado
) {
    public ResponseResumoCargaDto(ResumoCarga e) {
        this(e.getId(),
                e.getPortoTotalConsolidado(),
                e.getPortariaTotalConsolidada(),
                e.getPendentesTotalConsolidada(),
                e.getVolumeTotalConsolidado()
                );
    }

//    public ResumoCargaDto(ResumoCarga e) {
//        e.
//    }
}
