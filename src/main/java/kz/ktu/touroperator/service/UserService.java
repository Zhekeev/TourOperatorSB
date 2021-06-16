package kz.ktu.touroperator.service;

import kz.ktu.touroperator.connection.ConnectionPoolException;
import kz.ktu.touroperator.dao.impl.UserDaoImpl;
import kz.ktu.touroperator.model.Role;
import kz.ktu.touroperator.model.Text;
import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.TextRepository;
import kz.ktu.touroperator.repository.UserRepository;
import kz.ktu.touroperator.service.security.encryption.AES;
import kz.ktu.touroperator.service.security.validator.PasswordValidator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Value("${spring.mail.username}")
    private String username;

    private final UserRepository userRepository;
    private final TextRepository textRepository;
    private final MailSender mailSender;
    private final MailServiceForFiles mailServiceForFiles;
    private final PasswordEncoder passwordEncoder;
    private final UserDaoImpl userDao;
    private final AES aes;

    @Autowired
    public UserService(UserRepository userRepository, TextRepository textRepository, MailSender mailSender, MailServiceForFiles mailServiceForFiles, PasswordEncoder passwordEncoder, UserDaoImpl userDao, AES aes) {
        this.userRepository = userRepository;
        this.textRepository = textRepository;
        this.mailSender = mailSender;
        this.mailServiceForFiles = mailServiceForFiles;
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.aes = aes;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @SneakyThrows
    public boolean addUser(User user) {
        user.setFirstName(aes.encode(user.getFirstName()));
        user.setLastName(aes.encode(user.getLastName()));
        user.setPhone(aes.encode(user.getPhone()));
        user.setEmail(aes.encode(user.getEmail()));
        user.setStreet(aes.encode(user.getStreet()));
        user.setHouseNumber(aes.encode(user.getHouseNumber()));
        user.setApartmentNumber(aes.encode(user.getApartmentNumber()));
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());
        try {
            userDao.create(user);
            sendMessage(user);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkUser(User user) {
        User userFromDataBase = userRepository.findByUsername(user.getUsername());
        if (userFromDataBase != null) {
            return false;
        }
        return true;
    }

    public boolean checkPassword(User user) {
        return PasswordValidator.isValidPassword(user.getPassword());
    }

    @SneakyThrows
    public void update(Long id, String firstName, String lastName, String email, String phone, String street, String houseNumber, String apartmentNumber) {
        User user = new User();
        user.setId(id);
        user.setFirstName(aes.encode(firstName));
        user.setLastName(aes.encode(lastName));
        user.setEmail(aes.encode(email));
        user.setPhone(aes.encode(phone));
        user.setStreet(aes.encode(street));
        user.setHouseNumber(aes.encode(houseNumber));
        user.setApartmentNumber(aes.encode(apartmentNumber));
        try {
            userDao.update(user);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public boolean checkPasswordSimilarity(User user, String secondPasswordForValidation) {
        return user.getPassword().equals(secondPasswordForValidation);
    }

    public void textToAdmin(String text, User user) {
        Text textToAdmin = new Text();
        textToAdmin.setText(text);
        textToAdmin.setUser(user);
        textRepository.save(textToAdmin);
    }

    @SneakyThrows
    public User findUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        user.setUsername(username);
        user.setFirstName(aes.decode(user.getFirstName()));
        user.setLastName(aes.decode(user.getLastName()));
        user.setEmail(aes.decode(user.getEmail()));
        user.setPhone(aes.decode(user.getPhone()));
        user.setStreet(aes.decode(user.getStreet()));
        user.setHouseNumber(aes.decode(user.getHouseNumber()));
        user.setApartmentNumber(aes.decode(user.getApartmentNumber()));
        return user;
    }

    @SneakyThrows
    public void sendMessage(User user) {
        if (!StringUtils.isEmpty(aes.decode(user.getEmail()))) {
            String message = String.format(
                    "Здравствуйте, %s %s! \n" +
                            "Мы рады приветствовать вас на сайте Тур оператора. " +
                            "Чтобы активировать аккаунт, перейдите по следующей ссылке: http://localhost:8080/activate/%s",
                    aes.decode(user.getFirstName()),
                    aes.decode(user.getLastName()),
                    user.getActivationCode()
            );
            System.out.println(user.getEmail());
            mailSender.send(aes.decode(user.getEmail()), "Активационный код", message);
        }
    }

    @SneakyThrows
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActive(true);
        user.setActivationCode(null);
        userDao.activateUser(user);
        return true;
    }
}
