package com.erp.logistico.domain.dto;

import java.util.List;
import java.util.Map;

public record RespostaApiMaterialLogisitoco(
        Long processingTimeMs,
        Resumo resumo,
        Map<String, List<ResponseMaterialLogisticoDTO>> lista) {

}
