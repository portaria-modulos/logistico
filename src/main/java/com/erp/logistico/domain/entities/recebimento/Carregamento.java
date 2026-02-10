package com.erp.logistico.domain.entities.recebimento;

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

    public Carregamento(Long id,String nomeUsuario, Long usuarioId, Integer fIlial,String nomeFilial, List<ItensCarregamento> itens, LocalDateTime dataAt) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.usuarioId = usuarioId;
        this.nomeFilial = nomeFilial;
        this.itens = itens;
        this.dataAt = dataAt;
        this.fIlial =  fIlial;
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
}
