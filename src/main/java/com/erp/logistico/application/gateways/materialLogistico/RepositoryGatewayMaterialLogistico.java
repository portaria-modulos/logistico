package com.erp.logistico.application.gateways.materialLogistico;

import com.erp.logistico.domain.dto.MaterialLogisticoDTO;
import com.erp.logistico.domain.dto.RegistroMaterialDTO;
import com.erp.logistico.domain.dto.ResponseMaterialLogisticoDTO;
import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;

import java.util.List;

public interface RepositoryGatewayMaterialLogistico {
   List<ResponseMaterialLogisticoDTO> faidOneMaterialLogistico (String tipo,Long filial);
}
