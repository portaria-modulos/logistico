package com.erp.logistico.infrastructure.persistence.recebimento;

import com.erp.logistico.domain.entities.recebimento.ItensCarregamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "itens_carregamento",schema = "recebimento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItensCarregamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoBloco;
    private Integer qtdPendentes;
    private Integer qtdChamado;
    private Integer qtdDescarregado;
    private Integer qtdTotal;
    private LocalDateTime dataAt;
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "carregamento_id",
            referencedColumnName = "id"
    )
    private CarregamentoEntity carregamento;

    public ItensCarregamentoEntity(ItensCarregamento item,CarregamentoEntity c) {
        this.qtdPendentes = item.getQtdPendentes();
        this.tipoBloco = item.getTipoBloco();
        this.qtdChamado = item.getQtdChamado();
        this.dataAt = item.getDataAt();
        this.carregamento = c;
        this.qtdDescarregado = item.getQtdDescarregado();
    }
}
