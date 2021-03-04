package kz.ktu.touroperator.controller;

import kz.ktu.touroperator.model.Country;
import kz.ktu.touroperator.model.Tour;
import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.CountryRepository;
import kz.ktu.touroperator.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final TourRepository tourRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public MainController(TourRepository tourRepository, CountryRepository countryRepository) {
        this.tourRepository = tourRepository;
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public String getHomePage(Model model,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, value = 8) Pageable pageable) {
        Page<Tour> page = tourRepository.findAll(pageable);
        Iterable<Country> countries = countryRepository.findAll();
        page = tourRepository.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/");
        model.addAttribute("countries", countries);
        return "home";
    }

    @GetMapping("/contacts")
    public String getContactsPage(Model model,  @AuthenticationPrincipal User user) {
        if(user!=null){
            model.addAttribute("username", user.getUsername());
        }
        return "contacts";
    }

    @GetMapping("/about")
    public String getAboutUsPage() {
        return "about_us";
    }

}
