package com.erp.logistico.application.gateways;

import com.erp.logistico.application.usecases.MaterialLogistico.UpdateMaterialDTO;
import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;
import com.erp.logistico.domain.entities.registro.RegistroMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RepositoryGatewayRegistroMaterial {
   void save(RegistroMaterial dto);
   List<RegistroMaterial> listar(Integer filial,List<Integer> filiais);
    void delete(Long registroId,Integer filial);
    RegistroMaterial findOne(Integer filial);
    Page<RegistroMaterial> listarItensRegistroFiliais(Integer filial, Pageable page);
    void update(UpdateMaterialDTO d);

}
