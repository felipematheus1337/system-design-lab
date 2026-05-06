package delivery_api.v1.controller.request;

import java.math.BigDecimal;

public record CreateItemRequest(
        String sku,
        String name,
        BigDecimal unitPrice
) {
}