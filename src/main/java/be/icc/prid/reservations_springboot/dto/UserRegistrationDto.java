package be.icc.prid.reservations_springboot.dto;

import be.icc.prid.reservations_springboot.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatches
public class UserRegistrationDto {
    @NotBlank(message = "Le prenom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;

    @NotBlank(message = "Le login est obligatoire")
    @Size(min = 4, max = 20, message = "Le login doit contenir entre 4 et 20 caracteres")
    private String login;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caracteres")
    private String password;

    @NotBlank(message = "Veuillez confirmer votre mot de passe")
    private String confirmPassword;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "l'email doit etre valide")
    private String email;

    @NotBlank(message = "La langue est obligatoire")
    private String langue;

}
