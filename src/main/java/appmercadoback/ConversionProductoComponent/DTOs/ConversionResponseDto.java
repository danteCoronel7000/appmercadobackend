package appmercadoback.ConversionProductoComponent.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionResponseDto {
    private Integer productoId;
    private Integer unidadCompraId;
    private Integer unidadVentaId;
    private BigDecimal cantidadOriginal;
    private BigDecimal cantidadConvertida;
    private BigDecimal factor;
    private String nota; // por si hubo alguna advertencia
}
