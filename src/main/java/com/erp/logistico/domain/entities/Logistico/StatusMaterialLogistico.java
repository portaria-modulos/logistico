package com.erp.logistico.domain.entities.Logistico;

public enum StatusMaterialLogistico {
    ATIVO("ativo"),
    EM_MANUTENCAO("em_manutencao"),
    INATIVO("inativo");
    private String type;
     StatusMaterialLogistico(String type){
        this.type = type;
    }
    public static StatusMaterialLogistico setType(String type){
       for (StatusMaterialLogistico stutus:values()){
           if (stutus.type.equals(type)){
               return stutus;
           }
       }
        throw new IllegalArgumentException("Tipo de status inválido: " + type);
    }
}
