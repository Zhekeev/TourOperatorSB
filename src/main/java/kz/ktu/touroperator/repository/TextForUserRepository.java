package kz.ktu.touroperator.repository;

import kz.ktu.touroperator.model.TextForUser;
import kz.ktu.touroperator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextForUserRepository extends JpaRepository<TextForUser, Long> {
    List<TextForUser> findAllByUser(User user);
}
