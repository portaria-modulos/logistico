package com.erp.logistico.domain.entities.recebimento;
import java.time.LocalDateTime;

public class ItensCarregamento {
    private  Long id;
    private String tipoBloco;
    private Integer qtdPendentes;
    private Integer qtdPorto;
    private LocalDateTime dataAt;
    private Integer qtdDescarregado;

    public ItensCarregamento(Long id,
            String tipoBloco,
                             Integer qtdPendentes,
                             Integer qtdPorto,
                             Integer qtdDescarregado
                             ) {
        if( qtdPendentes < 0){
            throw new IllegalArgumentException("Valor invalido");
        }
        this.id = id;
        this.tipoBloco = tipoBloco;
        this.qtdPendentes = qtdPendentes;
        this.qtdPorto = qtdPorto;
        this.dataAt = LocalDateTime.now();
        this.qtdDescarregado = qtdDescarregado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getQtdPorto() {
        return qtdPorto;
    }

    public void setQtdPorto(Integer qtdChamado) {
        this.qtdPorto = qtdPorto;
    }

    public LocalDateTime getDataAt() {
        return dataAt;
    }

    public void setDataAt(LocalDateTime dataAt) {
        this.dataAt = dataAt;
    }

    public Integer getQtdDescarregado() {
        return qtdDescarregado;
    }

    public void setQtdDescarregado(Integer qtdDescarregado) {
        this.qtdDescarregado = qtdDescarregado;
    }
}
