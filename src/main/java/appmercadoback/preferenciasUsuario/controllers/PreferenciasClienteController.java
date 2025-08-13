package appmercadoback.preferenciasUsuario.controllers;

import appmercadoback.preferenciasUsuario.services.PreferenciasClienteService;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/preferencias")
public class PreferenciasClienteController {

    @Autowired
    private PreferenciasClienteService preferenciasClienteService;

    @GetMapping("/get")
    public List<ProductoEntity> explorar(@RequestParam Long clienteId) {
        return preferenciasClienteService.obtenerRecomendaciones(clienteId);
    }
}
