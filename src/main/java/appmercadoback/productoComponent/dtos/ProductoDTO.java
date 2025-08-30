package appmercadoback.productoComponent.dtos;

import appmercadoback.productoComponent.entitys.ProductoEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String medida;
    private Integer stockActual;
    private Integer stockMin;
    private Date fechaVencimiento;
    private Integer popularidad;
    private String etiquetas;
    private String categoriaNombre;
    private String imageUrl;

    // Constructor existente: convenientemente construye el DTO desde la entidad
    public ProductoDTO(ProductoEntity producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.perecedero = producto.getPerecedero();
        this.unidadMedida = producto.getUnidadMedida();
        this.medida  = producto.getMedida();
        this.stockActual = producto.getStockActual();
        this.stockMin = producto.getStockMin();
        this.fechaVencimiento = producto.getFechaVencimiento();
        this.popularidad = producto.getPopularidad();
        this.etiquetas = producto.getEtiquetas();
        this.categoriaNombre = producto.getCategoria() != null ? producto.getCategoria().getNombre() : null;
        this.imageUrl = producto.getImage() != null ? producto.getImage().getImageUrl() : null;
    }

    // Nuevo constructor posicional de 14 argumentos (el que necesitas para `new ProductoDTO(...14 args)`)
    @JsonCreator
    public ProductoDTO(
            @JsonProperty("id") Integer id,
            @JsonProperty("nombre") String nombre,
            @JsonProperty("descripcion") String descripcion,
            @JsonProperty("precio") Float precio,
            @JsonProperty("perecedero") Boolean perecedero,
            @JsonProperty("unidadMedida") String unidadMedida,
            @JsonProperty("medida") String medida,
            @JsonProperty("stockActual") Integer stockActual,
            @JsonProperty("stockMin") Integer stockMin,
            @JsonProperty("fechaVencimiento") Date fechaVencimiento,
            @JsonProperty("popularidad") Integer popularidad,
            @JsonProperty("etiquetas") String etiquetas,
            @JsonProperty("categoriaNombre") String categoriaNombre,
            @JsonProperty("imageUrl") String imageUrl
    ) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.perecedero = perecedero;
        this.unidadMedida = unidadMedida;
        this.medida = medida;
        this.stockActual = stockActual;
        this.stockMin = stockMin;
        this.fechaVencimiento = fechaVencimiento;
        this.popularidad = popularidad;
        this.etiquetas = etiquetas;
        this.categoriaNombre = categoriaNombre;
        this.imageUrl = imageUrl;
    }
}