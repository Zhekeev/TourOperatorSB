package kz.ktu.touroperator.controller;

import kz.ktu.touroperator.connection.ConnectionPoolException;
import kz.ktu.touroperator.dao.impl.UserDaoImpl;
import kz.ktu.touroperator.model.Bank;
import kz.ktu.touroperator.model.Contract;
import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.BankRepository;
import kz.ktu.touroperator.repository.UserRepository;
import kz.ktu.touroperator.service.BankService;
import kz.ktu.touroperator.service.ContractService;
import kz.ktu.touroperator.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final ContractService contractService;
    private final BankRepository bankRepository;
    private final BankService bankService;
    private final PasswordEncoder passwordEncoder;
    private final UserDaoImpl userDao;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, ContractService contractService, BankRepository bankRepository, BankService bankService, PasswordEncoder passwordEncoder, UserDaoImpl userDao) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.contractService = contractService;
        this.bankRepository = bankRepository;
        this.bankService = bankService;
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @SneakyThrows
    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {
        User userFromDb = userService.findUserByUsername(user.getUsername());
        List<Contract> contracts = contractService.getUserAllContract(user);
        model.addAttribute("contracts", contracts);
        model.addAttribute("user", userFromDb);
        return "profile";
    }

    @GetMapping("/settings")
    public String getSettingPage(@AuthenticationPrincipal User user, Model model) {
        User currentUser = userService.findUserByUsername(user.getUsername());
        model.addAttribute("user", currentUser);
        return "user_account_setting";
    }

    @PostMapping("/edit")
    public String updateUser(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String email,
                             @RequestParam String phone,
                             @RequestParam String street,
                             @RequestParam String houseNumber,
                             @RequestParam String apartmentNumber,
                             @AuthenticationPrincipal User user) {
        userService.update(user.getId(), firstName, lastName, email, phone, street, houseNumber, apartmentNumber);
        return "redirect:/user/profile";
    }

    @PostMapping("/password/edit")
    public String updateUserPassword(@RequestParam String password,
                                     @RequestParam String verificationPassword,
                                     @AuthenticationPrincipal User user,
                                     Model model) {
        user.setPassword(password);
        if (!userService.checkPasswordSimilarity(user, verificationPassword)) {
            model.addAttribute("verificationPasswordError", "Пароли не совпадают");
            return "user_change_password";
        }

        if (!userService.checkPassword(user)) {
            model.addAttribute("passwordError", "Пароль не прошел валидацию!");
            return "user_change_password";
        } else {
            try {
                user.setPassword(passwordEncoder.encode(password));
                userDao.updatePassword(user);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/user/profile";
    }

    @GetMapping("password/change")
    public String getChangePasswordPage(){
        return "user_change_password";
    }

    @GetMapping("delete")
    public String deactivateUser(@AuthenticationPrincipal User user){
        try {
            userDao.delete(user);
            if (userDao.findUserByUsername(user.getUsername()) == null){
                if (user.isActive() == false){
                    return "redirect:/login";
                }
            }
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @PostMapping("/text")
    public String writeToTheAdmin(@RequestParam String text,
                                  @AuthenticationPrincipal User user) {
        userService.textToAdmin(text, user);
        return "redirect:/user/profile";
    }

    @GetMapping("/bank")
    public String getBankPage(@AuthenticationPrincipal User user, Model model) {
        Bank bank = bankService.getUserBank(user);
        if (bank != null) {
            model.addAttribute("card_number", bank.getNumber());
            model.addAttribute("card_date", bank.getDate());
            model.addAttribute("card_secret_number", bank.getCvv());
            model.addAttribute("card_summ", bank.getMoneyOnCard());
        }
        return "bank";
    }

    @PostMapping("/bank/add")
    public String addBank(@RequestParam String cardNumber,
                          @RequestParam String cardDate,
                          @RequestParam String cvv,
                          @AuthenticationPrincipal User user) {
        bankService.addUserBank(cardNumber, cardDate, cvv, user);
        return "redirect:/user/profile";
    }

    @PostMapping("/bank/cash/add")
    public String addCash(@RequestParam String money,
                          @AuthenticationPrincipal User user) {
        bankService.addUserCash(money, user);
        return "redirect:/user/bank";
    }

    @GetMapping("/bank/delete")
    public String deleteBank(@AuthenticationPrincipal User user) {
        Bank bank = bankRepository.findByUser(user);
        bankRepository.delete(bank);
        return "redirect:/user/profile";
    }
}
