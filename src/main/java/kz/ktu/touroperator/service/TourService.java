package kz.ktu.touroperator.service;

import kz.ktu.touroperator.model.*;
import kz.ktu.touroperator.repository.BankRepository;
import kz.ktu.touroperator.repository.ContractRepository;
import kz.ktu.touroperator.repository.CountryRepository;
import kz.ktu.touroperator.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Service
public class TourService {
    @Value("${upload.path}")
    private String path;

    private final TourRepository tourRepository;
    private final CountryRepository countryRepository;
    private final BankRepository bankRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public TourService(TourRepository tourRepository, CountryRepository countryRepository, BankRepository bankRepository, ContractRepository contractRepository) {
        this.tourRepository = tourRepository;
        this.countryRepository = countryRepository;
        this.bankRepository = bankRepository;
        this.contractRepository = contractRepository;
    }

    public Tour saveTour(String name, String description,
                         BigDecimal price, Date date,
                         String numberOfDays, String numberOfPeople,
                         String countryName, MultipartFile image) throws IOException {
        Tour tour = new Tour();
        tour.setName(name);
        tour.setDescription(description);
        tour.setPrice(price);
        tour.setDate(date);
        tour.setNumberOfDays(numberOfDays);
        tour.setNumberOfPeople(numberOfPeople);
        tour.setCountry(setCountry(countryName));
        tour.setImage(setImage(image));
        tourRepository.save(tour);
        return tour;
    }

    public Country setCountry(String name){
        Country country = countryRepository.findCountryByName(name);
        return country;
    }

    public String setImage(MultipartFile image) throws IOException {
        if(image!=null && !image.getOriginalFilename().isEmpty()){
            File uploadDir = new File(path);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String forUniqueFile = UUID.randomUUID().toString();
            String fileName = forUniqueFile + "." + image.getOriginalFilename();
            image.transferTo(new File(path + "/" + fileName));
            return fileName;
        }
        return "";
    }

    public void buyTour(Long id, User user){
        Tour tour = tourRepository.findTourById(id);
        Contract contract = new Contract();
        contract.setUser(user);
        contract.setTour(tour);
        BigDecimal tourPrice = tour.getPrice();
        Bank bank = bankRepository.findByUser(user);
        BigDecimal userCash = bank.getMoneyOnCard();
        userCash = userCash.subtract(tourPrice);
        bankRepository.setCash(user.getId(), userCash);
        contractRepository.save(contract);
    }
}
