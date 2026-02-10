package com.erp.logistico.application.usecases.recebimento;

import com.erp.logistico.application.gateways.RepositoryGatewayRegistroMaterial;
import com.erp.logistico.application.gateways.carregamento.CarregamentoGateway;
import org.springframework.transaction.annotation.Transactional;

public class DeleteRecebimento {

    private final CarregamentoGateway repository;


    public DeleteRecebimento(CarregamentoGateway repository) {
        this.repository = repository;
    }
   @Transactional
    public void deleteRegistro(Long registroId,Integer filial){
       repository.delete(registroId,filial);
    }
}
