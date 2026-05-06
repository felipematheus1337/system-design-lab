package delivery_api.v1.repository;

import delivery_api.v1.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository  extends JpaRepository<Cart, Long> {

    Optional<Cart> findByPersonId(Long personId);
}
