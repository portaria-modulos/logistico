package com.erp.logistico.domain.entities.recebimento;

import com.erp.logistico.domain.dto.recebimentoDto.ResumoCargaDto;

import java.time.LocalDateTime;
import java.util.List;
public class Carregamento {
 private Long id;
 private String nomeUsuario;
 private Long usuarioId;
 private Integer fIlial;
 private String nomeFilial;
 private List<ItensCarregamento> itens;
 private LocalDateTime dataAt;
    private ResumoCarga resumoCarga;


    public Carregamento(Long id, String nomeUsuario, Long usuarioId, Integer fIlial, String nomeFilial, List<ItensCarregamento> itens, LocalDateTime dataAt, ResumoCargaDto r) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.usuarioId = usuarioId;
        this.nomeFilial = nomeFilial;
        this.itens = itens;
        this.dataAt = dataAt;
        this.fIlial =  fIlial;
        if(r!=null){
            this.resumoCarga = new ResumoCarga(
                  r.portoTotalConsolidado()
                    ,r.portariaTotalConsolidada()
                    ,r.pendentesTotalConsolidada(),r.volumeTotalConsolidado()
            );

        }
    }

    public Long getId() {
        return id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Integer getfIlial() {
        return fIlial;
    }

    public List<ItensCarregamento> getItens() {
        return itens;
    }

    public LocalDateTime getDataAt() {
        return dataAt;
    }

    public String getNomeFilial() {
        return nomeFilial;
    }

    public ResumoCarga getResumoCarga() {
        return resumoCarga;
    }
}
