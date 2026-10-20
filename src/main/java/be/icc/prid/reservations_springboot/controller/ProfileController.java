package be.icc.prid.reservations_springboot.controller;

import be.icc.prid.reservations_springboot.dto.UserProfileDto;
import be.icc.prid.reservations_springboot.model.Language;
import be.icc.prid.reservations_springboot.model.User;
import be.icc.prid.reservations_springboot.repository.UserRepository;
import be.icc.prid.reservations_springboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Locale;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final UserService userService;

    public ProfileController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // Afficher la page de profil
    @GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        String login = auth.getName();

        User user = userRepository.findByLogin(login);

        if (user == null) throw new RuntimeException("Utilisateur introuvable");

        // Conversion User -> UserProfileDto
        UserProfileDto dto = new UserProfileDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstname());
        dto.setLastName(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setLangue(user.getLangue());
        dto.setLogin(user.getLogin());
        dto.setRole(user.getRole().getValue());

        // Conversion du code linguistique en nom de la langue
        Language userLanguage = Arrays.stream(Language.values())
                .filter(lang -> lang.toString().equals(user.getLangue().toUpperCase()))
                .findFirst().get();

        model.addAttribute("user_language", userLanguage != null ?
                userLanguage.getDescription() : "Inconnue");
        model.addAttribute("user", dto);
        return "authentication/profile";
    }

    // Enregistrer les modifications
    @PostMapping(value = "/profile", params = {"edit"})
    public String updateProfile(
            @Valid @ModelAttribute("user") UserProfileDto dto, BindingResult result,
            Model model, RedirectAttributes redirAttrs) {

        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Erreurs de validation !");
            return "authentication/profile";
        }

        // Appel du service pour gerer mot de passe, etc.
        userService.updateUserFromDto(dto);
        redirAttrs.addFlashAttribute("successMessage", "Profil mis a jour avec succes !");
        return "redirect:profile";
    }

    @DeleteMapping("/profile/delete")
    public String deleteAccount(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirAttrs) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();

        if (request.isUserInRole("ADMIN")) {
            redirAttrs.addFlashAttribute("errorMessage",
                    "Pas de suppression de son propre compte admin !");
            return "redirect:/profile";
        }

        // Supprimer l'utilisateur courant
        userService.deleteByLogin(login);

        // Invalider la session HTTP (deconnecte l'utilisateur)
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        // Effacer le contexte de securite
        SecurityContextHolder.clearContext();
        redirAttrs.addFlashAttribute("successMessage", "Votre compte a ete supprime avec succes.");
        return "redirect:/";
    }

}
