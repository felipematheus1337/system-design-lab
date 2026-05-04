package delivery_api.v1.config;

import delivery_api.v1.domain.Cart;
import delivery_api.v1.domain.CartItem;
import delivery_api.v1.domain.Item;
import delivery_api.v1.domain.Person;
import delivery_api.v1.repository.ItemRepository;
import delivery_api.v1.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DatabaseSeederConfig {

    @Bean
    CommandLineRunner seedDatabase(
            PersonRepository personRepository,
            ItemRepository itemRepository
    ) {
        return args -> {
            if (personRepository.count() > 0 || itemRepository.count() > 0) {
                return;
            }

            Item burger = new Item();
            burger.setSku("BURGER-001");
            burger.setName("Burger Artesanal");
            burger.setValue(new BigDecimal("29.90"));

            Item fries = new Item();
            fries.setSku("FRIES-001");
            fries.setName("Batata Frita");
            fries.setValue(new BigDecimal("14.90"));

            Item soda = new Item();
            soda.setSku("SODA-001");
            soda.setName("Refrigerante Lata");
            soda.setValue(new BigDecimal("7.50"));

            itemRepository.saveAll(List.of(burger, fries, soda));

            Cart cart = new Cart();

            CartItem burgerCartItem = new CartItem();
            burgerCartItem.setItem(burger);
            burgerCartItem.setQuantity(2);
            burgerCartItem.setUnitValue(burger.getValue());

            CartItem friesCartItem = new CartItem();
            friesCartItem.setItem(fries);
            friesCartItem.setQuantity(1);
            friesCartItem.setUnitValue(fries.getValue());

            CartItem sodaCartItem = new CartItem();
            sodaCartItem.setItem(soda);
            sodaCartItem.setQuantity(2);
            sodaCartItem.setUnitValue(soda.getValue());

            cart.addItem(burgerCartItem);
            cart.addItem(friesCartItem);
            cart.addItem(sodaCartItem);

            Person person = new Person();
            person.setName("Felipe Matheus");
            person.setAge(25);
            person.setDocument("12345678909");

            person.setZipCode("01001-000");
            person.setStreet("Rua Exemplo");
            person.setNumber(100);
            person.setComplement("Apartamento 202");
            person.setNeighborhood("Centro");
            person.setCity("São Paulo");
            person.setState("SP");

            person.setCart(cart);
            cart.setPerson(person);

            personRepository.save(person);

            System.out.println("Database seeded successfully.");
        };
    }
}