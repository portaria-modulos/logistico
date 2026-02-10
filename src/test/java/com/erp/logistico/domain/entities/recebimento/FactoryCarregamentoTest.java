package com.erp.logistico.domain.entities.recebimento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
class FactoryCarregamentoTest {
    @Test
    public void deveSalvaItensViaFacture(){
        List<ItensCarregamento> itens = new ArrayList<>();
        var itensCarregameno = new ItensCarregamento(1l,"frios",10,20,1,23);
        itens.add(itensCarregameno);
        var ca = new FactoryCarregamento();
       var carregamento =   ca.fabricaDeCarregamento(1l,"Bruna","Itapera",1l,112,itens, LocalDateTime.now());
        Assertions.assertEquals("Bruna",carregamento.getNomeUsuario());
        Assertions.assertEquals(112,carregamento.getfIlial());
        Assertions.assertEquals("frios",itensCarregameno.getTipoBloco());


    }

}