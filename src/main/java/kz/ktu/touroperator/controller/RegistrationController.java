package kz.ktu.touroperator.controller;

import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.UserRepository;
import kz.ktu.touroperator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("secondPasswordForValidation") String secondPasswordForValidation,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        boolean isConfirmEmpty = StringUtils.isEmpty(secondPasswordForValidation);

        if (isConfirmEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (isConfirmEmpty) {
            model.addAttribute("secondPasswordForValidationError", "Поле пустое");
            return "registration";
        }

        if (!userService.checkPasswordSimilarity(user, secondPasswordForValidation)) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }

        if(!userService.checkUser(user)){
            model.addAttribute("usernameError", "Пользователь с этим логином уже зарегестрирован!");
            return "registration";
        } else if(!userService.checkPassword(user)){
            model.addAttribute("passwordError", "Пароль не прошел валидацию!");
            return "registration";
        } else {
            userService.addUser(user);
        }
        model.addAttribute("messageType", "success");
        model.addAttribute("message", "Вам на почту отправлено письмо для активации");
        return "login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Ваш аккаунт успешно активирован");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Активационный код не действителен");
        }
        return "login";
    }
}
