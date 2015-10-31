package example.controller;

import example.core.Person;
import example.repo.PersonRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private PersonRepository personRepo;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Person findOne(@PathVariable("id") Long id){
        return personRepo.findById(id);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public Iterable<Person> findAll(Pageable page){
        return personRepo.findAll(page);
    }
}
