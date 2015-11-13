package application.controller;

import application.core.User;
import application.repository.FollowRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository repo;

    @Autowired
    private FollowRepository follow;

    public String hello(){
        return "Hello Open!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ArrayList<User> findAll(){
        ArrayList<User> temp =  repo.findAll();
        for(User user: temp){
            user.setFollowList((ArrayList<Integer>)follow.getFollowers(user.getId()));
        }
        return temp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public User findById(@PathVariable("id") int id){
        User user = repo.findById(id);
        if(user == null){
            user = new User();
            user.setId(-1);
            return user;
        }
        user.setFollowList((ArrayList<Integer>)follow.getFollowers(user.getId()));
        return user;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/username/{username}")
    public User findByUsername(@PathVariable("username") String username){
        User user = repo.findByUsername(username);
        if(user == null){
            user = new User();
            user.setId(-1);
            return user;
        }
        user.setFollowList((ArrayList<Integer>)follow.getFollowers(user.getId()));
        return user;
    }
    /**
     * -3 : already exists
     */
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public User save(
            @RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("email") String email)
    {
        User temp = repo.findByUsername(username);
        if(temp != null){
            temp.setId(-3);
            return temp;
        }

        BCrypt crypto = new BCrypt();
        String rawPassword = password;
        String saltPassword = crypto.gensalt(12);
        String hashPassword = crypto.hashpw(rawPassword, saltPassword);

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword);
        user.setEmail(email);
        user.setFacebookId(username + "@facebook");
        user.setGoogleId(username  + "@google");
        user.setTwitterId(username + "@twitter");
        repo.save(user);
        return user;
    }
    /*
        -1 : member does not exist
        -2 : wrong password
    */
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public User login(
            @RequestParam("username") String username, @RequestParam("password") String password){

        User user = repo.findByUsername(username);
        if(user == null){
            user = new User();
            user.setId(-1);
            return user;
        }
        BCrypt crypto = new BCrypt();
        if(!crypto.checkpw(password, user.getPassword())){
            user.setId(-2);
            return user;
        }

        return user;
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public boolean deleteUser(@RequestParam("username") String username){
        boolean status = repo.deleteUserByUsername(username);
        return status;
    }

}
