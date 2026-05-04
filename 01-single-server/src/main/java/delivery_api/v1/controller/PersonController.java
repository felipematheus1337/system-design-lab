package delivery_api.v1.controller;

import delivery_api.v1.controller.request.CreatePersonRequest;
import delivery_api.v1.domain.Person;
import delivery_api.v1.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody CreatePersonRequest request) {
        Person person = new Person();

        person.setName(request.name());
        person.setAge(request.age());
        person.setDocument(request.document());

        person.setZipCode(request.zipCode());
        person.setStreet(request.street());
        person.setNumber(request.number());
        person.setComplement(request.complement());
        person.setNeighborhood(request.neighborhood());
        person.setCity(request.city());
        person.setState(request.state());

        return personRepository.save(person);
    }

    @GetMapping
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }
}