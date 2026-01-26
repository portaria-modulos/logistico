package com.erp.logistico.infrastructure.gateways;

import com.erp.logistico.application.gateways.materialLogistico.RepositoryGatewayMaterialLogistico;
import com.erp.logistico.domain.dto.MaterialLogisticoDTO;
import com.erp.logistico.domain.dto.ResponseMaterialLogisticoDTO;
import com.erp.logistico.domain.entities.Logistico.CriarMaterialLogisticoCommand;
import com.erp.logistico.domain.entities.Logistico.MaterialLogisticoFactory;
import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;
import com.erp.logistico.domain.entities.registro.RegistroMaterial;
import com.erp.logistico.infrastructure.persistence.RegistroMaterial.RegistroMaterialEntity;
import com.erp.logistico.infrastructure.persistence.materialLogistico.MaterialLogisticoEntity;
import com.erp.logistico.infrastructure.persistence.materialLogistico.RepositoryMaterialLogistico;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepositoryMateriaLogistico implements RepositoryGatewayMaterialLogistico {
    private final EntityManager entityManager;
    private final RepositoryMaterialLogistico repository;
    public RepositoryMateriaLogistico(
            RepositoryMaterialLogistico repository,
            EntityManager entityManager
    ){
        this.repository = repository;
        this.entityManager = entityManager;
    }

//    @Override
//    public void update(MaterialLogistico domain,Long registroId) {
//        RegistroMaterialEntity registroMaterial =
//                entityManager.getReference(RegistroMaterialEntity.class,registroId);
//        MaterialLogisticoEntity entidade =  new MaterialLogisticoEntity(domain);
//        entidade.setRegistroMaterial(registroMaterial);
//        repository.save(entidade);
//    }

    @Override
    public List<ResponseMaterialLogisticoDTO> faidOneMaterialLogistico(String tipo,Long filial) {
           return repository.findByTipoAndFilialOrderByOffsetDateTimeDesc(tipo,filial)
                   .stream().map(MapperMaterialLogistico::cmd)
                   .map(ResponseMaterialLogisticoDTO::new)
                   .toList();
    }
}
