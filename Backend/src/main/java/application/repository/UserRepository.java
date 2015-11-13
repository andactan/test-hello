package application.repository;

import application.core.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Integer>{

    User findById(Integer id);
    User findByUsername(String username);
    ArrayList<User> findAll();

    @Query("DELETE FROM User u where u.username = :username")
    boolean deleteUserByUsername(@Param("username") String username);
}
