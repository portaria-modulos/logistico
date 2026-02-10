package com.erp.logistico.domain.entities.recebimento;
import java.time.LocalDateTime;

public class ItensCarregamento {
    private  long id;
    private String tipoBloco;
    private Integer qtdPendentes;
    private Integer qtdChamado;
    private LocalDateTime dataAt;

    public ItensCarregamento(long id,
            String tipoBloco,
                             Integer qtdPendentes,
                             Integer qtdChamado
                             ) {
        if( qtdPendentes < 0){
            throw new IllegalArgumentException("Valor invalido");
        }
        this.id = id;
        this.tipoBloco = tipoBloco;
        this.qtdPendentes = qtdPendentes;
        this.qtdChamado = qtdChamado;
        this.dataAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoBloco() {
        return tipoBloco;
    }

    public void setTipoBloco(String tipoBloco) {
        this.tipoBloco = tipoBloco;
    }

    public Integer getQtdPendentes() {
        return qtdPendentes;
    }

    public void setQtdPendentes(Integer qtdPendentes) {
        this.qtdPendentes = qtdPendentes;
    }

    public Integer getQtdChamado() {
        return qtdChamado;
    }

    public void setQtdChamado(Integer qtdChamado) {
        this.qtdChamado = qtdChamado;
    }

    public LocalDateTime getDataAt() {
        return dataAt;
    }

    public void setDataAt(LocalDateTime dataAt) {
        this.dataAt = dataAt;
    }
}
