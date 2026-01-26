package com.erp.logistico.domain.dto;

import com.erp.logistico.infrastructure.persistence.RegistroMaterial.RegistroMaterialEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.stream.Collectors;

public record RegistroMaterialDTO(
         @NotBlank
         String usuario,
         @NotNull
         Long usuarioId,
         @NotBlank
         String nomeFilial,
         @NotNull
         Integer numeroFIlial,
         @Valid
         @NotEmpty
         @Size(max = 3, message = "É permitido no máximo 3 itens")
         Set<MaterialLogisticoDTO> itens

) {

}
