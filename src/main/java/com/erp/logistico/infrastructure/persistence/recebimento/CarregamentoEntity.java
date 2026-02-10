package com.erp.logistico.infrastructure.persistence.recebimento;

import com.erp.logistico.domain.entities.recebimento.ItensCarregamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "recebimento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarregamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String nomeUsuario;
 private Long usuarioId;
 private String nomeFilial;
 private Integer filial;
 @OneToMany(mappedBy = "carregamento",cascade = CascadeType.ALL)
 private List<ItensCarregamentoEntity> itens;
 private LocalDateTime dataAt;
    public CarregamentoEntity(
            String nomeUsuario,
            Long usuarioId,
            Integer fIlial,
            String nomeFilial,
            List<ItensCarregamento> itens,
            LocalDateTime dataAt) {
        this.nomeUsuario = nomeUsuario;
        this.usuarioId = usuarioId;
        this.filial = fIlial;
        this.itens = itens.stream().map(item->new ItensCarregamentoEntity(item,this)).toList();
        this.dataAt = dataAt;
        this.nomeFilial = nomeFilial;
    }
}
