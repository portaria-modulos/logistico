package com.erp.logistico.application.usecases.recebimento;

import com.erp.logistico.application.gateways.carregamento.CarregamentoGateway;

public class UpdateRecebimento {
   private CarregamentoGateway repository;

   public UpdateRecebimento(CarregamentoGateway repository){
       this.repository = repository;
   }


    public void updateRecebimento(UpdateRecebimentoDTO update){
      repository.UpdateCarregamento(update);
    }
}
