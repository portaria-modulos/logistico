package com.erp.logistico.application.usecases;

import com.erp.logistico.application.gateways.RepositoryGatewayRegistroMaterial;
import com.erp.logistico.application.gateways.materialLogistico.RepositoryGatewayMaterialLogistico;
import com.erp.logistico.domain.dto.MaterialLogisticoDTO;
import com.erp.logistico.domain.dto.RegistroMaterialDTO;
import com.erp.logistico.domain.entities.Logistico.CriarMaterialLogisticoCommand;
import com.erp.logistico.domain.entities.registro.CriarRegistroMaterialLogisticoCommand;
import com.erp.logistico.domain.entities.registro.RegistroMaterial;
import com.erp.logistico.domain.entities.registro.RegistroMaterialFactory;
import com.erp.logistico.infrastructure.gateways.RepositoryMateriaLogistico;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class CriarRegistroMaterial {
    private final RepositoryGatewayRegistroMaterial repository;
    private final RepositoryGatewayMaterialLogistico repositoryMateriaLogistico;
    private CriarMaterialLogisticoCommand criarMaterialLogisticoCommand;

    public CriarRegistroMaterial(
           RepositoryGatewayRegistroMaterial repository,
           RepositoryMateriaLogistico repositoryMateriaLogistico
   ){
        this.repository = repository;
        this.repositoryMateriaLogistico =repositoryMateriaLogistico;
   }
   public void criarControleMaterial(RegistroMaterialDTO dto){
        dto.itens().forEach(e->{
            if(e.qtdAtivo()<0 || e.qtdManutencao()<0){
                String msg = """
                        Quantidade invalida:
                        qtdAtivo: %s
                        qtdManutencao: %s
                        
                        """.formatted(e.qtdAtivo(),e.qtdManutencao());
                throw new RuntimeException(msg);
            }
        });
          RegistroMaterial registro = repository.findOne(dto.numeroFIlial());
          LocalDate hoje = LocalDate.now();
       if (registro != null ) {
           LocalDate dataUltimoRegistro = registro.getDataCreacao().toLocalDate();
               if (dataUltimoRegistro.equals(hoje)) {
                   throw new RuntimeException("A filial já possui um registro realizado hoje.");
               }
               return;
       }
       var registroDTO = new CriarRegistroMaterialLogisticoCommand (
               null,
               dto.usuario(),
               dto.usuarioId(),
               dto.nomeFilial(),
               dto.numeroFIlial(),
               dto.itens().stream().map(logisticoDto->cmd(logisticoDto,dto)).collect(Collectors.toSet())
       );
       var s = RegistroMaterialFactory.controleMaterialLogistrico(registroDTO);
       repository.save(s);
   }

   /// converte em criar materia comand para passar dentros do criar registro
   private CriarMaterialLogisticoCommand cmd(MaterialLogisticoDTO mat,RegistroMaterialDTO dto){
       return new CriarMaterialLogisticoCommand(
               null,
               mat.tipo(),
               null,
               mat.qtdManutencao(),
               mat.qtdAtivo(),dto.numeroFIlial(),
               dto.usuario(),
               dto.usuarioId(),
               null,
               null
       );
   }
}
