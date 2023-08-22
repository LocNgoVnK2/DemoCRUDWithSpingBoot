package com.example.cruddemowithspring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired UserService userService;

    @GetMapping("/users")
    public String ShowAllUserInList(Model model){
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";

    }
    @GetMapping("/users/new")
    public String ShowAddNewUserForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add new user");
        return "addNewUser";

    }
    @PostMapping("/users/save")
    public String SaveUser(User user, RedirectAttributes ra){
        userService.saveUser(user);
        ra.addFlashAttribute("message","User has been updated successfully");
        return "redirect:/users";
    }
    @GetMapping("/users/delete/{id}")
    public  String RemoveUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        User userRemove = userService.getUserById(id);
        userService.removeUser(userRemove);
        ra.addFlashAttribute("message","User has been deleted successfully");
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public  String UpdateUser(@PathVariable("id") Integer id,Model model){
        User userUpdate = userService.getUserById(id);
        model.addAttribute("user",userUpdate);
        model.addAttribute("pageTitle","Update User");
        return "addNewUser";
    }
}
