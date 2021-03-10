package kz.ktu.touroperator.repository;

import kz.ktu.touroperator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String user);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET first_name = :first_name, last_name = :last_name, phone = :phone, email = :email, street = :street, " +
            "house_number = :house_number, apartment_number = :apartment_number WHERE id = :id", nativeQuery = true)
    void update(@Param("id") Long id, @Param("first_name") String firstName
            , @Param("last_name") String lastName
            , @Param("phone") String phoneNumber
            , @Param("email") String email
            , @Param("street") String street
            , @Param("house_number") String houseNumber
            , @Param("apartment_number") String apartmentNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = :password WHERE id = :id", nativeQuery = true)
    void updatePassword(@Param("id") Long id, @Param("password") String password);
}
