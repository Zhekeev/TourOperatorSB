package kz.ktu.touroperator.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import kz.ktu.touroperator.connection.ConnectionPoolException;
import kz.ktu.touroperator.dao.impl.TourDaoImpl;
import kz.ktu.touroperator.model.*;
import kz.ktu.touroperator.repository.BankRepository;
import kz.ktu.touroperator.repository.ContractRepository;
import kz.ktu.touroperator.repository.CountryRepository;
import kz.ktu.touroperator.repository.TourRepository;
import kz.ktu.touroperator.service.security.encryption.AES;
import lombok.SneakyThrows;
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
    private final TourDaoImpl tourDao;
    private final AES aes;

    @Autowired
    public TourService(TourRepository tourRepository, CountryRepository countryRepository, BankRepository bankRepository, ContractRepository contractRepository, MailSender mailSender, UserService userService, TourDaoImpl tourDao, AES aes) {
        this.tourRepository = tourRepository;
        this.countryRepository = countryRepository;
        this.bankRepository = bankRepository;
        this.contractRepository = contractRepository;
        this.mailSender = mailSender;
        this.userService = userService;
        this.tourDao = tourDao;
        this.aes = aes;
    }

    public Tour saveTour(String name, String description,
                         BigDecimal price, Date date,
                         String numberOfDays, String numberOfPeople,
                         String countryName, MultipartFile image, MultipartFile firstPhoto, MultipartFile secondPhoto, MultipartFile thirdsPhoto) throws IOException {
        Tour tour = new Tour();
        tour.setName(name);
        tour.setDescription(description);
        tour.setPrice(price);
        tour.setDate(date);
        tour.setNumberOfDays(numberOfDays);
        tour.setNumberOfPeople(numberOfPeople);
        Country country = setCountry(countryName);
        Long countryId = country.getId();
        tour.setImage(setImage(image));
        tour.setFirstPhoto(setImage(firstPhoto));
        tour.setSecondPhoto(setImage(secondPhoto));
        tour.setThirdsPhoto(setImage(thirdsPhoto));
        tour.setActive(true);
        try {
            tourDao.createTour(tour, countryId);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return tour;
    }

    public Country setCountry(String name) {
        Country country = countryRepository.findCountryByName(name);
        return country;
    }

    public String setImage(MultipartFile image) throws IOException {
        if (image != null && !image.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(path);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String forUniqueFile = UUID.randomUUID().toString();
            String fileName = forUniqueFile + "." + image.getOriginalFilename();
            image.transferTo(new File(path + "/" + fileName));
            return fileName;
        }
        return "";
    }

    @SneakyThrows
    public void buyTour(Long id, User user) throws FileNotFoundException, DocumentException {
        Tour tour = tourRepository.findTourById(id);
        Contract contract = new Contract();
        contract.setUser(user);
        contract.setTour(tour);
        contract.setConfirmPurchase(UUID.randomUUID().toString());
        contract.setActive(true);
        generateDoc(tour);
        BigDecimal tourPrice = tour.getPrice();
        Bank bank = bankRepository.findByUser(user);
        if (bank != null){
            BigDecimal userCash = bank.getMoneyOnCard();
            if (userCash == null) {
                userCash = new BigDecimal(0);
            }
            userCash = userCash.subtract(tourPrice);
            bankRepository.setCash(user.getId(), userCash);
            contractRepository.save(contract);
            sendMessage(user, contract);
        }
    }

    public boolean confirmPurchase(String code) {
        Contract contract = contractRepository.findByConfirmPurchase(code);
        if (contract == null) {
            return false;
        }
        contract.setConfirmPurchase(null);
        contractRepository.save(contract);
        return true;
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

    @SneakyThrows
    public void sendMessage(User user, Contract contract) throws FileNotFoundException, DocumentException {
        if (!StringUtils.isEmpty(aes.decode(user.getEmail()))) {
            String message = String.format(
                    "Здравствуйте, %s %s! \n" +
                            "Вы собираетесь купить тур. Подтвердите покупку: http://localhost:8080/tour/contract/activate/%s. Если это не вы покупаете то не переходите по ссылке",
                    aes.decode(user.getFirstName()),
                    aes.decode(user.getLastName()),
                    contract.getConfirmPurchase()
            );
            mailSender.send(aes.decode(user.getEmail()), "Подтверждение тура", message);
        }
    }

    public void delete(Long id) {
        try {
            tourDao.delete(tourRepository.findTourById(id));
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
