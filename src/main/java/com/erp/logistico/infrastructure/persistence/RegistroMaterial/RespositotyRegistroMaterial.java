package com.erp.logistico.infrastructure.persistence.RegistroMaterial;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RespositotyRegistroMaterial extends JpaRepository<RegistroMaterialEntity,Long> {
    @Query("SELECT r FROM RegistroMaterialEntity r WHERE r.filial = :filial " +
            "AND CAST(r.dataCreacao AS date) = CURRENT_DATE")
    RegistroMaterialEntity findOneByFilial(@Param("filial") Integer filial);
    @Query("""
   SELECT r
   FROM RegistroMaterialEntity r
   WHERE r.filial IN :filiais
     AND CAST(r.dataCreacao AS date) = :dataInicio""")
    List<RegistroMaterialEntity> findAllRegistro(@Param("filiais") List<Integer> filiais,@Param("dataInicio") LocalDate dataInicio);
    @Query("SELECT r FROM RegistroMaterialEntity r WHERE r.filial = :filial and r.filial in :filiais " +
            "AND CAST(r.dataCreacao AS date) = :dataInicio")
    List<RegistroMaterialEntity> findAllRegistroFilial(
            @Param("filial") Integer filial,
            List<Integer> filiais,@Param("dataInicio") LocalDate dataInicio
    );
    @Query("SELECT r FROM RegistroMaterialEntity r WHERE r.filial = :filial ORDER BY r.id DESC")
    Page<RegistroMaterialEntity> findByFilial(Integer filial, Pageable pageable);

    Optional<RegistroMaterialEntity> findByIdAndFilial(Long id, Integer filial);
}
