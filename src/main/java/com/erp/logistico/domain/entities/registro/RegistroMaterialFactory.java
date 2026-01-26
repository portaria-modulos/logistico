package com.erp.logistico.domain.entities.registro;

import com.erp.logistico.domain.entities.Logistico.MaterialLogisticoFactory;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class RegistroMaterialFactory {
    private RegistroMaterialFactory controleMatrialLogistico;

    public static RegistroMaterial controleMaterialLogistrico(CriarRegistroMaterialLogisticoCommand dto){
       var materaial = dto.itens().stream().map(MaterialLogisticoFactory::materialLogistico).collect(Collectors.toSet());
       return new RegistroMaterial
                .Builder()
                .setDataCreacao(LocalDateTime.now())
                .setFilial(dto.numeroFIlial())
                .setFilialName(dto.nomeFilial())
               .setUsuario(dto.usuario())
               .SetUsuarioId(dto.usuarioId())
                .setItens(materaial)
                .build();
    }
}
