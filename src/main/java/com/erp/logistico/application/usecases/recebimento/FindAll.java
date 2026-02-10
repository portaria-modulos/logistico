package com.erp.logistico.application.usecases.recebimento;

import com.erp.logistico.application.gateways.carregamento.CarregamentoGateway;
import com.erp.logistico.domain.dto.recebimentoDto.CarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.RequestCarregamentoDto;

import java.util.List;

public class FindAll {
    private final CarregamentoGateway repository;
    public FindAll(CarregamentoGateway repository){
        this.repository = repository;
    }

    public List<RequestCarregamentoDto> FindAll(Integer filial, List<Integer> filiais){
       return repository.Lista(filial,filiais);
    }
}
