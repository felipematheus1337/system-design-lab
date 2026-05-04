package delivery_api.v1.service;

import delivery_api.v1.controller.request.CreateItemRequest;
import delivery_api.v1.domain.Item;
import delivery_api.v1.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item create(CreateItemRequest request) {
        Item item = new Item();
        item.setSku(request.sku());
        item.setName(request.name());
        item.setValue(request.value());

        return itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }
}