package com.erp.logistico.infrastructure.persistence.recebimento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarregamentoRepository extends JpaRepository<CarregamentoEntity,Long> {
    CarregamentoEntity findByFilial(Integer filial);

    @Query("""
   SELECT r
   FROM CarregamentoEntity r
   WHERE r.filial IN :filiais
     AND CAST(r.dataAt AS date) = :dataInicio""")
    List<CarregamentoEntity> findAllCarregamento(@Param("filiais") List<Integer> filiais, @Param("dataInicio") LocalDate dataInicio);

    @Query("SELECT r FROM CarregamentoEntity r WHERE r.filial = :filial " +
            "AND CAST(r.dataAt AS date) = CURRENT_DATE")
    CarregamentoEntity findOneByFilial(@Param("filial") Integer filial);
    @Query("SELECT r FROM CarregamentoEntity r WHERE r.id = :id and r.filial = :filial")
    Optional<CarregamentoEntity> findByIdAndFilial(Long id, Integer filial);

    @Query("SELECT r FROM CarregamentoEntity r WHERE r.filial = :filial and r.filial in :filiais " +
                  "AND CAST(r.dataAt AS date) = :dataInicio")
    List<CarregamentoEntity> findAllRegistroFilial(
            @Param("filial") Integer filial,
            List<Integer> filiais,@Param("dataInicio") LocalDate dataInicio
    );
    @Query("SELECT r FROM CarregamentoEntity r WHERE r.filial = :filial ORDER BY r.id DESC")
    Page<CarregamentoEntity> findByAllFilial(Integer filial, Pageable page);
}
