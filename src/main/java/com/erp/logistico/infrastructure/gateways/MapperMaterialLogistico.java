package com.erp.logistico.infrastructure.gateways;

import com.erp.logistico.domain.entities.Logistico.CriarMaterialLogisticoCommand;
import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;
import com.erp.logistico.domain.entities.Logistico.MaterialLogisticoFactory;
import com.erp.logistico.domain.entities.registro.RegistroMaterial;
import com.erp.logistico.infrastructure.persistence.RegistroMaterial.RegistroMaterialEntity;
import com.erp.logistico.infrastructure.persistence.materialLogistico.MaterialLogisticoEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class MapperMaterialLogistico {

    public static CriarMaterialLogisticoCommand cmd(MaterialLogisticoEntity ent){
        return new CriarMaterialLogisticoCommand(
                ent.getId(),
                ent.getTipo(),
                ent.getStatus().name(),
                ent.getQuantidadeManutencao(),
                ent.getQuantiadeAtivo(),
                ent.getFilial(),
                ent.getUsuario(),
                ent.getUsuarioId(),
                ent.getOffsetDateTime(),
                ent.getQuantidadeTotal()

        );
    }

}
