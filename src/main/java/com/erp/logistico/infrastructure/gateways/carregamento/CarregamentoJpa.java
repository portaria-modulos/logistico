package com.erp.logistico.infrastructure.gateways.carregamento;

import com.erp.logistico.application.gateways.carregamento.CarregamentoGateway;
import com.erp.logistico.application.usecases.recebimento.ItensCarregamentoUpdateDto;
import com.erp.logistico.application.usecases.recebimento.UpdateRecebimentoDTO;
import com.erp.logistico.domain.dto.recebimentoDto.CarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.ItensCarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.RequestCarregamentoDto;
import com.erp.logistico.domain.dto.recebimentoDto.ResumoCargaDto;
import com.erp.logistico.domain.entities.recebimento.Carregamento;
import com.erp.logistico.domain.entities.recebimento.FactoryCarregamento;
import com.erp.logistico.domain.entities.recebimento.ItensCarregamento;
import com.erp.logistico.domain.entities.recebimento.ResumoCarga;
import com.erp.logistico.infrastructure.persistence.recebimento.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class CarregamentoJpa implements CarregamentoGateway {
   private final CarregamentoRepository repository;

    public CarregamentoJpa(CarregamentoRepository repository){
        this.repository = repository;
    }


    @Override
    @Transactional
    public void save(RequestCarregamentoDto c) {
        var carregamento = new Carregamento(null,c.nomeUsuario(),c.usuarioId(),c.filial(),c.nomeFilial()
                ,c.itens().stream().map(e->new ItensCarregamento(null,e.TipoBloco(),e.qtdDescargasPendentes(),e.qtdPortoDescarregado(),e.qtdPortariaDescarregada(),e.gmBlocoId())).toList()
                ,LocalDateTime.now(),null);
        var portoTotalConsolidado = portoTotalConsolidado(carregamento.getItens());
        var pendentesTotalConsolidada = pendentesTotalConsolidada(carregamento.getItens());
        var portariaTotalConsolidada= portariaTotalConsolidada(carregamento.getItens());
        var volumeTotalConsolidado = volumeTotalConsolidado(carregamento.getItens());
      var l =  new ResumoCargaEntity(portoTotalConsolidado,portariaTotalConsolidada,pendentesTotalConsolidada,volumeTotalConsolidado);
        var entity = new EntityFactureRegistro().converte(carregamento);
        entity.setResumoCargaEntity(l);
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
        LocalDate dataInicio = LocalDateTime.now().minusDays(0).toLocalDate();
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
                                            item.getQtdDescargasPendentes(),
                                            item.getQtdPortoDescarregado(),
                                            item.getQtdPortariaDescarregada(),
                                            null
                                            )
                                    ).toList(), e.getDataAt(),FactureResumo.ConvertResumo(e.getResumoCargaEntity()))
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
                                                    item.getQtdDescargasPendentes(),
                                                    item.getQtdPortoDescarregado(),
                                                    item.getQtdPortariaDescarregada(),null
                                            )
                                    ).toList(), e.getDataAt(),FactureResumo.ConvertResumo(e.getResumoCargaEntity()))
            );
            lista =  car.map(RequestCarregamentoDto::new).toList();
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public CarregamentoDto findOne(Integer filial){
       var c =   repository.findOneByFilial(filial);
       if(c!=null){
           ResumoCarga resumo = new ResumoCarga(
                   c.getResumoCargaEntity().getPortoTotalConsolidado(),
                   c.getResumoCargaEntity().getPortariaTotalConsolidada(),
                   c.getResumoCargaEntity().getPendentesTotalConsolidada(),
                   c.getResumoCargaEntity().getVolumeTotalConsolidado()
           );
           Carregamento car = new Carregamento(c.getId(),c.getNomeUsuario(),c.getUsuarioId(),c.getFilial(),c.getNomeFilial(),c.getItens().stream().map(

                   e->
                           new ItensCarregamento(e.getId()
                                   ,e.getTipoBloco()
                                   ,e.getQtdDescargasPendentes()
                                   ,e.getQtdPortoDescarregado(),
                                   e.getQtdPortariaDescarregada()
                                   ,null
                           )
           ).toList(),c.getDataAt(),new ResumoCargaDto(resumo));
           return new CarregamentoDto(car);
       }
       return  null;
    }



    @Override
    public void UpdateCarregamento(UpdateRecebimentoDTO update) {
        var carregamento = repository.findById(update.registroId()).orElseThrow(()->new RuntimeException("Registro não encontrado"));
        if (update.save() != null && !update.save().isEmpty()) {
            update.save().forEach(e->{
                if(e.qtdPortoDescarregado()<0 || e.qtdDescargasPendentes()<0){
                    String msg = """
                        Quantidade invalida:
                        qtdChamado: %s
                        qtdPendentes: %s
                        
                        """.formatted(e.qtdPortoDescarregado(),e.qtdDescargasPendentes());
                    throw new RuntimeException(msg);
                }
            });
            for (ItensCarregamentoDto novo : update.save()) {
                ItensCarregamentoEntity item = new ItensCarregamentoEntity();
                item.setCarregamento(carregamento);
                item.setQtdPortoDescarregado(novo.qtdPortoDescarregado());
                item.setDataAt(LocalDateTime.now());
                item.setQtdDescargasPendentes(novo.qtdDescargasPendentes());
                item.setTipoBloco(novo.TipoBloco());
                item.setQtdPortariaDescarregada(novo.qtdPortariaDescarregada());
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
           itemEntity.setQtdDescargasPendentes(item.qtdDescargasPendentes());
           itemEntity.setQtdPortoDescarregado(item.qtdPortoDescarregado());
           itemEntity.setQtdPortariaDescarregada(item.qtdPortariaDescarregada());

        }
        //fazendo o pass usando streans de itensCarregamentoEntity para itensCarregamento
        var listaParaCalculo = carregamento.getItens().stream()
                .map(e -> new ItensCarregamento(
                        e.getId(),
                        e.getTipoBloco(),
                        e.getQtdDescargasPendentes(),
                        e.getQtdPortoDescarregado(),
                        e.getQtdPortariaDescarregada(),
                        e.getGmBlocoId()))
                .toList();

        var resumo = new ResumoCargaEntity(
                portoTotalConsolidado(listaParaCalculo),
                portariaTotalConsolidada(listaParaCalculo),
                pendentesTotalConsolidada(listaParaCalculo),
                volumeTotalConsolidado(listaParaCalculo)
        );

        // relação entre a tabela resumo e o carregamento

        carregamento.setResumoCargaEntity(resumo);

        repository.save(carregamento);
    }

    @Override
    public Page<RequestCarregamentoDto> listarItensRecebimento(Integer filial, Pageable page) {
        var item = repository.findByAllFilial(filial,page).map(c->{
            ResumoCarga resumo = new ResumoCarga(
                    c.getResumoCargaEntity().getPortoTotalConsolidado(),
                    c.getResumoCargaEntity().getPortariaTotalConsolidada(),
                    c.getResumoCargaEntity().getPendentesTotalConsolidada(),
                    c.getResumoCargaEntity().getVolumeTotalConsolidado()
            );
            return new Carregamento(c.getId(),c.getNomeUsuario(),c.getUsuarioId(),c.getFilial(),c.getNomeFilial(),c.getItens().stream().map(

                    e-> new ItensCarregamento(e.getId()
                                    ,e.getTipoBloco()
                                    ,e.getQtdDescargasPendentes(),
                                    e.getQtdPortoDescarregado(),
                                    e.getQtdPortariaDescarregada(),
                                    null
                            )
            ).toList(),c.getDataAt(),new ResumoCargaDto(resumo));
        });
      return item.map(RequestCarregamentoDto::new);
    }

    private long portoTotalConsolidado(List<ItensCarregamento> item) {
       return  item.stream().mapToLong(ItensCarregamento::getQtdPorto).sum();
    }
    private long portariaTotalConsolidada(List<ItensCarregamento> item) {
        return  item.stream().mapToLong(ItensCarregamento::getQtdPortariaDescarregada).sum();
    }
    private long volumeTotalConsolidado(List<ItensCarregamento> item) {
        return  item.stream().mapToLong(ItensCarregamento::getQtdtTotalCargaConcluida).sum();
    }
    private long pendentesTotalConsolidada(List<ItensCarregamento> item) {
        return  item.stream().mapToLong(ItensCarregamento::getQtdDescargasPendentes).sum();
    }

}
