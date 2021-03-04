package kz.ktu.touroperator.repository;

import kz.ktu.touroperator.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface TourRepository extends CrudRepository<Tour, Long> {
    Page<Tour> findAll(Pageable pageable);

    Page<Tour> findTourByName(String name, Pageable pageable);

    Tour findTourById(Long id);

    @Override
    void delete(Tour tour);

    Tour findTourByNameAndAndDate(String name, Date date);
}
