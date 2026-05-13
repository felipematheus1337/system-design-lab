package delivery_api.v1.service.repository;

import delivery_api.v1.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
