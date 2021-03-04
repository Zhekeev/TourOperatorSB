package kz.ktu.touroperator.controller;

import kz.ktu.touroperator.model.*;
import kz.ktu.touroperator.repository.*;
import kz.ktu.touroperator.service.TextService;
import kz.ktu.touroperator.service.TourService;
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
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public AdminController(TourRepository tourRepository, CountryRepository countryRepository, UserRepository userRepository, ContractRepository contractRepository, TourService tourService, TextRepository textRepository, TextService textService) {
        this.tourRepository = tourRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.contractRepository = contractRepository;
        this.tourService = tourService;
        this.textRepository = textRepository;
        this.textService = textService;
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
            @RequestParam String date,
            @RequestParam String numberOfDays,
            @RequestParam String numberOfPeople,
            @RequestParam String name, Model model,
            @RequestParam("file") MultipartFile file,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, value = 8) Pageable pageable)
            throws IOException {

        Date tourDate = Date.valueOf(date);
        BigDecimal price = BigDecimal.valueOf(Integer.valueOf(priceFromFront));
        tourService.saveTour(text, description, price, tourDate, numberOfDays, numberOfPeople, name, file);
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
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/user/edit")
    public String userUpdate(@RequestParam String username,
                             @RequestParam Map<String,String> form,
                             @RequestParam("userId") User user)
    {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
        return "redirect:/user";
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

    @PostMapping("/user/text/answer/user/{user_id}/text/{text_id}")
    public String answerUserText(@PathVariable(value = "user_id") Long userId,
                                 @PathVariable(value = "text_id") Long textId,
                                 String text) {
        textService.sendText(userId,textId,text);
        return "redirect:/admin/user/text";
    }
}