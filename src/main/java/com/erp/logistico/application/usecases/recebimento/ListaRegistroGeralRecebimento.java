package com.erp.logistico.application.usecases.recebimento;

import com.erp.logistico.application.gateways.RepositoryGatewayRegistroMaterial;
import com.erp.logistico.application.gateways.carregamento.CarregamentoGateway;
import com.erp.logistico.application.usecases.ResponseMaterialDTO;
import com.erp.logistico.domain.dto.recebimentoDto.CarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.RequestCarregamentoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListaRegistroGeralRecebimento {
    private final CarregamentoGateway repository;
   public ListaRegistroGeralRecebimento(CarregamentoGateway repository){
        this.repository = repository;
   }

   public Page<RequestCarregamentoDto> listarItensRegistroFiliais(Integer filial, Pageable page){
      return repository.listarItensRecebimento(filial,page);
   }
}
