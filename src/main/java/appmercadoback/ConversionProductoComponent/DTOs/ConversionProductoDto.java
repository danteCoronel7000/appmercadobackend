package appmercadoback.ConversionProductoComponent.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionProductoDto {
    private Integer id;
    private Integer productoId;
    private Integer unidadCompraId;
    private Integer unidadVentaId;
    private BigDecimal factor;
}