package com.erp.logistico.application.usecases;

import com.erp.logistico.application.gateways.RepositoryGatewayRegistroMaterial;

import java.util.List;
import java.util.stream.Collectors;

public class ListaRegistroMaterial {
    private final RepositoryGatewayRegistroMaterial repository;
   public ListaRegistroMaterial(RepositoryGatewayRegistroMaterial repository){
        this.repository = repository;
   }

   public List<ResponseMaterialDTO> lista(Integer filial,List<Integer> filiais){
      return repository.listar(filial,filiais).stream().map(ResponseMaterialDTO::new).toList();
   }
}
