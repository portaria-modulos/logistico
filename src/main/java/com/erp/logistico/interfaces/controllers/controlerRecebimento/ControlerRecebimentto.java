package com.erp.logistico.interfaces.controllers.controlerRecebimento;
import com.erp.logistico.application.usecases.CriarRegistroMaterial;
import com.erp.logistico.application.usecases.ListaRegistroGeralMaterial;
import com.erp.logistico.application.usecases.ListaRegistroMaterial;
import com.erp.logistico.application.usecases.MaterialLogistico.DeleteMaterialLogistico;
import com.erp.logistico.application.usecases.MaterialLogistico.UpdateMaterialDTO;
import com.erp.logistico.application.usecases.MaterialLogistico.UpdateMaterialLogistico;
import com.erp.logistico.application.usecases.recebimento.*;
import com.erp.logistico.domain.dto.RegistroMaterialDTO;
import com.erp.logistico.domain.dto.recebimentoDto.RequestCarregamentoDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("communit/v1/recebimento")
public class ControlerRecebimentto {
   private final CriarRegistroRecebimento criarRegistroRecebimento;
   private final FindAll lista;
   private UpdateRecebimento updateService;
   private DeleteRecebimento deleteRecebimento;
   private ListaRegistroGeralRecebimento listaService;
   public ControlerRecebimentto(
           CriarRegistroRecebimento criarRegistroRecebimento,
           FindAll lista,UpdateRecebimento updateServic,DeleteRecebimento deleteRecebimento,ListaRegistroGeralRecebimento service){
       this.criarRegistroRecebimento = criarRegistroRecebimento;
       this.deleteRecebimento = deleteRecebimento;
       this.lista = lista;
       this.updateService = updateServic;
       this.listaService = service;
   }
//    @PreAuthorize("@permissaoService.hasPermission(authentication,'DELETE_LOGISTICO')")
    @PostMapping("/registro")
    public ResponseEntity<?> save(@RequestBody() @Valid RequestCarregamentoDto request){
        criarRegistroRecebimento.save(request);
        return ResponseEntity.ok().body(Map.of("msg","Salvo com sucesso!"));
    }
    @GetMapping("/lista")
    public ResponseEntity<?> findAll(@RequestParam(value = "filial",required = false) Integer filial, @RequestParam("filiais") List<Integer> filiais){
        return ResponseEntity.ok(lista.FindAll(filial,filiais));
    }

    @PreAuthorize("@permissaoService.hasPermission(authentication,'CRIAR_REGISTRO')")
    @PutMapping("/update")
    public ResponseEntity<?> updateMaterialLogistico(@RequestBody() UpdateRecebimentoDTO updateRecebimentoDTO){
        updateService.updateRecebimento(updateRecebimentoDTO);
        return ResponseEntity.ok("atualizado com sucesso");
    }
    @PreAuthorize("@permissaoService.hasPermission(authentication,'DELETE_LOGISTICO')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMaterialLogistico(@RequestParam("registroId") Long registroId, @RequestParam("filial") Integer filial){
        deleteRecebimento.deleteRegistro(registroId,filial);
        System.out.println("id "+registroId + " f"+filial);
        return ResponseEntity.ok("deletado com sucesso");
    }

    @GetMapping("/lista/geral")
    public ResponseEntity<?> finAllGeral(
            @RequestParam(value = "filial") Integer filial,
            Pageable page
    ){
        var lista =listaService.listarItensRegistroFiliais(filial,page);
        return ResponseEntity.ok(Map.of("recebimentpFilias",lista));
    }
}
