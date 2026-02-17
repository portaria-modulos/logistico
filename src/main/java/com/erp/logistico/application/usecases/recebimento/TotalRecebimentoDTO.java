package com.erp.logistico.application.usecases.recebimento;

public record TotalRecebimentoDTO (Long totalPortaria,
                                   Long totalPorto,
                                   Long totalGeral,
                                   Long totalPendente){
}
