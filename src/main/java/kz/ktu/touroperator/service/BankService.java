package kz.ktu.touroperator.service;

import kz.ktu.touroperator.model.Bank;
import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.BankRepository;
import kz.ktu.touroperator.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Service
public class BankService {
    private final UserRepository userRepository;
    private final BankRepository bankRepository;

    public BankService(UserRepository userRepository, BankRepository bankRepository) {
        this.userRepository = userRepository;
        this.bankRepository = bankRepository;
    }

    public void addUserBank(String cardNumber, String cardDate, String cardSecretNumber, User user){
        Bank bank = new Bank();
        bank.setCardNumber(cardNumber);
        bank.setCardDate(cardDate);
        bank.setCardSecretNumber(Long.valueOf(cardSecretNumber));
        bank.setUser(user);
        bankRepository.save(bank);
    }

    public void addUserCash(String money, User user){
        BigDecimal cash = BigDecimal.valueOf(Integer.valueOf(money));
        Bank bank = bankRepository.findByUser(user);
        BigDecimal userCash = bank.getMoneyOnCard();
        userCash = userCash.add(cash);
        bankRepository.setCash(user.getId(), userCash);
    }

    public BigDecimal getUserCash(User user){
        Bank bank = bankRepository.findByUser(user);
        BigDecimal userCash = bank.getMoneyOnCard();
        return userCash;
    }

    public Bank getUserBank(User user){
        return bankRepository.findByUser(user);
    }
}
