package com.erp.logistico.infrastructure.persistence.materialLogistico;
import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;
import com.erp.logistico.domain.entities.Logistico.StatusMaterialLogistico;
import com.erp.logistico.infrastructure.persistence.RegistroMaterial.RegistroMaterialEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "material_logistico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialLogisticoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private StatusMaterialLogistico status;
    private Integer quantidadeManutencao;
    private Integer quantiadeAtivo;
    private Integer quantidadeTotal;
    private Integer filial;
    private  String usuario;
    private  Long usuarioId;
    private OffsetDateTime offsetDateTime;
    private boolean ativo;
    @ManyToOne
    private RegistroMaterialEntity registroMaterial;
    public MaterialLogisticoEntity(MaterialLogistico e) {
        this.tipo = e.getTipo();
        this.status = e.getStatus();
        this.quantidadeManutencao = e.getQuantidadeManutencao();
        this.quantiadeAtivo = e.getQuantiadeAtivo();
        this.filial = e.getFilial();
        this.usuario = e.getUsuario();
        this.usuarioId = e.getUsuarioId();
        this.offsetDateTime = e.getDataCriacao();
        this.ativo = e.isAtivo();
        this.quantidadeTotal = e.getQuantidadeTotal();
    }


}
