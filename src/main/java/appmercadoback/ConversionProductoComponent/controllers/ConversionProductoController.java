package appmercadoback.ConversionProductoComponent.controllers;

import appmercadoback.ConversionProductoComponent.DTOs.ConversionProductoDto;
import appmercadoback.ConversionProductoComponent.DTOs.ConversionRequestDto;
import appmercadoback.ConversionProductoComponent.DTOs.ConversionResponseDto;
import appmercadoback.ConversionProductoComponent.Entity.ConversionProductoEntity;
import appmercadoback.ConversionProductoComponent.repository.ConversionProductoRepository;
import appmercadoback.ConversionProductoComponent.services.ConversionProductoService;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import appmercadoback.productoComponent.repositorys.ProductoRepository;
import appmercadoback.unidadMedidaComponent.entity.UnidadMedidaEntity;
import appmercadoback.unidadMedidaComponent.repository.UnidadMedidaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/conversiones")
public class ConversionProductoController {

    private final ConversionProductoService service;
    private final ProductoRepository productoRepo;
    private final UnidadMedidaRepository unidadRepo;
    private final ConversionProductoRepository conversionRepo;

    public ConversionProductoController(ConversionProductoService service,
                                        ProductoRepository productoRepo,
                                        UnidadMedidaRepository unidadRepo,
                                        ConversionProductoRepository conversionRepo) {
        this.service = service;
        this.productoRepo = productoRepo;
        this.unidadRepo = unidadRepo;
        this.conversionRepo = conversionRepo;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ConversionProductoDto dto) {
        ProductoEntity producto = productoRepo.findById(dto.getProductoId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        UnidadMedidaEntity uc = unidadRepo.findById(dto.getUnidadCompraId())
                .orElseThrow(() -> new IllegalArgumentException("Unidad de compra no encontrada"));
        UnidadMedidaEntity uv = unidadRepo.findById(dto.getUnidadVentaId())
                .orElseThrow(() -> new IllegalArgumentException("Unidad de venta no encontrada"));

        ConversionProductoEntity conv = new ConversionProductoEntity();
        conv.setProducto(producto);
        conv.setUnidadCompra(uc);
        conv.setUnidadVenta(uv);
        conv.setFactor(dto.getFactor());

        ConversionProductoEntity creado = service.create(conv);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ConversionProductoEntity>> listaPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity.ok(service.findByProductoId(productoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversionProductoEntity> obtener(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversionProductoEntity> actualizar(@PathVariable Integer id, @RequestBody ConversionProductoDto dto) {
        ConversionProductoEntity conv = conversionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conversión no encontrada"));

        ProductoEntity producto = productoRepo.findById(dto.getProductoId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        UnidadMedidaEntity uc = unidadRepo.findById(dto.getUnidadCompraId())
                .orElseThrow(() -> new IllegalArgumentException("Unidad de compra no encontrada"));
        UnidadMedidaEntity uv = unidadRepo.findById(dto.getUnidadVentaId())
                .orElseThrow(() -> new IllegalArgumentException("Unidad de venta no encontrada"));

        conv.setProducto(producto);
        conv.setUnidadCompra(uc);
        conv.setUnidadVenta(uv);
        conv.setFactor(dto.getFactor());

        ConversionProductoEntity actualizado = service.update(id, conv);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint de conversión rápido: recibe cantidad en unidadCompra y devuelve cantidad en unidadVenta
    @PostMapping("/convertir")
    public ResponseEntity<ConversionResponseDto> convertir(@RequestBody ConversionRequestDto req) {
        BigDecimal convertido = service.convertir(req.getProductoId(), req.getUnidadCompraId(), req.getUnidadVentaId(), req.getCantidad());

        ConversionResponseDto resp = new ConversionResponseDto();
        resp.setProductoId(req.getProductoId());
        resp.setUnidadCompraId(req.getUnidadCompraId());
        resp.setUnidadVentaId(req.getUnidadVentaId());
        resp.setCantidadOriginal(req.getCantidad());
        resp.setCantidadConvertida(convertido);
        // intentar obtener el factor para incluirlo en la respuesta (opcional)
        conversionRepo.findByProductoIdAndUnidadCompraIdAndUnidadVentaId(req.getProductoId(), req.getUnidadCompraId(), req.getUnidadVentaId())
                .ifPresent(c -> resp.setFactor(c.getFactor()));

        return ResponseEntity.ok(resp);
    }
}