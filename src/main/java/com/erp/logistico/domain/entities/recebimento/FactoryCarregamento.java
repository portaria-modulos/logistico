package com.erp.logistico.domain.entities.recebimento;
import com.erp.logistico.domain.dto.recebimentoDto.ResumoCargaDto;

import java.time.LocalDateTime;
import java.util.List;
public class FactoryCarregamento {


    public Carregamento fabricaDeCarregamento(Long id, String nomeUsuario, String nomeFilial, Long usuarioId, Integer fIlial, List<ItensCarregamento> itens, LocalDateTime dataAt,ResumoCarga resumo){
        var itensCarregamento = itens.stream()
                .map(
                        e-> FactoryItens
                                .fabricaDeCarregamento(
                                        e.getId(),
                                        e.getTipoBloco(),
                                        e.getQtdPendentes()
                                        ,e.getQtdPorto()
                                        ,e.getQtdDescarregado(),e.getGmBlocoId()
                                ))
                .toList();
        return  new Carregamento(id,nomeUsuario,usuarioId,fIlial,nomeFilial,itensCarregamento,dataAt,new ResumoCargaDto(resumo));
    }
}
