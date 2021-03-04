package kz.ktu.touroperator.controller;

import kz.ktu.touroperator.model.Tour;
import kz.ktu.touroperator.model.User;
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

import java.math.BigDecimal;

@Controller
@RequestMapping("/tour")
public class TourController {
    private final TourRepository tourRepository;
    private final TourService tourService;
    private final BankService bankService;

    @Autowired
    public TourController(TourRepository tourRepository, TourService tourService, BankService bankService) {
        this.tourRepository = tourRepository;
        this.tourService = tourService;
        this.bankService = bankService;
    }

    @GetMapping("/all")
    public String getAllTour(Model model,
                             @RequestParam(required = false, defaultValue = "") String name,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, value = 8) Pageable pageable) {
        Page<Tour> page;
        if (name != null && !name.isEmpty()) {
            page = tourRepository.findTourByName(name, pageable);
        } else {
            page = tourRepository.findAll(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("url", "/tour/all");
        model.addAttribute("filter", name);
        return "tours";
    }


    @GetMapping("/about/{id}")
    public String aboutTour(@PathVariable(value = "id") Long id,
                            @AuthenticationPrincipal User user,
                            Model model) {
        Tour tour = tourRepository.findTourById(id);
        BigDecimal userCash = bankService.getUserCash(user);
        BigDecimal tourPrice = tour.getPrice();
        if (tourPrice.compareTo(userCash) >= 0) {
            model.addAttribute("cash_error", userCash);
        }
        model.addAttribute("tour", tour);
        return "about_tour";
    }

    @GetMapping("/buy/{id}")
    public String buyTour(@PathVariable(value = "id") Long id,
                          @AuthenticationPrincipal User user) {
        tourService.buyTour(id, user);
        return "redirect:/user/profile";
    }
}
