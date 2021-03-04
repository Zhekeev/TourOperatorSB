package kz.ktu.touroperator.controller;

import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.UserRepository;
import kz.ktu.touroperator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public RegistrationController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        if(!userService.addUser(user)){
            model.put("message", "User exists!");
            return "registration";
        }
        return "redirect:/login";
    }
}
