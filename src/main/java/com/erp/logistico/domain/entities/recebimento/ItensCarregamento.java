package com.erp.logistico.domain.entities.recebimento;
import java.time.LocalDateTime;

public class ItensCarregamento {
    private  Long id;
    private String tipoBloco;
    private Integer qtdPortariaDescarregada;
    private Integer qtdPortoDescarregado;
    private Integer qtdDescargasPendentes;
    private LocalDateTime dataAt;
    private Long  gmBlocoId;

    public ItensCarregamento(Long id,
            String tipoBloco,
                             Integer qtdDescargasPendentes,
                             Integer qtdPortoDescarregado,
                             Integer qtdPortariaDescarregada,
                             Long  gmBlocoId

                             ) {
        if( qtdDescargasPendentes < 0){
            throw new IllegalArgumentException("Valor invalido");
        }
        this.id = id;
        this.tipoBloco = tipoBloco;
        this.qtdDescargasPendentes = qtdDescargasPendentes;
        this.qtdPortoDescarregado = qtdPortoDescarregado;
        this.dataAt = LocalDateTime.now();
        this.qtdPortariaDescarregada = qtdPortariaDescarregada;
        this.gmBlocoId = gmBlocoId;
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
        return qtdDescargasPendentes;
    }

    public void setQtdPendentes(Integer qtdDescargasPendentes) {
        this.qtdDescargasPendentes = qtdDescargasPendentes;
    }

    public Integer getQtdPorto() {
        return this.qtdPortoDescarregado;
    }

    public void setQtdPorto(Integer qtdChamado) {
        this.qtdPortoDescarregado = qtdPortoDescarregado;
    }

    public LocalDateTime getDataAt() {
        return dataAt;
    }

    public void setDataAt(LocalDateTime dataAt) {
        this.dataAt = dataAt;
    }

    public Integer getQtdDescarregado() {
        return qtdPortariaDescarregada;
    }

    public void setQtdDescarregado(Integer qtdPortariaDescarregada) {
        this.qtdPortariaDescarregada = qtdPortariaDescarregada;
    }

    public Long getGmBlocoId() {
        return gmBlocoId;
    }
}
