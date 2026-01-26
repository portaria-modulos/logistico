package com.erp.logistico.application.usecases.MaterialLogistico;

import com.erp.logistico.application.gateways.RepositoryGatewayRegistroMaterial;
import org.springframework.transaction.annotation.Transactional;

public class DeleteMaterialLogistico {

    private final RepositoryGatewayRegistroMaterial repository;


    public DeleteMaterialLogistico(RepositoryGatewayRegistroMaterial repository) {
        this.repository = repository;
    }
   @Transactional
    public void deleteLogistico(Long registroId,Integer filial){
       repository.delete(registroId,filial);
    }
}
