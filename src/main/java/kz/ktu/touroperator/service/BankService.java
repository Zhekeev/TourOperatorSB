package kz.ktu.touroperator.service;

import kz.ktu.touroperator.model.Bank;
import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.BankRepository;
import kz.ktu.touroperator.repository.UserRepository;
import kz.ktu.touroperator.service.security.encryption.AES;
import kz.ktu.touroperator.service.security.encryption.MD5Hash;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankService {
    private final UserRepository userRepository;
    private final BankRepository bankRepository;
    private final MD5Hash md5Hash;
    private final AES aes;

    public BankService(UserRepository userRepository, BankRepository bankRepository, MD5Hash md5Hash, AES aes) {
        this.userRepository = userRepository;
        this.bankRepository = bankRepository;
        this.md5Hash = md5Hash;
        this.aes = aes;
    }

    @SneakyThrows
    public void addUserBank(String cardNumber, String cardDate, String cardSecretNumber, User user){
        Bank bank = new Bank();
        bank.setNumber(aes.encode(cardNumber));
        bank.setDate(aes.encode(cardDate));
        bank.setCvv(md5Hash.md5Custom(cardSecretNumber));
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
        BigDecimal userCash = new BigDecimal(0);
        if (bank!= null){
            userCash = bank.getMoneyOnCard();
            if(userCash == null){
                userCash = new BigDecimal(0);
                return userCash;
            }
        }
        return userCash;
    }

    @SneakyThrows
    public Bank getUserBank(User user){
        Bank bank = bankRepository.findByUser(user);
        if (bank!=null){
            bank.setNumber(aes.decode(bank.getNumber()));
            bank.setDate(aes.decode(bank.getDate()));
        }
        return bank;
    }
}
