package com.erp.logistico.interfaces.controllers;
import com.erp.logistico.application.usecases.CriarRegistroMaterial;
import com.erp.logistico.application.usecases.ListaRegistroGeralMaterial;
import com.erp.logistico.application.usecases.ListaRegistroMaterial;
import com.erp.logistico.application.usecases.MaterialLogistico.DeleteMaterialLogistico;
import com.erp.logistico.application.usecases.MaterialLogistico.UpdateMaterialDTO;
import com.erp.logistico.application.usecases.MaterialLogistico.UpdateMaterialLogistico;
import com.erp.logistico.domain.dto.RegistroMaterialDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("communit/v1/runmit")
public class ControlerRegistroMaterial {
    private final CriarRegistroMaterial criarRegistroMaterial;
    private final ListaRegistroMaterial serviceLista;
    private final ListaRegistroGeralMaterial listaRegralUseCase;
    private final UpdateMaterialLogistico serviceUpdate;
    private final DeleteMaterialLogistico serviceDelete;

    public ControlerRegistroMaterial(
            CriarRegistroMaterial criarRegistro,
            ListaRegistroMaterial serviceLista,
            ListaRegistroGeralMaterial listaRegralUseCase,
            UpdateMaterialLogistico serviceUpdate,
            DeleteMaterialLogistico serviceDelete
    ){
        this.criarRegistroMaterial = criarRegistro;
        this.serviceLista = serviceLista;
        this.listaRegralUseCase = listaRegralUseCase;
        this.serviceUpdate = serviceUpdate;
        this.serviceDelete = serviceDelete;
    }

@PostMapping("/registro")
public ResponseEntity<?> save(@RequestBody() @Valid() RegistroMaterialDTO dto){
   try{
       criarRegistroMaterial.criarControleMaterial(dto);
       return ResponseEntity.ok().body(Map.of("msg","Salvo com sucesso!"));
   }catch (Exception e){
       throw new RuntimeException("Erro ao salvar dados "+e.getMessage());
   }
}
 @GetMapping("/lista")
 public ResponseEntity<?> finAll(@RequestParam(value = "filial",required = false) Integer filial, @RequestParam("filiais") List<Integer> filiais){
        var lista =serviceLista.lista(filial,filiais);
            return ResponseEntity.ok(Map.of("logisticoFilias",lista));
 }

    @GetMapping("/lista/geral")
    public ResponseEntity<?> finAllGeral(
            @RequestParam(value = "filial") Integer filial,
            Pageable page
    ){
        var lista =listaRegralUseCase.listarItensRegistroFiliais(filial,page);
        return ResponseEntity.ok(Map.of("logisticoFilias",lista));
    }
    @PreAuthorize("@permissaoService.hasPermission(authentication,'CRIAR_REGISTRO')")
    @PutMapping("/update")
    public ResponseEntity<?> updateMaterialLogistico(@RequestBody() UpdateMaterialDTO updateMaterialLogistico){
        serviceUpdate.updateLogistico(updateMaterialLogistico);
        return ResponseEntity.ok("atualizado com sucesso");
    }
    @PreAuthorize("@permissaoService.hasPermission(authentication,'DELETE_LOGISTICO')")
    @DeleteMapping("/delete/cardlogistico")
    public ResponseEntity<?> deleteMaterialLogistico(@RequestParam("registroId") Long registroId, @RequestParam("filial") Integer filial){
        serviceDelete.deleteLogistico(registroId,filial);
        return ResponseEntity.ok("deletado com sucesso");
    }

}
