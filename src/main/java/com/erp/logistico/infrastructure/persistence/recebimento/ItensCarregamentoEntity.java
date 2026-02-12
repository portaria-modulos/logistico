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
    private Long  gmBlocoId;
    private String tipoBloco;
    private int qtdPortariaDescarregada;
    private int qtdPortoDescarregado;
    private int qtdDescargasPendentes;
    private Integer qtdTotal;
    private LocalDateTime dataAt;
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "carregamento_id",
            referencedColumnName = "id"
    )
    private CarregamentoEntity carregamento;

    public ItensCarregamentoEntity(ItensCarregamento item,CarregamentoEntity c) {
        this.qtdDescargasPendentes = item.getQtdPendentes();
        this.tipoBloco = item.getTipoBloco();
        this.qtdPortoDescarregado = item.getQtdPorto();
        this.dataAt = item.getDataAt();
        this.carregamento = c;
        this.qtdPortariaDescarregada = item.getQtdDescarregado();
        this.gmBlocoId = c.getId();
    }
}
