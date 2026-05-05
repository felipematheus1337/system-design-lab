package delivery_api.v1.controller;

import delivery_api.v1.controller.request.AddCartItemRequest;
import delivery_api.v1.domain.Cart;
import delivery_api.v1.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/persons/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createCartForPerson(@PathVariable Long personId) {
        return cartService.createCartForPerson(personId);
    }

    @GetMapping("/persons/{personId}")
    public Cart getCartByPerson(@PathVariable Long personId) {
        return cartService.getCartByPerson(personId);
    }

    @PostMapping("/persons/{personId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart addItemToCart(
            @PathVariable Long personId,
            @RequestBody AddCartItemRequest request
    ) {
        return cartService.addItemToCart(personId, request);
    }

    @DeleteMapping("/persons/{personId}/items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItemFromCart(
            @PathVariable Long personId,
            @PathVariable Long cartItemId
    ) {
        cartService.removeItemFromCart(personId, cartItemId);
    }
}