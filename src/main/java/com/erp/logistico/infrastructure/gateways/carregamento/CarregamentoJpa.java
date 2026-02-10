package com.erp.logistico.infrastructure.gateways.carregamento;

import com.erp.logistico.application.gateways.carregamento.CarregamentoGateway;
import com.erp.logistico.application.usecases.recebimento.ItensCarregamentoUpdateDto;
import com.erp.logistico.application.usecases.recebimento.UpdateRecebimentoDTO;
import com.erp.logistico.domain.dto.MaterialLogisticoDTO;
import com.erp.logistico.domain.dto.recebimentoDto.CarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.ItensCarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.RequestCarregamentoDto;
import com.erp.logistico.domain.entities.Logistico.StatusMaterialLogistico;
import com.erp.logistico.domain.entities.recebimento.Carregamento;
import com.erp.logistico.domain.entities.recebimento.FactoryCarregamento;
import com.erp.logistico.domain.entities.recebimento.ItensCarregamento;
import com.erp.logistico.infrastructure.persistence.materialLogistico.MaterialLogisticoEntity;
import com.erp.logistico.infrastructure.persistence.recebimento.CarregamentoEntity;
import com.erp.logistico.infrastructure.persistence.recebimento.CarregamentoRepositoty;
import com.erp.logistico.infrastructure.persistence.recebimento.EntityFactureRegistro;
import com.erp.logistico.infrastructure.persistence.recebimento.ItensCarregamentoEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CarregamentoJpa implements CarregamentoGateway {
   private final CarregamentoRepositoty repositoty;

    public CarregamentoJpa(CarregamentoRepositoty repositoty){
        this.repositoty = repositoty;
    }


    @Override
    public void save(RequestCarregamentoDto c) {
        var carregamento = new Carregamento(c.usuarioId(),c.nomeUsuario(),c.usuarioId(),c.filial(),c.nomeFilial()
                ,c.itens().stream().map(e->new ItensCarregamento(e.id(),e.TipoBloco(),e.qtdPendentes(),e.qtdChamado())).toList()
                ,LocalDateTime.now());
        var entity = new EntityFactureRegistro().converte(carregamento);
        repositoty.save(entity);

    }

    @Override
    public void delete(Long id,Integer filial) {
        System.out.println("id "+id + "filial "+filial);
        var item = repositoty.findByIdAndFilial(id,filial).orElseThrow(()->new RuntimeException("Registro não encontrado"));
         repositoty.delete(item);
    }

    @Override
    public List<RequestCarregamentoDto> Lista(Integer filial,List<Integer> filiais) {
        List<RequestCarregamentoDto> lista;
        LocalDate dataInicio = LocalDateTime.now().minusDays(1).toLocalDate();
        if(filial!=null){
            var car = repositoty.findAllRegistroFilial(filial,filiais,dataInicio).stream().map(
                    e ->
                            new FactoryCarregamento().fabricaDeCarregamento(
                                    e.getId(),
                                    e.getNomeUsuario(),
                                    e.getNomeFilial(),
                                    e.getUsuarioId(),
                                    e.getFilial(),
                                    e.getItens().stream().map(item -> new ItensCarregamento(item.getId(), item.getTipoBloco(), item.getQtdPendentes(), item.getQtdChamado())
                                    ).toList(), e.getDataAt())
            );
            lista =  car.map(RequestCarregamentoDto::new).toList();
        }else {
            var car = repositoty.findAllCarregamento(filiais, dataInicio).stream().map(
                    e ->
                            new FactoryCarregamento().fabricaDeCarregamento(
                                    e.getId(),
                                    e.getNomeUsuario(),
                                    e.getNomeFilial(),
                                    e.getUsuarioId(),
                                    e.getFilial(),
                                    e.getItens().stream().map(item -> new ItensCarregamento(item.getId(), item.getTipoBloco(), item.getQtdPendentes(), item.getQtdChamado())
                                    ).toList(), e.getDataAt())
            );
            lista =  car.map(RequestCarregamentoDto::new).toList();
        }
        return lista;
    }

    @Override
    public CarregamentoDto findOne(Integer filial){
       var c =   repositoty.findByFilial(filial);
       if(c!=null){
           Carregamento cs = new Carregamento(c.getId(),c.getNomeUsuario(),c.getUsuarioId(),c.getFilial(),c.getNomeFilial(),c.getItens().stream().map(

                   e->new ItensCarregamento(e.getId(),e.getTipoBloco(),e.getQtdPendentes(),e.getQtdChamado())
           ).toList(),c.getDataAt());
           return new CarregamentoDto(cs);
       }
       return  null;
    }



    @Override
    public void UpdateCarregamento(UpdateRecebimentoDTO update) {
        var carregamento = repositoty.findById(update.registroId()).orElseThrow(()->new RuntimeException("Registro não encontrado"));
        if (update.save() != null && !update.save().isEmpty()) {
            update.save().forEach(e->{
                if(e.qtdChamado()<0 || e.qtdPendentes()<0){
                    String msg = """
                        Quantidade invalida:
                        qtdChamado: %s
                        qtdPendentes: %s
                        
                        """.formatted(e.qtdChamado(),e.qtdPendentes());
                    throw new RuntimeException(msg);
                }
            });
            for (ItensCarregamentoDto novo : update.save()) {
                ItensCarregamentoEntity item = new ItensCarregamentoEntity();
                item.setCarregamento(carregamento);
                item.setQtdChamado(novo.qtdChamado());
                item.setDataAt(LocalDateTime.now());
                item.setQtdPendentes(novo.qtdPendentes());
                item.setTipoBloco(novo.TipoBloco());
                carregamento.getItens().add(item);
            }

        }
        for(ItensCarregamentoUpdateDto item:update.itens()){
           ItensCarregamentoEntity itemEntity;
           System.out.println("item "+item.id());
           if(item.id()!=null){
              itemEntity = carregamento.getItens()
                      .stream().filter(e-> Objects.equals(e.getId(),item.id()))
                      .findFirst().orElseThrow(()->new RuntimeException("Item nao encontrado"));;
          }else{
               itemEntity = new ItensCarregamentoEntity();
               itemEntity.setCarregamento(carregamento);
          }

           itemEntity.setQtdPendentes(item.qtdPendentes());
           itemEntity.setQtdChamado(item.qtdChamado());
           repositoty.save(carregamento);
        }
    }
}
