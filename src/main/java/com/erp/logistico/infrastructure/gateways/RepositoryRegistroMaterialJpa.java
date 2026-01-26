package com.erp.logistico.infrastructure.gateways;

import com.erp.logistico.application.gateways.RepositoryGatewayRegistroMaterial;
import com.erp.logistico.application.usecases.MaterialLogistico.UpdateMaterialDTO;
import com.erp.logistico.domain.dto.MaterialLogisticoDTO;
import com.erp.logistico.domain.dto.RegistroMaterialDTO;
import com.erp.logistico.domain.dto.UpdateMaterialLogisticoDTO;
import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;
import com.erp.logistico.domain.entities.registro.RegistroMaterial;
import com.erp.logistico.domain.entities.registro.RegistroMaterialFactory;
import com.erp.logistico.infrastructure.persistence.RegistroMaterial.RegistroMaterialEntity;
import com.erp.logistico.infrastructure.persistence.RegistroMaterial.RespositotyRegistroMaterial;
import com.erp.logistico.infrastructure.persistence.materialLogistico.MaterialLogisticoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class RepositoryRegistroMaterialJpa implements RepositoryGatewayRegistroMaterial {
    private final RespositotyRegistroMaterial repository;

    public RepositoryRegistroMaterialJpa(RespositotyRegistroMaterial repository){
        this.repository = repository;
    }

    @Override
    public void save(RegistroMaterial dto) {
       repository.save(new RegistroMaterialEntity(dto));
    }

    @Override
    public List<RegistroMaterial> listar(Integer filial,List<Integer> filiais) {
        List<RegistroMaterial> lista;
        if(filial!=null){
            lista = repository.findAllRegistroFilial(filial,filiais).stream()
                    .map(MapperMaterial::convert).toList();
        }
        else {
            lista = repository.findAllRegistro(filiais).stream()
                    .map(MapperMaterial::convert).toList();
        }
        return  lista;
    }
    @Override
    public Page<RegistroMaterial> listarItensRegistroFiliais(Integer filial, Pageable page) {

        return repository.findByFilial(filial,page)
                .map(MapperMaterial::convert);
    }

    @Override
    public RegistroMaterial findOne(Integer filial) {
        var registro = repository.findOneByFilial(filial);
        if(registro!=null){
            return MapperMaterial.convert(registro);
        }
        return null;
    }

    @Override
    @Transactional
    public void update(UpdateMaterialDTO update) {
        Set<MaterialLogisticoEntity> itensAtualizado = new HashSet<>();
        var material = repository.findById(update.registroId()).orElseThrow(()->new RuntimeException("Registro não encontrado"));
        for (UpdateMaterialLogisticoDTO d:update.itens()){
            MaterialLogisticoEntity item;
            if(d.id()!=null){
                 item = material.getItens().stream().filter(s -> Objects.equals(s.getId(), d.id()))
                     .findFirst()
                     .orElseThrow(()->new RuntimeException("Item nao encontrado"));

        } else {
                item = new MaterialLogisticoEntity();
                item.setRegistroMaterial(material);
                material.getItens().add(item);
// 🔥 essencial
            }
            item.setQuantidadeManutencao(d.qtdManutencao());
            item.setQuantiadeAtivo(d.qtdAtivo());
            var total = d.qtdAtivo() + d.qtdManutencao();
            item.setQuantidadeTotal(total);
            repository.save(material);

        }

    }


    @Override
    public void delete(Long id,Integer filial){
        var material = repository.findByIdAndFilial(id,filial).orElseThrow(()->new RuntimeException("Registro não encontrado"));
        repository.delete(material);

    }
}
