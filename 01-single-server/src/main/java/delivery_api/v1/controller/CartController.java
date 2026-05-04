package delivery_api.v1.controller;

import delivery_api.v1.controller.request.AddCartItemRequest;
import delivery_api.v1.domain.Cart;
import delivery_api.v1.domain.CartItem;
import delivery_api.v1.domain.Item;
import delivery_api.v1.domain.Person;
import delivery_api.v1.repository.CartRepository;
import delivery_api.v1.repository.ItemRepository;
import delivery_api.v1.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final PersonRepository personRepository;
    private final ItemRepository itemRepository;

    public CartController(
            CartRepository cartRepository,
            PersonRepository personRepository,
            ItemRepository itemRepository
    ) {
        this.cartRepository = cartRepository;
        this.personRepository = personRepository;
        this.itemRepository = itemRepository;
    }

    @PostMapping("/persons/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createCartForPerson(@PathVariable Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        if (person.getCart() != null) {
            return person.getCart();
        }

        Cart cart = new Cart();

        person.setCart(cart);
        cart.setPerson(person);

        personRepository.save(person);

        return cart;
    }

    @GetMapping("/persons/{personId}")
    public Cart getCartByPerson(@PathVariable Long personId) {
        return cartRepository.findByPersonId(personId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @PostMapping("/persons/{personId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart addItemToCart(
            @PathVariable Long personId,
            @RequestBody AddCartItemRequest request
    ) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        Cart cart = person.getCart();

        if (cart == null) {
            cart = new Cart();
            person.setCart(cart);
            cart.setPerson(person);
        }

        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(request.quantity());
        cartItem.setUnitValue(item.getValue());

        cart.addItem(cartItem);

        personRepository.save(person);

        return cart;
    }

    @DeleteMapping("/persons/{personId}/items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItemFromCart(
            @PathVariable Long personId,
            @PathVariable Long cartItemId
    ) {
        Cart cart = cartRepository.findByPersonId(personId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItemToRemove = cart.getItems()
                .stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cart.removeItem(cartItemToRemove);

        cartRepository.save(cart);
    }
}