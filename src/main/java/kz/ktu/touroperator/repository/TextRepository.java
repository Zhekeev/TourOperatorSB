package kz.ktu.touroperator.repository;

import kz.ktu.touroperator.model.Text;
import kz.ktu.touroperator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Long> {
    List<Text> findAllByUser(User user);
}
