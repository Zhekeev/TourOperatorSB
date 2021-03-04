package kz.ktu.touroperator.service;

import kz.ktu.touroperator.model.*;
import kz.ktu.touroperator.repository.BankRepository;
import kz.ktu.touroperator.repository.TextRepository;
import kz.ktu.touroperator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final TextRepository textRepository;

    @Autowired
    public UserService(UserRepository userRepository, TextRepository textRepository) {
        this.userRepository = userRepository;
        this.textRepository = textRepository;
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
}
