package example.repo;

import example.core.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Iterable<Person> findAll(Pageable pageable);
    Person findById(Long id);
}
