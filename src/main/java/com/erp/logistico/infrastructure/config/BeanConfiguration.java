package com.erp.logistico.infrastructure.config;

import com.erp.logistico.application.gateways.RepositoryGatewayRegistroMaterial;
import com.erp.logistico.application.gateways.carregamento.CarregamentoGateway;
import com.erp.logistico.application.gateways.materialLogistico.RepositoryGatewayMaterialLogistico;
import com.erp.logistico.application.usecases.CriarRegistroMaterial;
import com.erp.logistico.application.usecases.ListaRegistroGeralMaterial;
import com.erp.logistico.application.usecases.ListaRegistroMaterial;
import com.erp.logistico.application.usecases.MaterialLogistico.DeleteMaterialLogistico;
import com.erp.logistico.application.usecases.MaterialLogistico.FindOneMaterialLogistico;
import com.erp.logistico.application.usecases.MaterialLogistico.UpdateMaterialLogistico;
import com.erp.logistico.application.usecases.recebimento.CriarRegistroRecebimento;
import com.erp.logistico.application.usecases.recebimento.DeleteRecebimento;
import com.erp.logistico.application.usecases.recebimento.FindAll;
import com.erp.logistico.application.usecases.recebimento.UpdateRecebimento;
import com.erp.logistico.domain.dto.recebimentoDto.RequestCarregamentoDto;
import com.erp.logistico.infrastructure.gateways.RepositoryMateriaLogistico;
import com.erp.logistico.infrastructure.gateways.RepositoryRegistroMaterialJpa;
import com.erp.logistico.infrastructure.gateways.carregamento.CarregamentoJpa;
import com.erp.logistico.infrastructure.persistence.RegistroMaterial.RespositotyRegistroMaterial;
import com.erp.logistico.infrastructure.persistence.materialLogistico.RepositoryMaterialLogistico;
import com.erp.logistico.infrastructure.persistence.recebimento.CarregamentoRepositoty;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public RepositoryRegistroMaterialJpa materialJpa(RespositotyRegistroMaterial repository){
        return new RepositoryRegistroMaterialJpa(repository);
    }
    @Bean
    public CriarRegistroMaterial criarMaterial(
            RepositoryGatewayRegistroMaterial repository,
            RepositoryMateriaLogistico repositoryMateriaLogistico
    ){
        return new CriarRegistroMaterial(repository,repositoryMateriaLogistico);
    }
    @Bean
    public UpdateMaterialLogistico update(RepositoryGatewayRegistroMaterial repository) {
       return new UpdateMaterialLogistico(repository);
    }
    @Bean
    public DeleteMaterialLogistico delelee(RepositoryGatewayRegistroMaterial repository) {
        return new DeleteMaterialLogistico(repository);
    }

    @Bean
    public ListaRegistroMaterial listaRegistro(RepositoryGatewayRegistroMaterial repository){
        return  new ListaRegistroMaterial(repository);
    }
    @Bean
    public ListaRegistroGeralMaterial registroGeraisLista(RepositoryGatewayRegistroMaterial repository){
        return new ListaRegistroGeralMaterial(repository);
    }
    @Bean
    public RepositoryMateriaLogistico repositoryMateriaLogistico(
            RepositoryMaterialLogistico repository,
            EntityManager entityManager
    ){
        return new RepositoryMateriaLogistico(repository,entityManager);
    }
    @Bean
    public FindOneMaterialLogistico findOneMaterialLogistico(RepositoryGatewayMaterialLogistico repository){
        return new FindOneMaterialLogistico(repository);
    }

    @Bean
    public CarregamentoJpa inicializa(CarregamentoRepositoty repositoty){
       return new CarregamentoJpa(repositoty);
    }

    @Bean
    public CriarRegistroRecebimento criarRegistroRecebimento(CarregamentoGateway repository){
      return new CriarRegistroRecebimento(repository);
    }
    @Bean
    public FindAll findAll(CarregamentoGateway repository){
      return new FindAll(repository);
    }

    @Bean
    public UpdateRecebimento updateCarregamento(CarregamentoGateway repository){
        return new UpdateRecebimento(repository);
    }
    @Bean
    public  DeleteRecebimento deleteregistro(CarregamentoGateway repository){
        return new DeleteRecebimento(repository);
    }

}
