package kz.ktu.touroperator.controller;

import com.itextpdf.text.DocumentException;
import kz.ktu.touroperator.model.*;
import kz.ktu.touroperator.repository.*;
import kz.ktu.touroperator.service.BankService;
import kz.ktu.touroperator.service.ContractService;
import kz.ktu.touroperator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final ContractRepository contractRepository;
    private final ContractService contractService;
    private final TourRepository tourRepository;
    private final TextRepository textRepository;
    private final BankRepository bankRepository;
    private final BankService bankService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, ContractRepository contractRepository, ContractService contractService, TourRepository tourRepository, TextRepository textRepository, BankRepository bankRepository, BankService bankService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.contractRepository = contractRepository;
        this.contractService = contractService;
        this.tourRepository = tourRepository;
        this.textRepository = textRepository;
        this.bankRepository = bankRepository;
        this.bankService = bankService;
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model){
        User user1 = userRepository.findByUsername(user.getUsername());
        List<Contract> contracts = contractService.getUserAllContract(user);
        model.addAttribute("contracts",contracts);
        model.addAttribute("user", user1);
        return "profile";
    }

    @GetMapping("/settings")
    public String getSettingPage(@AuthenticationPrincipal User user, Model model){
        User currentUser = userRepository.findByUsername(user.getUsername());
        model.addAttribute("user", currentUser);
        return "user_account_setting";
    }

    @PostMapping("/edit")
    public String updateUser(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String email,
                             @RequestParam String phoneNumber,
                             @RequestParam String street,
                             @RequestParam String houseNumber,
                             @RequestParam String apartmentNumber,
                                  @AuthenticationPrincipal User user){
        userRepository.update(user.getId(),firstName, lastName, email, phoneNumber, street, houseNumber, apartmentNumber);
        return "redirect:/user/profile";
    }

    @PostMapping("/password/edit")
    public String updateUserPassword(@RequestParam String password,
                                     @RequestParam String verificationPassword,
                             @AuthenticationPrincipal User user) {
        userRepository.updatePassword(user.getId(), password);
        return "redirect:/user/profile";
    }

    @PostMapping("/text")
    public String writeToTheAdmin(@RequestParam String text,
                                  @AuthenticationPrincipal User user) {
       userService.textToAdmin(text, user);
       return "redirect:/user/profile";
    }

    @GetMapping("/bank")
    public String getBankPage(@AuthenticationPrincipal User user, Model model){
        Bank bank = bankService.getUserBank(user);
        if(bank!=null){
            model.addAttribute("card_number", bank.getCardNumber());
            model.addAttribute("card_date", bank.getCardDate());
            model.addAttribute("card_secret_number",bank.getCardSecretNumber());
            model.addAttribute("card_summ", bank.getMoneyOnCard());
        }
        return "bank";
    }

    @PostMapping("/bank/add")
    public String addBank(@RequestParam String cardNumber,
                          @RequestParam String cardDate,
                          @RequestParam String cardSecretNumber,
                                  @AuthenticationPrincipal User user) {
        bankService.addUserBank(cardNumber,cardDate,cardSecretNumber,user);
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
