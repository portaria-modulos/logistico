package com.erp.logistico.infrastructure.persistence.recebimento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "resumo_carga",schema = "recebimento")
@Getter
@Setter
public class ResumoCargaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Long portariaTotalConsolidada;

    Long portoTotalConsolidado;

    Long volumeTotalConsolidado;

    Long pendentesTotalConsolidada;

    public ResumoCargaEntity(Long portoTotalConsolidado,Long portariaTotalConsolidada,Long pendentesTotalConsolidada,  Long volumeTotalConsolidado) {
        this.pendentesTotalConsolidada = pendentesTotalConsolidada;
        this.portoTotalConsolidado = portoTotalConsolidado;
        this.portariaTotalConsolidada = portariaTotalConsolidada;
        this.volumeTotalConsolidado = volumeTotalConsolidado;
    }

    public ResumoCargaEntity() {

    }
}
