package com.erp.logistico.infrastructure.persistence.materialLogistico;

import com.erp.logistico.domain.dto.MaterialLogisticoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryMaterialLogistico extends JpaRepository<MaterialLogisticoEntity,Long> {
   List<MaterialLogisticoEntity> findByTipoAndFilialOrderByOffsetDateTimeDesc(String tipo,Long filial);
}
