package delivery_api.v1.service;

import delivery_api.v1.controller.request.CreateItemRequest;
import delivery_api.v1.domain.Item;
import delivery_api.v1.exception.BusinessException;
import delivery_api.v1.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Item create(CreateItemRequest request) {
        itemRepository.findBySku(request.sku())
                .ifPresent(item -> {
                    throw new BusinessException("SKU already exists: " + request.sku());
                });

        Item item = new Item();
        item.setSku(request.sku());
        item.setName(request.name());
        item.setUnitPrice(request.unitPrice());

        return itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Item not found with id: " + id));
    }
}