package com.erp.logistico.application.usecases.recebimento;

import com.erp.logistico.application.gateways.carregamento.CarregamentoGateway;
import com.erp.logistico.domain.dto.recebimentoDto.RequestCarregamentoDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CriarRegistroRecebimento {

    private final CarregamentoGateway repository;

  public CriarRegistroRecebimento(CarregamentoGateway repository){
      this.repository = repository;
  }

  public void save(RequestCarregamentoDto d){
     if(d.itens().size()>8){
         throw new RuntimeException("Lista de itens muito grande");
     }

     var recebimento = repository.findOne(d.filial());

     if(recebimento!=null){
         LocalDate data = LocalDateTime.now().toLocalDate();
         LocalDate dataRecebimento = recebimento.dataAt().toLocalDate();
         if(dataRecebimento.equals(data)){
             throw new RuntimeException("A filial selecionada já possui um registro realizado para o dia de hoje.");

         }
     }
      repository.save(d);
  }
}
