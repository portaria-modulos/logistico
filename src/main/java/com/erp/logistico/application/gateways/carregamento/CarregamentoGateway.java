package com.erp.logistico.application.gateways.carregamento;

import com.erp.logistico.application.usecases.recebimento.UpdateRecebimentoDTO;
import com.erp.logistico.domain.dto.recebimentoDto.CarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.RequestCarregamentoDto;

import java.util.List;

public interface CarregamentoGateway {

    void save(RequestCarregamentoDto carregamento);
    void delete(Long id,Integer filial);
    List<RequestCarregamentoDto> Lista(Integer filial,List<Integer> filiais);
     CarregamentoDto findOne(Integer filial);
     void UpdateCarregamento(UpdateRecebimentoDTO update);
}
