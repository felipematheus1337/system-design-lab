package delivery_api.v1.service;

import delivery_api.v1.controller.request.CreatePersonRequest;
import delivery_api.v1.domain.Person;
import delivery_api.v1.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(CreatePersonRequest request) {
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

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }
}