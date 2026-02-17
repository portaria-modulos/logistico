package com.erp.logistico.domain.entities.recebimento;
import com.erp.logistico.domain.dto.recebimentoDto.ResumoCargaDto;

import java.time.LocalDateTime;

public class ItensCarregamento {
    private  Long id;
    private String tipoBloco;
    private Integer qtdPortariaDescarregada;
    private Integer qtdPortoDescarregado;
    private Integer qtdDescargasPendentes;
    private LocalDateTime dataAt;
    private Integer qtdtTotalCargaConcluida;
    private Long  gmBlocoId;
    public ItensCarregamento(Long id,
            String tipoBloco, Integer qtdDescargasPendentes,Integer qtdPortoDescarregado,Integer qtdPortariaDescarregada,Long gmBlocoId) {
        if( qtdDescargasPendentes < 0){
            throw new IllegalArgumentException("Valor invalido");
        }
        this.id = id;
        this.tipoBloco = tipoBloco;
        this.qtdDescargasPendentes = qtdDescargasPendentes;
        this.qtdPortoDescarregado = qtdPortoDescarregado;
        this.dataAt = LocalDateTime.now();
        this.qtdPortariaDescarregada = qtdPortariaDescarregada;
        this.qtdtTotalCargaConcluida = qtdPortoDescarregado + qtdPortariaDescarregada;
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

    public Integer getQtdPortariaDescarregada() {
        return qtdPortariaDescarregada;
    }

    public Integer getQtdtTotalCargaConcluida() {
        return qtdtTotalCargaConcluida;
    }

    public Integer getQtdDescargasPendentes() {
        return qtdDescargasPendentes;
    }

    public Integer getQtdPortoDescarregado() {
        return qtdPortoDescarregado;
    }

    public void setQtdtTotalCargaConcluida(Integer qtdtTotalCargaConcluida) {
        this.qtdtTotalCargaConcluida = qtdtTotalCargaConcluida;
    }

}
