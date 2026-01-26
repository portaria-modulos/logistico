package com.erp.logistico.application.usecases.MaterialLogistico;

import com.erp.logistico.domain.dto.MaterialLogisticoDTO;
import com.erp.logistico.domain.dto.UpdateMaterialLogisticoDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateMaterialDTO(
         @NotNull
         Long registroId,
         Long usuarioId,
         @NotNull
         @NotEmpty
         @Size(max = 3, message = "É permitido no máximo 3 itens")
         List<UpdateMaterialLogisticoDTO> itens
) {

}
