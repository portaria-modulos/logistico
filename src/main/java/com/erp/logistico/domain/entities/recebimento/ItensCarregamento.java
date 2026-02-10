package com.erp.logistico.domain.entities.recebimento;

import lombok.Getter;

import java.time.LocalDateTime;

public class ItensCarregamento {
    private  Long id;
    private String TipoBloco;
    private Integer qtdPendentes;
    private Integer qtdChamado;
    private LocalDateTime dataAt;

    public ItensCarregamento(Long id,
            String tipoBloco,
                             Integer qtdPendentes,
                             Integer qtdChamado
                             ) {
        if( qtdPendentes < 0){
            throw new IllegalArgumentException("Valor invalido");
        }
        this.id = id;
        this.TipoBloco = tipoBloco;
        this.qtdPendentes = qtdPendentes;
        this.qtdChamado = qtdChamado;
        this.dataAt = LocalDateTime.now();
    }


}
