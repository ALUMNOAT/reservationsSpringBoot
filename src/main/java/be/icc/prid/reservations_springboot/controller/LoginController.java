package be.icc.prid.reservations_springboot.controller;

import be.icc.prid.reservations_springboot.model.User;
import be.icc.prid.reservations_springboot.service.EmailService;
import be.icc.prid.reservations_springboot.service.PasswordResetTokenService;
import be.icc.prid.reservations_springboot.service.UserService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final PasswordResetTokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

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

    // Page "mot de passe oublie"
    @GetMapping("/forgot-password")
    public String forgotPasswordForm() {
        return "authentication/forgot-password";
    }

    // Form submit - on ne revele pas si l'email existe (pour eviter le user enumeration)
    @PostMapping("/forgot-password")
    public String handleForgotPassword(@RequestParam("email") @NotBlank @Email String email, Model model) {
        User user = userService.findByEmail(email);
        IO.println(user); //DEBUG

        if (user != null) {
            var token = tokenService.createTokenForUser(user);
            IO.println(token.getToken());
            emailService.sendPasswordResetMail(user.getEmail(), token.getToken());
        }
        // Toujours montrer le meme message
        model.addAttribute("successMessage",
                "Si un compte existe pour cet e-mail, tu recevras un lien de reinitialisation.");
        return "authentication/forgot-password";
    }

    // Page reset form (avec token en query param)
    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam("token") String token, Model model) {
        var user = tokenService.validatePasswordResetToken(token);
        if (user == null) {
            model.addAttribute("errorMessage", "Lien invalide ou expire.");
            return "authentication/reset-password";
        }
        model.addAttribute("token", token);
        return "authentication/reset-password";
    }

    //Submit nouveau mot de passe
    @PostMapping("/reset-password")
    public String handleResetPassword(@RequestParam String token,
                                      @RequestParam("password") @NotBlank String password,
                                      @RequestParam("confirmPassword") @NotBlank String confirmPassword,
                                      Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("token", token);
            model.addAttribute("errorMessage", "Les mots de passe ne correspondent pas.");
            return "authentication/reset-password";
        }

        var user = tokenService.validatePasswordResetToken(token);
        if (user == null) {
            model.addAttribute("errorMessage", "Lien invalide ou expire.");
            return "authentication/reset-password";
        }

        user.setPassword(passwordEncoder.encode(password));
        userService.updateUser(user.getId(), user);
        tokenService.deleteToken(token);

        return "authentication/reset-password";
    }
}
