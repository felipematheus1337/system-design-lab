package delivery_api.v1.repository;

import delivery_api.v1.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findBySku(String sku);
}
