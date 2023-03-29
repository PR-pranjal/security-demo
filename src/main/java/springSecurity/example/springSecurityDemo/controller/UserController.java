package springSecurity.example.springSecurityDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.*;
import springSecurity.example.springSecurityDemo.model.User;
import springSecurity.example.springSecurityDemo.service.UserService;

import java.util.List;
@RestController
@RequestMapping("/users")
@EnableGlobalAuthentication
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getAllUsers(){
       return userService.getAllUsers();

    }
    @GetMapping("/{username}")
    public User getSingleUser(@PathVariable("username") String userName){
        return userService.getSingleUser(userName);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
