package com.erp.logistico.domain.entities.recebimento;

public class ResumoCarga {
    private Long id;
    Long portariaTotalConsolidada;

    Long portoTotalConsolidado;

    Long volumeTotalConsolidado;

    Long pendentesTotalConsolidada;

    public ResumoCarga(Long portoTotalConsolidado,
                       Long portariaTotalConsolidada,
                       Long pendentesTotalConsolidada,  Long volumeTotalConsolidado) {
        this.portoTotalConsolidado = portoTotalConsolidado;
       this.portariaTotalConsolidada = portariaTotalConsolidada;
       this.pendentesTotalConsolidada = pendentesTotalConsolidada;
       this.volumeTotalConsolidado = volumeTotalConsolidado;


    }
   public  ResumoCarga(){}

    public Long getId() {
        return id;
    }

    public Long getPendentesTotalConsolidada() {
        return pendentesTotalConsolidada;
    }

    public void setPendentesTotalConsolidada(Long pendentesTotalConsolidada) {
        this.pendentesTotalConsolidada = pendentesTotalConsolidada;
    }

    public Long getVolumeTotalConsolidado() {
        return volumeTotalConsolidado;
    }

    public void setVolumeTotalConsolidado(Long volumeTotalConsolidado) {
        this.volumeTotalConsolidado = volumeTotalConsolidado;
    }

    public Long getPortoTotalConsolidado() {
        return portoTotalConsolidado;
    }

    public void setPortoTotalConsolidado(Long portoTotalConsolidado) {
        this.portoTotalConsolidado = portoTotalConsolidado;
    }

    public Long getPortariaTotalConsolidada() {
        return portariaTotalConsolidada;
    }

    public void setPortariaTotalConsolidada(Long portariaTotalConsolidada) {
        this.portariaTotalConsolidada = portariaTotalConsolidada;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
