package com.erp.logistico.application.gateways.carregamento;

import com.erp.logistico.application.usecases.recebimento.UpdateRecebimentoDTO;
import com.erp.logistico.domain.dto.recebimentoDto.CarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.RequestCarregamentoDto;
import com.erp.logistico.domain.entities.registro.RegistroMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarregamentoGateway {

    void save(RequestCarregamentoDto carregamento);
    void delete(Long id,Integer filial);
    List<RequestCarregamentoDto> Lista(Integer filial,List<Integer> filiais);
     CarregamentoDto findOne(Integer filial);
     void UpdateCarregamento(UpdateRecebimentoDTO update);
    Page<RequestCarregamentoDto> listarItensRecebimento(Integer filial, Pageable page);
}
