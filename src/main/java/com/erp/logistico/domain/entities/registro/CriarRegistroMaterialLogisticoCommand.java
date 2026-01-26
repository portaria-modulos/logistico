package com.erp.logistico.domain.entities.registro;

import com.erp.logistico.domain.entities.Logistico.CriarMaterialLogisticoCommand;

import java.util.Set;

public record CriarRegistroMaterialLogisticoCommand(
        String id,
        String usuario,
        Long usuarioId,
        String nomeFilial,
        Integer numeroFIlial,
        Set<CriarMaterialLogisticoCommand> itens
) {
}
