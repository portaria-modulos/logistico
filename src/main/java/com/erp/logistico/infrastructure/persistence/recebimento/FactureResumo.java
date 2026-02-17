package com.erp.logistico.infrastructure.persistence.recebimento;

import com.erp.logistico.domain.entities.recebimento.ResumoCarga;

public class FactureResumo {

    public  static ResumoCarga ConvertResumo(ResumoCargaEntity r){
        return new ResumoCarga(
                r.portoTotalConsolidado,r.portariaTotalConsolidada,r.pendentesTotalConsolidada,r.volumeTotalConsolidado
        );

    }
}
