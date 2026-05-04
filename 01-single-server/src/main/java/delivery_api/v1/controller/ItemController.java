package delivery_api.v1.controller;

import delivery_api.v1.controller.request.CreateItemRequest;
import delivery_api.v1.domain.Item;
import delivery_api.v1.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody CreateItemRequest request) {
        Item item = new Item();
        item.setSku(request.sku());
        item.setName(request.name());
        item.setValue(request.value());

        return itemRepository.save(item);
    }

    @GetMapping
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Item findById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }
}