package kz.ktu.touroperator.controller;

import com.itextpdf.text.DocumentException;
import kz.ktu.touroperator.model.Country;
import kz.ktu.touroperator.model.Tour;
import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.CountryRepository;
import kz.ktu.touroperator.repository.TourRepository;
import kz.ktu.touroperator.service.BankService;
import kz.ktu.touroperator.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Date;

@Controller
@RequestMapping("/tour")
public class TourController {
    private final TourRepository tourRepository;
    private final TourService tourService;
    private final BankService bankService;
    private final CountryRepository countryRepository;

    @Autowired
    public TourController(TourRepository tourRepository, TourService tourService, BankService bankService, CountryRepository countryRepository) {
        this.tourRepository = tourRepository;
        this.tourService = tourService;
        this.bankService = bankService;
        this.countryRepository = countryRepository;
    }

    @GetMapping("/all")
    public String getAllTour(Model model,
                             @RequestParam(required = false, defaultValue = "") String date,
                             @RequestParam(required = false, defaultValue = "") String countryName,
                             @RequestParam(required = false, defaultValue = "") String numberOfPeople,
                             @RequestParam(required = false, defaultValue = "") String numberOfDays,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, value = 8) Pageable pageable) {
        Iterable<Country> countries = countryRepository.findAll();
        Country country = countryRepository.findCountryByName(countryName);
        Page<Tour> page;
        if (date != null && !date.isEmpty()
                && countryName != null && !countryName.isEmpty()
                && numberOfPeople != null && numberOfPeople.isEmpty()
                && numberOfDays != null && numberOfDays.isEmpty()) {
            page = tourRepository.findTourByParameters(Date.valueOf(date), numberOfDays, numberOfPeople, country, pageable);
        } else {
            page = tourRepository.findAll(pageable);
        }
        model.addAttribute("countries", countries);
        model.addAttribute("page", page);
        model.addAttribute("url", "/tour/all");
        model.addAttribute("filter", date);
        return "tours";
    }


    @GetMapping("/about/{id}")
    public String aboutTour(@PathVariable(value = "id") Long id,
                            Model model) {
        Tour tour = tourRepository.findTourById(id);
        model.addAttribute("tour", tour);
        return "about_tour";
    }

    @GetMapping("/cheque/{id}")
    public String getChequePage(@PathVariable(value = "id") Long id,
                                @AuthenticationPrincipal User user,
                                Model model) {
        Tour tour = tourRepository.findTourById(id);
        BigDecimal userCash = bankService.getUserCash(user);
        BigDecimal tourPrice = tour.getPrice();
        if (userCash == null) {
            model.addAttribute("user_error", userCash);
        }
        if (tourPrice.compareTo(userCash) >= 0) {
            model.addAttribute("cash_error", userCash);
        }
        model.addAttribute("tour", tour);
        return "cheque";
    }

    @GetMapping("/buy/{id}")
    public String buyTour(@PathVariable(value = "id") Long id,
                          @AuthenticationPrincipal User user) throws FileNotFoundException, DocumentException {
        tourService.buyTour(id, user);
        return "redirect:/user/profile";
    }

    @GetMapping("/contract/activate/{code}")
    public String activate(@PathVariable String code) {
        tourService.confirmPurchase(code);
        return "redirect:/user/profile";
    }
}
