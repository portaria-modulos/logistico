package com.erp.logistico.interfaces.controllers.controllerMaterialLogistico;


import com.erp.logistico.application.usecases.MaterialLogistico.FindOneMaterialLogistico;
import com.erp.logistico.application.usecases.MaterialLogistico.UpdateMaterialDTO;
import com.erp.logistico.application.usecases.MaterialLogistico.UpdateMaterialLogistico;
import com.erp.logistico.domain.dto.RespostaApiMaterialLogisitoco;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("material/v1")
public class ControlerMaterialLogistico {
    private final FindOneMaterialLogistico service;

    public ControlerMaterialLogistico(FindOneMaterialLogistico service) {
        this.service = service;
    }
    @GetMapping("/findOne")
    public ResponseEntity<RespostaApiMaterialLogisitoco> findOneMaterialLogistico(@RequestParam("tipo") String tipo,@RequestParam("filial") Long filial){
        var tipoDto = service.findOneMaterialLogistico(tipo,filial);
        return ResponseEntity.ok(tipoDto);
    }

}
