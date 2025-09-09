package appmercadoback.productoComponent.dtos;

import appmercadoback.productoComponent.entitys.ProductoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class ProductoDtoForWeb {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Boolean perecedero;
    private String unidadMedida;
    private String presentacion;
    private String medida;
    private Integer stockActual;
    private Integer stockMin;
    private Date fechaVencimiento;
    private Integer popularidad;
    private String etiquetas;
    private String categoriaNombre;
    private Integer idCategoria;
    private String imageUrl;

    // Constructor existente: convenientemente construye el DTO desde la entidad
    public ProductoDtoForWeb(ProductoEntity producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.perecedero = producto.getPerecedero();
        this.unidadMedida = producto.getUnidadMedida();
        this.medida = producto.getMedida();
        this.stockActual = producto.getStockActual();
        this.stockMin = producto.getStockMin();
        this.fechaVencimiento = producto.getFechaVencimiento();
        this.popularidad = producto.getPopularidad();
        this.etiquetas = producto.getEtiquetas();
        this.categoriaNombre = producto.getCategoria() != null ? producto.getCategoria().getNombre() : null;
        this.idCategoria = producto.getCategoria() != null ? producto.getCategoria().getId(): null;
        this.imageUrl = producto.getImage() != null ? producto.getImage().getImageUrl() : null;
    }
}
