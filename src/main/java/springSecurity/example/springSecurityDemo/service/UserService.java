package springSecurity.example.springSecurityDemo.service;

import org.springframework.stereotype.Service;
import springSecurity.example.springSecurityDemo.model.User;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    List<User> userList=new ArrayList<>();
    public UserService(){
        userList.add(new User("pranjal","xyz","pranjal@gmail.com"));
        userList.add(new User("piyush","abc","piyush@gmail.com"));
    }
    public List<User> getAllUsers(){
        return this.userList;
    }
    public User getSingleUser(String userName){
        return this.userList.stream().filter(user -> user.getUserName().equals(userName)).findAny().orElse(null);

    }
    public User addUser(User user){
        this.userList.add(user);
        return user;
    }
}
