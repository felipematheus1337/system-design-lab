package delivery_api.v1.controller.request;

public record AddCartItemRequest(
        Long itemId,
        Integer quantity
) {
}