package com.erp.logistico.infrastructure.persistence.RegistroMaterial;

import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;
import com.erp.logistico.domain.entities.registro.RegistroMaterial;
import com.erp.logistico.infrastructure.persistence.materialLogistico.MaterialLogisticoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "controle_material_logistico",schema = "logistico")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroMaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataCreacao;
    private Integer filial;
    private String filialName;
    private String usuario;
    private Long usuarioId;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "registroMaterial")
    private Set<MaterialLogisticoEntity> itens;
    public RegistroMaterialEntity(RegistroMaterial model) {
        if(model.getItens()!=null) {
            this.itens = model.getItens().stream().map(MaterialLogisticoEntity::new).collect(Collectors.toSet());
            this.itens.forEach(e->{
                e.setRegistroMaterial(this);
            });
            this.dataCreacao = model.getDataCreacao();
            this.filialName = model.getFilialName();
            this.filial  = model.getFilial();
            this.usuario = model.getUsuario();
            this.usuarioId = model.getUsuarioId();
        }
    }

//    public RegistroMaterialEntity(RegistroMaterialEntity model) {
//        if(model.getItens()!=null) {
//            this.itens = model.getItens().stream().map(MaterialLogisticoEntity::new).collect(Collectors.toSet());
//            this.itens.forEach(e->{
//                e.setRegistroMaterial(this);
//            });
//            this.dataCreacao = model.getDataCreacao();
//            this.filialName = model.getFilialName();
//            this.filial  = model.getFilial();
//        }
//    }
}
