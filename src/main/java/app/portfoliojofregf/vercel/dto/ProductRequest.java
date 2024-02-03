package app.portfoliojofregf.vercel.dto;

import lombok.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
}
