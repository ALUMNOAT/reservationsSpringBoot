package be.icc.prid.reservations_springboot.validation;

import be.icc.prid.reservations_springboot.dto.PasswordConfirmable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, PasswordConfirmable> {  // <- changé

    @Override
    public boolean isValid(PasswordConfirmable dto, ConstraintValidatorContext context) {  // <- changé
        String pwd = dto.getPassword();
        String confirm = dto.getConfirmPassword();

        // Si les deux champs sont vides, on considere que le mot de passe
        // n'est pas modifie => OK
        if ((pwd == null || pwd.isBlank()) && (confirm == null || confirm.isBlank())) {
            return true;
        }

        // Sinon, il faut qu'ils soient identiques
        if (pwd == null) return false;
        return pwd.equals(confirm);
    }
}