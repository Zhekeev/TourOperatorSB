package kz.ktu.touroperator.repository;

import kz.ktu.touroperator.model.Bank;
import kz.ktu.touroperator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByUser(User user);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bank SET money_on_card = :money_on_card WHERE user_id = :id", nativeQuery = true)
    void setCash(@Param("id") Long id, @Param("money_on_card") BigDecimal money);
}
