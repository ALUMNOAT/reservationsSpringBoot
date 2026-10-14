package be.icc.prid.reservations_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login (
            @RequestParam(required = false) final Boolean loginRequired,
            @RequestParam(required = false) final Boolean loginError,
            @RequestParam(required = false) final Boolean logoutSuccess,
            final Model model) {

        if (loginRequired == Boolean.TRUE) {
            model.addAttribute("errorMessage", "Vous devez vous connecter pour avoir acces.");
        }

        if (loginError == Boolean.TRUE) {
            model.addAttribute("errorMessage", "Echec de la connection !");
        }

        if (logoutSuccess == Boolean.TRUE) {
            model.addAttribute("successMessage", "Vous etes deconnecte avec succes.");
        }

        return "authentication/login";
    }
}
