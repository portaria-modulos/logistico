package com.erp.logistico.application.usecases;

import com.erp.logistico.application.gateways.RepositoryGatewayRegistroMaterial;
import com.erp.logistico.domain.entities.registro.RegistroMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ListaRegistroGeralMaterial {
    private final RepositoryGatewayRegistroMaterial repository;
   public ListaRegistroGeralMaterial(RepositoryGatewayRegistroMaterial repository){
        this.repository = repository;
   }

   public Page<ResponseMaterialDTO> listarItensRegistroFiliais(Integer filial, Pageable page){
      return repository.listarItensRegistroFiliais(filial,page).map(ResponseMaterialDTO::new);
   }
}
