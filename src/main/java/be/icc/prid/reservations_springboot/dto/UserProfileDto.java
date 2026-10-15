package be.icc.prid.reservations_springboot.dto;

import be.icc.prid.reservations_springboot.validation.PasswordMatches;
import be.icc.prid.reservations_springboot.validation.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@PasswordMatches(message = "Les mots de passe ne correspondent pas")
public class UserProfileDto implements PasswordConfirmable{

    private Long id;

    @NotBlank(message = "Le prenom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit etre valide")
    private String email;

    @NotBlank(message = "La langue est obligatoire")
    private String langue;

    private String login; // visible mais non modifiable
    private String role; // visible mais non modifiable

    @StrongPassword
    private String password; // nouveau mot de passe (optionel)
    private String confirmPassword; // confirmation du mot de passe

}
