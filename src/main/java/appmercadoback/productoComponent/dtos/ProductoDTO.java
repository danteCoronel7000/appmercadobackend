package appmercadoback.productoComponent.dtos;

import appmercadoback.productoComponent.entitys.ProductoEntity;
import lombok.Data;

import java.util.Date;
@Data
public class ProductoDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Boolean perecedero;
    private String unidadMedida;
    private Integer stockActual;
    private Integer stockMin;
    private Date fechaVencimiento;
    private Integer popularidad;
    private String etiquetas;
    private String categoriaNombre;
    private String imageUrl;

    // Constructor con campos relevantes
    public ProductoDTO(ProductoEntity producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.perecedero = producto.getPerecedero();
        this.unidadMedida = producto.getUnidadMedida();
        this.stockActual = producto.getStockActual();
        this.stockMin = producto.getStockMin();
        this.fechaVencimiento = producto.getFechaVencimiento();
        this.popularidad = producto.getPopularidad();
        this.etiquetas = producto.getEtiquetas();
        this.categoriaNombre = producto.getCategoria() != null ? producto.getCategoria().getNombre() : null;
        this.imageUrl = producto.getImage() != null ? producto.getImage().getImageUrl() : null;
    }
}
