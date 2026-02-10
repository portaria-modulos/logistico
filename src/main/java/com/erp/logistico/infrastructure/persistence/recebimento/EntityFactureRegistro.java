package com.erp.logistico.infrastructure.persistence.recebimento;

import com.erp.logistico.domain.entities.recebimento.Carregamento;

public class EntityFactureRegistro {
    public CarregamentoEntity converte(Carregamento c){
        return new CarregamentoEntity(c.getNomeUsuario(),c.getUsuarioId(),c.getfIlial(),c.getNomeFilial(),c.getItens(),c.getDataAt());
    }
}
