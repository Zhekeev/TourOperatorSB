package kz.ktu.touroperator.service;

import kz.ktu.touroperator.dao.impl.CountryDaoImpl;
import kz.ktu.touroperator.model.Country;
import kz.ktu.touroperator.model.Role;
import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final CountryDaoImpl countryDao;

    public AdminService(UserRepository userRepository, CountryDaoImpl countryDao) {
        this.userRepository = userRepository;
        this.countryDao = countryDao;
    }

    public void updateUser(String username,
                           Map<String,String> form,
                           User user){
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    @SneakyThrows
    public void createCountry(String name, String description){
        Country country = new Country();
        country.setName(name);
        country.setDescription(description);
        countryDao.create(country);
    }
}
