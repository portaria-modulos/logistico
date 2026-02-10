package com.erp.logistico.domain.entities.recebimento;

import java.time.LocalDateTime;
import java.util.List;

public class FactoryItens {

    public static ItensCarregamento fabricaDeCarregamento(Long id,String tipoBloco,
                                                   Integer qtdPendentes,
                                                   Integer qtdChamado
                                                   ){
        return  new ItensCarregamento(id,tipoBloco,qtdPendentes,qtdChamado);
    }
}
