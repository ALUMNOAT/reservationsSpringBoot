package be.icc.prid.reservations_springboot.controller;

import be.icc.prid.reservations_springboot.dto.UserRegistrationDto;
import be.icc.prid.reservations_springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "authentication/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDto dto,
                               BindingResult result, Model model, RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Erreurs de validation !");
            return "authentication/register";
        }

        // Verification de doublons
        if (!userService.isLoginAndEmailAvailable(dto.getLogin(), dto.getEmail())) {
            model.addAttribute("errorMessage", "Email ou login deja utilise !");
            return "authentication/register";
        }

        userService.registerFromDto(dto);
        redirAttrs.addFlashAttribute("successMessage", "Inscription reussie !");
        return "redirect:login";
    }
}
