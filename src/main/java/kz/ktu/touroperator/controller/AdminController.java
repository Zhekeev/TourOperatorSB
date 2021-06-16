package kz.ktu.touroperator.controller;

import kz.ktu.touroperator.model.Country;
import kz.ktu.touroperator.model.Role;
import kz.ktu.touroperator.model.Tour;
import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.*;
import kz.ktu.touroperator.service.AdminService;
import kz.ktu.touroperator.service.TextService;
import kz.ktu.touroperator.service.TourService;
import kz.ktu.touroperator.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final TourRepository tourRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final ContractRepository contractRepository;
    private final TourService tourService;
    private final TextRepository textRepository;
    private final TextService textService;
    private final AdminService adminService;
    private final UserService userService;

    public AdminController(TourRepository tourRepository, CountryRepository countryRepository, UserRepository userRepository, ContractRepository contractRepository, TourService tourService, TextRepository textRepository, TextService textService, AdminService adminService, UserService userService) {
        this.tourRepository = tourRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.contractRepository = contractRepository;
        this.tourService = tourService;
        this.textRepository = textRepository;
        this.textService = textService;
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping()
    public String getMainPage(@RequestParam(required = false, defaultValue = "") String name, Model model,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, value = 8) Pageable pageable) {
        Page<Tour> page;
        Iterable<Country> countries = countryRepository.findAll();
        if (name != null && !name.isEmpty()) {
            page = tourRepository.findTourByName(name, pageable);
        } else {
            page = tourRepository.findAll(pageable);
        }
        model.addAttribute("countries", countries);
        model.addAttribute("page", page);
        model.addAttribute("url", "/admin");
        model.addAttribute("filter", name);
        return "main";
    }

    @PostMapping()
    public String addTour(
            @RequestParam String text,
            @RequestParam String description,
            @RequestParam String priceFromFront,
            @RequestParam Date date,
            @RequestParam String numberOfDays,
            @RequestParam String numberOfPeople,
            @RequestParam String name, Model model,
            @RequestParam("file") MultipartFile file,
            @RequestParam("firstPhoto") MultipartFile firstPhoto,
            @RequestParam("secondPhoto") MultipartFile secondPhoto,
            @RequestParam("thirdPhoto") MultipartFile thirdPhoto,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, value = 8) Pageable pageable)
            throws IOException {
        /*Date tourDate = Date.valueOf(date);*/
        BigDecimal price = BigDecimal.valueOf(Integer.valueOf(priceFromFront));
        tourService.saveTour(text, description, price, date, numberOfDays, numberOfPeople, name, file,firstPhoto,secondPhoto,thirdPhoto);
        Iterable<Tour> tours = tourRepository.findAll(pageable);
        Iterable<Country> countries = countryRepository.findAll(pageable);
        model.addAttribute("page", tours);
        model.addAttribute("url", "/admin");
        model.addAttribute("countries", countries);
        return "redirect:/admin";
    }


    @GetMapping("/user/all")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user-list";
    }

    @GetMapping("/user/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        User userFromDb = userService.findUserByUsername(user.getUsername());
        model.addAttribute("user", userFromDb);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/user/edit")
    public String userUpdate(@RequestParam String username,
                             @RequestParam Map<String, String> form,
                             @RequestParam("userId") User user) {
        adminService.updateUser(username, form, user);
        return "redirect:/admin/user/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteTour(@PathVariable(value = "id") Long id) {
        Tour tour = tourRepository.findTourById(id);
        tourRepository.delete(tour);
        return "redirect:/admin";
    }

    @GetMapping("/user/text")
    public String userText(Model model) {
        model.addAttribute("users", textRepository.findAll());
        return "user_texts";
    }

    @GetMapping("/tour/edit/{id}")
    public String getEditTourPage(@PathVariable(value = "id") Long id, Model model){
        Tour tour = tourRepository.findTourById(id);
        model.addAttribute("tour", tour);
        return "tour_edit";
    }

    @GetMapping("/tour/delete/{id}")
    public String getDelete(@PathVariable(value = "id") Long id, Model model){
        tourService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/country/add")
    public String addCountry( @RequestParam String countryName,
                              @RequestParam String countryDescription){
        adminService.createCountry(countryName, countryDescription);
        return "redirect:/admin";
    }
}
