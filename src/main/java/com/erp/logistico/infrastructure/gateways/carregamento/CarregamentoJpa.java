package com.erp.logistico.infrastructure.gateways.carregamento;

import com.erp.logistico.application.gateways.carregamento.CarregamentoGateway;
import com.erp.logistico.application.usecases.recebimento.ItensCarregamentoUpdateDto;
import com.erp.logistico.application.usecases.recebimento.UpdateRecebimentoDTO;
import com.erp.logistico.domain.dto.recebimentoDto.CarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.ItensCarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.RequestCarregamentoDto;
import com.erp.logistico.domain.entities.recebimento.Carregamento;
import com.erp.logistico.domain.entities.recebimento.FactoryCarregamento;
import com.erp.logistico.domain.entities.recebimento.ItensCarregamento;
import com.erp.logistico.infrastructure.persistence.recebimento.CarregamentoRepository;
import com.erp.logistico.infrastructure.persistence.recebimento.EntityFactureRegistro;
import com.erp.logistico.infrastructure.persistence.recebimento.ItensCarregamentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CarregamentoJpa implements CarregamentoGateway {
   private final CarregamentoRepository repository;

    public CarregamentoJpa(CarregamentoRepository repository){
        this.repository = repository;
    }


    @Override
    public void save(RequestCarregamentoDto c) {
        var carregamento = new Carregamento(null,c.nomeUsuario(),c.usuarioId(),c.filial(),c.nomeFilial()
                ,c.itens().stream().map(e->new ItensCarregamento(null,e.TipoBloco(),e.qtdPendentes(),e.qtdChamado(),e.qtdDescarregado())).toList()
                ,LocalDateTime.now());
        var entity = new EntityFactureRegistro().converte(carregamento);
        repository.save(entity);

    }

    @Override
    public void delete(Long id,Integer filial) {
        var item = repository.findByIdAndFilial(id,filial).orElseThrow(()->new RuntimeException("Registro não encontrado"));
         repository.delete(item);
    }

    @Override
    public List<RequestCarregamentoDto> Lista(Integer filial,List<Integer> filiais) {
        List<RequestCarregamentoDto> lista;
        LocalDate dataInicio = LocalDateTime.now().minusDays(1).toLocalDate();
        if(filial!=null){
            var car = repository.findAllRegistroFilial(filial,filiais,dataInicio).stream().map(
                    e ->
                            new FactoryCarregamento().fabricaDeCarregamento(
                                    e.getId(),
                                    e.getNomeUsuario(),
                                    e.getNomeFilial(),
                                    e.getUsuarioId(),
                                    e.getFilial(),
                                    e.getItens().stream().map(item -> new ItensCarregamento(item.getId(),
                                            item.getTipoBloco(),
                                            item.getQtdPendentes(),
                                            item.getQtdChamado(),
                                            item.getQtdDescarregado()
                                            )
                                    ).toList(), e.getDataAt())
            );
            lista =  car.map(RequestCarregamentoDto::new).toList();
        }else {
            var car = repository.findAllCarregamento(filiais, dataInicio).stream().map(
                    e ->
                            new FactoryCarregamento().fabricaDeCarregamento(
                                    e.getId(),
                                    e.getNomeUsuario(),
                                    e.getNomeFilial(),
                                    e.getUsuarioId(),
                                    e.getFilial(),
                                    e.getItens().stream().map(item ->
                                            new ItensCarregamento(item.getId(),
                                                    item.getTipoBloco(),
                                                    item.getQtdPendentes(),
                                                    item.getQtdChamado(),
                                                    item.getQtdDescarregado()
                                            )
                                    ).toList(), e.getDataAt())
            );
            lista =  car.map(RequestCarregamentoDto::new).toList();
        }
        return lista;
    }

    @Override
    public CarregamentoDto findOne(Integer filial){
       var c =   repository.findOneByFilial(filial);
       if(c!=null){
           Carregamento cs = new Carregamento(c.getId(),c.getNomeUsuario(),c.getUsuarioId(),c.getFilial(),c.getNomeFilial(),c.getItens().stream().map(

                   e->
                           new ItensCarregamento(e.getId()
                                   ,e.getTipoBloco()
                                   ,e.getQtdPendentes()
                                   ,e.getQtdChamado(),
                                   e.getQtdDescarregado()
                           )
           ).toList(),c.getDataAt());
           return new CarregamentoDto(cs);
       }
       return  null;
    }



    @Override
    public void UpdateCarregamento(UpdateRecebimentoDTO update) {
        var carregamento = repository.findById(update.registroId()).orElseThrow(()->new RuntimeException("Registro não encontrado"));
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
                item.setQtdDescarregado(novo.qtdDescarregado());
                carregamento.getItens().add(item);

            }

        }
        for(ItensCarregamentoUpdateDto item:update.itens()){
           ItensCarregamentoEntity itemEntity;
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
           itemEntity.setQtdDescarregado(item.qtdDescarregado());
           repository.save(carregamento);
        }
    }

    @Override
    public Page<RequestCarregamentoDto> listarItensRecebimento(Integer filial, Pageable page) {
        var item = repository.findByAllFilial(filial,page).map(c->{
            return new Carregamento(c.getId(),c.getNomeUsuario(),c.getUsuarioId(),c.getFilial(),c.getNomeFilial(),c.getItens().stream().map(

                    e->
                            new ItensCarregamento(e.getId()
                                    ,e.getTipoBloco()
                                    ,e.getQtdPendentes()
                                    ,e.getQtdChamado(),
                                    e.getQtdDescarregado()
                            )
            ).toList(),c.getDataAt());
        });
      return item.map(RequestCarregamentoDto::new);
    }
}
