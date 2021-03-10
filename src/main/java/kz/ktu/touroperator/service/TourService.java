package kz.ktu.touroperator.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import kz.ktu.touroperator.model.*;
import kz.ktu.touroperator.repository.BankRepository;
import kz.ktu.touroperator.repository.ContractRepository;
import kz.ktu.touroperator.repository.CountryRepository;
import kz.ktu.touroperator.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    private final MailSender mailSender;
    private final UserService userService;

    @Autowired
    public TourService(TourRepository tourRepository, CountryRepository countryRepository, BankRepository bankRepository, ContractRepository contractRepository, MailSender mailSender, UserService userService) {
        this.tourRepository = tourRepository;
        this.countryRepository = countryRepository;
        this.bankRepository = bankRepository;
        this.contractRepository = contractRepository;
        this.mailSender = mailSender;
        this.userService = userService;
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

    public void buyTour(Long id, User user) throws FileNotFoundException, DocumentException {
        Tour tour = tourRepository.findTourById(id);
        Contract contract = new Contract();
        contract.setUser(user);
        contract.setTour(tour);
        generateDoc(tour);
        BigDecimal tourPrice = tour.getPrice();
        Bank bank = bankRepository.findByUser(user);
        BigDecimal userCash = bank.getMoneyOnCard();
        if(userCash == null){
            userCash = new BigDecimal(0);
        }
        userCash = userCash.subtract(tourPrice);
        bankRepository.setCash(user.getId(), userCash);
        contractRepository.save(contract);
        userService.sendMessage(user);
    }

    public void generateDoc(Tour tour) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Название тура " + tour.getName(), font);
        document.add(chunk);
        document.close();
    }

    public void sendMessage(User user, Document document) throws FileNotFoundException, DocumentException {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Sweater. Please, visit next link: http://localhost:8080/activate",
                    user.getUsername()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }
}
