package com.erp.logistico.infrastructure.persistence.recebimento;

import com.erp.logistico.domain.entities.recebimento.Carregamento;
import com.erp.logistico.domain.entities.recebimento.ItensCarregamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
class CarregamentoEntityTest {
    List<ItensCarregamento> itens;

    @Test
    @DisplayName("Salva registro")
    public void deveSalvaRegistroCarregamento(){
//     arrange
        itens = new ArrayList<>();
        itens.add(new ItensCarregamento(1l,"hort",
                1,2));
        Carregamento c = new Carregamento(
                1l,
                "Elivandro",
                1l,
                116,"itapera",itens, LocalDateTime.now());

        //     active

        //     assert
        itens.forEach(e->{
            Assertions.assertEquals("hort",e.getTipoBloco());
        });
        Assertions.assertEquals("Elivandro",c.getNomeUsuario());
        Assertions.assertEquals(116,c.getfIlial());
    }
    @Test
    public void deveLancarExeption(){
       Assertions.assertThrows(IllegalArgumentException.class,()->{
          itens = new ArrayList<>();
           itens.add(new ItensCarregamento(1l,"",
                   1,-2));
            new Carregamento(
                    1l,
                   "Elivandro",
                   1l,
                   1,"itapera",itens, LocalDateTime.now());
       });
    }

}