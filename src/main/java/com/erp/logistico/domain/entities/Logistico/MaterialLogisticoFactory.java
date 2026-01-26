package com.erp.logistico.domain.entities.Logistico;

public class MaterialLogisticoFactory {

    public static MaterialLogistico materialLogistico(CriarMaterialLogisticoCommand cmd){

        return new MaterialLogistico.Builder().salve(cmd).build();
    }
}
