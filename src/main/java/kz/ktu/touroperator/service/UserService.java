package kz.ktu.touroperator.service;

import com.itextpdf.text.*;
import kz.ktu.touroperator.model.*;
import kz.ktu.touroperator.model.dto.EmailRequestDto;
import kz.ktu.touroperator.repository.TextRepository;
import kz.ktu.touroperator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Value("${spring.mail.username}")
    private String username;

    private final UserRepository userRepository;
    private final TextRepository textRepository;
    private final MailSender mailSender;
    private final MailServiceForFiles mailServiceForFiles;

    @Autowired
    public UserService(UserRepository userRepository, TextRepository textRepository, MailSender mailSender, MailServiceForFiles mailServiceForFiles) {
        this.userRepository = userRepository;
        this.textRepository = textRepository;
        this.mailSender = mailSender;
        this.mailServiceForFiles = mailServiceForFiles;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user){
        User userFromDataBase = userRepository.findByUsername(user.getUsername());
        if(userFromDataBase != null){
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return true;
    }

    public void textToAdmin(String text, User user){
        Text textToAdmin = new Text();
        textToAdmin.setText(text);
        textToAdmin.setUser(user);
        textRepository.save(textToAdmin);
    }

    public void update(String firstName,
                       String lastName,
                       String email,
                       String phoneNumber,
                       String street,
                       String houseNumber,
                       String apartmentNumber,
                       User user){
        User userToDB = new User();
        userToDB.setId(user.getId());
        userToDB.setFirstName(firstName);
        userToDB.setLastName(lastName);
        userToDB.setEmail(email);
        userToDB.setPhone(phoneNumber);
        userToDB.setStreet(street);
        userToDB.setHouseNumber(houseNumber);
        userToDB.setApartmentNumber(apartmentNumber);
        userRepository.save(user);
    }

    public void sendMessage(User user) throws FileNotFoundException, DocumentException {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Sweater. Please, visit next link: http://localhost:8080/activate",
                    user.getUsername()
            );
            System.out.println(user.getEmail());
            mailSender.send(user.getEmail(), "Activation code", message);
            EmailRequestDto emailRequestDto = new EmailRequestDto();
            emailRequestDto.setFrom(username);
            emailRequestDto.setTo(user.getEmail());
            emailRequestDto.setName(message);
            emailRequestDto.setSubject("Activation code");
            mailServiceForFiles.sendMail(emailRequestDto);
        }
    }

    public void send(){

    }

}
