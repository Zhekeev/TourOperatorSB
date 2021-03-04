package kz.ktu.touroperator.repository;

import kz.ktu.touroperator.model.Contract;
import kz.ktu.touroperator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String user);

}
