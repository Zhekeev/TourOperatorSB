package kz.ktu.touroperator.repository;

import kz.ktu.touroperator.model.Contract;
import kz.ktu.touroperator.model.Tour;
import kz.ktu.touroperator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByUser(User user);

}
