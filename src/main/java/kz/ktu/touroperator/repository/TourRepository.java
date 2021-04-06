package kz.ktu.touroperator.repository;

import kz.ktu.touroperator.model.Country;
import kz.ktu.touroperator.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface TourRepository extends CrudRepository<Tour, Long> {
    Page<Tour> findAll(Pageable pageable);

    Page<Tour> findTourByName(String name, Pageable pageable);

    @Query("select t from Tour t  WHERE t.date = :date and t.numberOfDays = :numberOfDays " +
            "and t.numberOfPeople = :numberOfPeople and t.country = :country")
    Page<Tour> findTourByParameters(@Param("date") Date date
            , @Param("numberOfDays") String numberOfDays
            , @Param("numberOfPeople") String numberOfPeople
            , @Param("country") Country country
            , Pageable pageable);

    Tour findTourById(Long id);

    @Override
    void delete(Tour tour);
}
