package com.example.cruddemowithspring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired private UserRepository _repoUser;

    public List<User> listAll(){
        return  (List<User>)_repoUser.findAll();
    }
    public void saveUser(User user){
        _repoUser.save(user);
    }
    public void removeUser(User user){
        _repoUser.delete(user);

    }
    public User getUserById(Integer id){
        return (User)_repoUser.findById(id).orElse(null);
    }
}
