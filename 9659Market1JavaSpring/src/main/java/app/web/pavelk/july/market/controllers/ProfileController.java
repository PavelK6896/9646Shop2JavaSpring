package app.web.pavelk.july.market.controllers;

import app.web.pavelk.july.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String profileDto(Model model, Principal principal) {
        model.addAttribute("profile", userService.findByUsernameDto(principal.getName()));
        return "profile";
    }

    @GetMapping("/full")
    public String profile(Model model, Principal principal) {
        model.addAttribute("profileFull", userService.findByUsername(principal.getName()));
        return "profile";
    }

    @Secured("ROLE_ADMIN")//уровень методов защта
    @GetMapping("/admin")
    public String adminProfile(Model model) {
        model.addAttribute("profileAll", userService.findAll());
        return "profile";
    }

}
