package com.erp.logistico.application.usecases.MaterialLogistico;

import com.erp.logistico.application.gateways.materialLogistico.RepositoryGatewayMaterialLogistico;
import com.erp.logistico.domain.dto.ResponseMaterialLogisticoDTO;
import com.erp.logistico.domain.dto.RespostaApiMaterialLogisitoco;
import com.erp.logistico.domain.dto.Resumo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindOneMaterialLogistico {
    private final RepositoryGatewayMaterialLogistico repository;

    public FindOneMaterialLogistico(RepositoryGatewayMaterialLogistico repository) {
        this.repository = repository;
    }
    public RespostaApiMaterialLogisitoco findOneMaterialLogistico (String tipo,Long filial){
        Long inicio = System.nanoTime();
        var listaDTO = repository.faidOneMaterialLogistico(tipo,filial);
        Map<String,List<ResponseMaterialLogisticoDTO>> lista = new HashMap<>();
        lista.put("logistico",listaDTO);
        var fim = System.nanoTime();
        long tempoMs = (fim - inicio) / 1_000_000;
        long quantidadeativo = 0;
        long qtdManu = 0;
        for(ResponseMaterialLogisticoDTO s:listaDTO){
            quantidadeativo += s.qtdAtivo();
            qtdManu += s.qtdManutencao();
        }

        return new RespostaApiMaterialLogisitoco(tempoMs,new Resumo(quantidadeativo,qtdManu),lista);
    }
}
