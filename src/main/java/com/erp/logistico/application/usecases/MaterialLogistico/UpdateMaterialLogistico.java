package com.erp.logistico.application.usecases.MaterialLogistico;

import com.erp.logistico.application.gateways.RepositoryGatewayRegistroMaterial;
import com.erp.logistico.application.gateways.materialLogistico.RepositoryGatewayMaterialLogistico;
import com.erp.logistico.application.usecases.CriarRegistroMaterial;
import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;
import com.erp.logistico.domain.entities.registro.RegistroMaterialFactory;
import org.springframework.transaction.annotation.Transactional;

public class UpdateMaterialLogistico {

    private final RepositoryGatewayRegistroMaterial repository;


    public UpdateMaterialLogistico(RepositoryGatewayRegistroMaterial repository) {
        this.repository = repository;
    }
   @Transactional
    public void updateLogistico(UpdateMaterialDTO dto){
       dto.itens().forEach(e->{
           if(e.qtdAtivo()<0 || e.qtdManutencao()<0){
               throw new RuntimeException("Valor invalido");
           }
       });
       repository.update(dto);
    }
}
