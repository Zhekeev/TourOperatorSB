package kz.ktu.touroperator.repository;

import kz.ktu.touroperator.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findCountryById(Long id);

    Country findCountryByName(String name);
}
