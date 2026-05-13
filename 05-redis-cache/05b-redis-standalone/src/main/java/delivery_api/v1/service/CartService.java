package delivery_api.v1.service;

import delivery_api.v1.controller.request.AddCartItemRequest;
import delivery_api.v1.domain.Cart;
import delivery_api.v1.domain.CartItem;
import delivery_api.v1.domain.Item;
import delivery_api.v1.domain.Person;
import delivery_api.v1.exception.BusinessException;
import delivery_api.v1.service.repository.CartRepository;
import delivery_api.v1.service.repository.ItemRepository;
import delivery_api.v1.service.repository.PersonRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final PersonRepository personRepository;
    private final ItemRepository itemRepository;
    private final CacheService cacheService;

    public CartService(
            CartRepository cartRepository,
            PersonRepository personRepository,
            ItemRepository itemRepository, CacheService cacheService
    ) {
        this.cartRepository = cartRepository;
        this.personRepository = personRepository;
        this.itemRepository = itemRepository;
        this.cacheService = cacheService;
    }

    @Transactional
    public Cart createCartForPerson(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        if (person.getCart() != null) {
            return person.getCart();
        }

        Cart cart = new Cart();

        person.setCart(cart);
        cart.setPerson(person);

        personRepository.save(person);

        cacheService.save(personId, cart);

        return cart;
    }

    public Cart getCartByPerson(Long personId) {
        return (Cart) cacheService.findById(personId)
                .orElseGet(() -> findByIdFromDatabaseAndCache(personId));

    }

    private Cart findByIdFromDatabaseAndCache(Long personId) {
        Cart cart = cartRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Cart not found"));

        cacheService.save(personId, cart);

        return cart;
    }

    @Transactional
    public Cart addItemToCart(Long personId, AddCartItemRequest request) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new BusinessException("Person not found with id: " + personId));

        Cart cart = person.getCart();

        if (cart == null) {
            cart = new Cart();
            person.setCart(cart);
            cart.setPerson(person);
        }

        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(() -> new BusinessException("Item not found with id: " + request.itemId()));

        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(request.quantity());
        cartItem.setUnitValue(item.getUnitPrice());

        cart.addItem(cartItem);

        personRepository.save(person);
        cacheService.save(personId, cart);

        return cart;
    }

    @Transactional
    public void removeItemFromCart(Long personId, Long cartItemId) {
        Cart cart = cartRepository.findByPersonId(personId)
                .orElseThrow(() -> new BusinessException("Cart not found"));

        CartItem cartItemToRemove = cart.getItems()
                .stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new BusinessException("CartItem not found"));

        cart.removeItem(cartItemToRemove);

        cartRepository.save(cart);
        cacheService.evict(personId);
    }
}