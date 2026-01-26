package com.erp.logistico.domain.entities.Logistico;

import java.time.OffsetDateTime;

public class MaterialLogistico {
    private Long id;
    private String tipo;
    private StatusMaterialLogistico status;
    private Integer quantidadeManutencao;
    private Integer quantiadeAtivo;
    private Integer quantidadeTotal;
    private Integer filial;
    private  String usuario;
    private  Long usuarioId;
    private OffsetDateTime offsetDateTime;
    private boolean ativo;
    public MaterialLogistico() {}

    public MaterialLogistico(Builder build) {
        validaFilial(build);
        this.tipo = build.tipo;
        this.ativo = true;
        this.status = build.status;
        this.quantidadeManutencao = build.quantidadeManutencao;
        this.quantiadeAtivo = build.quantiadeAtivo;
        this.filial = build.filial;
        this.usuario = build.usuario;
        this.usuarioId = build.usuarioId;
        this.quantidadeTotal = build.quantidadeTotal;
        this.offsetDateTime = OffsetDateTime.now();
        if(build.id!=null){
            this.id = build.id;
        }
    }
    private void validaFilial(Builder d){
        if(d.filial==null){
            throw new IllegalArgumentException("FIlial invalida");
        }
        if(d.tipo==null){
            throw new IllegalArgumentException("Tipo invalida!");
        }
    }


    public Integer getFilial() {
        return filial;
    }
    public Integer getQuantiadeAtivo() {
        return quantiadeAtivo;
    }

    public Integer getQuantidadeManutencao() {
        return quantidadeManutencao;
    }
    public StatusMaterialLogistico getStatus() {
        return status;
    }


    public String getTipo() {
        return tipo;
    }


    public Long getId() {
        return id;
    }

    public static class Builder {
        private  Long id;
        private String tipo;
        private StatusMaterialLogistico Status;
        private Integer quantidadeManutencao;
        private Integer quantiadeAtivo;
        private Integer filial;
        private StatusMaterialLogistico status;
        private  String usuario;
        private  Long usuarioId;
        private OffsetDateTime offsetDateTime;
        private Integer quantidadeTotal;


        public Builder setTipo(String tipo) {
            this.tipo = tipo;
            return this;
        }
        public Builder setId(Long id){
            this.id = id;
            return this;
        }

        public Builder setQuantidadeManutencao(Integer quantidadeManutencao) {
            this.quantidadeManutencao = quantidadeManutencao;
            return this;
        }

        public Builder setStatus(StatusMaterialLogistico status) {
            this.status = status;
            return this;
        }

        public Builder setQuantiadeAtivo(Integer quantiadeAtivo) {
            this.quantiadeAtivo = quantiadeAtivo;
            return this;
        }
        public Builder salve (CriarMaterialLogisticoCommand cmd) {
            this.tipo = cmd.tipo();
            this.status = StatusMaterialLogistico.ATIVO;
            this.quantidadeManutencao = cmd.qtdManutencao();
            this.quantiadeAtivo = cmd.qtdAtivo();
            this.filial = cmd.filial();
            this.usuarioId = cmd.usuarioId();
            this.usuario = cmd.usuario();
            calcularQuantidadeTotal();

            if(cmd.id()!=null){
                this.id=cmd.id();
            }
            return this;
        };
        public Builder setFilial(Integer filial) {
            this.filial = filial;
            return this;

        }
        public Builder setUsuario(String usuario) {
            this.usuario = usuario;
            return this;

        }
        public Builder setUsuarioId(Long usuarioId) {
            this.usuarioId = usuarioId;
            return this;

        }

        public Builder setOffsetDateTime(OffsetDateTime offsetDateTime) {
            this.offsetDateTime = offsetDateTime;
            return this;
        }

        public MaterialLogistico build(){
            if(tipo!=null){
                return new MaterialLogistico(this);
            }
            return null;
        }
        public void calcularQuantidadeTotal() {
            int ativo = quantiadeAtivo != null ? quantiadeAtivo : 0;
            int manutencao = quantidadeManutencao != null ? quantidadeManutencao : 0;
            this.quantidadeTotal = ativo + manutencao;
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public OffsetDateTime getDataCriacao() {
        return offsetDateTime;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantidadeManutencao(Integer quantidadeManutencao) {
        this.quantidadeManutencao = quantidadeManutencao;
    }

    public void setQuantiadeAtivo(Integer quantiadeAtivo) {
        this.quantiadeAtivo = quantiadeAtivo;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }


    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }
}
