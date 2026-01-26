package com.erp.logistico.domain.entities.registro;

import com.erp.logistico.domain.entities.Logistico.MaterialLogistico;

import java.time.LocalDateTime;
import java.util.Set;

public class RegistroMaterial {
    private Long id;
    private  LocalDateTime dataCreacao;
    private  Integer filial;
    private  String filialName;
    private String usuario;
    private Long usuarioId;
    private  Set<MaterialLogistico> itens;
private RegistroMaterial(Builder cmd) {
    if(cmd.itens.size()>3){
        throw new RuntimeException("So pode 3 item pos vez");
    }
    try {
        if(cmd.filial!=null && cmd.itens!=null) {
            this.id = cmd.id;
            this.dataCreacao = LocalDateTime.now();
            this.itens = cmd.itens;
            this.filial = cmd.filial;
            this.filialName = cmd.filialName;
            this.usuarioId = cmd.usuarioId;
            this.usuario = cmd.usuario;

        }
    }catch (RuntimeException e){
        throw new RuntimeException("Dados com estágio invalido, por favor verifique os campos");

    }
}
    private RegistroMaterial(ConverteRegistro cmd) {
                this.id = cmd.id;
                this.dataCreacao = cmd.dataCreacao;
                this.itens = cmd.itens;
                this.filial = cmd.filial;
                this.filialName = cmd.filialName;
                this.usuario = cmd.usuario;

    }

    public Long getId() {
        return id;
    }



    public LocalDateTime getDataCreacao() {
        return dataCreacao;
    }



    public Integer getFilial() {
        return filial;
    }
    public Set<MaterialLogistico> getItens() {
        return itens;
    }

    public String getFilialName() {
        return filialName;
    }

    public static class Builder{
       private Long id;
        private LocalDateTime dataCreacao;
        private Integer filial;
        private String filialName;
        private String usuario;
        private Long usuarioId;
        private Set<MaterialLogistico> itens;

        public Long getId() {
            return id;
        }

        public String getUsuario() {
            return usuario;
        }

        public Long getUsuarioId() {
            return usuarioId;
        }

        public Builder setFilialName(String name) {
            this.filialName= name;
            return this;
        }

        public Builder setUsuario(String usuario) {
            this.usuario = usuario;
            return this;
        }
        public Builder SetUsuarioId(Long id) {
            this.usuarioId= id;
            return this;
        }

        public Builder setFilial(Integer filial) {
            this.filial = filial;
            return this;
        }

        public Builder setDataCreacao(LocalDateTime dataCreacao) {
            this.dataCreacao = dataCreacao;
            return this;
        }
        public Builder setItens(Set<MaterialLogistico> itens){
            this.itens = itens;
            return this;
        }
        public Builder setId(Long id){
            this.id = id;
            return this;
        }
        public RegistroMaterial build(){
            if(filial!=null){
                return new RegistroMaterial(this);
            }
            throw new RuntimeException("erro");
        }


    }
    public static class ConverteRegistro{
        private Long id;
        private LocalDateTime dataCreacao;
        private Integer filial;
        private String filialName;
        private Set<MaterialLogistico> itens;
        private String usuario;
        private Long usuarioId;

        public ConverteRegistro setFilialName(String name) {
            this.filialName= name;
            return this;
        }
        public ConverteRegistro setUsuario(String user) {
           this.usuario  = user;
            return this;
        }
        public ConverteRegistro setUsuarioId(Long id) {
            this.usuarioId= id;
            return this;
        }

        public ConverteRegistro setFilial(Integer filial) {
            this.filial = filial;
            return this;
        }

        public ConverteRegistro setDataCreacao(LocalDateTime dataCreacao) {
            this.dataCreacao = dataCreacao;
            return this;
        }
        public ConverteRegistro setItens(Set<MaterialLogistico> itens){
            this.itens = itens;
            return this;
        }
        public ConverteRegistro setId(Long id){
            this.id = id;
            return this;
        }
        public RegistroMaterial build(){
                return new RegistroMaterial(this);
        }


    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getUsuario() {
        return usuario;
    }
}
